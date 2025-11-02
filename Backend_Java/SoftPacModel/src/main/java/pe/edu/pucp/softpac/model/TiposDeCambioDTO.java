package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;
import java.util.Date;

public class TiposDeCambioDTO {
    private Integer tipo_cambio_id;
    private Date fecha;
    private BigDecimal tasa_de_cambio;
    private MonedasDTO moneda_origen;
    private MonedasDTO moneda_destino;

    public TiposDeCambioDTO(){
        tipo_cambio_id=null;
        fecha=null;
        tasa_de_cambio=null;
        moneda_origen=null;
        moneda_destino=null;
    }

    public TiposDeCambioDTO(Integer tipo_cambio_id, Date fecha, BigDecimal tasa_de_cambio){
        this.tipo_cambio_id=tipo_cambio_id;
        this.fecha=fecha;
        this.tasa_de_cambio=tasa_de_cambio;
        moneda_origen=null;
        moneda_destino=null;
    }
    public TiposDeCambioDTO(Integer tipo_cambio_id, Date fecha, BigDecimal tasa_de_cambio, MonedasDTO moneda_origen, MonedasDTO moneda_destino){
        this.tipo_cambio_id=tipo_cambio_id;
        this.fecha=fecha;
        this.tasa_de_cambio=tasa_de_cambio;
        this.moneda_origen=moneda_origen;
        this.moneda_destino=moneda_destino;
    }
    public TiposDeCambioDTO(TiposDeCambioDTO tipo_de_cambio){
        this.tipo_cambio_id=tipo_de_cambio.tipo_cambio_id;
        this.fecha=tipo_de_cambio.fecha;
        this.tasa_de_cambio=tipo_de_cambio.tasa_de_cambio;
        this.moneda_origen=new MonedasDTO(tipo_de_cambio.moneda_origen);
        this.moneda_destino=new MonedasDTO(tipo_de_cambio.moneda_destino);
    }

    public Integer getTipo_cambio_id() {
        return tipo_cambio_id;
    }

    public void setTipo_cambio_id(Integer tipo_cambio_id) {
        this.tipo_cambio_id = tipo_cambio_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTasa_de_cambio() {
        return tasa_de_cambio;
    }

    public void setTasa_de_cambio(BigDecimal tasa_de_cambio) {
        this.tasa_de_cambio = tasa_de_cambio;
    }

    public MonedasDTO getMoneda_origen() {
        return moneda_origen;
    }

    public void setMoneda_origen(MonedasDTO moneda_origen) {
        this.moneda_origen = moneda_origen;
    }

    public MonedasDTO getMoneda_destino() {
        return moneda_destino;
    }

    public void setMoneda_destino(MonedasDTO moneda_destino) {
        this.moneda_destino = moneda_destino;
    }
}
