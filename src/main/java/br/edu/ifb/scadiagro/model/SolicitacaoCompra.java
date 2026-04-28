package br.edu.ifb.scadiagro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SolicitacaoCompra {
    private Long id;
    private String codigoPedidoErp;
    private String observacao;
    private String proprietario;
    private StatusSolicitacao status = StatusSolicitacao.RASCUNHO;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private List<ItemSolicitacao> itens = new ArrayList<>();
    private List<CotacaoResposta> cotacoes = new ArrayList<>();

    public SolicitacaoCompra() {}

    public SolicitacaoCompra(Long id, String codigoPedidoErp, String proprietario) {
        this.id = id;
        this.codigoPedidoErp = codigoPedidoErp;
        this.proprietario = proprietario;
    }

    public boolean validarCodigo() {
        return codigoPedidoErp != null && !codigoPedidoErp.trim().isEmpty();
    }

    public void adicionarItem(ItemSolicitacao item) {
        if (item == null) return;
        boolean existe = itens.stream()
                .filter(i -> i.getId() != null && item.getId() != null)
                .anyMatch(i -> Objects.equals(i.getId(), item.getId()));
        if (!existe) {
            itens.add(item);
        }
    }

    public void removerItem(Long itemId) {
        if (itemId == null) return;
        Iterator<ItemSolicitacao> it = itens.iterator();
        while (it.hasNext()) {
            ItemSolicitacao current = it.next();
            if (Objects.equals(current.getId(), itemId)) {
                it.remove();
                break;
            }
        }
    }

    public void adicionarCotacao(CotacaoResposta cotacao) {
        if (cotacao == null) return;
        cotacao.setSolicitacao(this);
        cotacoes.add(cotacao);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoPedidoErp() { return codigoPedidoErp; }
    public void setCodigoPedidoErp(String codigoPedidoErp) { this.codigoPedidoErp = codigoPedidoErp; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public String getProprietario() { return proprietario; }
    public void setProprietario(String proprietario) { this.proprietario = proprietario; }

    public StatusSolicitacao getStatus() { return status; }
    public void setStatus(StatusSolicitacao status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public List<ItemSolicitacao> getItens() { return itens; }
    public void setItens(List<ItemSolicitacao> itens) { this.itens = itens; }

    public List<CotacaoResposta> getCotacoes() { return cotacoes; }
    public void setCotacoes(List<CotacaoResposta> cotacoes) { this.cotacoes = cotacoes; }

    @Override
    public String toString() {
        return "SolicitacaoCompra{" +
                "id=" + id +
                ", codigoPedidoErp='" + codigoPedidoErp + '\'' +
                ", proprietario='" + proprietario + '\'' +
                ", status=" + status +
                ", dataCriacao=" + dataCriacao +
                ", itens=" + itens.size() +
                ", cotacoes=" + cotacoes.size() +
                '}';
    }
}
