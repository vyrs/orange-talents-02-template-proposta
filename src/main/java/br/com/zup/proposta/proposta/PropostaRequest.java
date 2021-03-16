package br.com.zup.proposta.proposta;

import br.com.zup.proposta.compartilhado.validacao.CPFouCNPJ;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank @CPFouCNPJ
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotNull @Positive
    private BigDecimal salario;

    @NotNull @Valid
    private EnderecoRequest endereco;

    public PropostaRequest(@NotBlank String documento, @NotBlank @Email String email,
                           @NotBlank String nome, @NotNull @Positive BigDecimal salario,
                           @NotNull @Valid EnderecoRequest endereco) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public Proposta toModel() {
        return new Proposta(documento, email, nome, salario, endereco.toModel());
    }
}
