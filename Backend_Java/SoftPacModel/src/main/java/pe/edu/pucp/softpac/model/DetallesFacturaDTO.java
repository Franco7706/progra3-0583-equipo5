package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;

public class DetallesFacturaDTO extends EliminableDTOBase{
    private Integer detalle_factura_id;
    private BigDecimal subtotal;
    private String descripcion;
    private FacturasDTO factura;

    public DetallesFacturaDTO() {
        super();
        detalle_factura_id=null;
        subtotal=new BigDecimal("0.0");
        descripcion=null;
        factura=null;
    }

    public DetallesFacturaDTO(Integer detalle_factura_id, BigDecimal subtotal, String descripcion, FacturasDTO factura) {
        this.detalle_factura_id = detalle_factura_id;
        this.subtotal = subtotal;
        this.descripcion = descripcion;
        this.factura = factura;
    }
    
    public DetallesFacturaDTO(DetallesFacturaDTO detalle_factura){
        super(detalle_factura);
        this.detalle_factura_id = detalle_factura.getDetalle_factura_id();
        this.subtotal = detalle_factura.getSubtotal();
        this.descripcion = detalle_factura.getDescripcion();
        this.factura = new FacturasDTO(detalle_factura.getFactura());
    }

    /**
     * @return the detalle_factura_id
     */
    public Integer getDetalle_factura_id() {
        return detalle_factura_id;
    }

    /**
     * @param detalle_factura_id the detalle_factura_id to set
     */
    public void setDetalle_factura_id(Integer detalle_factura_id) {
        this.detalle_factura_id = detalle_factura_id;
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

    public FacturasDTO getFactura() {
        return factura;
    }

    public  void setFactura(FacturasDTO factura) {
        this.factura = factura;
    }
}
