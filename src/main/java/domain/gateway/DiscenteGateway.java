package domain.gateway;

import domain.model.Discente;

public class DiscenteGateway {

    public Discente pegarDiscentePeloNumeroPedidoEstagio(int numeroPedidoEstagio) {
        return new Discente("admin@mail.com", "email", "email");
    }

    public Discente pegarDiscentePeloEmail(String email) {
        return new Discente("admin@mail.com", "email", "email");
    }

}
