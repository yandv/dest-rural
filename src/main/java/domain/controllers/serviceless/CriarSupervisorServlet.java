package domain.controllers.serviceless;

import java.io.IOException;

import domain.exception.impl.BadRequestException;
import domain.exception.impl.IntershipRequestConflict;
import domain.exception.impl.InvalidRequestOrder;
import domain.gateway.DiscenteGateway;
import domain.gateway.PedEstagGateway;
import domain.gateway.SupervisorGateway;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/without-service/criar-supervisor")
public class CriarSupervisorServlet extends HttpServlet {

    private PedEstagGateway pedidoEstagioGateway;
    private DiscenteGateway discenteGateway;
    private SupervisorGateway supervisorGateway;

    public CriarSupervisorServlet() {
        this.pedidoEstagioGateway = new PedEstagGateway();
        this.discenteGateway = new DiscenteGateway();
        this.supervisorGateway = new SupervisorGateway();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var numeroPedidoEstagio = ParameterUtil.parseInt(req, "numeroPedido");

        var pedidoEstagio = this.pedidoEstagioGateway
                .pegarPedidoEstagioPeloNumeroPedidoEstagio(numeroPedidoEstagio);

        if (pedidoEstagio == null) {
            throw new InvalidRequestOrder();
        }

        if (pedidoEstagio.getSupervisorId() == null) {
            throw new IntershipRequestConflict();
        }

        var discente = discenteGateway.pegarDiscentePeloNumeroPedidoEstagio(numeroPedidoEstagio);

        var nomeDiscente = discente.getNome();
        var nomeEmpresa = pedidoEstagio.getNomeEmpresa();

        // nome, função, email, senha e telefone

        var supervisorNome = ParameterUtil.parseString(req, "nome");
        var supervisorFuncao = ParameterUtil.parseString(req, "funcao");
        var supervisorEmail = ParameterUtil.parseString(req, "email");
        var supervisorSenha = ParameterUtil.parseString(req, "senha");
        var supervisorTelefone = ParameterUtil.parseString(req, "telefone");

        if (!isEmailFormValid(supervisorEmail)) {
            throw new BadRequestException("Email inválido");
        }

        this.supervisorGateway.criarSupervisor(supervisorNome, supervisorFuncao, supervisorEmail,
                supervisorSenha, supervisorTelefone);
        pedidoEstagio.setSupervisorId(supervisorEmail);
        this.pedidoEstagioGateway.salvarPedidoEstagio(pedidoEstagio);
    }

    private boolean isEmailFormValid(String email) {
        return email.contains("@") && email.contains("\\.");
    }

}
