package br.edu.ifb.scadiagro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ItemSolicitacaoDTO {

    private Long id;

    @Positive(message = "Código Scadiagro deve ser positivo")
    private int codScadiagro;

    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    private BigDecimal quantidade;

    @NotBlank(message = "Nome do produto é obrigatório")
    private String nomeProduto;

    public ItemSolicitacaoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCodScadiagro() { return codScadiagro; }
    public void setCodScadiagro(int codScadiagro) { this.codScadiagro = codScadiagro; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
}
