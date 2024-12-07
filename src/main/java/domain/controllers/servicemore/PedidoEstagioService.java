package domain.controllers.servicemore;

import java.io.IOException;

import domain.controllers.serviceless.PedidoEstagioMDS;
import domain.model.DiscenteDto;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/with-service/criar-pedido-intencao-estagio")
public class PedidoEstagioService extends HttpServlet {
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pedidoEstagioMDS = PedidoEstagioMDS.getInstance();

        HttpSession httpSession = req.getSession(true);

        var discente = ParameterUtil.cast(httpSession.getAttribute("usuario"), DiscenteDto.class);

        var cargaHorariaCumprida = ParameterUtil.parseInt(req, "cargaHorariaCumprida");
        var ira = ParameterUtil.parseDouble(req, "ira");

        pedidoEstagioMDS.criarPedEstag(cargaHorariaCumprida, ira);

        var cargaHorariaSemanal = ParameterUtil.parseInt(req, "cargaHorariaSemanal");
        var nomeEmpresa = ParameterUtil.parseString(req, "nomeEmpresa");
        var primeiroEstagio = ParameterUtil.parseBoolean(req, "primeiroEstagio");

        pedidoEstagioMDS.informarDadosPedEstag(cargaHorariaSemanal, nomeEmpresa, primeiroEstagio);

        resp.sendRedirect("pedido-estagio-criado-sucesso.jsp?numeroPedidoEstagio="
                + pedidoEstagioMDS.getPedidoEstagioDto().getNumeroPedidoEstagio()
                + "&discenteId=" + discente.getId() + "&nomeDiscente=" + discente.getNome() +
                "&nomeEmpresa=" + nomeEmpresa);
    }

}
