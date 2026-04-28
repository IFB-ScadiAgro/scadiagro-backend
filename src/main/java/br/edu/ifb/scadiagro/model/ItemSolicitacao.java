package br.edu.ifb.scadiagro.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "item_solicitacao")
public class ItemSolicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private int codScadiagro;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal quantidade = BigDecimal.ZERO;
    
    private String nomeProduto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitacao_id")
    private SolicitacaoCompra solicitacao;

    public ItemSolicitacao() {}

    public ItemSolicitacao(Long id, int codScadiagro, BigDecimal quantidade, String nomeProduto) {
        this.id = id;
        this.codScadiagro = codScadiagro;
        this.quantidade = quantidade != null ? quantidade : BigDecimal.ZERO;
        this.nomeProduto = nomeProduto;
    }

    public boolean validarCodigo() {
        return codScadiagro > 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCodScadiagro() { return codScadiagro; }
    public void setCodScadiagro(int codScadiagro) { this.codScadiagro = codScadiagro; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public SolicitacaoCompra getSolicitacao() { return solicitacao; }
    public void setSolicitacao(SolicitacaoCompra solicitacao) { this.solicitacao = solicitacao; }

    @Override
    public String toString() {
        return "ItemSolicitacao{" +
                "id=" + id +
                ", codScadiagro=" + codScadiagro +
                ", quantidade=" + quantidade +
                ", nomeProduto='" + nomeProduto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSolicitacao that = (ItemSolicitacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
