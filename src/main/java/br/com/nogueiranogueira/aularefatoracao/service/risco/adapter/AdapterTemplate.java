package br.com.nogueiranogueira.aularefatoracao.service.risco.adapter;

import br.com.nogueiranogueira.aularefatoracao.dto.SolicitacaoCreditoRecord;
import br.com.nogueiranogueira.aularefatoracao.service.validacao.ValidadorDocumentoFactory;

public abstract class AdapterTemplate {

    public final boolean executarAnalise(SolicitacaoCreditoRecord solicitacao) {
        try {
            validarDocumento(solicitacao);

            String payload = montarPayload(solicitacao);

            String resposta = executarChamada(payload);

            return interpretarResposta(resposta);

        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro na execução da análise: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }

    private void validarDocumento(SolicitacaoCreditoRecord solicitacao) {
        String documento = solicitacao.documento();
        var validador = ValidadorDocumentoFactory.criar(documento);
        if (!validador.validar(documento)) {
            throw new IllegalArgumentException("Documento inválido: " + documento);
        }
    }

    protected abstract String montarPayload(SolicitacaoCreditoRecord solicitacao);

    protected abstract String executarChamada(String payload);

    protected abstract boolean interpretarResposta(String resposta);
}
