package br.com.zup.proposta.bloqueio;

public class BloqueioRequest {

    private String sistemaResponsavel;


    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
