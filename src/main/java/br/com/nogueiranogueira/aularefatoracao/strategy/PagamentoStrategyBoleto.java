package br.com.nogueiranogueira.aularefatoracao.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagamentoStrategyBoleto implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PagamentoStrategyBoleto.class);

    private static final double TAXA = 3.50;

    @Override
    public void pagar(double valor) {
        double valorBoleto = valor + TAXA;
        log.info("Registrando boleto no banco emissor...");
        log.info("Gerando código de barras com vencimento para 3 dias úteis.");
        log.info("Pagamento via Boleto processado. Total cobrado: R$ {}", valorBoleto);
    }
}
