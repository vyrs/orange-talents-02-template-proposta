package br.com.zup.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    boolean existsByDocumento(String documento);

    Proposta findByDocumento(String documento);

    List<Proposta> findFirst10ByStatusOrderByDataCriacao(StatusProposta status);

    List<Proposta> findFirst10ByStatusAndCartaoIsNullOrderByDataCriacao(StatusProposta elegivel);
}
