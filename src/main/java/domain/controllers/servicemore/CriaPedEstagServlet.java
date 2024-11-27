package domain.controllers.servicemore;

import java.io.IOException;

import domain.exception.impl.BadRequestException;
import domain.gateway.PedEstagGateway;
import domain.model.Discente;
import domain.service.EstagioService;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/with-service/criar-pedido-intencao-estagio")
public class CriaPedEstagServlet extends HttpServlet {

    private EstagioService estagioFacade;

    public CriaPedEstagServlet() {
        this.estagioFacade = new EstagioService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var usuario = ParameterUtil.parseAttribute(req, "usuario", Discente.class);

        // Extração dos parâmetros
        int cargaHorariaTotalDisciplinasObrigatoriasCumpridas = ParameterUtil.parseInt(req, "cargaHorariaCumprida");
        double ira = ParameterUtil.parseDouble(req, "ira");
        String enderecoResidencial = req.getParameter("enderecoResidencial");
        int cargaHorariaSemanal = ParameterUtil.parseInt(req, "cargaHorariaSemanal");
        boolean primeiroEstagio = ParameterUtil.parseBoolean(req, "primeiroEstagio");
        String nomeEmpresa = ParameterUtil.parseString(req, "nomeEmpresa");
        String enderecoEmpresa = ParameterUtil.parseString(req, "enderecoEmpresa");
        String modalidade = ParameterUtil.parseString(req, "modalidade");
        String resumoAtividades = ParameterUtil.parseString(req, "resumoAtividades");
        String relacaoConteudo = ParameterUtil.parseString(req, "relacaoConteudo");
        String motivoIntencao = ParameterUtil.parseString(req, "motivoIntencao");

        // Delegação para o serviço
        estagioFacade.solicitarEstagio(
                usuario,
                cargaHorariaTotalDisciplinasObrigatoriasCumpridas,
                ira,
                enderecoResidencial,
                cargaHorariaSemanal,
                primeiroEstagio,
                nomeEmpresa,
                enderecoEmpresa,
                modalidade,
                resumoAtividades,
                relacaoConteudo,
                motivoIntencao);

        resp.setContentType("text/html");
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<h1>Pedido de Intenção de Estágio Enviado com Sucesso!</h1>");
        resp.getWriter().println("</body></html>");
    }

}
