package br.edu.ifb.scadiagro.service;

import br.edu.ifb.scadiagro.dto.FornecedorDTO;
import br.edu.ifb.scadiagro.model.Fornecedor;
import br.edu.ifb.scadiagro.repository.FornecedorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<FornecedorDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FornecedorDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fornecedor não encontrado: " + id));
    }

    public FornecedorDTO criar(FornecedorDTO dto) {
        Fornecedor fornecedor = toEntity(dto);
        return toDTO(repository.save(fornecedor));
    }

    public FornecedorDTO atualizar(Long id, FornecedorDTO dto) {
        Fornecedor existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fornecedor não encontrado: " + id));
        existing.setRazaoSocial(dto.getRazaoSocial());
        existing.setNomeFantasia(dto.getNomeFantasia());
        existing.setCnpj(dto.getCnpj());
        existing.setEmail(dto.getEmail());
        existing.setTelefone(dto.getTelefone());
        existing.setTokenVinculo(dto.getTokenVinculo());
        return toDTO(repository.save(existing));
    }

    public void deletar(Long id) {
        if (!repository.deleteById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado: " + id);
        }
    }

    private FornecedorDTO toDTO(Fornecedor f) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(f.getId());
        dto.setRazaoSocial(f.getRazaoSocial());
        dto.setNomeFantasia(f.getNomeFantasia());
        dto.setCnpj(f.getCnpj());
        dto.setEmail(f.getEmail());
        dto.setTelefone(f.getTelefone());
        dto.setTokenVinculo(f.getTokenVinculo());
        return dto;
    }

    private Fornecedor toEntity(FornecedorDTO dto) {
        Fornecedor f = new Fornecedor(dto.getId(), dto.getRazaoSocial(), dto.getNomeFantasia(), dto.getCnpj());
        f.setEmail(dto.getEmail());
        f.setTelefone(dto.getTelefone());
        f.setTokenVinculo(dto.getTokenVinculo());
        return f;
    }
}
