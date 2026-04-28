package br.edu.ifb.scadiagro.dto;

import jakarta.validation.constraints.NotBlank;

public class FornecedorDTO {

    private Long id;

    @NotBlank(message = "Razão Social é obrigatória")
    private String razaoSocial;

    private String nomeFantasia;

    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;

    private String email;
    private String telefone;
    private String tokenVinculo;

    public FornecedorDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getTokenVinculo() { return tokenVinculo; }
    public void setTokenVinculo(String tokenVinculo) { this.tokenVinculo = tokenVinculo; }
}
