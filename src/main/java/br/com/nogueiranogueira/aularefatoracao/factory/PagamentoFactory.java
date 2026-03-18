package br.com.nogueiranogueira.aularefatoracao.factory;

import br.com.nogueiranogueira.aularefatoracao.dto.MetodoPagamento;
import br.com.nogueiranogueira.aularefatoracao.strategy.*;

public class PagamentoFactory {
    public static PagamentoStrategy obterEstrategia(MetodoPagamento metodo) {
        return switch (metodo) {
            case PIX -> new PagamentoStrategyPIX();
            case CARTAO_CREDITO -> new PagamentoStrategyCartao();
            case BOLETO ->  new PagamentoStrategyBoleto();
            case PAYPAL ->  new PagamentoStrategyPaypal();
            default -> throw new IllegalArgumentException("Método de pagamento não suportado: " + metodo);
        };
    }
}
