package br.com.zup.proposta.proposta;

import br.com.zup.proposta.cartao.Cartao;
import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String documento;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String documentoEncript;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal salario;

    @NotNull
    @Embedded
    private Endereco endereco;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProposta status = StatusProposta.PENDENTE;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    /**
     * @Deprecated for hibernate use only
     */
    public Proposta() {}

    public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome, @NotNull @Positive BigDecimal salario, @NotNull Endereco endereco) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
        this.documentoEncript = Encryptors.text("abcabc", "cbacba").encrypt(documento);
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

    public Endereco getEndereco() {
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

    public Cartao getCartao() { return cartao; }

    public String getDocumentoEncript() { return documentoEncript; }

    public void atualizaStatus(StatusProposta status) {

        if (status == null) throw new IllegalArgumentException("Status não pode ser null!");

        this.status = status;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void associaCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
