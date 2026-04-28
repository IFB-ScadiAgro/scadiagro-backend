package br.edu.ifb.scadiagro.repository;

import br.edu.ifb.scadiagro.model.CotacaoResposta;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CotacaoRespostaRepository {

    private final Map<Long, CotacaoResposta> store = new LinkedHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public List<CotacaoResposta> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<CotacaoResposta> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public CotacaoResposta save(CotacaoResposta cotacao) {
        if (cotacao.getId() == null) {
            cotacao.setId(sequence.getAndIncrement());
        }
        store.put(cotacao.getId(), cotacao);
        return cotacao;
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}
