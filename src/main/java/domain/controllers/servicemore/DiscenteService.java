package domain.controllers.servicemore;

import java.io.IOException;

import domain.controllers.serviceless.DiscenteMDS;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/with-service/identificar-usuario")
public class DiscenteService extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var discenteMDS = DiscenteMDS.getInstance();

        discenteMDS.setRequest(req);

        var email = ParameterUtil.parseString(req, "email");
        var senha = ParameterUtil.parseString(req, "senha");

        discenteMDS.criarSessao(email, senha);

        resp.sendRedirect("/dest-rural/criar-pedido-estagio.jsp");
    }

}
