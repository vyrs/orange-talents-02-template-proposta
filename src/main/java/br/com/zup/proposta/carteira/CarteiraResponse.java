package br.com.zup.proposta.carteira;

public class CarteiraResponse {

    private String resultado;

    private String id;

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    public boolean associada() {
        if (resultado.equals("ASSOCIADA")) {
            return true;
        }
        return false;
    }
}
