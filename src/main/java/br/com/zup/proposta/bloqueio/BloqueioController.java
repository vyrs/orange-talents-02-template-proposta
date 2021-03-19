package br.com.zup.proposta.bloqueio;

import br.com.zup.proposta.cartao.AssociaCartaoProposta;
import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.StatusCartao;
import br.com.zup.proposta.cartao.integracao.CartaoCliente;
import br.com.zup.proposta.cartao.integracao.CartaoResponse;
import br.com.zup.proposta.proposta.StatusProposta;
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
@RequestMapping("/bloqueio")
public class BloqueioController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CartaoCliente cartaoCliente;

    private final Logger logger = LoggerFactory.getLogger(BloqueioController.class);

    @PostMapping("/{cartaoId}")
    @Transactional
    public ResponseEntity<?> bloqueia(@PathVariable("cartaoId") Long cartaoId,
                                      HttpServletRequest requestInfo,
                                      @RequestHeader("user-agent") String agent) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId);

        if (cartao == null) return ResponseEntity.notFound().build();
        if (cartao.bloqueado()) return ResponseEntity.unprocessableEntity().build();

        try {
            logger.info("Iniciando tentatica de bloqueio do cartão {}", cartao.getNumeroCartao());
            BloqueioResponse response = cartaoCliente.bloqueia(cartao.getNumeroCartao(), new BloqueioRequest("Proposta"));
            StatusCartao status = response.toModel();
            cartao.atualizaStatus(status);

            Bloqueio bloqueio = new Bloqueio(cartao, requestInfo.getRemoteAddr(), agent);
            entityManager.persist(bloqueio);

            logger.info("Cartão {} bloqueado com sucesso!", cartao.getNumeroCartao());
        }
        catch (FeignException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Um erro Inesperado aconteceu!");
        }
        return ResponseEntity.ok().build();
    }
}
