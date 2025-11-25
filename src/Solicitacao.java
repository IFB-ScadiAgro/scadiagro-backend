import java.time.LocalDateTime;
import java.util.List;

public class Solicitacao {
    private int idSolicitacao;
    private LocalDateTime dataSolicitacao;
    private String propSolicitacao;
    private boolean validarCodigo;
    private String obsSolicitacao;
    private String obsFornecedor;
    private List<Produto> produtos;
    private String status;

    public Solicitacao(int idSolicitacao, LocalDateTime dataSolicitacao, String propSolicitacao, boolean validarCodigo, String obsSolicitacao, String obsFornecedor, List<Produto> produtos, String status) {
        this.idSolicitacao = idSolicitacao;
        this.dataSolicitacao = dataSolicitacao;
        this.propSolicitacao = propSolicitacao;
        this.validarCodigo = validarCodigo;
        this.obsSolicitacao = obsSolicitacao;
        this.obsFornecedor = obsFornecedor;
        this.produtos = produtos;
        this.status = status;
    }

    public void incluirSolicitacao() {

    }

    public void enviaSolicitacao() {

    }
}
