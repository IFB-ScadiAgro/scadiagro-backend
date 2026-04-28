package br.edu.ifb.scadiagro.model;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cotacao_resposta")
public class CotacaoResposta {

    private static final Logger log = LoggerFactory.getLogger(CotacaoResposta.class);
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String obsFornecedor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;
    
    @OneToMany(mappedBy = "cotacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CotacaoRespostaItem> itensResposta = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitacao_id", nullable = false)
    private SolicitacaoCompra solicitacao;

    public CotacaoResposta() {}

    public CotacaoResposta(Long id, Fornecedor fornecedor) {
        this.id = id;
        this.fornecedor = fornecedor;
    }

    public void enviarResposta() {
        log.info("Enviando cotação (id={}) do fornecedor: {}", id,
                (fornecedor != null ? fornecedor.getNomeFantasia() : "N/A"));
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
