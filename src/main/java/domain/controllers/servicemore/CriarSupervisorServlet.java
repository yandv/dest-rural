package domain.controllers.servicemore;

import java.io.IOException;

import domain.exception.impl.BadRequestException;
import domain.exception.impl.IntershipRequestConflict;
import domain.exception.impl.InvalidRequestOrder;
import domain.gateway.DiscenteGateway;
import domain.gateway.PedEstagGateway;
import domain.gateway.SupervisorGateway;
import domain.service.EstagioService;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/with-service/criar-supervisor")
public class CriarSupervisorServlet extends HttpServlet {

    private EstagioService estagioFacade;

    public CriarSupervisorServlet() {
        this.estagioFacade = new EstagioService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numeroPedidoEstagio = ParameterUtil.parseInt(req, "numeroPedido");
        String nome = ParameterUtil.parseString(req, "nome");
        String funcao = ParameterUtil.parseString(req, "funcao");
        String email = ParameterUtil.parseString(req, "email");
        String senha = ParameterUtil.parseString(req, "senha");
        String telefone = ParameterUtil.parseString(req, "telefone");

        // Delegação para o serviço
        estagioFacade.registrarSupervisorParaPedidoEstagio(
            numeroPedidoEstagio, nome, funcao, email, senha, telefone
        );
    }

}
