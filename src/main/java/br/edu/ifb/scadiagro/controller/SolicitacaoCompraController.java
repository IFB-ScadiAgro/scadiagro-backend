package br.edu.ifb.scadiagro.controller;

import br.edu.ifb.scadiagro.dto.ItemSolicitacaoDTO;
import br.edu.ifb.scadiagro.dto.SolicitacaoCompraDTO;
import br.edu.ifb.scadiagro.model.StatusSolicitacao;
import br.edu.ifb.scadiagro.service.SolicitacaoCompraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoCompraController {

    private final SolicitacaoCompraService service;

    public SolicitacaoCompraController(SolicitacaoCompraService service) {
        this.service = service;
    }

    @GetMapping
    public List<SolicitacaoCompraDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public SolicitacaoCompraDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoCompraDTO> criar(@Valid @RequestBody SolicitacaoCompraDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public SolicitacaoCompraDTO atualizar(@PathVariable Long id, @Valid @RequestBody SolicitacaoCompraDTO dto) {
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}/status")
    public SolicitacaoCompraDTO atualizarStatus(@PathVariable Long id, @RequestParam StatusSolicitacao status) {
        return service.atualizarStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/itens")
    public ResponseEntity<SolicitacaoCompraDTO> adicionarItem(
            @PathVariable Long id,
            @Valid @RequestBody ItemSolicitacaoDTO itemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.adicionarItem(id, itemDTO));
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    public SolicitacaoCompraDTO removerItem(@PathVariable Long id, @PathVariable Long itemId) {
        return service.removerItem(id, itemId);
    }
}
