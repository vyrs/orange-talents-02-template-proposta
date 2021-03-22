package br.com.zup.proposta.biometria;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String fingerprint;

    @ManyToOne
    private Cartao cartao;

    @Column(updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    /**
     * @Deprecated for hibernate use only
     */
    public Biometria(){}

    public Biometria(@NotBlank String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
