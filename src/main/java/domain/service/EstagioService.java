package domain.service;

import domain.exception.impl.BadRequestException;
import domain.exception.impl.IntershipRequestConflict;
import domain.exception.impl.InvalidRequestOrder;
import domain.exception.impl.UserInvalidPasswordException;
import domain.gateway.DiscenteGateway;
import domain.gateway.PedEstagGateway;
import domain.gateway.SupervisorGateway;
import domain.model.Discente;
import domain.model.Usuario;

public class EstagioService {

    private final PedEstagGateway pedEstagGateway;
    private final DiscenteGateway discenteGateway;
    private final SupervisorGateway supervisorGateway;

    public EstagioService() {
        this.pedEstagGateway = new PedEstagGateway();
        this.discenteGateway = new DiscenteGateway();
        this.supervisorGateway = new SupervisorGateway();
    }

    public Usuario autenticarDiscente(String email, String senha) throws UserInvalidPasswordException {
        var usuario = discenteGateway.pegarDiscentePeloEmail(email);

        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new UserInvalidPasswordException();
        }

        return usuario;
    }

    public void solicitarEstagio(
            Discente usuario,
            int cargaHorariaTotalDisciplinasObrigatoriasCumpridas,
            double ira,
            String enderecoResidencial,
            int cargaHorariaSemanal,
            boolean primeiroEstagio,
            String nomeEmpresa,
            String enderecoEmpresa,
            String modalidade,
            String resumoAtividades,
            String relacaoConteudo,
            String motivoIntencao) throws BadRequestException {

        validarCargaHoraria(cargaHorariaTotalDisciplinasObrigatoriasCumpridas);
        validarIRA(ira);
        validarCargaHorariaSemanal(cargaHorariaSemanal);

        pedEstagGateway.criarPedEstag(usuario.getId(), nomeEmpresa);
    }

    public void registrarSupervisorParaPedidoEstagio(
            int numeroPedidoEstagio,
            String nome,
            String funcao,
            String email,
            String senha,
            String telefone) throws InvalidRequestOrder, IntershipRequestConflict, BadRequestException {
        var pedidoEstagio = pedEstagGateway
                .pegarPedidoEstagioPeloNumeroPedidoEstagio(numeroPedidoEstagio);

        if (pedidoEstagio == null) {
            throw new InvalidRequestOrder();
        }

        if (pedidoEstagio.getSupervisorId() != null) {
            throw new IntershipRequestConflict();
        }

        var discente = discenteGateway.pegarDiscentePeloNumeroPedidoEstagio(numeroPedidoEstagio);
        var nomeDiscente = discente.getNome();
        var nomeEmpresa = pedidoEstagio.getNomeEmpresa();

        if (!isEmailFormValid(email)) {
            throw new BadRequestException("Email inválido");
        }

        supervisorGateway.criarSupervisor(nome, funcao, email, senha, telefone);

        pedidoEstagio.setSupervisorId(email);
        pedEstagGateway.salvarPedidoEstagio(pedidoEstagio);
    }

    private boolean isEmailFormValid(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private void validarCargaHoraria(int cargaHorariaTotalDisciplinasObrigatoriasCumpridas) throws BadRequestException {
        if (cargaHorariaTotalDisciplinasObrigatoriasCumpridas / 15 < 80) {
            throw new BadRequestException(
                    "O discente ainda não cumpriu a carga horária mínima de disciplinas obrigatórias para estágio, precisa ter cumprido menos de 80 créditos em disciplinas obrigatórias.");
        }
    }

    private void validarIRA(double ira) throws BadRequestException {
        if (ira < 7) {
            throw new BadRequestException(
                    "O discente não possui IRA suficiente para estágio, precisa ter IRA maior ou igual a 7.");
        }
    }

    private void validarCargaHorariaSemanal(int cargaHorariaSemanal) throws BadRequestException {
        if (cargaHorariaSemanal > 30) {
            throw new BadRequestException(
                    "A carga horária semanal não atende aos requisitos do curso, ela deve ser menor ou igual a 30 horas semanais.");
        }
    }

}
