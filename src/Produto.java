import java.time.LocalDateTime;

public class Produto {
    private String nome;
    private int qtdProduto;
    private String unidadeMedida;
    private int idProduto;
    private double valorUnitario;
    private boolean temEstoque;
    private double percentualDesconto;
    private LocalDateTime validadePreco;
    private int codScadiagro;

    public void cadastrarProduto(){

    }

    public void alterarProduto() {

    }

    public void excluirProduto() {

    }

    public boolean ehValido(int codScadiagro) {
        return true;
    }
}
