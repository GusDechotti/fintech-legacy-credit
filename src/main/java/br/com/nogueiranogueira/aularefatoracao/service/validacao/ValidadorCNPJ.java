package br.com.nogueiranogueira.aularefatoracao.service.validacao;

public class ValidadorCNPJ implements ValidadorDocumento {
    
    @Override
    public boolean validar(String documento) {
        if (documento == null || documento.isEmpty()) {
            return false;
        }
        
        // Remove caracteres não numéricos
        String apenasNumeros = documento.replaceAll("\\D", "");
        
        // Valida se tem exatamente 14 dígitos
        return apenasNumeros.length() == 14 && apenasNumeros.matches("\\d{14}");
    }
}
