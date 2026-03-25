package br.com.nogueiranogueira.aularefatoracao.dto;

import java.math.BigDecimal;

public record SolicitacaoCreditoRecord(
        String cpf,
        BigDecimal valor,
        int score,
        String documento
) {
}
