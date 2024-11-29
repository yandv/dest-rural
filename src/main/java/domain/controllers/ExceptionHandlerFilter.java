package domain.controllers;

import java.io.IOException;

import domain.exception.DomainException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("*")
public class ExceptionHandlerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }

        var servletResponse = (HttpServletResponse) response;

        try {
            response.setContentType("application/json");

            chain.doFilter(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
            
            if (exception instanceof DomainException) {
                var domainException = (DomainException) exception;
                servletResponse.setStatus(domainException.getStatusCode());
                response.getWriter().println(domainException.toJson());
                return;
            }

            servletResponse.setStatus(500);
            response.getWriter().println("{\"message\": \"Erro interno do servidor.\"}");
        }
    }

}
