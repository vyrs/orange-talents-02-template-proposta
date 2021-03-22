package br.com.zup.proposta.proposta;

import br.com.zup.proposta.proposta.integracao.AnaliseFinanceira;
import br.com.zup.proposta.proposta.integracao.AnaliseRequest;
import br.com.zup.proposta.proposta.integracao.AnaliseResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseFinanceira analiseFinanceira;

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @Transactional
    @Scheduled(fixedDelay = 60000, initialDelay = 20000)
    public void processa() {
        // Envia para analise
        List<Proposta> pendentes = propostaRepository.findFirst10ByStatusOrderByDataCriacao(StatusProposta.PENDENTE);

        if (!pendentes.isEmpty()) {
            pendentes.forEach(proposta -> {
                StatusProposta status = enviaParaAnalise(proposta);
                proposta.atualizaStatus(status);
                logger.info("Proposta documento={} atualizada com sucesso!", Encryptors.text("abcabc", "cbacba").decrypt(proposta.getDocumentoEncript()));
            });
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {

        logger.info("Recebendo request para criação de proposta!");

        if(propostaRepository.existsByDocumento(request.getDocumento())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        // Salva no banco
        Proposta proposta = request.toModel();
        propostaRepository.save(proposta);

        logger.info("Proposta documento={} criada com sucesso!", proposta.getDocumento());

        URI location = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> busca(@PathVariable Long id) {
        Optional<Proposta> proposta = propostaRepository.findById(id);

        if (proposta.isPresent()) {
            return ResponseEntity.ok(new PropostaResponse(proposta.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private StatusProposta enviaParaAnalise(Proposta proposta) {

        try {
            AnaliseRequest req = new AnaliseRequest(proposta);
            AnaliseResponse response = analiseFinanceira.enviaParaAnalise(req);
            return response.toModel();
        } catch (FeignException.UnprocessableEntity e) { // codigo 422
            return StatusProposta.NAO_ELEGIVEL;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Um erro Inesperado aconteceu!");
        }
    }

}
