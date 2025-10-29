package pe.edu.pucp.softpac.daoImpl.util;

public class ReporteFacturasPendientesParametros {

    private Integer idAcreedor;
    private Integer idPais;
    private Integer idMoneda;
    
    public ReporteFacturasPendientesParametros(Integer idAcreedor, Integer idPais, Integer idMoneda) {
        this.idAcreedor = idAcreedor;
        this.idPais = idPais;
        this.idMoneda = idMoneda;
    }

    //METODOS SELECTORES:
    
    /**
     * @return the idAcreedor
     */
    public Integer getIdAcreedor() {
        return idAcreedor;
    }

    /**
     * @param idAcreedor the idAcreedor to set
     */
    public void setIdAcreedor(Integer idAcreedor) {
        this.idAcreedor = idAcreedor;
    }

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
     * @return the idMoneda
     */
    public Integer getIdMoneda() {
        return idMoneda;
    }

    /**
     * @param idMoneda the idMoneda to set
     */
    public void setIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }
    


    
}