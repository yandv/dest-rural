package domain.controllers.servicemore;

import java.io.IOException;

import domain.exception.impl.UserInvalidPasswordException;
import domain.gateway.DiscenteGateway;
import domain.service.EstagioService;
import domain.utils.ParameterUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/with-service/criar-pedido-intencao-estagio")
public class IdentificarUsuarioServlet implements Filter {

    private EstagioService estagioFacade;

    public IdentificarUsuarioServlet() {
        this.estagioFacade = new EstagioService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        var req = (HttpServletRequest) request;

        var email = ParameterUtil.parseString(req, "email");
        var senha = ParameterUtil.parseString(req, "senha");

        var usuario = estagioFacade.autenticarDiscente(email, senha);

        request.setAttribute("usuario", usuario);

        filterChain.doFilter(request, response);
    }

}
