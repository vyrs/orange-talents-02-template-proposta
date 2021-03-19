package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.integracao.CartaoCliente;
import br.com.zup.proposta.cartao.integracao.CartaoRequest;
import br.com.zup.proposta.cartao.integracao.CartaoResponse;
import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.proposta.PropostaRepository;
import br.com.zup.proposta.proposta.StatusProposta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AssociaCartaoProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoCliente cartaoCliente;

    private final Logger logger = LoggerFactory.getLogger(AssociaCartaoProposta.class);

    @Transactional
    @Scheduled(fixedDelay = 80000, initialDelay = 30000)
    public void processaCartao() {
        List<Proposta> propostas = propostaRepository.findFirst10ByStatusAndCartaoIsNullOrderByDataCriacao(StatusProposta.ELEGIVEL);

        if (!propostas.isEmpty()) {
            propostas.forEach(proposta -> {
                try {
                    logger.info("Solicitando cartão para {}", proposta.getId());

                    CartaoRequest request = new CartaoRequest(proposta);
                    CartaoResponse response = cartaoCliente.criaCartao(request);
                    Cartao cartao = response.toModel(proposta);
                    proposta.associaCartao(cartao);
                    propostaRepository.save(proposta);

                    logger.info("Proposta documento={} atualizada com cartão numero {}!", proposta.getDocumento(), cartao.getNumeroCartao());
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Um erro Inesperado aconteceu!");
                }
            });
        }
    }
}
