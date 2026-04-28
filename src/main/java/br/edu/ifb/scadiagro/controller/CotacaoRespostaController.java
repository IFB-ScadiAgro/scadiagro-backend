package br.edu.ifb.scadiagro.controller;

import br.edu.ifb.scadiagro.dto.CotacaoRespostaDTO;
import br.edu.ifb.scadiagro.service.CotacaoRespostaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CotacaoRespostaController {

    private final CotacaoRespostaService service;

    public CotacaoRespostaController(CotacaoRespostaService service) {
        this.service = service;
    }

    @GetMapping("/cotacoes")
    public List<CotacaoRespostaDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/cotacoes/{id}")
    public CotacaoRespostaDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/solicitacoes/{solicitacaoId}/cotacoes")
    public ResponseEntity<CotacaoRespostaDTO> criar(
            @PathVariable Long solicitacaoId,
            @Valid @RequestBody CotacaoRespostaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(solicitacaoId, dto));
    }

    @PostMapping("/cotacoes/{id}/enviar")
    public CotacaoRespostaDTO enviar(@PathVariable Long id) {
        return service.enviar(id);
    }

    @DeleteMapping("/cotacoes/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
