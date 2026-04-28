package br.edu.ifb.scadiagro.controller;

import br.edu.ifb.scadiagro.dto.ClienteErpDTO;
import br.edu.ifb.scadiagro.service.ClienteErpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteErpController {

    private final ClienteErpService service;

    public ClienteErpController(ClienteErpService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteErpDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ClienteErpDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ClienteErpDTO> criar(@Valid @RequestBody ClienteErpDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ClienteErpDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteErpDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
