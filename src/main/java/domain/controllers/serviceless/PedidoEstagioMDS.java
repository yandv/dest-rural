package domain.controllers.serviceless;

import java.io.IOException;

import domain.exception.DomainException;
import domain.gateway.PedEstagGateway;
import domain.model.DiscenteDto;
import domain.model.PedidoEstagioDto;
import domain.model.SupervisorDto;
import domain.utils.ParameterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/without-service/criar-pedido-intencao-estagio")
public class PedidoEstagioMDS extends HttpServlet {

    private static PedidoEstagioMDS instance;

    public static PedidoEstagioMDS getInstance() {
        if (instance == null) {
            instance = new PedidoEstagioMDS();
        }
        return instance;
    }

    private int cargaHorariaCumprida;
    private double ira;

    private PedidoEstagioDto pedidoEstagioDto;

    public void criarPedEstag(int cargaHorariaCumprida, double ira) {
        validarCargaCumprida(cargaHorariaCumprida);
        validarIraMinimo(ira);

        this.cargaHorariaCumprida = cargaHorariaCumprida;
        this.ira = ira;
    }

    public void informarDadosPedEstag(DiscenteDto discente, int cargaHorariaSemanal, String nomeEmpresa,
            boolean primeiroEstagio) {

        validarCargaSemanal(cargaHorariaSemanal);

        this.pedidoEstagioDto = PedEstagGateway.getInstance().criarPedEstag(discente.getId(), nomeEmpresa,
                cargaHorariaCumprida, ira, cargaHorariaSemanal, primeiroEstagio);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(true);
        
        var discente = ParameterUtil.cast(httpSession.getAttribute("usuario"), DiscenteDto.class);

        var cargaHorariaCumprida = ParameterUtil.parseInt(req, "cargaHorariaCumprida");
        var ira = ParameterUtil.parseDouble(req, "ira");

        this.criarPedEstag(cargaHorariaCumprida, ira);

        var cargaHorariaSemanal = ParameterUtil.parseInt(req, "cargaHorariaSemanal");
        var nomeEmpresa = ParameterUtil.parseString(req, "nomeEmpresa");
        var primeiroEstagio = ParameterUtil.parseBoolean(req, "primeiroEstagio");

        this.informarDadosPedEstag(discente, cargaHorariaSemanal, nomeEmpresa, primeiroEstagio);

        resp.sendRedirect("pedido-estagio-criado-sucesso.jsp?numeroPedidoEstagio="
                + this.pedidoEstagioDto.getNumeroPedidoEstagio()
                + "&discenteId=" + discente.getId() + "&nomeDiscente=" + discente.getNome() +
                "&nomeEmpresa=" + nomeEmpresa);
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

    public void associarSupervisor(PedidoEstagioDto pedidoEstagio, SupervisorDto supervisor) {
        pedidoEstagio.setSupervisorId(supervisor.getId());
    }

    public static PedidoEstagioDto pegarPedidoEstagioPeloNumeroPedidoEstagio(int numeroPedidoEstagio) {
        return PedEstagGateway.getInstance().pegarPedidoEstagioPeloNumeroPedidoEstagio(numeroPedidoEstagio);
    }

}
