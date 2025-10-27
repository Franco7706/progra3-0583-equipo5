package pe.edu.pucp.softpac.model;


public class AcreedoresDTO extends EliminableDTOBase {
    private Integer acreedor_id;
    private String razon_social;
    private String ruc;
    private String direccion_fiscal;
    private String condicion;
    private Integer plazo_de_pago;
    private Boolean activo;
    private PaisesDTO pais;

    public AcreedoresDTO() {
        super();
        acreedor_id=null;
        razon_social=null;
        ruc=null;
        direccion_fiscal=null;
        pais=null;
        condicion=null;
        plazo_de_pago=0;
    }

    public AcreedoresDTO(Integer acreedor_id, String razon_social, String ruc, String direccion_fiscal, PaisesDTO pais,
            String condicion, Integer plazo_de_pago, Boolean activo) {
        super();
        this.acreedor_id = acreedor_id;
        this.razon_social = razon_social;
        this.ruc = ruc;
        this.direccion_fiscal = direccion_fiscal;
        this.pais = new PaisesDTO(pais);
        this.condicion = condicion;
        this.plazo_de_pago = plazo_de_pago;
        this.activo = activo;
    }
    
    public AcreedoresDTO(AcreedoresDTO acreedor){
        super(acreedor);
        this.acreedor_id = acreedor.getAcreedor_id();
        this.razon_social = acreedor.getRazon_social();
        this.ruc = acreedor.getRuc();
        this.direccion_fiscal = acreedor.getDireccion_fiscal();
        this.pais = acreedor.getPais();
        this.condicion = acreedor.getCondicion();
        this.plazo_de_pago = acreedor.getPlazo_de_pago();
        this.activo = acreedor.getActivo();
    }

    /**
     * @return the acreedor_id
     */
    public Integer getAcreedor_id() {
        return acreedor_id;
    }

    /**
     * @param acreedor_id the acreedor_id to set
     */
    public void setAcreedor_id(Integer acreedor_id) {
        this.acreedor_id = acreedor_id;
    }

    /**
     * @return the razon_social
     */
    public String getRazon_social() {
        return razon_social;
    }

    /**
     * @param razon_social the razon_social to set
     */
    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the direccion_fiscal
     */
    public String getDireccion_fiscal() {
        return direccion_fiscal;
    }

    /**
     * @param direccion_fiscal the direccion_fiscal to set
     */
    public void setDireccion_fiscal(String direccion_fiscal) {
        this.direccion_fiscal = direccion_fiscal;
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
     * @return the condicion
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the plazo_de_pago
     */
    public Integer getPlazo_de_pago() {
        return plazo_de_pago;
    }

    /**
     * @param plazo_de_pago the plazo_de_pago to set
     */
    public void setPlazo_de_pago(Integer plazo_de_pago) {
        this.plazo_de_pago = plazo_de_pago;
    }

    /**
     * @return the activo
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
