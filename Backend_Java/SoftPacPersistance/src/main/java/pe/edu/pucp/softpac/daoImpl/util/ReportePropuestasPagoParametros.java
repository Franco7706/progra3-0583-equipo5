package pe.edu.pucp.softpac.daoImpl.util;

public class ReportePropuestasPagoParametros {

    private Integer idPais;
    private Integer idBanco;
    
    public ReportePropuestasPagoParametros(Integer idPais, Integer idBanco) {
        this.idPais = idPais;
        this.idBanco = idBanco;
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
}