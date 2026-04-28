package br.edu.ifb.scadiagro.model;

import java.util.ArrayList;
import java.util.List;

public class ClienteErp {
    private Long id;
    private String codigoErp;
    private String nomeRazao;
    private String cpfCnpj;
    private List<SolicitacaoCompra> solicitacoes = new ArrayList<>();

    public ClienteErp() {}

    public ClienteErp(Long id, String codigoErp, String nomeRazao, String cpfCnpj) {
        this.id = id;
        this.codigoErp = codigoErp;
        this.nomeRazao = nomeRazao;
        this.cpfCnpj = cpfCnpj;
    }

    public boolean validarCpfCnpj() {
        if (cpfCnpj == null) return false;
        String digits = cpfCnpj.replaceAll("\\D", "");
        return digits.length() == 11 || digits.length() == 14;
    }

    public void adicionarSolicitacao(SolicitacaoCompra s) {
        if (s != null) {
            solicitacoes.add(s);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoErp() { return codigoErp; }
    public void setCodigoErp(String codigoErp) { this.codigoErp = codigoErp; }

    public String getNomeRazao() { return nomeRazao; }
    public void setNomeRazao(String nomeRazao) { this.nomeRazao = nomeRazao; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public List<SolicitacaoCompra> getSolicitacoes() { return solicitacoes; }
    public void setSolicitacoes(List<SolicitacaoCompra> solicitacoes) { this.solicitacoes = solicitacoes; }

    @Override
    public String toString() {
        return "ClienteErp{" +
                "id=" + id +
                ", codigoErp='" + codigoErp + '\'' +
                ", nomeRazao='" + nomeRazao + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", solicitacoes=" + solicitacoes.size() +
                '}';
    }
}
