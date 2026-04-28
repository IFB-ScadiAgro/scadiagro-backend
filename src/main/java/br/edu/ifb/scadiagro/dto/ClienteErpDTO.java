package br.edu.ifb.scadiagro.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteErpDTO {

    private Long id;

    @NotBlank(message = "Código ERP é obrigatório")
    private String codigoErp;

    @NotBlank(message = "Nome/Razão Social é obrigatório")
    private String nomeRazao;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    private String cpfCnpj;

    public ClienteErpDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoErp() { return codigoErp; }
    public void setCodigoErp(String codigoErp) { this.codigoErp = codigoErp; }

    public String getNomeRazao() { return nomeRazao; }
    public void setNomeRazao(String nomeRazao) { this.nomeRazao = nomeRazao; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }
}
