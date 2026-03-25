package br.com.nogueiranogueira.aularefatoracao.service.risco.adapter;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRecord;
import br.com.nogueiranogueira.aularefatoracao.service.risco.ServicoAnaliseRisco;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class BacenRestAdapter extends AdapterTemplate implements ServicoAnaliseRisco {

    private static final String ENDPOINT = "https://qa.bacen.gov.br/api/risco";
    private final HttpClient httpClient;

    public BacenRestAdapter() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public boolean avaliarCredito(SolicitacaoCreditoRecord solicitacao) {
        return executarAnalise(solicitacao);
    }

    @Override
    protected String montarPayload(SolicitacaoCreditoRecord solicitacao) {
        return """
                {
                    "cpf": "%s",
                    "valorSolicitado": %s,
                    "scoreInterno": %d
                }
                """.formatted(solicitacao.cpf(), solicitacao.valor(), solicitacao.score());
    }

    @Override
    protected String executarChamada(String payload) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ENDPOINT))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Simula resposta da API
            return simularRespostaDaApiExterna();

        } catch (Exception e) {
            System.err.println("Erro ao executar chamada Bacen: " + e.getMessage());
            e.printStackTrace(System.err);
            return "";
        }
    }

    @Override
    protected boolean interpretarResposta(String resposta) {
        return resposta != null && resposta.contains("\"status\":\"APROVADO\"");
    }

    private String simularRespostaDaApiExterna() {
        return """
                {
                    "status": "APROVADO",
                    "score": 750,
                    "dataAnalise": "2026-03-25T10:30:00",
                    "risco": "BAIXO"
                }
                """;
    }
}
