package br.com.nogueiranogueira.aularefatoracao.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagamentoStrategyPIX implements PagamentoStrategy {

    private static final Logger log = LoggerFactory.getLogger(PagamentoStrategyPIX.class);

    private static final double DESCONTOPERC = 0.95;

    @Override
    public void pagar(double valor) {
        double valorComDesconto = valor * DESCONTOPERC;
        log.info("Calculando desconto do PIX...");
        log.info("Gerando chave Copia e Cola.");
        log.info("Pagamento via PIX processado. Total cobrado: R$ {}", valorComDesconto);
    }
}
