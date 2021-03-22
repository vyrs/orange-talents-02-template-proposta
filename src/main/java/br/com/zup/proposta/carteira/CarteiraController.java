package br.com.zup.proposta.carteira;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.integracao.CartaoCliente;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CartaoCliente cartaoCliente;

    @PostMapping("/{cartaoId}")
    @Transactional
    public ResponseEntity<?> cria(@PathVariable("cartaoId") Long cartaoId,
                                  @RequestBody @Valid CarteiraRequest request,
                                  UriComponentsBuilder uriBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId);
        if(cartao == null) return ResponseEntity.notFound().build();
        if(cartao.carteiraJaAssociada(request.getCarteira())) return ResponseEntity.unprocessableEntity().build();

        try {
            CarteiraResponse response = cartaoCliente.associaCarteira(cartao.getNumeroCartao(), request);

            if (response.associada()) {
                Carteira carteira = request.toModel(cartao, response.getId());
                entityManager.persist(carteira);

                URI uri = uriBuilder.path("/carteira/{id}").buildAndExpand(carteira.getId()).toUri();

                return ResponseEntity.created(uri).build();
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);

        } catch (FeignException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Um erro Inesperado aconteceu!");
        }
    }

}
