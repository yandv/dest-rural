package domain.gateway;

import java.util.HashMap;
import java.util.Map;

import domain.model.PedidoEstagio;

public class PedEstagGateway {

    private static PedEstagGateway instance;

    public static PedEstagGateway getInstance() {
        if (instance == null) {
            instance = new PedEstagGateway();
        }
        return instance;
    }

    private Map<Integer, PedidoEstagio> pedEstagMap;

    protected PedEstagGateway() {
        pedEstagMap = new HashMap<>();
    }

    public PedidoEstagio pegarPedidoEstagioPeloNumeroPedidoEstagio(int numeroPedidoEstagio) {
        return this.pedEstagMap.get(numeroPedidoEstagio);
    }

    public void salvarPedEstag(PedidoEstagio pedidoEstagio) {
        this.pedEstagMap.put(pedidoEstagio.getNumeroPedidoEstagio(), pedidoEstagio);
    }

    public PedidoEstagio criarPedEstag(String discenteId, String nomeEmpresa,
            int cargaHorariaCumprida, double ira, int cargaHorariaSemanal, boolean primeiroEstagio) {
        return new PedidoEstagio((int) Math.floor(Math.random() * 1000000), discenteId, nomeEmpresa,
                cargaHorariaCumprida,
                ira,
                cargaHorariaSemanal, primeiroEstagio);
    }

}
