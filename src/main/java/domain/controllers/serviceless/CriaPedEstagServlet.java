package domain.controllers.serviceless;

import java.io.IOException;

import domain.exception.impl.BadRequestException;
import domain.gateway.PedEstagGateway;
import domain.model.Discente;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/without-service/criar-pedido-intencao-estagio")
public class CriaPedEstagServlet extends HttpServlet {

    private PedEstagGateway pedEstagGateway;

    public CriaPedEstagServlet() {
        this.pedEstagGateway = new PedEstagGateway();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            
            var usuario = ParameterUtil.parseAttribute(req, "usuario", Discente.class);

            var numeroPedidoEstagio = ParameterUtil.parseBoolean(req, "numeroPedidoEstagio");
            var cargaHorariaCumprida = ParameterUtil.parseInt(req, "cargaHorariaCumprida");
            var ira = ParameterUtil.parseDouble(req, "ira");
            var cargaHorariaSemanal = ParameterUtil.parseInt(req, "cargaHorariaSemanal");
            var nomeEmpresa = ParameterUtil.parseString(req, "nomeEmpresa");
            var primeiroEstagio = ParameterUtil.parseBoolean(req, "primeiroEstagio");

            
            PedidoEstagio pedidoEstagio = new PedidoEstagio(
                numeroPedidoEstagio,
                usuario.getId(),
                nomeEmpresa,
                cargaHorariaCumprida,
                ira,
                cargaHorariaSemanal,
                primeiroEstagio
            );

            pedidoEstagio.validar();

            pedEstagGateway.criarPedEstag(pedidoEstagio.getDiscenteId(), pedidoEstagio.getNomeEmpresa());
            
            resp.setContentType("text/html");
            resp.getWriter().println("<html><body>");
            resp.getWriter().println("<h1>Pedido de Intenção de Estágio Enviado com Sucesso!</h1>");
            resp.getWriter().println("</body></html>");

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("<h1>Erro: " + e.getMessage() + "</h1>");
        }
    }

}
