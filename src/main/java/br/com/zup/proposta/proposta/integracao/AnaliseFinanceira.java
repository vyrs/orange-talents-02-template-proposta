package br.com.zup.proposta.proposta.integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseFinanceira", url = "${analiseFinanceira.targetUrl}")
public interface AnaliseFinanceira {

    @PostMapping("/api/solicitacao")
    public AnaliseResponse enviaParaAnalise(@RequestBody AnaliseRequest request);

}
