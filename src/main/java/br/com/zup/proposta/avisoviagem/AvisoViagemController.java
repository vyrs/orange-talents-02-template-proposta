package br.com.zup.proposta.avisoviagem;

import br.com.zup.proposta.bloqueio.BloqueioController;
import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.integracao.CartaoCliente;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/avisoviagem")
public class AvisoViagemController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CartaoCliente cartaoCliente;

    private final Logger logger = LoggerFactory.getLogger(BloqueioController.class);

    @PostMapping("/{cartaoId}")
    @Transactional
    public ResponseEntity<?> cria(@PathVariable("cartaoId") Long cartaoId,
                                  HttpServletRequest requestInfo,
                                  @RequestBody @Valid AvisoViagemRequest request,
                                  @RequestHeader("user-agent") String agent) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId);
        if(cartao == null) return ResponseEntity.notFound().build();

        try {
            logger.info("Iniciando criação de aviso de viagem, cartão {}", cartao.getNumeroCartao());

            AvisoViagemResponse response =  cartaoCliente.avisaViagem(cartao.getNumeroCartao(), request);
            AvisoViagem avisoViagem = request.toModel(cartao, requestInfo.getRemoteAddr(), agent);

            if(response.criado()) entityManager.persist(avisoViagem);
            logger.info(" Aviso de viagem para o Cartão {} criado com sucesso!", cartao.getNumeroCartao());

        } catch (FeignException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Um erro Inesperado aconteceu!");
        }
        return ResponseEntity.ok().build();
    }
}






















