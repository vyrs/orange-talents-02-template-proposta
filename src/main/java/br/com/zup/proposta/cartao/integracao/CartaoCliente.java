package br.com.zup.proposta.cartao.integracao;

import br.com.zup.proposta.avisoviagem.AvisoViagemRequest;
import br.com.zup.proposta.avisoviagem.AvisoViagemResponse;
import br.com.zup.proposta.bloqueio.BloqueioRequest;
import br.com.zup.proposta.bloqueio.BloqueioResponse;
import br.com.zup.proposta.carteira.CarteiraRequest;
import br.com.zup.proposta.carteira.CarteiraResponse;
import br.com.zup.proposta.proposta.integracao.AnaliseRequest;
import br.com.zup.proposta.proposta.integracao.AnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartao", url = "${cartao.targetUrl}")
public interface CartaoCliente {

    @PostMapping("/api/cartoes")
    CartaoResponse criaCartao(@RequestBody CartaoRequest request);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    BloqueioResponse bloqueia(@PathVariable String id, BloqueioRequest request);

    @PostMapping("/api/cartoes/{id}/avisos")
    AvisoViagemResponse avisaViagem(@PathVariable String id, AvisoViagemRequest request);

    @PostMapping("/api/cartoes/{id}/carteiras")
    CarteiraResponse associaCarteira(@PathVariable String id, CarteiraRequest request);
}
