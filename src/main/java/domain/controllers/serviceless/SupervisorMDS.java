package domain.controllers.serviceless;

import java.io.IOException;

import domain.exception.impl.BadRequestException;
import domain.exception.impl.IntershipRequestConflict;
import domain.gateway.SupervisorGateway;
import domain.model.PedidoEstagioDto;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/without-service/criar-supervisor")
public class SupervisorMDS extends HttpServlet {

    private static SupervisorMDS instance;

    public static SupervisorMDS getInstance() {
        return instance;
    }

    private PedidoEstagioDto pedidoEstagio;

    public void criarSupervisor(int numeroPedidoEstagio) {
        var pedidoEstagio = PedidoEstagioMDS
                .pegarPedidoEstagioPeloNumeroPedidoEstagio(numeroPedidoEstagio);

        if (pedidoEstagio == null) {
            throw new IllegalArgumentException("Pedido de estágio não encontrado.");
        }

        validarJaTemSupervisor(pedidoEstagio);

        // var discente = DiscenteMDS.pegarDiscentePeloId(pedidoEstagio.getDiscenteId());

        // var nomeDiscente = discente.getNome();
        // var nomeEmpresa = pedidoEstagio.getNomeEmpresa();

        this.pedidoEstagio = pedidoEstagio;
    }

    public void informarDadosCriarSupervisor(String nome, String funcao, String email, String senha, String telefone) {

        validarEmail(email);

        var supervisor = SupervisorGateway.getInstance().criarSupervisor(nome, funcao, email,
                senha, telefone);
        PedidoEstagioMDS.getInstance().associarSupervisor(pedidoEstagio, supervisor);
    }

    private void validarEmail(String email) {
        if (!email.contains("@") || !email.contains("\\.")) {
            throw new BadRequestException("Email inválido");
        }
    }

    private void validarJaTemSupervisor(PedidoEstagioDto pedEstagDto) {
        if (pedEstagDto.getSupervisorId() == null) {
            throw new IntershipRequestConflict();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var numeroPedidoEstagio = ParameterUtil.parseInt(req, "numeroPedido");

        criarSupervisor(numeroPedidoEstagio);

        // nome, função, email, senha e telefone

        var supervisorNome = ParameterUtil.parseString(req, "nome");
        var supervisorFuncao = ParameterUtil.parseString(req, "funcao");
        var supervisorEmail = ParameterUtil.parseString(req, "email");
        var supervisorSenha = ParameterUtil.parseString(req, "senha");
        var supervisorTelefone = ParameterUtil.parseString(req, "telefone");

        informarDadosCriarSupervisor(supervisorNome, supervisorFuncao, supervisorEmail, supervisorSenha,
                supervisorTelefone);
    }

}
