package br.edu.ifb.scadiagro.repository;

import br.edu.ifb.scadiagro.model.CotacaoResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoRespostaRepository extends JpaRepository<CotacaoResposta, Long> {
}
