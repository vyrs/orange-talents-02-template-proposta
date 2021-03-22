package br.com.zup.proposta.carteira;

import br.com.zup.proposta.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private CarteirasEnum carteira;

    public CarteiraRequest(@NotBlank @Email String email, @NotNull CarteirasEnum carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public CarteirasEnum getCarteira() {
        return carteira;
    }

    public Carteira toModel(Cartao cartao, String associacaoId) {
        return new Carteira(associacaoId, email, carteira, cartao);
    }
}
