package pe.edu.pucp.softpac.daoImpl.util;

import java.time.LocalDateTime;

public class ReportePropuestasPagoParametrosBuilder {
    private Integer idPais;
    private Integer idBanco;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public ReportePropuestasPagoParametrosBuilder conIdPais(Integer idPais) {
        this.idPais = idPais;
        return this;
    }

    public ReportePropuestasPagoParametrosBuilder conIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
        return this;
    }
    
    public ReportePropuestasPagoParametrosBuilder conFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }
    
    public ReportePropuestasPagoParametrosBuilder conFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public ReportePropuestasPagoParametros build() {
        return new ReportePropuestasPagoParametros(idPais,idBanco,fechaInicio,fechaFin);
    }
}