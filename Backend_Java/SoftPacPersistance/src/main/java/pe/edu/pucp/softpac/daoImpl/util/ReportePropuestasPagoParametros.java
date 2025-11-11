package pe.edu.pucp.softpac.daoImpl.util;

import java.util.Date;

public class ReportePropuestasPagoParametros {

    private Integer idPais;
    private Integer idBanco;
    private Date fechaInicio;
    private Date fechaFin;
    
    public ReportePropuestasPagoParametros(Integer idPais, Integer idBanco, Date fechaInicio, Date fechaFin) {
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
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}