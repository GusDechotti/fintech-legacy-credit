package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Regras de crédito para o mercado mexicano (documento: CURP).
 */
public final class AnaliseStrategyMX implements AnaliseStrategy {

    private static final Logger log = LoggerFactory.getLogger(AnaliseStrategyMX.class);

    private static final int    SCORE_MINIMO      = 450;
    private static final double LIMITE_ALTO_VALOR = 100_000.0; // pesos mexicanos

    @Override
    public boolean analisar(SolicitacaoCreditoRequest solicitacao) {
        if (solicitacao.negativado()) {
            log.warn("[MX] Reprovado: cliente con historial negativo");
            return false;
        }
        if (solicitacao.score() <= SCORE_MINIMO) {
            log.warn("[MX] Reprovado: score insuficiente ({})", solicitacao.score());
            return false;
        }
        if (solicitacao.valor() > LIMITE_ALTO_VALOR) {
            log.warn("[MX] Reprovado: valor acima do limite permitido");
            return false;
        }
        log.info("[MX] Aprobado: {}", solicitacao.cliente());
        return true;
    }

    @Override
    public String pais() { return "MX"; }
}