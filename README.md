# scadiagro-backend

API REST para o sistema ScadiAgro, desenvolvida com **Spring Boot 3** e **Maven**.

## Tecnologias

- Java 17
- Spring Boot 3.3 (Web, Validation, Actuator)
- Maven 3.x
- Armazenamento in-memory (sem banco de dados externo)

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/edu/ifb/scadiagro/
│   │   ├── ScadiAgroApplication.java      # Classe principal
│   │   ├── controller/                    # Endpoints REST
│   │   ├── service/                       # Regras de negócio
│   │   ├── repository/                    # Persistência in-memory
│   │   ├── model/                         # Entidades do domínio
│   │   └── dto/                           # Data Transfer Objects
│   └── resources/
│       └── application.properties
└── test/
    └── java/br/edu/ifb/scadiagro/
        └── ScadiAgroApplicationTests.java
```

## Como Rodar

### Pré-requisitos
- Java 17+
- Maven 3.6+

### Executar a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### Executar os testes

```bash
mvn test
```

### Compilar o projeto

```bash
mvn clean package
java -jar target/scadiagro-backend-0.0.1-SNAPSHOT.jar
```

---

## Endpoints

### Health

```bash
# Verificar saúde da aplicação
curl http://localhost:8080/health

# Actuator health (detalhado)
curl http://localhost:8080/actuator/health
```

---

### Clientes (`/api/clientes`)

```bash
# Listar todos os clientes
curl http://localhost:8080/api/clientes

# Buscar cliente por ID
curl http://localhost:8080/api/clientes/1

# Criar cliente
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "codigoErp": "ERP-001",
    "nomeRazao": "Empresa Exemplo LTDA",
    "cpfCnpj": "12345678000199"
  }'

# Atualizar cliente
curl -X PUT http://localhost:8080/api/clientes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "codigoErp": "ERP-001",
    "nomeRazao": "Empresa Exemplo Atualizada LTDA",
    "cpfCnpj": "12345678000199"
  }'

# Deletar cliente
curl -X DELETE http://localhost:8080/api/clientes/1
```

---

### Fornecedores (`/api/fornecedores`)

```bash
# Listar todos os fornecedores
curl http://localhost:8080/api/fornecedores

# Criar fornecedor
curl -X POST http://localhost:8080/api/fornecedores \
  -H "Content-Type: application/json" \
  -d '{
    "razaoSocial": "Fornecedor A Ltda",
    "nomeFantasia": "FornA",
    "cnpj": "11222333000144",
    "email": "contato@forna.com",
    "telefone": "(61) 99999-0001"
  }'

# Atualizar fornecedor
curl -X PUT http://localhost:8080/api/fornecedores/1 \
  -H "Content-Type: application/json" \
  -d '{
    "razaoSocial": "Fornecedor A Ltda",
    "nomeFantasia": "FornA Novo",
    "cnpj": "11222333000144"
  }'

# Deletar fornecedor
curl -X DELETE http://localhost:8080/api/fornecedores/1
```

---

### Solicitações de Compra (`/api/solicitacoes`)

```bash
# Listar todas as solicitações
curl http://localhost:8080/api/solicitacoes

# Criar solicitação
curl -X POST http://localhost:8080/api/solicitacoes \
  -H "Content-Type: application/json" \
  -d '{
    "codigoPedidoErp": "PED-2025-001",
    "proprietario": "João",
    "observacao": "Pedido urgente"
  }'

# Atualizar status da solicitação
# Valores: RASCUNHO, ENVIADA, EM_ABERTO, FECHADA, CANCELADA
curl -X PATCH "http://localhost:8080/api/solicitacoes/1/status?status=ENVIADA"

# Adicionar item à solicitação
curl -X POST http://localhost:8080/api/solicitacoes/1/itens \
  -H "Content-Type: application/json" \
  -d '{
    "codScadiagro": 12345,
    "quantidade": 10,
    "nomeProduto": "Parafuso Aço"
  }'

# Remover item da solicitação
curl -X DELETE http://localhost:8080/api/solicitacoes/1/itens/1
```

---

### Cotações (`/api/cotacoes`, `/api/solicitacoes/{id}/cotacoes`)

```bash
# Listar todas as cotações
curl http://localhost:8080/api/cotacoes

# Criar cotação para uma solicitação
# (itensResposta referencia IDs de itens já adicionados à solicitação)
curl -X POST http://localhost:8080/api/solicitacoes/1/cotacoes \
  -H "Content-Type: application/json" \
  -d '{
    "fornecedorId": 1,
    "obsFornecedor": "Entrega em 7 dias",
    "itensResposta": [
      {
        "itemSolicitacaoId": 1,
        "valorUnitario": 5.50,
        "percentualDesconto": 0.05,
        "dataValidade": "2025-12-31",
        "semEstoque": false
      }
    ]
  }'

# Enviar cotação
curl -X POST http://localhost:8080/api/cotacoes/1/enviar

# Deletar cotação
curl -X DELETE http://localhost:8080/api/cotacoes/1
```
