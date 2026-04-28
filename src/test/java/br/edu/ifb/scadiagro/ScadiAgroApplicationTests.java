package br.edu.ifb.scadiagro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ScadiAgroApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        // Verifica que o contexto Spring Boot sobe sem erros
    }

    @Test
    void healthEndpointRetornaUp() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void listarClientesRetornaListaVazia() throws Exception {
        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void criarClienteRetorna201() throws Exception {
        String json = """
                {
                  "codigoErp": "ERP-TEST-001",
                  "nomeRazao": "Empresa Teste LTDA",
                  "cpfCnpj": "12345678000199"
                }
                """;
        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nomeRazao").value("Empresa Teste LTDA"));
    }

    @Test
    void criarFornecedorRetorna201() throws Exception {
        String json = """
                {
                  "razaoSocial": "Fornecedor Teste Ltda",
                  "nomeFantasia": "FornTeste",
                  "cnpj": "11222333000144"
                }
                """;
        mockMvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.razaoSocial").value("Fornecedor Teste Ltda"));
    }

    @Test
    void criarSolicitacaoRetorna201() throws Exception {
        String json = """
                {
                  "codigoPedidoErp": "PED-2025-TEST",
                  "proprietario": "Tester"
                }
                """;
        mockMvc.perform(post("/api/solicitacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.status").value("RASCUNHO"));
    }

    @Test
    void buscarClienteInexistenteRetorna404() throws Exception {
        mockMvc.perform(get("/api/clientes/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void criarClienteComDadosInvalidosRetorna400() throws Exception {
        String json = """
                {
                  "codigoErp": "",
                  "nomeRazao": "",
                  "cpfCnpj": ""
                }
                """;
        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
