package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;

public class DetallesFacturaDTO {
    private String detalle_factura_id;
    private MonedasDTO moneda;
    private BigDecimal subtotal;
    private String descripcion;
    private BigDecimal monto_descuentos;
    private FacturasDTO factura;

    public DetallesFacturaDTO() {
        detalle_factura_id=null;
        moneda=null;
        subtotal=new BigDecimal("0.0");
        descripcion=null;
        monto_descuentos=new BigDecimal("0.0");
        factura=null;
    }

    public DetallesFacturaDTO(String detalle_factura_id, MonedasDTO moneda, BigDecimal subtotal, String descripcion, BigDecimal monto_descuentos, FacturasDTO factura) {
        this.detalle_factura_id = detalle_factura_id;
        this.moneda = moneda;
        this.subtotal = subtotal;
        this.descripcion = descripcion;
        this.monto_descuentos = monto_descuentos;
        this.factura = factura;
    }
    
    public DetallesFacturaDTO(DetallesFacturaDTO detalle_factura){
        this.detalle_factura_id = detalle_factura.getDetalle_factura_id();
        this.moneda = detalle_factura.getMoneda();
        this.subtotal = detalle_factura.getSubtotal();
        this.descripcion = detalle_factura.getDescripcion();
        this.monto_descuentos = detalle_factura.getMonto_descuentos();
        this.factura = detalle_factura.getFactura();
    }

    /**
     * @return the detalle_factura_id
     */
    public String getDetalle_factura_id() {
        return detalle_factura_id;
    }

    /**
     * @param detalle_factura_id the detalle_factura_id to set
     */
    public void setDetalle_factura_id(String detalle_factura_id) {
        this.detalle_factura_id = detalle_factura_id;
    }

    /**
     * @return the subtotal
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the monto_descuentos
     */
    public BigDecimal getMonto_descuentos() {
        return monto_descuentos;
    }

    /**
     * @param monto_descuentos the monto_descuentos to set
     */
    public void setMonto_descuentos(BigDecimal monto_descuentos) {
        this.monto_descuentos = monto_descuentos;
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
