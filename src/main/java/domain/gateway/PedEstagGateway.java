package domain.gateway;

import domain.model.PedidoEstagio;

public class PedEstagGateway {

    public PedidoEstagio pegarPedidoEstagioPeloNumeroPedidoEstagio(int numeroPedidoEstagio) {
        return new PedidoEstagio(numeroPedidoEstagio, null, "Empresa Aleatória");
    }

    public void salvarPedidoEstagio(PedidoEstagio pedidoEstagio) {
        System.out.println("Pedido de estágio salvo com sucesso!");
        throw new UnsupportedOperationException("Unimplemented method 'salvarPedidoEstagio'");
    }

    public PedidoEstagio criarPedEstag(String discenteId, String nomeEmpresa) {
        return new PedidoEstagio(1, discenteId, nomeEmpresa);
    }

}
