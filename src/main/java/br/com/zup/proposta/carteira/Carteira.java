package br.com.zup.proposta.carteira;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String associacaoId;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CarteirasEnum carteiraEnum;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    /**
     * @Deprecated for hibernate use only
     */
    public Carteira(){}

    public Carteira(@NotBlank String associacaoId, @NotBlank @Email String email,
                    @NotNull CarteirasEnum carteiraEnum, @NotNull Cartao cartao) {
        this.associacaoId = associacaoId;
        this.email = email;
        this.carteiraEnum = carteiraEnum;
        this.cartao = cartao;
    }

    public Long getId() { return id; }

    public String getAssociacaoId() { return associacaoId; }

    public String getEmail() { return email; }

    public CarteirasEnum getCarteiraEnum() { return carteiraEnum; }

    public Cartao getCartao() { return cartao; }
}
