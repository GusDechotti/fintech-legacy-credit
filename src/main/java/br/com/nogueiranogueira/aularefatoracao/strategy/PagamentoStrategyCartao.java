package br.com.nogueiranogueira.aularefatoracao.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagamentoStrategyCartao implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PagamentoStrategyCartao.class);

    private static final double ACRESCIMOPERC = 1.05;

    @Override
    public void pagar(double valor) {
        double valorComAcrescimo = valor * ACRESCIMOPERC;
        log.info("Conectando com a adquirente (Cielo/Rede)...");
        log.info("Validando limite e risco de fraude.");
        log.info("Pagamento via Cartão processado. Total cobrado: R$ {}", valorComAcrescimo);
    }
}
