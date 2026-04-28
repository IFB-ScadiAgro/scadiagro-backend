package br.edu.ifb.scadiagro.repository;

import br.edu.ifb.scadiagro.model.Fornecedor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class FornecedorRepository {

    private final Map<Long, Fornecedor> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public List<Fornecedor> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Fornecedor> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Fornecedor save(Fornecedor fornecedor) {
        if (fornecedor.getId() == null) {
            fornecedor.setId(sequence.getAndIncrement());
        }
        store.put(fornecedor.getId(), fornecedor);
        return fornecedor;
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}
