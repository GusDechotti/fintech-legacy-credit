package br.com.nogueiranogueira.aularefatoracao.service.risco.adapter;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRecord;
import br.com.nogueiranogueira.aularefatoracao.service.risco.ServicoAnaliseRisco;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SerasaSoapAdapter extends AdapterTemplate implements ServicoAnaliseRisco {

    private static final String ENDPOINT = "https://qa.serasa.com.br/ws/ConsultaCredito";
    private final HttpClient httpClient;

    public SerasaSoapAdapter() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public boolean avaliarCredito(SolicitacaoCreditoRecord solicitacao) {
        return executarAnalise(solicitacao);
    }

    @Override
    protected String montarPayload(SolicitacaoCreditoRecord solicitacao) {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap:Body>
                        <ConsultaCredito xmlns="http://serasa.com.br/ws">
                            <cpf>%s</cpf>
                            <valorSolicitado>%s</valorSolicitado>
                            <scoreInterno>%d</scoreInterno>
                        </ConsultaCredito>
                    </soap:Body>
                </soap:Envelope>
                """.formatted(solicitacao.cpf(), solicitacao.valor(), solicitacao.score());
    }

    @Override
    protected String executarChamada(String payload) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ENDPOINT))
                    .header("Content-Type", "text/xml; charset=utf-8")
                    .header("SOAPAction", "")
                    .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Simula resposta da API
            return simularRespostaDaApiExterna();

        } catch (Exception e) {
            System.err.println("Erro ao executar chamada Serasa: " + e.getMessage());
            e.printStackTrace(System.err);
            return "";
        }
    }

    @Override
    protected boolean interpretarResposta(String resposta) {
        return resposta != null && resposta.contains("<statusConsulta>APROVADO");
    }

    private String simularRespostaDaApiExterna() {
        return """
                <?xml version="1.0" encoding="UTF-8"?>
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap:Body>
                        <ConsultaCreditoResponse xmlns="http://serasa.com.br/ws">
                            <statusConsulta>APROVADO_BAIXO_RISCO</statusConsulta>
                            <risco>BAIXO</risco>
                            <dataConsulta>2026-03-25T10:30:00</dataConsulta>
                        </ConsultaCreditoResponse>
                    </soap:Body>
                </soap:Envelope>
                """;
    }
}
