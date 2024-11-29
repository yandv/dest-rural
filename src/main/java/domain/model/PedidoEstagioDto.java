package domain.model;

public class PedidoEstagioDto {

    private final int numeroPedidoEstagio;
    private final String discenteId;
    private final String nomeEmpresa;
    private final int cargaHorariaCumprida;
    private final double ira;
    private final int cargaHorariaSemanal;
    private final boolean primeiroEstagio;

    private String supervisorId;

    public PedidoEstagioDto(Integer numeroPedidoEstagio, String discenteId, String nomeEmpresa,
            int cargaHorariaCumprida, double ira, int cargaHorariaSemanal, boolean primeiroEstagio) {
        this.numeroPedidoEstagio = numeroPedidoEstagio;
        this.discenteId = discenteId;
        this.nomeEmpresa = nomeEmpresa;
        this.cargaHorariaCumprida = cargaHorariaCumprida;
        this.ira = ira;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.primeiroEstagio = primeiroEstagio;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
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

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public int getCargaHorariaCumprida() {
        return cargaHorariaCumprida;
    }

    public double getIra() {
        return ira;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public boolean isPrimeiroEstagio() {
        return primeiroEstagio;
    }
}
