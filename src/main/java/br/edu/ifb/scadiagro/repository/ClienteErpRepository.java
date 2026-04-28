package br.edu.ifb.scadiagro.repository;

import br.edu.ifb.scadiagro.model.ClienteErp;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteErpRepository {

    private final Map<Long, ClienteErp> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public List<ClienteErp> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<ClienteErp> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public ClienteErp save(ClienteErp cliente) {
        if (cliente.getId() == null) {
            cliente.setId(sequence.getAndIncrement());
        }
        store.put(cliente.getId(), cliente);
        return cliente;
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}
