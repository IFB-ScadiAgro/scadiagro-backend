package br.edu.ifb.scadiagro.service;

import br.edu.ifb.scadiagro.dto.ClienteErpDTO;
import br.edu.ifb.scadiagro.model.ClienteErp;
import br.edu.ifb.scadiagro.repository.ClienteErpRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteErpService {

    private final ClienteErpRepository repository;

    public ClienteErpService(ClienteErpRepository repository) {
        this.repository = repository;
    }

    public List<ClienteErpDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteErpDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado: " + id));
    }

    public ClienteErpDTO criar(ClienteErpDTO dto) {
        ClienteErp cliente = toEntity(dto);
        return toDTO(repository.save(cliente));
    }

    public ClienteErpDTO atualizar(Long id, ClienteErpDTO dto) {
        ClienteErp existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado: " + id));
        existing.setCodigoErp(dto.getCodigoErp());
        existing.setNomeRazao(dto.getNomeRazao());
        existing.setCpfCnpj(dto.getCpfCnpj());
        return toDTO(repository.save(existing));
    }

    public void deletar(Long id) {
        if (!repository.deleteById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado: " + id);
        }
    }

    private ClienteErpDTO toDTO(ClienteErp c) {
        ClienteErpDTO dto = new ClienteErpDTO();
        dto.setId(c.getId());
        dto.setCodigoErp(c.getCodigoErp());
        dto.setNomeRazao(c.getNomeRazao());
        dto.setCpfCnpj(c.getCpfCnpj());
        return dto;
    }

    private ClienteErp toEntity(ClienteErpDTO dto) {
        return new ClienteErp(dto.getId(), dto.getCodigoErp(), dto.getNomeRazao(), dto.getCpfCnpj());
    }
}
