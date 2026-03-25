package br.com.nogueiranogueira.aularefatoracao.service.relatorio;

import java.util.List;

public class RelatorioCSV extends GeradorRelatorioTemplate {

    @Override
    protected String formatarCabecalho() {
        return "CPF;VALOR;STATUS\n";
    }

    @Override
    protected String formatarCorpo(List<String> dados) {
        StringBuilder corpo = new StringBuilder();
        for (String linha : dados) {
            // Dados já estão formatados como CSV na origem
            corpo.append(linha).append("\n");
        }
        return corpo.toString();
    }
}
