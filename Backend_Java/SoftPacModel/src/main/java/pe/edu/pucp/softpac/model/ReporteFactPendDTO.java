package pe.edu.pucp.softpac.model;

public class ReporteFactPendDTO {

    private AcreedoresDTO acreedor;
    private MonedasDTO moneda;
    private PaisesDTO pais;
    private FacturasDTO factura;
    
    private Integer dias_vencimiento; //En BD es int 
    private String rango_vencimiento; //En BD es varchar(10)
    
    public ReporteFactPendDTO() {
        this.acreedor = null;
        this.moneda = null;
        this.pais = null;
        this.factura = null;
        
        this.dias_vencimiento = null;
        this.rango_vencimiento = null;

    }
    
    public ReporteFactPendDTO(AcreedoresDTO acreedor, MonedasDTO moneda, PaisesDTO pais,
            FacturasDTO factura, Integer dias_vencimiento, String rango_vencimiento) {
        this.acreedor = acreedor;
        this.moneda = moneda;
        this.pais = pais;
        this.factura = factura;
        
        this.dias_vencimiento = dias_vencimiento;
        this.rango_vencimiento = rango_vencimiento;

    }

    /**
     * @return the acreedor
     */
    public AcreedoresDTO getAcreedor() {
        return acreedor;
    }

    /**
     * @param acreedor the acreedor to set
     */
    public void setAcreedor(AcreedoresDTO acreedor) {
        this.acreedor = acreedor;
    }

    /**
     * @return the moneda
     */
    public MonedasDTO getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(MonedasDTO moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the pais
     */
    public PaisesDTO getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(PaisesDTO pais) {
        this.pais = pais;
    }

    /**
     * @return the dias_vencimiento
     */
    public Integer getDias_vencimiento() {
        return dias_vencimiento;
    }

    /**
     * @param dias_vencimiento the dias_vencimiento to set
     */
    public void setDias_vencimiento(Integer dias_vencimiento) {
        this.dias_vencimiento = dias_vencimiento;
    }

    /**
     * @return the rango_vencimiento
     */
    public String getRango_vencimiento() {
        return rango_vencimiento;
    }

    /**
     * @param rango_vencimiento the rango_vencimiento to set
     */
    public void setRango_vencimiento(String rango_vencimiento) {
        this.rango_vencimiento = rango_vencimiento;
    }

    /**
     * @return the factura
     */
    public FacturasDTO getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(FacturasDTO factura) {
        this.factura = factura;
    }
    
}