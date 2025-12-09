public class Fornecedor {
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String telefone;
    private String tokenVinculo;

    public Fornecedor() {}

    public Fornecedor(Long id, String razaoSocial, String nomeFantasia, String cnpj) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    public boolean validarCnpj() {
        if (cnpj == null) return false;
        String digits = cnpj.replaceAll("\\D", "");
        return digits.length() == 14;
    }

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

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }

}
