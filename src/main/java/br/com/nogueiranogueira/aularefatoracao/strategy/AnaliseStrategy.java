package br.com.nogueiranogueira.aularefatoracao.strategy;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRequest;

/**
 * Sealed: só as três estratégias abaixo podem implementar este contrato.
 * O compilador sabe em tempo de compilação todos os subtipos → switch exaustivo.
 */
public sealed interface AnaliseStrategy
        permits AnaliseStrategyPF, AnaliseStrategyPJ,
        AnaliseStrategyBR, AnaliseStrategyMX, AnaliseStrategyUS {
    boolean analisar(SolicitacaoCreditoRequest solicitacao);
    String pais();
}