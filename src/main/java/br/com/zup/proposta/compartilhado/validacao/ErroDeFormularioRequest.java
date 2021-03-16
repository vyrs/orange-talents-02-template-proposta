package br.com.zup.proposta.compartilhado.validacao;

public class ErroDeFormularioRequest {

    private String campo;
    private String erro;

    public ErroDeFormularioRequest(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
