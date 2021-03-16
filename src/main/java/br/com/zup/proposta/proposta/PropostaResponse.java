package br.com.zup.proposta.proposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private BigDecimal salario;
    private EnderecoToPropostaResponse endereco;
    private LocalDateTime dataCriacao;
    private StatusProposta status;
    private LocalDateTime dataAtualizacao;
    private CartaoToPropostaResponse cartao;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.salario = proposta.getSalario();
        this.endereco = new EnderecoToPropostaResponse(proposta.getEndereco());
        this.dataCriacao = proposta.getDataCriacao();
        this.status = proposta.getStatus();
        this.dataAtualizacao = proposta.getDataAtualizacao();
        this.cartao = proposta.getCartao() == null ? null : new CartaoToPropostaResponse(proposta.getCartao());
    }

    public Long getId() {
        return id;
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

    public EnderecoToPropostaResponse getEndereco() {
        return endereco;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public CartaoToPropostaResponse getCartao() {
        return cartao;
    }
}
