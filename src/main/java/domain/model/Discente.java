package domain.model;

public class Discente extends Usuario {

    private int numeroPedidoEstagio;

    public Discente(String email, String nome, String senha) {
        super(email, nome, senha);
    }

    public void associarPedidoEstagio(int numeroPedidoEstagio) {
        this.numeroPedidoEstagio = numeroPedidoEstagio;
    }

    public int getNumeroPedidoEstagio() {
        return numeroPedidoEstagio;
    }

}
