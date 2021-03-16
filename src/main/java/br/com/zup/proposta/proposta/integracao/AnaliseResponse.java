package br.com.zup.proposta.proposta.integracao;

import br.com.zup.proposta.proposta.StatusProposta;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnaliseResponse {

    private String idProposta;

    @JsonProperty("resultadoSolicitado")
    private StatusSolicitacao status;

    public String getIdProposta() {
        return idProposta;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public StatusProposta toModel() {
        if ("COM_RESTRICAO".equals(status)) {
            return StatusProposta.NAO_ELEGIVEL;
        }
        return StatusProposta.ELEGIVEL;
    }
}
