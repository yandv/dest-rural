package domain.gateway;

import domain.model.Supervisor;

public class SupervisorGateway {

    private static SupervisorGateway instance;

    public static SupervisorGateway getInstance() {
        if (instance == null) {
            instance = new SupervisorGateway();
        }
        return instance;
    }

    protected SupervisorGateway() {}

    public Supervisor criarSupervisor(String supervisorNome, String supervisorFuncao, String supervisorEmail,
            String supervisorSenha, String supervisorTelefone) {
        throw new UnsupportedOperationException("Unimplemented method 'criarSupervisor'");
    }

}
