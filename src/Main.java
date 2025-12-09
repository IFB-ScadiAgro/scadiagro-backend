import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {// Cliente
        ClienteErp cliente = new ClienteErp(1L, "ERP-001", "Empresa Exemplo LTDA", "12345678000199");
        System.out.println("Cliente válido? " + cliente.validarCpfCnpj());

        // Solicitação
        SolicitacaoCompra solic = new SolicitacaoCompra(100L, "PED-2025-001", "João");
        cliente.adicionarSolicitacao(solic);

        // Itens
        ItemSolicitacao item1 = new ItemSolicitacao(1L, 12345, new BigDecimal("10"), "Parafuso Aço");
        ItemSolicitacao item2 = new ItemSolicitacao(2L, 56789, new BigDecimal("5"), "Martelo Madeira");

        solic.adicionarItem(item1);
        solic.adicionarItem(item2);

        System.out.println("Solicitação criada: " + solic);

        // Fornecedores
        Fornecedor fornA = new Fornecedor(10L, "Fornecedor A Ltda", "FornA", "11.222.333/0001-44");
        Fornecedor fornB = new Fornecedor(11L, "Fornecedor B SA", "FornB", "22.333.444/0001-55");

        System.out.println("Fornecedor A é válido? " + fornA.validarCnpj());
        System.out.println("Fornecedor B é válido? " + fornB.validarCnpj());

        // Cotação do fornecedor A
        CotacaoResposta cotA = new CotacaoResposta(1000L, fornA);
        CotacaoRespostaItem criA1 = new CotacaoRespostaItem(500L, new BigDecimal("5.50"),
                new BigDecimal("0.05"), LocalDate.now().plusDays(7), false, item1); // parafuso
        CotacaoRespostaItem criA2 = new CotacaoRespostaItem(501L, new BigDecimal("8.00"),
                BigDecimal.ZERO, LocalDate.now().plusDays(7), false, item2); // martelo
        cotA.adicionarItemResposta(criA1);
        cotA.adicionarItemResposta(criA2);

        // Cotação do fornecedor B
        CotacaoResposta cotB = new CotacaoResposta(1001L, fornB);
        CotacaoRespostaItem criB1 = new CotacaoRespostaItem(502L, new BigDecimal("5.40"),
                new BigDecimal("0.02"), LocalDate.now().plusDays(10), false, item1);
        CotacaoRespostaItem criB2 = new CotacaoRespostaItem(503L, new BigDecimal("7.90"),
                new BigDecimal("0.01"), LocalDate.now().plusDays(10), false, item2);
        cotB.adicionarItemResposta(criB1);
        cotB.adicionarItemResposta(criB2);

        // Adiciona cotações à solicitação
        solic.adicionarCotacao(cotA);
        solic.adicionarCotacao(cotB);

        // Enviar cotação A
        cotA.enviarResposta();

        // Enviar cotação B
        cotB.enviarResposta();

        // Comparar totais
        BigDecimal totalA = criA1.calcularValorTotal().add(criA2.calcularValorTotal());
        BigDecimal totalB = criB1.calcularValorTotal().add(criB2.calcularValorTotal());

        System.out.println("Total Fornecedor A: " + totalA);
        System.out.println("Total Fornecedor B: " + totalB);

        // Remover item exemplo
        solic.removerItem(2L);
        System.out.println("Após remover item 2, quantidade de itens: " + solic.getItens().size());

        // Comparar novamente totais
        BigDecimal novoTotalA = criA1.calcularValorTotal();
        BigDecimal novoTotalB = criB1.calcularValorTotal();

        System.out.println("Total Fornecedor A: " + novoTotalA);
        System.out.println("Total Fornecedor B: " + novoTotalB);
    }
}