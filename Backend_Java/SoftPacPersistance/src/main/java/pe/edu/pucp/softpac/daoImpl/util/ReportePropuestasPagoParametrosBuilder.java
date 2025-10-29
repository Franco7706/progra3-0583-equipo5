package pe.edu.pucp.softpac.daoImpl.util;

public class ReportePropuestasPagoParametrosBuilder {
    private Integer idPais;
    private Integer idBanco;

    public ReportePropuestasPagoParametrosBuilder conIdPais(Integer idPais) {
        this.idPais = idPais;
        return this;
    }

    public ReportePropuestasPagoParametrosBuilder conIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
        return this;
    }

    public ReportePropuestasPagoParametros build() {
        return new ReportePropuestasPagoParametros(idPais,idBanco);
    }
}