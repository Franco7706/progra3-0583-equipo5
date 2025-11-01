package pe.edu.pucp.softpac.daoImpl.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportePropuestasPagoParametros {

    private Integer idPais;
    private Integer idBanco;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    
    public ReportePropuestasPagoParametros(Integer idPais, Integer idBanco, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.idPais = idPais;
        this.idBanco = idBanco;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    //METODOS SELECTORES:
    
    /**
     * @return the idPais
     */
    public Integer getIdPais() {
        return idPais;
    }

    /**
     * @param idPais the idPais to set
     */
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    /**
     * @return the idBanco
     */
    public Integer getIdBanco() {
        return idBanco;
    }

    /**
     * @param idBanco the idBanco to set
     */
    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    /**
     * @return the fechaInicio
     */
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}