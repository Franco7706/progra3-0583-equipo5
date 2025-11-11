package pe.edu.pucp.softpac.daoImpl.util;

import java.util.Date;

public class ReportePropuestasPagoParametrosBuilder {
    private Integer idPais;
    private Integer idBanco;
    private Date fechaInicio;
    private Date fechaFin;

    public ReportePropuestasPagoParametrosBuilder conIdPais(Integer idPais) {
        this.idPais = idPais;
        return this;
    }

    public ReportePropuestasPagoParametrosBuilder conIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
        return this;
    }
    
    public ReportePropuestasPagoParametrosBuilder conFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }
    
    public ReportePropuestasPagoParametrosBuilder conFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public ReportePropuestasPagoParametros build() {
        return new ReportePropuestasPagoParametros(idPais,idBanco,fechaInicio,fechaFin);
    }
}