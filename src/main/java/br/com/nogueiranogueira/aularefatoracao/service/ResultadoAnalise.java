package br.com.nogueiranogueira.aularefatoracao.service;

/**
 * Record imutável para resultado — elimina o HashMap<String,Object> do controller.
 */
public record ResultadoAnalise(boolean aprovado, String pais, String motivo) {

    public static ResultadoAnalise aprovado(String pais) {
        return new ResultadoAnalise(true, pais, null);
    }

    public static ResultadoAnalise reprovado(String motivo) {
        return new ResultadoAnalise(false, null, motivo);
    }
}