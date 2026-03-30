package br.com.nogueiranogueira.aularefatoracao.factory;

import br.com.nogueiranogueira.aularefatoracao.domain.Documento;
import br.com.nogueiranogueira.aularefatoracao.strategy.*;

/**
 * ✅ Zero ifs.
 * O switch sobre um sealed type é EXAUSTIVO — o compilador avisa
 * se uma nova implementação de Documento for adicionada sem ser tratada aqui.
 */
public class AnaliseCreditoFactory {
    public static AnaliseStrategy obterEstrategia(Documento documento) {
        return switch (documento) {
            case Documento.Cpf  cpf  -> new AnaliseStrategyBR();
            case Documento.Cnpj cnpj -> new AnaliseStrategyBR();
            case Documento.Curp curp -> new AnaliseStrategyMX();
            case Documento.Ssn  ssn  -> new AnaliseStrategyUS();
        };
    }
}