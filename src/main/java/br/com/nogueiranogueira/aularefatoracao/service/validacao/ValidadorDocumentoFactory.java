package br.com.nogueiranogueira.aularefatoracao.service.validacao;

public class ValidadorDocumentoFactory {
    public static ValidadorDocumento criar(String documento) {
        if (documento == null || documento.isEmpty())
            throw new IllegalArgumentException("Tipo de documento não reconhecido");
        String apenasNumeros = documento.replaceAll("\\D", "");
        if (apenasNumeros.length() == 11) return new ValidadorCPF();
        else if (apenasNumeros.length() == 14) return new ValidadorCNPJ();
        else throw new IllegalArgumentException("Tipo de documento não reconhecido");
    }
}