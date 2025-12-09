import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ItemSolicitacao {
    private Long id;
    private int codScadiagro;
    private BigDecimal quantidade = BigDecimal.ZERO;
    private String nomeProduto;

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
