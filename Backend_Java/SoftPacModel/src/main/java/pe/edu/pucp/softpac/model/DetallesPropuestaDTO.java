package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;

public class DetallesPropuestaDTO extends EliminableDTOBase{
    private Integer detalle_propuesta_id;
    private BigDecimal monto_pago;
    private char forma_pago;
    private PropuestasPagoDTO propuesta_pago;
    private FacturasDTO factura;
    private CuentasAcreedorDTO cuenta_acreedor;
    private CuentasPropiasDTO cuenta_propia;

    public DetallesPropuestaDTO() {
        super();
        detalle_propuesta_id=null;
        monto_pago=new BigDecimal("0.0");
        forma_pago='T';
        propuesta_pago=null;
        factura=null;
        cuenta_acreedor=null;
        cuenta_propia=null;
    }

    public DetallesPropuestaDTO(Integer detalle_propuesta_id, BigDecimal monto_pago, char forma_pago, CuentasAcreedorDTO cuenta_acreedor, PropuestasPagoDTO propuesta_pago, CuentasPropiasDTO cuenta_propia, FacturasDTO factura) {
        this.detalle_propuesta_id = detalle_propuesta_id;
        this.monto_pago = monto_pago;
        this.forma_pago = forma_pago;
        this.propuesta_pago = propuesta_pago;
        this.factura = factura;
        this.cuenta_acreedor = cuenta_acreedor;
        this.cuenta_propia = cuenta_propia;
    }

    public DetallesPropuestaDTO(DetallesPropuestaDTO detalle_propuesta_pago){
        super(detalle_propuesta_pago);
        this.detalle_propuesta_id = detalle_propuesta_pago.getDetalle_propuesta_id();
        this.monto_pago = detalle_propuesta_pago.getMonto_pago();
        this.forma_pago = detalle_propuesta_pago.getForma_pago();
        this.propuesta_pago = detalle_propuesta_pago.getPropuesta_pago();
        this.cuenta_acreedor = detalle_propuesta_pago.getCuenta_acreedor();
        this.cuenta_propia = detalle_propuesta_pago.getCuenta_propia();
        this.factura = detalle_propuesta_pago.getFactura();
    }

    public Integer getDetalle_propuesta_id() {
        return detalle_propuesta_id;
    }

    public void setDetalle_propuesta_id(Integer detalle_propuesta_id) {
        this.detalle_propuesta_id = detalle_propuesta_id;
    }

    public BigDecimal getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(BigDecimal monto_pago) {
        this.monto_pago = monto_pago;
    }

    public char getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(char forma_pago) {
        this.forma_pago = forma_pago;
    }

    public PropuestasPagoDTO getPropuesta_pago() {
        return propuesta_pago;
    }

    public void setPropuesta_pago(PropuestasPagoDTO propuesta_pago) {
        this.propuesta_pago = propuesta_pago;
    }

    public FacturasDTO getFactura() {
        return factura;
    }

    public void setFactura(FacturasDTO factura) {
        this.factura = factura;
    }

    public CuentasAcreedorDTO getCuenta_acreedor() {
        return cuenta_acreedor;
    }

    public void setCuenta_acreedor(CuentasAcreedorDTO cuenta_acreedor) {
        this.cuenta_acreedor = cuenta_acreedor;
    }

    public CuentasPropiasDTO getCuenta_propia() {
        return cuenta_propia;
    }

    public void setCuenta_propia(CuentasPropiasDTO cuenta_propia) {
        this.cuenta_propia = cuenta_propia;
    }
}
