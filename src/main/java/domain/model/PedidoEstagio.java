package domain.model;

public class PedidoEstagio {

    private final int numeroPedidoEstagio;

    private final String discenteId;
    private final String nomeEmpresa;

    private String supervisorId;

    public PedidoEstagio(int numeroPedidoEstagio, String discenteId, String nomeEmpresa) {
        this.numeroPedidoEstagio = numeroPedidoEstagio;
        this.discenteId = discenteId;
        this.nomeEmpresa = nomeEmpresa;
        this.supervisorId = "Supervisor Aleatório";
    }

    public int getNumeroPedidoEstagio() {
        return numeroPedidoEstagio;
    }

    public String getDiscenteId() {
        return discenteId;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

}
