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
        var usuario = ParameterUtil.parseAttribute(req, "usuario", Discente.class);
        var cargaHorariaTotalDisciplinasObrigatoriasCumpridas = ParameterUtil.parseInt(req, "cargaHorariaCumprida");

        if (cargaHorariaTotalDisciplinasObrigatoriasCumpridas / 15 < 80) {
            throw new BadRequestException(
                    "O discente ainda não cumpriu a carga horária mínima de disciplinas obrigatórias para estágio, precisa ter cumprido menos de 80 créditos em disciplinas obrigatórias.");
        }

        var ira = ParameterUtil.parseDouble(req, "ira");

        if (ira < 7) {
            throw new BadRequestException(
                    "O discente não possui IRA suficiente para estágio, precisa ter IRA maior ou igual a 7.");
        }

        var enderecoResidencial = req.getParameter("enderecoResidencial");

        var cargaHorariaSemanal = ParameterUtil.parseInt(req, "cargaHorariaSemanal");

        if (cargaHorariaSemanal > 30) {
            throw new BadRequestException(
                    "A carga horária semanal não atende aos requisitos do curso, ela deve ser menor ou igual a 30 horas semanais.");
        }

        var primeiroEstagio = ParameterUtil.parseBoolean(req, "primeiroEstagio");
        var nomeEmpresa = ParameterUtil.parseString(req, "nomeEmpresa");
        var enderecoEmpresa = ParameterUtil.parseString(req, "enderecoEmpresa");
        var modalidade = ParameterUtil.parseString(req, "modalidade");

        var resumoAtividades = ParameterUtil.parseString(req, "resumoAtividades");
        var relacaoConteudo = ParameterUtil.parseString(req, "relacaoConteudo");
        var motivoIntencao = ParameterUtil.parseString(req, "motivoIntencao");

        pedEstagGateway.criarPedEstag(usuario.getId(), nomeEmpresa);

        resp.setContentType("text/html");
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<h1>Pedido de Intenção de Estágio Enviado com Sucesso!</h1>");
        resp.getWriter().println("</body></html>");
    }

}
