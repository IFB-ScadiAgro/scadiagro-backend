package br.edu.ifb.scadiagro.dto;

import br.edu.ifb.scadiagro.model.StatusSolicitacao;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoCompraDTO {

    private Long id;

    @NotBlank(message = "Código do pedido ERP é obrigatório")
    private String codigoPedidoErp;

    private String observacao;

    @NotBlank(message = "Proprietário é obrigatório")
    private String proprietario;

    private StatusSolicitacao status;
    private LocalDateTime dataCriacao;
    private List<ItemSolicitacaoDTO> itens = new ArrayList<>();

    public SolicitacaoCompraDTO() {}

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

    public List<ItemSolicitacaoDTO> getItens() { return itens; }
    public void setItens(List<ItemSolicitacaoDTO> itens) { this.itens = itens; }
}
