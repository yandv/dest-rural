package domain.gateway;

import domain.model.PedidoEstagio;

public class PedEstagGateway {

    public PedidoEstagio pegarPedidoEstagioPeloNumeroPedidoEstagio(int numeroPedidoEstagio) {
        return new PedidoEstagio(numeroPedidoEstagio, "id do discente", "IBM",
                30, 7.3, 30, true);
    }

    public void salvarPedEstag(PedidoEstagio pedidoEstagio) {
        System.out.println("Pedido de estágio salvo com sucesso!");
    }

    public PedidoEstagio criarPedEstag(String discenteId, String nomeEmpresa,
            int cargaHorariaCumprida, double ira, int cargaHorariaSemanal, boolean primeiroEstagio) {
        return new PedidoEstagio((int) Math.floor(Math.random() * 1000000), discenteId, nomeEmpresa,
                cargaHorariaCumprida,
                ira,
                cargaHorariaSemanal, primeiroEstagio);
    }

}
