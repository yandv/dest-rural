package domain.controllers.serviceless;

import java.io.IOException;

import domain.exception.impl.UserInvalidPasswordException;
import domain.gateway.DiscenteGateway;
import domain.utils.ParameterUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/without-service/criar-pedido-intencao-estagio")
public class IdentificarUsuarioServlet implements Filter {

    private DiscenteGateway discenteGateway;

    public IdentificarUsuarioServlet() {
        this.discenteGateway = new DiscenteGateway();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        var req = (HttpServletRequest) request;

        var email = ParameterUtil.parseString(req, "email");
        var senha = ParameterUtil.parseString(req, "senha");

        var usuario = this.discenteGateway.pegarDiscentePeloEmail(email);

        if (usuario == null) {
            throw new UserInvalidPasswordException();
        }

        usuario.validarSenha(senha);

        request.setAttribute("usuario", usuario);

        filterChain.doFilter(request, response);
    }

}
