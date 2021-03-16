package br.com.zup.proposta.cartao;

import br.com.zup.proposta.biometria.Biometria;
import br.com.zup.proposta.proposta.Proposta;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroCartao;

    @Column(nullable = false)
    private String titular;

    @Column(nullable = false)
    private BigDecimal limite;

    @Column(updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @OneToOne()
    @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Biometria> biometrias;

    /**
     * @Deprecated for hibernate use only
     */
    public Cartao(){}

    public Cartao(String numeroCartao, String titular, BigDecimal limite, LocalDateTime dataCriacao, Proposta proposta) {
        this.numeroCartao = numeroCartao;
        this.titular = titular;
        this.limite = limite;
        this.dataCriacao = dataCriacao;
        this.proposta = proposta;
    }

    public Long getId() {
        return id;
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

    public Proposta getProposta() {
        return proposta;
    }

    public Set<Biometria> getBiometrias() { return biometrias; }
}
