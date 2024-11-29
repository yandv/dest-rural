package domain.gateway;

import java.util.HashMap;
import java.util.Map;

import domain.model.PedidoEstagioDto;

public class PedEstagGateway {

    private static PedEstagGateway instance;

    public static PedEstagGateway getInstance() {
        if (instance == null) {
            instance = new PedEstagGateway();
        }
        return instance;
    }

    private Map<Integer, PedidoEstagioDto> pedEstagMap;

    protected PedEstagGateway() {
        pedEstagMap = new HashMap<>();
    }

    public PedidoEstagioDto pegarPedidoEstagioPeloNumeroPedidoEstagio(int numeroPedidoEstagio) {
        return this.pedEstagMap.get(numeroPedidoEstagio);
    }

    public void salvarPedEstag(PedidoEstagioDto pedidoEstagio) {
        this.pedEstagMap.put(pedidoEstagio.getNumeroPedidoEstagio(), pedidoEstagio);
    }

    public PedidoEstagioDto criarPedEstag(String discenteId, String nomeEmpresa,
            int cargaHorariaCumprida, double ira, int cargaHorariaSemanal, boolean primeiroEstagio) {
        return new PedidoEstagioDto((int) Math.floor(Math.random() * 1000000), discenteId, nomeEmpresa,
                cargaHorariaCumprida,
                ira,
                cargaHorariaSemanal, primeiroEstagio);
    }

}
