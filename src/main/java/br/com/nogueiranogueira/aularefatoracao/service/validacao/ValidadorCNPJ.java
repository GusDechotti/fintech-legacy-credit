package br.com.nogueiranogueira.aularefatoracao.service.validacao;

public class ValidadorCNPJ implements ValidadorDocumento {
    @Override
    public boolean validar(String documento) {
        if (documento == null || documento.isEmpty()) return false;
        String apenasNumeros = documento.replaceAll("\\D", "");
        return apenasNumeros.length() == 14 && apenasNumeros.matches("\\d{14}");
    }
}