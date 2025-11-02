package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class FacturasDTO extends EliminableDTOBase{
    private Integer factura_id;
    private String numero_factura;
    private Date fecha_emision;
    private Date fecha_recepcion;
    private Date fecha_limite_pago;
    private String estado;
    private BigDecimal monto_total;
    private BigDecimal monto_igv;
    private BigDecimal monto_restante;
    private String regimen_fiscal;
    private BigDecimal tasa_iva;
    private BigDecimal otros_tributos;
    private AcreedoresDTO acreedor;
    private MonedasDTO moneda;
    private ArrayList<DetallesFacturaDTO> detalles_factura;

    public FacturasDTO() {
        super();
        factura_id=0;
        numero_factura=null;
        fecha_emision=null;
        fecha_recepcion=null;
        fecha_limite_pago=null;
        estado="I";
        monto_total=new BigDecimal("0.0");
        monto_igv=new BigDecimal("0.0");
        monto_restante=new BigDecimal("0.0");
        detalles_factura = new ArrayList<>();
    }

    public FacturasDTO(Integer factura_id, String numero_factura, Date fecha_emision,
                       Date fecha_recepcion, Date fecha_limite_pago, String estado,
                       BigDecimal monto_total, BigDecimal monto_igv, BigDecimal monto_restante,
                       String regimen_fiscal, BigDecimal tasa_iva, BigDecimal otros_tributos,
                       AcreedoresDTO acreedor, MonedasDTO moneda) {
        this.factura_id=factura_id;
        this.numero_factura = numero_factura;
        this.fecha_emision = fecha_emision;
        this.fecha_recepcion = fecha_recepcion;
        this.fecha_limite_pago = fecha_limite_pago;
        this.estado = estado;
        this.monto_total = monto_total;
        this.monto_igv = monto_igv;
        this.monto_restante = monto_restante;
        this.regimen_fiscal = regimen_fiscal;
        this.tasa_iva = tasa_iva;
        this.otros_tributos = otros_tributos;
        this.acreedor = acreedor;
        this.moneda = moneda;
        this.detalles_factura = new ArrayList<>();
    }

    public FacturasDTO(Integer factura_id, String numero_factura, Date fecha_emision,
                       Date fecha_recepcion, Date fecha_limite_pago, String estado,
                       BigDecimal monto_total, BigDecimal monto_igv, BigDecimal monto_restante,
                       String regimen_fiscal, BigDecimal tasa_iva, BigDecimal otros_tributos,
                       AcreedoresDTO acreedor, MonedasDTO moneda,  ArrayList<DetallesFacturaDTO> detalles_factura) {
        this.factura_id=factura_id;
        this.numero_factura = numero_factura;
        this.fecha_emision = fecha_emision;
        this.fecha_recepcion = fecha_recepcion;
        this.fecha_limite_pago = fecha_limite_pago;
        this.estado = estado;
        this.monto_total = monto_total;
        this.monto_igv = monto_igv;
        this.monto_restante = monto_restante;
        this.regimen_fiscal = regimen_fiscal;
        this.tasa_iva = tasa_iva;
        this.otros_tributos = otros_tributos;
        this.acreedor = acreedor;
        this.moneda = moneda;
        this.detalles_factura = detalles_factura;
    }

    public FacturasDTO(FacturasDTO factura) {
        super(factura);
        this.factura_id=factura.getFactura_id();
        this.numero_factura=factura.getNumero_factura();
        this.fecha_emision=factura.getFecha_emision();
        this.fecha_recepcion=factura.getFecha_recepcion();
        this.fecha_limite_pago=factura.getFecha_limite_pago();
        this.estado = factura.getEstado();
        this.monto_total=factura.getMonto_total();
        this.monto_igv=factura.getMonto_igv();
        this.monto_restante=factura.getMonto_restante();
        this.regimen_fiscal=factura.getRegimen_fiscal();
        this.tasa_iva=factura.getTasa_iva();
        this.otros_tributos=factura.getOtros_tributos();
        this.acreedor=factura.getAcreedor();
        this.moneda=factura.getMoneda();
        this.detalles_factura=factura.getDetalles_Factura();
    }

    public Integer getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(Integer factura_id) {
        this.factura_id = factura_id;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public Date getFecha_limite_pago() {
        return fecha_limite_pago;
    }

    public void setFecha_limite_pago(Date fecha_limite_pago) {
        this.fecha_limite_pago = fecha_limite_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(BigDecimal monto_total) {
        this.monto_total = monto_total;
    }

    public BigDecimal getMonto_igv() {
        return monto_igv;
    }

    public void setMonto_igv(BigDecimal monto_igv) {
        this.monto_igv = monto_igv;
    }

    public BigDecimal getMonto_restante() {
        return monto_restante;
    }

    public void setMonto_restante(BigDecimal monto_restante) {
        this.monto_restante = monto_restante;
    }

    public String getRegimen_fiscal() {
        return regimen_fiscal;
    }

    public void setRegimen_fiscal(String regimen_fiscal) {
        this.regimen_fiscal = regimen_fiscal;
    }

    public BigDecimal getTasa_iva() {
        return tasa_iva;
    }

    public void setTasa_iva(BigDecimal tasa_iva) {
        this.tasa_iva = tasa_iva;
    }

    public BigDecimal getOtros_tributos() {
        return otros_tributos;
    }

    public void setOtros_tributos(BigDecimal otros_tributos) {
        this.otros_tributos = otros_tributos;
    }

    public AcreedoresDTO getAcreedor() {
        return acreedor;
    }

    public void setAcreedor(AcreedoresDTO acreedor) {
        this.acreedor = acreedor;
    }

    public MonedasDTO getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedasDTO moneda) {
        this.moneda = moneda;
    }
    
    public DetallesFacturaDTO getDetalle_Factura(Integer index) {
        return detalles_factura.get(index);
    }

    public void addDetalle_Factura(DetallesFacturaDTO tipo){
        if(detalles_factura==null){
            detalles_factura=new ArrayList<>();
        }
        this.detalles_factura.add(tipo);
    }

    public ArrayList<DetallesFacturaDTO> getDetalles_Factura(){
        return detalles_factura;
    }
    public void setDetalles_Factura(ArrayList<DetallesFacturaDTO> detalles_factura){
        this.detalles_factura=detalles_factura;
    }
}
