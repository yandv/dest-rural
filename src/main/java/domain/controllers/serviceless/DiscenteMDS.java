package domain.controllers.serviceless;

import java.io.IOException;

import domain.exception.impl.UserAlreadyAuthenticatedException;
import domain.exception.impl.UserInvalidPasswordException;
import domain.gateway.DiscenteGateway;
import domain.model.DiscenteDto;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;

@WebServlet("/without-service/identificar-usuario")
public class DiscenteMDS extends HttpServlet {

    private static DiscenteMDS instance;

    public static DiscenteMDS getInstance() {
        if (instance == null) {
            instance = new DiscenteMDS();
        }
        return instance;
    }

    @Setter
    private HttpServletRequest request;
    private DiscenteDto discente;

    public void criarSessao(String email, String senha) {
        var discente = DiscenteGateway.getInstance().pegarDiscentePeloEmail(email);

        if (discente == null) {
            throw new UserInvalidPasswordException();
        }

        this.discente = discente;
        
        validarSenha(senha);

        ativarSessao();
    }

    private void ativarSessao() {
        if (request.getSession().getAttribute("usuario") != null) {
            throw new UserAlreadyAuthenticatedException();
        }

        this.request.getSession().setAttribute("usuario", this.discente);
    }

    private void validarSenha(String senha) {
        if (!discente.getSenha().equals(senha)) {
            throw new UserInvalidPasswordException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.request = req;

        var email = ParameterUtil.parseString(req, "email");
        var senha = ParameterUtil.parseString(req, "senha");

        criarSessao(email, senha);

        resp.sendRedirect("/dest-rural/criar-pedido-estagio.jsp");
    }

}
