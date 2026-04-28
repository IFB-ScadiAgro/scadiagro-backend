package br.edu.ifb.scadiagro.service;

import br.edu.ifb.scadiagro.dto.ItemSolicitacaoDTO;
import br.edu.ifb.scadiagro.dto.SolicitacaoCompraDTO;
import br.edu.ifb.scadiagro.model.ItemSolicitacao;
import br.edu.ifb.scadiagro.model.SolicitacaoCompra;
import br.edu.ifb.scadiagro.model.StatusSolicitacao;
import br.edu.ifb.scadiagro.repository.SolicitacaoCompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class SolicitacaoCompraService {

    private final SolicitacaoCompraRepository repository;
    private final AtomicLong itemSequence = new AtomicLong(1);

    public SolicitacaoCompraService(SolicitacaoCompraRepository repository) {
        this.repository = repository;
    }

    public List<SolicitacaoCompraDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SolicitacaoCompraDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Solicitação não encontrada: " + id));
    }

    public SolicitacaoCompraDTO criar(SolicitacaoCompraDTO dto) {
        SolicitacaoCompra solicitacao = new SolicitacaoCompra(null, dto.getCodigoPedidoErp(), dto.getProprietario());
        solicitacao.setObservacao(dto.getObservacao());
        if (dto.getStatus() != null) {
            solicitacao.setStatus(dto.getStatus());
        }
        return toDTO(repository.save(solicitacao));
    }

    public SolicitacaoCompraDTO atualizar(Long id, SolicitacaoCompraDTO dto) {
        SolicitacaoCompra existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Solicitação não encontrada: " + id));
        existing.setCodigoPedidoErp(dto.getCodigoPedidoErp());
        existing.setProprietario(dto.getProprietario());
        existing.setObservacao(dto.getObservacao());
        if (dto.getStatus() != null) {
            existing.setStatus(dto.getStatus());
        }
        return toDTO(repository.save(existing));
    }

    public SolicitacaoCompraDTO atualizarStatus(Long id, StatusSolicitacao novoStatus) {
        SolicitacaoCompra existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Solicitação não encontrada: " + id));
        existing.setStatus(novoStatus);
        return toDTO(repository.save(existing));
    }

    public void deletar(Long id) {
        if (!repository.deleteById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada: " + id);
        }
    }

    public SolicitacaoCompraDTO adicionarItem(Long solicitacaoId, ItemSolicitacaoDTO itemDTO) {
        SolicitacaoCompra solicitacao = repository.findById(solicitacaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Solicitação não encontrada: " + solicitacaoId));
        ItemSolicitacao item = new ItemSolicitacao(
                itemSequence.getAndIncrement(),
                itemDTO.getCodScadiagro(),
                itemDTO.getQuantidade(),
                itemDTO.getNomeProduto());
        solicitacao.adicionarItem(item);
        return toDTO(repository.save(solicitacao));
    }

    public SolicitacaoCompraDTO removerItem(Long solicitacaoId, Long itemId) {
        SolicitacaoCompra solicitacao = repository.findById(solicitacaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Solicitação não encontrada: " + solicitacaoId));
        solicitacao.removerItem(itemId);
        return toDTO(repository.save(solicitacao));
    }

    private SolicitacaoCompraDTO toDTO(SolicitacaoCompra s) {
        SolicitacaoCompraDTO dto = new SolicitacaoCompraDTO();
        dto.setId(s.getId());
        dto.setCodigoPedidoErp(s.getCodigoPedidoErp());
        dto.setObservacao(s.getObservacao());
        dto.setProprietario(s.getProprietario());
        dto.setStatus(s.getStatus());
        dto.setDataCriacao(s.getDataCriacao());
        dto.setItens(s.getItens().stream().map(this::itemToDTO).collect(Collectors.toList()));
        return dto;
    }

    private ItemSolicitacaoDTO itemToDTO(ItemSolicitacao i) {
        ItemSolicitacaoDTO dto = new ItemSolicitacaoDTO();
        dto.setId(i.getId());
        dto.setCodScadiagro(i.getCodScadiagro());
        dto.setQuantidade(i.getQuantidade());
        dto.setNomeProduto(i.getNomeProduto());
        return dto;
    }
}
