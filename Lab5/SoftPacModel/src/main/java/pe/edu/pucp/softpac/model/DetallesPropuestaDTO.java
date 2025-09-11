package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;

public class DetallesPropuestaDTO {
    private String detalle_propuesta_pago_id;
    private BigDecimal monto_pago;
    private MonedasDTO moneda;
    private char estado_pago;
    private char forma_pago;
    private CuentasAcreedorDTO cuenta_acreedor;
    private PropuestasPagoDTO propuesta_pago;
    private CuentasPropiasDTO cuenta_propia;
    private FacturasDTO factura;

    public DetallesPropuestaDTO() {
        detalle_propuesta_pago_id=null;
        moneda=null;
        cuenta_acreedor=null;
        propuesta_pago=null;
        cuenta_propia=null;
        factura=null;
        monto_pago=new BigDecimal("0.0");
        estado_pago='P';
        forma_pago='T';
    }

    public DetallesPropuestaDTO(String detalle_propuesta_pago_id, BigDecimal monto_pago, MonedasDTO moneda, char estado_pago, char forma_pago, CuentasAcreedorDTO cuenta_acreedor, PropuestasPagoDTO propuesta_pago, CuentasPropiasDTO cuenta_propia, FacturasDTO factura) {
        this.detalle_propuesta_pago_id = detalle_propuesta_pago_id;
        this.monto_pago = monto_pago;
        this.moneda = moneda;
        this.estado_pago = estado_pago;
        this.forma_pago = forma_pago;
        this.cuenta_acreedor = cuenta_acreedor;
        this.propuesta_pago = propuesta_pago;
        this.cuenta_propia = cuenta_propia;
        this.factura = factura;
    }

    public DetallesPropuestaDTO(DetallesPropuestaDTO detalle_propuesta_pago){
        this.detalle_propuesta_pago_id = detalle_propuesta_pago.getDetalle_propuesta_pago_id();
        this.monto_pago = detalle_propuesta_pago.getMonto_pago();
        this.moneda = detalle_propuesta_pago.getMoneda();
        this.estado_pago = detalle_propuesta_pago.getEstado_pago();
        this.forma_pago = detalle_propuesta_pago.getForma_pago();
        this.cuenta_acreedor = detalle_propuesta_pago.getCuenta_acreedor();
        this.propuesta_pago = detalle_propuesta_pago.getPropuesta_pago();
        this.cuenta_propia = detalle_propuesta_pago.getCuenta_propia();
        this.factura = detalle_propuesta_pago.getFactura();
    }
    /**
     * @return the detalle_propuesta_pago_id
     */
    public String getDetalle_propuesta_pago_id() {
        return detalle_propuesta_pago_id;
    }

    /**
     * @param detalle_propuesta_pago_id the detalle_propuesta_pago_id to set
     */
    public void setDetalle_propuesta_pago_id(String detalle_propuesta_pago_id) {
        this.detalle_propuesta_pago_id = detalle_propuesta_pago_id;
    }

    /**
     * @return the monto_pago
     */
    public BigDecimal getMonto_pago() {
        return monto_pago;
    }

    /**
     * @param monto_pago the monto_pago to set
     */
    public void setMonto_pago(BigDecimal monto_pago) {
        this.monto_pago = monto_pago;
    }

    /**
     * @return the estado_pago
     */
    public char getEstado_pago() {
        return estado_pago;
    }

    /**
     * @param estado_pago the estado_pago to set
     */
    public void setEstado_pago(char estado_pago) {
        this.estado_pago = estado_pago;
    }

    /**
     * @return the forma_pago
     */
    public char getForma_pago() {
        return forma_pago;
    }

    /**
     * @param forma_pago the forma_pago to set
     */
    public void setForma_pago(char forma_pago) {
        this.forma_pago = forma_pago;
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
     * @return the cuenta_acreedor
     */
    public CuentasAcreedorDTO getCuenta_acreedor() {
        return cuenta_acreedor;
    }

    /**
     * @param cuenta_acreedor the cuenta_acreedor to set
     */
    public void setCuenta_acreedor(CuentasAcreedorDTO cuenta_acreedor) {
        this.cuenta_acreedor = cuenta_acreedor;
    }

    /**
     * @return the propuesta_pago
     */
    public PropuestasPagoDTO getPropuesta_pago() {
        return propuesta_pago;
    }

    /**
     * @param propuesta_pago the propuesta_pago to set
     */
    public void setPropuesta_pago(PropuestasPagoDTO propuesta_pago) {
        this.propuesta_pago = propuesta_pago;
    }

    /**
     * @return the cuenta_propia
     */
    public CuentasPropiasDTO getCuenta_propia() {
        return cuenta_propia;
    }

    /**
     * @param cuenta_propia the cuenta_propia to set
     */
    public void setCuenta_propia(CuentasPropiasDTO cuenta_propia) {
        this.cuenta_propia = cuenta_propia;
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
