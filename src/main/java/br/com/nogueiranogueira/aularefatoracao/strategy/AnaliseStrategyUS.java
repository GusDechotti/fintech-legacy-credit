package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Regras de crédito para o mercado americano (documento: SSN).
 */
public final class AnaliseStrategyUS implements AnaliseStrategy {

    private static final Logger log = LoggerFactory.getLogger(AnaliseStrategyUS.class);

    private static final int    SCORE_MINIMO      = 600; // FICO score mínimo
    private static final double LIMITE_ALTO_VALOR = 50_000.0; // USD

    @Override
    public boolean analisar(SolicitacaoCreditoRequest solicitacao) {
        if (solicitacao.negativado()) {
            log.warn("[US] Denied: derogatory credit history");
            return false;
        }
        if (solicitacao.score() <= SCORE_MINIMO) {
            log.warn("[US] Denied: FICO score too low ({})", solicitacao.score());
            return false;
        }
        if (solicitacao.valor() > LIMITE_ALTO_VALOR && solicitacao.score() < 750) {
            log.warn("[US] Denied: high-value loan requires score >= 750");
            return false;
        }
        log.info("[US] Approved: {}", solicitacao.cliente());
        return true;
    }

    @Override
    public String pais() { return "US"; }
}