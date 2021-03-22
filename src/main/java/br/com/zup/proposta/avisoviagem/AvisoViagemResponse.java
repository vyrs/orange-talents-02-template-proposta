package br.com.zup.proposta.avisoviagem;

public class AvisoViagemResponse {

    private String resultado;

    public String getResultado() {
        return resultado;
    }

    public boolean criado() {
        if (resultado.equals("CRIADO")) {
            return true;
        }
        return false;
    }
}
