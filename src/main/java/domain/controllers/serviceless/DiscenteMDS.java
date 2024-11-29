package domain.controllers.serviceless;

import java.io.IOException;

import domain.exception.impl.UserInvalidPasswordException;
import domain.gateway.DiscenteGateway;
import domain.model.DiscenteDto;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/without-service/identificar-usuario")
public class DiscenteMDS extends HttpServlet {

    private DiscenteDto discente;

    private void criarSessao(String email, String senha) {
        var discente = DiscenteGateway.getInstance().pegarDiscentePeloEmail(email);

        if (discente == null) {
            throw new UserInvalidPasswordException();
        }

        validarSenha(discente, senha);
        this.discente = discente;
    }

    private void validarSenha(DiscenteDto discente, String senha) {
        if (!discente.getSenha().equals(senha)) {
            throw new UserInvalidPasswordException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var email = ParameterUtil.parseString(req, "email");
        var senha = ParameterUtil.parseString(req, "senha");

        criarSessao(email, senha);

        req.getSession().setAttribute("usuario", this.discente);
        resp.sendRedirect("/dest-rural/criar-pedido-estagio.jsp");
    }

}
