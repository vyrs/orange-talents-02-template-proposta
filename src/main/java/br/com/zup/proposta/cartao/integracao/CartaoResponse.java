package br.com.zup.proposta.cartao.integracao;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.proposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {

    @JsonProperty("id")
    private String numeroCartao;

    private String titular;

    private BigDecimal limite;

    @JsonProperty("emitidoEm")
    private LocalDateTime dataCriacao;

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

    public Cartao toModel(Proposta proposta) {
        return new Cartao(numeroCartao, titular, limite, dataCriacao, proposta);
    }
}
