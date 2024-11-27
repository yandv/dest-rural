package domain.model;

public class PedidoEstagio {

    private final int numeroPedidoEstagio;
    private final String discenteId;
    private final String nomeEmpresa;
    private final int cargaHorariaCumprida;
    private final double ira;
    private final int cargaHorariaSemanal;
    private final boolean primeiroEstagio;

    private String supervisorId;

    public PedidoEstagio(int numeroPedidoEstagio, String discenteId, String nomeEmpresa,
                         int cargaHorariaCumprida, double ira, int cargaHorariaSemanal, boolean primeiroEstagio, String supervisorId) {
        this.numeroPedidoEstagio = numeroPedidoEstagio;
        this.discenteId = discenteId;
        this.nomeEmpresa = nomeEmpresa;
        this.cargaHorariaCumprida = cargaHorariaCumprida;
        this.ira = ira;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.primeiroEstagio = primeiroEstagio;
        this.supervisorId = supervisorId;
    }

    // Método para validar as regras de negócio
    public void validar() {
        if (cargaHorariaCumprida / 15 < 80) {
            throw new IllegalArgumentException(
                    "O discente ainda não cumpriu a carga horária mínima de disciplinas obrigatórias para estágio, precisa ter cumprido menos de 80 créditos em disciplinas obrigatórias.");
        }

        if (ira < 7) {
            throw new IllegalArgumentException(
                    "O discente não possui IRA suficiente para estágio, precisa ter IRA maior ou igual a 7.");
        }

        if (cargaHorariaSemanal > 30) {
            throw new IllegalArgumentException(
                    "A carga horária semanal não atende aos requisitos do curso, ela deve ser menor ou igual a 30 horas semanais.");
        }
    }

    // Getters e Setters
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
