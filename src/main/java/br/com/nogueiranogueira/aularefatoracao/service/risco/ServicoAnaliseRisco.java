package br.com.nogueiranogueira.aularefatoracao.service.risco;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRecord;

public interface ServicoAnaliseRisco {
    boolean avaliarCredito(SolicitacaoCreditoRecord solicitacao);
}
