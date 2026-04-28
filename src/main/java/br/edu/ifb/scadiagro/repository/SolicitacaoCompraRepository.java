package br.edu.ifb.scadiagro.repository;

import br.edu.ifb.scadiagro.model.SolicitacaoCompra;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SolicitacaoCompraRepository {

    private final Map<Long, SolicitacaoCompra> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public List<SolicitacaoCompra> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<SolicitacaoCompra> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public SolicitacaoCompra save(SolicitacaoCompra solicitacao) {
        if (solicitacao.getId() == null) {
            solicitacao.setId(sequence.getAndIncrement());
        }
        store.put(solicitacao.getId(), solicitacao);
        return solicitacao;
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}
