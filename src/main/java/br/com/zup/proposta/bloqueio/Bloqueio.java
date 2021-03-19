package br.com.zup.proposta.bloqueio;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Cartao cartao;

    @Column(nullable = false)
    private String ipUsuario;

    @Column(nullable = false)
    private String userAgent;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    /**
     * @Deprecated for hibernate use only
     */
    public Bloqueio() {}

    public Bloqueio(Cartao cartao, String ipUsuario, String userAgent) {
        this.cartao = cartao;
        this.ipUsuario = ipUsuario;
        this.userAgent = userAgent;
    }
}
