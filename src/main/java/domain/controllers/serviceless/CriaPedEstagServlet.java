package domain.controllers.serviceless;

import java.io.IOException;

import domain.exception.DomainException;
import domain.gateway.PedEstagGateway;
import domain.model.Discente;
import domain.model.PedidoEstagio;
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
        var usuario = ParameterUtil.parseAttribute(req, "usuario", Discente.class);

        var cargaHorariaCumprida = ParameterUtil.parseInt(req, "cargaHorariaCumprida");
        var ira = ParameterUtil.parseDouble(req, "ira");

        validarCargaCumprida(cargaHorariaCumprida);
        validarIraMinimo(ira);

        var cargaHorariaSemanal = ParameterUtil.parseInt(req, "cargaHorariaSemanal");
        var nomeEmpresa = ParameterUtil.parseString(req, "nomeEmpresa");
        var primeiroEstagio = ParameterUtil.parseBoolean(req, "primeiroEstagio");

        validarCargaSemanal(cargaHorariaSemanal);

        var pedEstag = pedEstagGateway.criarPedEstag(usuario.getId(), nomeEmpresa,
                cargaHorariaCumprida, ira, cargaHorariaSemanal, primeiroEstagio);

        resp.setContentType("text/html");
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<h1>Pedido de Intenção de Estágio Enviado com Sucesso!</h1>");
        resp.getWriter().println("<br>");
        resp.getWriter().println("<h2>Nome do Discente: " + usuario.getNome() + "</h2>");
        resp.getWriter().println("<br>");
        resp.getWriter().println("<br>");
        resp.getWriter().println("<h2>O número do pedido é " + pedEstag.getNumeroPedidoEstagio() + "</h2>");
        resp.getWriter().println("</body></html>");
    }

    private void validarCargaCumprida(int cargaHorariaCumprida) {
        if (cargaHorariaCumprida / 15 < 80) {
            throw new DomainException(
                    "O discente ainda não cumpriu a carga horária mínima de disciplinas obrigatórias para estágio, precisa ter cumprido menos de 80 créditos em disciplinas obrigatórias.",
                    400);
        }
    }

    private void validarIraMinimo(double ira) {
        if (ira < 7) {
            throw new DomainException(
                    "O discente não possui IRA suficiente para estágio, precisa ter IRA maior ou igual a 7.", 400);
        }
    }

    private void validarCargaSemanal(int cargaHorariaSemanal) {
        if (cargaHorariaSemanal > 30) {
            throw new DomainException(
                    "A carga horária semanal não atende aos requisitos do curso, ela deve ser menor ou igual a 30 horas semanais.",
                    400);
        }
    }

}
