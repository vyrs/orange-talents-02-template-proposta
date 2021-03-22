package br.com.zup.proposta.avisoviagem;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @NotBlank
    private String destino;

    @NotNull
    @Future
    private LocalDate validoAte;

    @NotBlank
    private String ipCliente;

    @NotBlank
    private String userAgent;

    @Column(updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    /**
     * @Deprecated for hibernate use only
     */
    public AvisoViagem(){}

    public AvisoViagem(@NotNull Cartao cartao, @NotBlank String destino,
                       @NotNull @Future LocalDate validoAte,
                       @NotBlank String ipCliente, @NotBlank String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.validoAte = validoAte;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
