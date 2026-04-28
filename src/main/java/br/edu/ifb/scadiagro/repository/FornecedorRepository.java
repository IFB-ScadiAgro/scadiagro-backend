package br.edu.ifb.scadiagro.repository;

import br.edu.ifb.scadiagro.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
