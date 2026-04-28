package br.edu.ifb.scadiagro.service;

import br.edu.ifb.scadiagro.dto.CotacaoRespostaDTO;
import br.edu.ifb.scadiagro.model.*;
import br.edu.ifb.scadiagro.repository.CotacaoRespostaRepository;
import br.edu.ifb.scadiagro.repository.FornecedorRepository;
import br.edu.ifb.scadiagro.repository.SolicitacaoCompraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CotacaoRespostaService {

    private final CotacaoRespostaRepository cotacaoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final SolicitacaoCompraRepository solicitacaoRepository;

    public CotacaoRespostaService(CotacaoRespostaRepository cotacaoRepository,
                                  FornecedorRepository fornecedorRepository,
                                  SolicitacaoCompraRepository solicitacaoRepository) {
        this.cotacaoRepository = cotacaoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public List<CotacaoRespostaDTO> listarTodos() {
        return cotacaoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CotacaoRespostaDTO buscarPorId(Long id) {
        return cotacaoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cotação não encontrada: " + id));
    }

    public CotacaoRespostaDTO criar(Long solicitacaoId, CotacaoRespostaDTO dto) {
        SolicitacaoCompra solicitacao = solicitacaoRepository.findById(solicitacaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Solicitação não encontrada: " + solicitacaoId));

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Fornecedor não encontrado: " + dto.getFornecedorId()));

        CotacaoResposta cotacao = new CotacaoResposta(null, fornecedor);
        cotacao.setObsFornecedor(dto.getObsFornecedor());

        if (dto.getItensResposta() != null) {
             for (CotacaoRespostaDTO.CotacaoRespostaItemDTO itemDTO : dto.getItensResposta()) {
                 ItemSolicitacao itemSolicitacao = solicitacao.getItens().stream()
                         .filter(i -> i.getId().equals(itemDTO.getItemSolicitacaoId()))
                         .findFirst()
                         .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                 "Item não encontrado na solicitação: " + itemDTO.getItemSolicitacaoId()));

                 CotacaoRespostaItem cri = new CotacaoRespostaItem(
                         null,
                         itemDTO.getValorUnitario(),
                         itemDTO.getPercentualDesconto(),
                         itemDTO.getDataValidade(),
                         itemDTO.isSemEstoque(),
                         itemSolicitacao);
                 cotacao.adicionarItemResposta(cri);
             }
         }

        cotacaoRepository.save(cotacao);
        solicitacao.adicionarCotacao(cotacao);
        solicitacaoRepository.save(solicitacao);
        return toDTO(cotacao);
    }

    public CotacaoRespostaDTO enviar(Long id) {
        CotacaoResposta cotacao = cotacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cotação não encontrada: " + id));
        cotacao.enviarResposta();
        return toDTO(cotacaoRepository.save(cotacao));
    }

    public void deletar(Long id) {
        if (!cotacaoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cotação não encontrada: " + id);
        }
        cotacaoRepository.deleteById(id);
    }

    private CotacaoRespostaDTO toDTO(CotacaoResposta c) {
        CotacaoRespostaDTO dto = new CotacaoRespostaDTO();
        dto.setId(c.getId());
        dto.setObsFornecedor(c.getObsFornecedor());
        if (c.getFornecedor() != null) {
            dto.setFornecedorId(c.getFornecedor().getId());
        }
        List<CotacaoRespostaDTO.CotacaoRespostaItemDTO> itens = c.getItensResposta().stream()
                .map(i -> {
                    CotacaoRespostaDTO.CotacaoRespostaItemDTO iDto = new CotacaoRespostaDTO.CotacaoRespostaItemDTO();
                    iDto.setId(i.getId());
                    iDto.setValorUnitario(i.getValorUnitario());
                    iDto.setPercentualDesconto(i.getPercentualDesconto());
                    iDto.setDataValidade(i.getDataValidade());
                    iDto.setSemEstoque(i.isSemEstoque());
                    iDto.setValorTotal(i.calcularValorTotal());
                    if (i.getItemSolicitacao() != null) {
                        iDto.setItemSolicitacaoId(i.getItemSolicitacao().getId());
                    }
                    return iDto;
                }).collect(Collectors.toList());
        dto.setItensResposta(itens);
        return dto;
    }
}
