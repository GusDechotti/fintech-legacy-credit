package br.com.nogueiranogueira.aularefatoracao.service;

import java.util.logging.Logger;

public class ProcessadorVendaService {

    private static final Logger logger = Logger.getLogger(ProcessadorVendaService.class.getName());

    // Constantes de frete por região
    private static final String CEP_PARANA = "85";
    private static final String CEP_SAO_PAULO = "01";
    private static final double FRETE_PARANA = 10.0;
    private static final double FRETE_SAO_PAULO = 20.0;
    private static final double FRETE_PADRAO = 50.0;

    // Constantes de imposto por tipo
    private static final String TIPO_PRODUTO = "PRODUTO";
    private static final String TIPO_SERVICO = "SERVICO";
    private static final double IMPOSTO_PRODUTO = 0.18;
    private static final double IMPOSTO_SERVICO = 0.05;

    // Mensagens de erro
    private static final String ERRO_CLIENTE_INVALIDO = "Erro: Cliente inválido";
    private static final String ERRO_VALOR_INVALIDO = "Erro: Valor inválido";
    private static final String ERRO_TIPO_INVALIDO = "Erro: Tipo de operação inválido";

    public void processar(String cliente, double valor, String tipo, String cep) {
        validarCliente(cliente);
        validarValor(valor);
        validarTipo(tipo);

        double frete = calcularFrete(cep);
        double imposto = calcularImposto(valor, tipo);
        double total = valor + frete + imposto;

        registrarPedido(cliente, total);
        logger.info("Recibo enviado para " + cliente);
    }

    private void validarCliente(String cliente) {
        if (cliente == null || cliente.isEmpty()) {
            throw new IllegalArgumentException(ERRO_CLIENTE_INVALIDO);
        }
    }

    private void validarValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException(ERRO_VALOR_INVALIDO);
        }
    }

    private void validarTipo(String tipo) {
        if (!tipo.equals(TIPO_PRODUTO) && !tipo.equals(TIPO_SERVICO)) {
            throw new IllegalArgumentException(ERRO_TIPO_INVALIDO);
        }
    }

    private double calcularFrete(String cep) {
        if (cep.startsWith(CEP_PARANA)) {
            return FRETE_PARANA;
        } else if (cep.startsWith(CEP_SAO_PAULO)) {
            return FRETE_SAO_PAULO;
        }
        return FRETE_PADRAO;
    }

    private double calcularImposto(double valor, String tipo) {
        if (tipo.equals(TIPO_PRODUTO)) {
            return valor * IMPOSTO_PRODUTO;
        } else if (tipo.equals(TIPO_SERVICO)) {
            return valor * IMPOSTO_SERVICO;
        }
        return 0;
    }

    private void registrarPedido(String cliente, double total) {
        logger.info("Conectando no banco...");
        String sql = String.format("INSERT INTO PEDIDOS (cliente, total) VALUES (?, ?)");
        logger.info("Executando: " + sql + " com cliente=" + cliente + " e total=" + total);
    }
}
