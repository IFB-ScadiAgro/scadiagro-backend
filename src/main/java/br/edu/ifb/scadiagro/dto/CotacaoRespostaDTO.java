package br.edu.ifb.scadiagro.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CotacaoRespostaDTO {

    private Long id;

    @NotNull(message = "Fornecedor é obrigatório")
    private Long fornecedorId;

    private String obsFornecedor;
    private List<CotacaoRespostaItemDTO> itensResposta = new ArrayList<>();

    public CotacaoRespostaDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }

    public String getObsFornecedor() { return obsFornecedor; }
    public void setObsFornecedor(String obsFornecedor) { this.obsFornecedor = obsFornecedor; }

    public List<CotacaoRespostaItemDTO> getItensResposta() { return itensResposta; }
    public void setItensResposta(List<CotacaoRespostaItemDTO> itensResposta) { this.itensResposta = itensResposta; }

    // Nested DTO
    public static class CotacaoRespostaItemDTO {
        private Long id;
        private Long itemSolicitacaoId;
        private BigDecimal valorUnitario;
        private BigDecimal percentualDesconto;
        private LocalDate dataValidade;
        private boolean semEstoque;
        private BigDecimal valorTotal;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Long getItemSolicitacaoId() { return itemSolicitacaoId; }
        public void setItemSolicitacaoId(Long itemSolicitacaoId) { this.itemSolicitacaoId = itemSolicitacaoId; }

        public BigDecimal getValorUnitario() { return valorUnitario; }
        public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

        public BigDecimal getPercentualDesconto() { return percentualDesconto; }
        public void setPercentualDesconto(BigDecimal percentualDesconto) { this.percentualDesconto = percentualDesconto; }

        public LocalDate getDataValidade() { return dataValidade; }
        public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

        public boolean isSemEstoque() { return semEstoque; }
        public void setSemEstoque(boolean semEstoque) { this.semEstoque = semEstoque; }

        public BigDecimal getValorTotal() { return valorTotal; }
        public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    }
}
