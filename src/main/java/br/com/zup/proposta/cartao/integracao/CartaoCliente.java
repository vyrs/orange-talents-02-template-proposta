package br.com.zup.proposta.cartao.integracao;

import br.com.zup.proposta.proposta.integracao.AnaliseRequest;
import br.com.zup.proposta.proposta.integracao.AnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartao", url = "${cartao.targetUrl}")
public interface CartaoCliente {

    @PostMapping("/api/cartoes")
    public CartaoResponse getCartao(@RequestBody CartaoRequest request);

}
