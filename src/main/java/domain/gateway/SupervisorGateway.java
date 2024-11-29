package domain.gateway;

import domain.model.SupervisorDto;

public class SupervisorGateway {

    private static SupervisorGateway instance;

    public static SupervisorGateway getInstance() {
        if (instance == null) {
            instance = new SupervisorGateway();
        }
        return instance;
    }

    protected SupervisorGateway() {}

    public SupervisorDto criarSupervisor(String supervisorNome, String supervisorFuncao, String supervisorEmail,
            String supervisorSenha, String supervisorTelefone) {
        throw new UnsupportedOperationException("Unimplemented method 'criarSupervisor'");
    }

}
