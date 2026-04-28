package br.edu.ifb.scadiagro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CotacaoRespostaItem {
    private Long id;
    private BigDecimal valorUnitario = BigDecimal.ZERO;
    private BigDecimal percentualDesconto = BigDecimal.ZERO;
    private LocalDate dataValidade;
    private boolean semEstoque = false;
    private ItemSolicitacao itemSolicitacao;

    public CotacaoRespostaItem() {}

    public CotacaoRespostaItem(Long id, BigDecimal valorUnitario, BigDecimal percentualDesconto,
                               LocalDate dataValidade, boolean semEstoque, ItemSolicitacao itemSolicitacao) {
        this.id = id;
        this.valorUnitario = valorUnitario != null ? valorUnitario : BigDecimal.ZERO;
        this.percentualDesconto = percentualDesconto != null ? percentualDesconto : BigDecimal.ZERO;
        this.dataValidade = dataValidade;
        this.semEstoque = semEstoque;
        this.itemSolicitacao = itemSolicitacao;
    }

    public BigDecimal calcularValorTotal() {
        if (itemSolicitacao == null) return BigDecimal.ZERO;
        BigDecimal quantidade = itemSolicitacao.getQuantidade() != null ? itemSolicitacao.getQuantidade() : BigDecimal.ZERO;
        BigDecimal desconto = BigDecimal.ONE.subtract(percentualDesconto != null ? percentualDesconto : BigDecimal.ZERO);
        return valorUnitario.multiply(quantidade).multiply(desconto);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(BigDecimal valorUnitario) { this.valorUnitario = valorUnitario; }

    public BigDecimal getPercentualDesconto() { return percentualDesconto; }
    public void setPercentualDesconto(BigDecimal percentualDesconto) { this.percentualDesconto = percentualDesconto; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    public boolean isSemEstoque() { return semEstoque; }
    public void setSemEstoque(boolean semEstoque) { this.semEstoque = semEstoque; }

    public ItemSolicitacao getItemSolicitacao() { return itemSolicitacao; }
    public void setItemSolicitacao(ItemSolicitacao itemSolicitacao) { this.itemSolicitacao = itemSolicitacao; }

    @Override
    public String toString() {
        return "CotacaoRespostaItem{" +
                "id=" + id +
                ", valorUnitario=" + valorUnitario +
                ", percentualDesconto=" + percentualDesconto +
                ", dataValidade=" + dataValidade +
                ", semEstoque=" + semEstoque +
                ", itemSolicitacao=" + (itemSolicitacao != null ? itemSolicitacao.getId() : null) +
                '}';
    }
}
