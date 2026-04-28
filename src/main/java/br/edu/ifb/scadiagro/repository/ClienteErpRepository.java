package br.edu.ifb.scadiagro.repository;

import br.edu.ifb.scadiagro.model.ClienteErp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteErpRepository extends JpaRepository<ClienteErp, Long> {
}
