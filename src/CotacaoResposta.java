import java.util.ArrayList;
import java.util.List;

public class CotacaoResposta {
    private Long id;
    private String obsFornecedor;
    private Fornecedor fornecedor;
    private List<CotacaoRespostaItem> itensResposta = new ArrayList<>();
    private SolicitacaoCompra solicitacao; // backlink opcional

    public CotacaoResposta() {}

    public CotacaoResposta(Long id, Fornecedor fornecedor) {
        this.id = id;
        this.fornecedor = fornecedor;
    }

    public void enviarResposta() {
        // comportamento exemplo: apenas logar / marcar envio
        System.out.println("Enviando cotação (id=" + id + ") do fornecedor: " +
                (fornecedor != null ? fornecedor.getNomeFantasia() : "N/A"));
        // aqui poderia disparar evento, notificação, persistência etc.
    }

    public void adicionarItemResposta(CotacaoRespostaItem item) {
        if (item != null) {
            itensResposta.add(item);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getObsFornecedor() { return obsFornecedor; }
    public void setObsFornecedor(String obsFornecedor) { this.obsFornecedor = obsFornecedor; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }

    public List<CotacaoRespostaItem> getItensResposta() { return itensResposta; }
    public void setItensResposta(List<CotacaoRespostaItem> itensResposta) { this.itensResposta = itensResposta; }

    public SolicitacaoCompra getSolicitacao() { return solicitacao; }
    public void setSolicitacao(SolicitacaoCompra solicitacao) { this.solicitacao = solicitacao; }

    @Override
    public String toString() {
        return "CotacaoResposta{" +
                "id=" + id +
                ", fornecedor=" + (fornecedor != null ? fornecedor.getNomeFantasia() : null) +
                ", itensResposta=" + itensResposta.size() +
                '}';
    }
}
