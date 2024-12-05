sequenceDiagram
    participant Usuario as Supervisor (Usuário)
    participant CriarSupervisorServlet as CriarSupervisorServlet
    participant PedidoEstagioGateway as PedidoEstagioGateway
    participant DiscenteGateway as DiscenteGateway
    participant SupervisorGateway as SupervisorGateway
    participant PedidoEstagio as PedidoEstagio
    participant Discente as Discente

    Usuario->>CriarSupervisorServlet: Requisição POST (numeroPedido, nome, função, email, senha, telefone)
    CriarSupervisorServlet->>PedidoEstagioGateway: buscarPorNumeroPedidoEstagio(numeroPedido: int)
    PedidoEstagioGateway-->>CriarSupervisorServlet: Retorna PedidoEstagio

    alt PedidoEstagio é nulo
        CriarSupervisorServlet-->>Usuario: Lança InvalidRequestOrder
    end

    CriarSupervisorServlet->>PedidoEstagio: validarPedidoExiste()
    CriarSupervisorServlet->>PedidoEstagio: validarSupervisorAtribuido()
    
    alt Pedido já possui Supervisor
        CriarSupervisorServlet-->>Usuario: Lança IntershipRequestConflict
    end

    CriarSupervisorServlet->>DiscenteGateway: buscarPorNumeroPedidoEstagio(numeroPedido: int)
    DiscenteGateway-->>CriarSupervisorServlet: Retorna Discente

    CriarSupervisorServlet->>Discente: getNome()
    Discente-->>CriarSupervisorServlet: Retorna nome do Discente
    CriarSupervisorServlet->>PedidoEstagio: getNomeEmpresa()
    PedidoEstagio-->>CriarSupervisorServlet: Retorna nome da Empresa

    CriarSupervisorServlet->>CriarSupervisorServlet: validarEmail(supervisorEmail)
    alt Email inválido
        CriarSupervisorServlet-->>Usuario: Lança BadRequestException
    end

    CriarSupervisorServlet->>SupervisorGateway: criarSupervisor(nome, função, email, senha, telefone)
    SupervisorGateway-->>CriarSupervisorServlet: Supervisor criado

    CriarSupervisorServlet->>PedidoEstagio: setSupervisorId(supervisorEmail)
    CriarSupervisorServlet->>PedidoEstagioGateway: salvarPedidoEstagio(PedidoEstagio)
    CriarSupervisorServlet-->>Usuario: Supervisor cadastrado com sucesso
