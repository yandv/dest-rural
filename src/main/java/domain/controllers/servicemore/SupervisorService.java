package domain.controllers.servicemore;

import java.io.IOException;

import domain.controllers.serviceless.SupervisorMDS;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/with-service/identificar-usuario")
public class SupervisorService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupervisorMDS supervisorMds = SupervisorMDS.getInstance();

        var numeroPedidoEstagio = ParameterUtil.parseInt(req, "numeroPedido");

        supervisorMds.criarSupervisor(numeroPedidoEstagio);

        // nome, função, email, senha e telefone

        var supervisorNome = ParameterUtil.parseString(req, "nome");
        var supervisorFuncao = ParameterUtil.parseString(req, "funcao");
        var supervisorEmail = ParameterUtil.parseString(req, "email");
        var supervisorSenha = ParameterUtil.parseString(req, "senha");
        var supervisorTelefone = ParameterUtil.parseString(req, "telefone");

        supervisorMds.informarDadosCriarSupervisor(supervisorNome, supervisorFuncao, supervisorEmail, supervisorSenha,
                supervisorTelefone);
    }

}
