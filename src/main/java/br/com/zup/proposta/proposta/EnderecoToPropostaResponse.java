package br.com.zup.proposta.proposta;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class EnderecoToPropostaResponse {

    private String logradouro;
    private String numero;
    private String cep;

    public EnderecoToPropostaResponse(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.cep = endereco.getCep();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }
}
