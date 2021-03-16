package br.com.zup.proposta.proposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String cep;

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public EnderecoRequest(@NotBlank String logradouro, @NotBlank String numero, @NotBlank String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
    }

    public Endereco toModel() {
        return new Endereco(logradouro, numero, cep);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
