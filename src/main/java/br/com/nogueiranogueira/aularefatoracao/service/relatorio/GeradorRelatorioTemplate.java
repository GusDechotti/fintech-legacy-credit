package br.com.nogueiranogueira.aularefatoracao.service.relatorio;

import java.util.List;

public abstract class GeradorRelatorioTemplate {

    public final void gerarRelatorio(String dataReferencia) {
        List<String> dados = extrairDadosDoBanco();
        String cabecalho = formatarCabecalho();
        String corpo = formatarCorpo(dados);
        String conteudoFinal = cabecalho + corpo;
        salvarArquivo(conteudoFinal);
    }

    private List<String> extrairDadosDoBanco() {
        // Comportamento fixo: simula extração de dados do banco
        return List.of(
                "12345678901,1000.00,APROVADO",
                "98765432109,5000.00,REPROVADO",
                "11122233344,2500.00,APROVADO");
    }

    private void salvarArquivo(String conteudo) {
        // Comportamento fixo: simula salvamento em arquivo
        System.out.println("=== CONTEÚDO DO RELATÓRIO ===");
        System.out.println(conteudo);
        System.out.println("=== FIM DO RELATÓRIO ===");
    }

    protected abstract String formatarCabecalho();

    protected abstract String formatarCorpo(List<String> dados);
}
