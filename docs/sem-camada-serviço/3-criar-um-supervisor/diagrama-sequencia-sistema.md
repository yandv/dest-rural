# Diagrama de Sequência do Sistema
## Caso de Uso UC3 - Criar um Supervisor

### Fluxo principal

```mermaid
sequenceDiagram
    actor Supervisor
    participant Sistema

    Supervisor->>Sistema: 1. cadastrarSupervisor(numeroPedidoEstagio)
    Sistema-->>Supervisor: Apresenta nome do aluno e empresa

    Supervisor->>Sistema: 2. informaDadosDoCadastro(nome, função, email, senha, telefone)
    Sistema-->>Supervisor: Supervisor cadastrado com sucesso
    Sistema-->>Supervisor: Pedido de Intenção de Estágio associado ao supervisor
```

### Fluxo alternativo 1 - Email inválido

```mermaid
sequenceDiagram
    actor Supervisor
    participant Sistema

    Supervisor->>Sistema: 1. cadastrarSupervisor(numeroPedidoEstagio)
    Sistema-->>Supervisor: Apresenta nome do aluno e empresa

    Supervisor->>Sistema: 2. informaDadosDoCadastro(nome, função, email, senha, telefone)
    Sistema-->>Supervisor: Email inválido
```

### Fluxo alternativo 2 - O supervisor insere informações incompletas

```mermaid
sequenceDiagram
    actor Supervisor
    participant Sistema

    Supervisor->>Sistema: 1. cadastrarSupervisor(numeroPedidoEstagio)
    Sistema-->>Supervisor: Apresenta nome do aluno e empresa

    Supervisor->>Sistema: 2. informaDadosDoCadastro(nome, função, email, senha, telefone)
    Sistema-->>Supervisor: Informações incompletas
```

### Fluxo alternativo 3 - Número de pedido de estágio inválido ou inexistente

```mermaid
sequenceDiagram
    actor Supervisor
    participant Sistema

    Supervisor->>Sistema: cadastrarSupervisor(numeroPedidoEstagio)
    Sistema-->>Supervisor: Número de pedido de estágio inválido ou inexistente

```

### Fluxo alternativo 4 - Número de Pedido de Intenção de Estágio já possui um supervisor

```mermaid
sequenceDiagram
    actor Supervisor
    participant Sistema

    Supervisor->>Sistema: cadastrarSupervisor(numeroPedidoEstagio)
    Sistema-->>Supervisor: Número de Pedido de Intenção de Estágio já possui um supervisor
```

#### Perguntas:

- É necessário referenciar o passo que o fluxo alternativo deve voltar?