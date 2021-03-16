package br.com.zup.proposta.biometria;

import br.com.zup.proposta.cartao.Cartao;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    private String fingerprint;

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public boolean isBase64() {
        try {
            Base64.getDecoder().decode(fingerprint.getBytes());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(fingerprint, cartao);
    }
}
