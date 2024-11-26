# Diagrama de Sequência
## Caso de Uso UC3 - Criar um Supervisor

### Fluxo principal

```mermaid
sequenceDiagram
    participant Supervisor

    participant CriarSupervisorController as Camada de Apresentação

    participant PedidoEstagioGateway as :PedidoEstagioGateway
    participant DiscenteGateway as :DiscenteGateway
    participant SupervisorGateway as :SupervisorGateway
    
    participant Discente as discente :Discente

    Supervisor->>CriarSupervisorController: 1. cadastrarSupervisor(numeroPedidoEstagio)
    CriarSupervisorController->>PedidoEstagioGateway: 1.1 pedidoEstagio = buscarPorNumero(numeroPedidoEstagio: int)

    alt O pedido de estágio é inválido ou não existe
    CriarSupervisorController-->>Supervisor: Número de pedido de estágio inválido ou inexistente
    end

    alt O estágio informado já possui supervisor
    CriarSupervisorController-->>Supervisor: O estágio informado já possui supervisor
    end
    
    CriarSupervisorController->>DiscenteGateway: 1.2 discente = buscarPorNumeroPedidoEstagio(numeroPedidoEstagio: int)
    CriarSupervisorController->>Discente: 1.3 nome = discente.getNome()
    Discente-->>CriarSupervisorController: Retorna nome do discente
    CriarSupervisorController->>Discente: 1.4 empresa = pedidoEstagio.getNomeEmpresa()
    Discente-->>CriarSupervisorController: Retorna nome da empresa

    Supervisor->>CriarSupervisorController: 2. informaDadosDoCadastro(nome: String, função: String, email: String, senha: String, telefone: String)

    alt Email inválido
    CriarSupervisorController-->>Supervisor: Email inválido
    end

    alt O supervisor insere informações incompletas
    CriarSupervisorController-->>Supervisor: As informações foram informadas de forma incompleta
    end

    CriarSupervisorController->>SupervisorGateway: 2.2 supervisor = buscarPorNumeroPedidoEstagio(numeroPedidoEstagio: int)

    CriarSupervisorController->>Supervisor: 2.3 supervisor = <<criar>> new Supervisor(nome: String, função: String, email: String, senha: String, telefone: String)
    CriarSupervisorController->>SupervisorGateway: 2.4 salvarSupervisor(supervisor: Supervisor)
    CriarSupervisorController->>SupervisorGateway: 2.5 associarAoPedidoEstagio(supervisor: Supervisor, pedidoEstagio: PedidoEstagio)
    CriarSupervisorController-->>Supervisor: 2.6 Supervisor cadastrado com sucesso
```

### Fluxo alternativo 1 - Email inválido

```mermaid
sequenceDiagram
    participant Supervisor

    participant CriarSupervisorController as Camada de Apresentação

    participant PedidoEstagioGateway as :PedidoEstagioGateway
    participant DiscenteGateway as :DiscenteGateway
    participant SupervisorGateway as :SupervisorGateway
    
    participant Discente as discente :Discente

    Supervisor->>CriarSupervisorController: 1. cadastrarSupervisor(numeroPedidoEstagio)
    CriarSupervisorController->>PedidoEstagioGateway: 1.1 pedidoEstagio = buscarPorNumero(numeroPedidoEstagio: int)

    alt O pedido de estágio é inválido ou não existe
    CriarSupervisorController-->>Supervisor: Número de pedido de estágio inválido ou inexistente
    end

    alt O estágio informado já possui supervisor
    CriarSupervisorController-->>Supervisor: O estágio informado já possui supervisor
    end
    
    CriarSupervisorController->>DiscenteGateway: 1.2 discente = buscarPorNumeroPedidoEstagio(numeroPedidoEstagio: int)
    CriarSupervisorController->>Discente: 1.3 nome = discente.getNome()
    Discente-->>CriarSupervisorController: Retorna nome do discente
    CriarSupervisorController->>Discente: 1.4 empresa = pedidoEstagio.getNomeEmpresa()
    Discente-->>CriarSupervisorController: Retorna nome da empresa

    Supervisor->>CriarSupervisorController: 2. informaDadosDoCadastro(nome: String, função: String, email: String, senha: String, telefone: String)

    alt Email inválido
    CriarSupervisorController-->>Supervisor: Email inválido
    end
```

