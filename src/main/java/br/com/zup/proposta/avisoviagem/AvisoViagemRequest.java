package br.com.zup.proposta.avisoviagem;

import br.com.zup.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotNull
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    public AvisoViagemRequest(@NotNull String destino, @NotNull @Future LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }


    public AvisoViagem toModel(Cartao cartao, String remoteAddr, String agent) {
        return new AvisoViagem(cartao, destino, validoAte, remoteAddr, agent);
    }
}
