package br.com.nogueiranogueira.aularefatoracao.service.validacao;

public class ValidadorCPF implements ValidadorDocumento {
    
    @Override
    public boolean validar(String documento) {
        if (documento == null || documento.isEmpty()) {
            return false;
        }
        
        // Remove caracteres não numéricos
        String apenasNumeros = documento.replaceAll("\\D", "");
        
        // Valida se tem exatamente 11 dígitos
        return apenasNumeros.length() == 11 && apenasNumeros.matches("\\d{11}");
    }
}