### Fluxo alternativo 2 - O supervisor insere informações incompletas

```mermaid
sequenceDiagram
    participant Supervisor

    participant CriarSupervisorController as Camada de Apresentação

    participant PedidoEstagioGateway as :PedidoEstagioGateway
    participant DiscenteGateway as :DiscenteGateway
    participant SupervisorGateway as :SupervisorGateway
    
    participant Discente as discente :Discente

    Supervisor->>CriarSupervisorController: 1. cadastrarSupervisor(numeroPedidoEstagio)
    CriarSupervisorController->>PedidoEstagioGateway: 1.1 pedidoEstagio = buscarPorNumero(numeroPedidoEstagio: int)

    alt O pedido de estágio é inválido ou não existe
    CriarSupervisorController-->>Supervisor: Número de pedido de estágio inválido ou inexistente
    end

    alt O estágio informado já possui supervisor
    CriarSupervisorController-->>Supervisor: O estágio informado já possui supervisor
    end
    
    CriarSupervisorController->>DiscenteGateway: 1.2 discente = buscarPorNumeroPedidoEstagio(numeroPedidoEstagio: int)
    CriarSupervisorController->>Discente: 1.3 nome = discente.getNome()
    Discente-->>CriarSupervisorController: Retorna nome do discente
    CriarSupervisorController->>Discente: 1.4 empresa = pedidoEstagio.getNomeEmpresa()
    Discente-->>CriarSupervisorController: Retorna nome da empresa

    Supervisor->>CriarSupervisorController: 2. informaDadosDoCadastro(nome: String, função: String, email: String, senha: String, telefone: String)

    alt O supervisor insere informações incompletas
    CriarSupervisorController-->>Supervisor: As informações foram informadas de forma incompleta
    end
```

### Fluxo alternativo 3 - Número de pedido de estágio inválido ou inexistente

```mermaid
sequenceDiagram
    participant Supervisor

    participant CriarSupervisorController as Camada de Apresentação

    participant PedidoEstagioGateway as :PedidoEstagioGateway
    
    Supervisor->>CriarSupervisorController: 1. cadastrarSupervisor(numeroPedidoEstagio)
    CriarSupervisorController->>PedidoEstagioGateway: 1.1 pedidoEstagio = buscarPorNumero(numeroPedidoEstagio: int)

    alt O pedido de estágio é inválido ou não existe
    CriarSupervisorController-->>Supervisor: Número de pedido de estágio inválido ou inexistente
    end
```

### Fluxo alternativo 4 - Número de Pedido de Intenção de Estágio já possui um supervisor

```mermaid
sequenceDiagram
    participant Supervisor

    participant CriarSupervisorController as Camada de Apresentação

    participant PedidoEstagioGateway as :PedidoEstagioGateway
    
    Supervisor->>CriarSupervisorController: 1. cadastrarSupervisor(numeroPedidoEstagio)
    CriarSupervisorController->>PedidoEstagioGateway: 1.1 pedidoEstagio = buscarPorNumero(numeroPedidoEstagio: int)

    alt O pedido de estágio é inválido ou não existe
    CriarSupervisorController-->>Supervisor: Número de pedido de estágio inválido ou inexistente
    end

    alt O estágio informado já possui supervisor
    CriarSupervisorController-->>Supervisor: O estágio informado já possui supervisor
    end
```

#### Dúvidas

- No fluxo alternativo, é pra reescrever o fluxo principal adicionando o que acontece no fluxo alternativo? Precisamos incluir o que acontece nos fluxos alternativos anteriores a "ação principal" do fluxo alternativo atual?
- Está em um bom formato?
- Está claro?