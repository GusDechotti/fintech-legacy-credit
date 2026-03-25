package br.com.nogueiranogueira.aularefatoracao.service.relatorio;

import java.util.List;

public class RelatorioPDF extends GeradorRelatorioTemplate {

    @Override
    protected String formatarCabecalho() {
        return "=== RELATÓRIO DE AUDITORIA ===\n\n";
    }

    @Override
    protected String formatarCorpo(List<String> dados) {
        StringBuilder corpo = new StringBuilder();
        for (String linha : dados) {
            corpo.append("---\n");
            corpo.append(linha).append("\n");
        }
        corpo.append("---\n");
        return corpo.toString();
    }
}
