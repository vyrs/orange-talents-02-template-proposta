package br.com.zup.proposta.biometria;

import br.com.zup.proposta.cartao.Cartao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);

    @PostMapping("/{cartaoId}")
    @Transactional
    public ResponseEntity<?> cria(@PathVariable("cartaoId") Long cartaoId,
                                  @RequestBody @Valid BiometriaRequest request,
                                  UriComponentsBuilder uriBuilder) {

        Cartao cartao = entityManager.find(Cartao.class, cartaoId);

        if (cartao == null) return ResponseEntity.notFound().build();

        if (!request.isBase64()) return ResponseEntity.badRequest().build();

        Biometria biometria = request.toModel(cartao);
        entityManager.persist(biometria);

        URI location = uriBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
