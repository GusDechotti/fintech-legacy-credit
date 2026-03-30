package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Regras de crédito para Pessoa Física brasileira (documento: CPF).
 */
public final class AnaliseStrategyBR implements AnaliseStrategy {

    private static final Logger log = LoggerFactory.getLogger(AnaliseStrategyBR.class);

    private static final int  SCORE_MINIMO      = 500;
    private static final int  SCORE_ALTO_VALOR  = 800;
    private static final double LIMITE_ALTO_VALOR = 5_000.0;

    @Override
    public boolean analisar(SolicitacaoCreditoRequest solicitacao) {
        if (solicitacao.negativado()) {
            log.warn("[BR] Reprovado: cliente negativado");
            return false;
        }
        if (solicitacao.score() <= SCORE_MINIMO) {
            log.warn("[BR] Reprovado: score insuficiente ({})", solicitacao.score());
            return false;
        }
        if (solicitacao.valor() > LIMITE_ALTO_VALOR && solicitacao.score() < SCORE_ALTO_VALOR) {
            log.warn("[BR] Reprovado: valor alto com score médio");
            return false;
        }
        log.info("[BR] Aprovado: {}", solicitacao.cliente());
        return true;
    }

    @Override
    public String pais() { return "BR"; }
}