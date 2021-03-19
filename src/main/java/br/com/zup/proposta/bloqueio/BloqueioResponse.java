package br.com.zup.proposta.bloqueio;

import br.com.zup.proposta.cartao.StatusCartao;

public class BloqueioResponse {

    private String resultado;

    public String getResultado() {
        return resultado;
    }

    public StatusCartao toModel() {
        if (resultado.equals("BLOQUEADO")) {
            return StatusCartao.BLOQUEADO;
        }
        return StatusCartao.ATIVO;
    }
}
