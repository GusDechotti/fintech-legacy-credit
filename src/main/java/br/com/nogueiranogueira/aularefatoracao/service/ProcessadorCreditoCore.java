package br.com.nogueiranogueira.aularefatoracao.service;

import br.com.nogueiranogueira.aularefatoracao.domain.Documento;
import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import br.com.nogueiranogueira.aularefatoracao.factory.AnaliseCreditoFactory;
import br.com.nogueiranogueira.aularefatoracao.strategy.AnaliseStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ✅ Sem nenhum if (tipo == "BR") ou if (tipo == "MX").
 * A lógica de despacho é 100% delegada para Factory + sealed types.
 */
@Service
public class ProcessadorCreditoCore {

    private static final Logger log = LoggerFactory.getLogger(ProcessadorCreditoCore.class);

    public ResultadoAnalise processar(SolicitacaoCreditoRequest solicitacao, Documento documento) {

        // 1. Valida o documento — o próprio record sabe como se validar
        if (!documento.validar()) {
            log.warn("Documento inválido para o cliente {}: {}", solicitacao.cliente(), documento.numero());
            return ResultadoAnalise.reprovado("Documento inválido: " + documento.numero());
        }

        // 2. Obtém a estratégia correta via Factory — ZERO ifs aqui
        AnaliseStrategy strategy = AnaliseCreditoFactory.obterEstrategia(documento);

        log.info("Aplicando regras do país [{}] para: {}", strategy.pais(), solicitacao.cliente());

        // 3. Executa a análise
        boolean aprovado = strategy.analisar(solicitacao);

        // 4. Retorna resultado tipado
        return aprovado
                ? ResultadoAnalise.aprovado(strategy.pais())
                : ResultadoAnalise.reprovado("Reprovado pelas regras de crédito: " + strategy.pais());
    }
}