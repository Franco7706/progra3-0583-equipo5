package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;
import java.util.Date;

public class FacturasDTO {
    private String factura_id;
    private BigDecimal monto_total;
    private MonedasDTO moneda;
    private Date fecha_emision;
    private Date fecha_limite_pago;
    private Date fecha_recepcion;
    private String motivo;
    private AcreedoresDTO acreedor;
    private BigDecimal monto_restante;
    private BigDecimal igv;

    public FacturasDTO() {
        factura_id=null;
        moneda = new MonedasDTO();
        fecha_emision=null;
        fecha_limite_pago=null;
        fecha_recepcion=null;
        motivo=null;
        acreedor=new AcreedoresDTO();
        igv=new BigDecimal("0.0");
        monto_total=new BigDecimal("0.0");
        monto_restante=new BigDecimal("0.0");
    }

    public FacturasDTO(String factura_id, BigDecimal monto_total, MonedasDTO moneda, Date fecha_emision, Date fecha_limite_pago, Date fecha_recepcion, String motivo, AcreedoresDTO acreedor, BigDecimal monto_restante, BigDecimal igv) {
        this.factura_id = factura_id;
        this.monto_total = monto_total;
        this.moneda = new MonedasDTO(moneda);
        this.fecha_emision = fecha_emision;
        this.fecha_limite_pago = fecha_limite_pago;
        this.fecha_recepcion = fecha_recepcion;
        this.motivo = motivo;
        this.acreedor = acreedor;
        this.monto_restante = monto_restante;
        this.igv = igv;
    }
    
    public FacturasDTO(FacturasDTO factura){
        this.factura_id = factura.getFactura_id();
        this.monto_total = factura.getMonto_total();
        this.moneda = new MonedasDTO(factura.getMoneda());
        this.fecha_emision = factura.getFecha_emision();
        this.fecha_limite_pago = factura.getFecha_limite_pago();
        this.fecha_recepcion = factura.getFecha_recepcion();
        this.motivo = factura.getMotivo();
        this.acreedor = new AcreedoresDTO(factura.getProveedor());
        this.monto_restante = factura.getMonto_restante();
        this.igv = factura.getIgv();
    }
    
    /**
     * @return the factura_id
     */
    public String getFactura_id() {
        return factura_id;
    }

    /**
     * @param factura_id the factura_id to set
     */
    public void setFactura_id(String factura_id) {
        this.factura_id = factura_id;
    }

    /**
     * @return the monto_total
     */
    public BigDecimal getMonto_total() {
        return monto_total;
    }

    /**
     * @param monto_total the monto_total to set
     */
    public void setMonto_total(BigDecimal monto_total) {
        this.monto_total = monto_total;
    }

    /**
     * @return moneda
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
     * @return the fecha_emision
     */
    public Date getFecha_emision() {
        return fecha_emision;
    }

    /**
     * @param fecha_emision the fecha_emision to set
     */
    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    /**
     * @return the fecha_limite_pago
     */
    public Date getFecha_limite_pago() {
        return fecha_limite_pago;
    }

    /**
     * @param fecha_limite_pago the fecha_limite_pago to set
     */
    public void setFecha_limite_pago(Date fecha_limite_pago) {
        this.fecha_limite_pago = fecha_limite_pago;
    }

    /**
     * @return the fecha_recepcion
     */
    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    /**
     * @param fecha_recepcion the fecha_recepcion to set
     */
    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the acreedor
     */
    public AcreedoresDTO getProveedor() {
        return acreedor;
    }

    /**
     * @param acreedor the acreedor to set
     */
    public void setProveedor(AcreedoresDTO acreedor) {
        this.acreedor = acreedor;
    }

    /**
     * @return the monto_restante
     */
    public BigDecimal getMonto_restante() {
        return monto_restante;
    }

    /**
     * @param monto_restante the monto_restante to set
     */
    public void setMonto_restante(BigDecimal monto_restante) {
        this.monto_restante = monto_restante;
    }

    /**
     * @return the igv
     */
    public BigDecimal getIgv() {
        return igv;
    }

    /**
     * @param igv the igv to set
     */
    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }
    
    
}
