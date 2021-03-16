package br.com.zup.proposta.proposta;

import br.com.zup.proposta.cartao.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoToPropostaResponse {

    private String numeroCartao;
    private String titular;
    private BigDecimal limite;
    private LocalDateTime dataCriacao;

    public CartaoToPropostaResponse(Cartao cartao) {
        this.numeroCartao = cartao.getNumeroCartao();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
        this.dataCriacao = cartao.getDataCriacao();
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
