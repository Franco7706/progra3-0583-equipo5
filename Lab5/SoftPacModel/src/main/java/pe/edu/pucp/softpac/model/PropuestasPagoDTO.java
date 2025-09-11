package pe.edu.pucp.softpac.model;

import java.util.Date;

public class PropuestasPagoDTO {
    private String propuesta_id;
    private Date fecha_hora_creacion;
    private MonedasDTO moneda;
    private EntidadesBancariasDTO entidad_bancaria;

    public PropuestasPagoDTO() {
        propuesta_id=null;
        fecha_hora_creacion=null;
        entidad_bancaria=new EntidadesBancariasDTO();
        moneda=new MonedasDTO();
        
    }

    public PropuestasPagoDTO(String propuesta_id, Date fecha_hora_creacion, EntidadesBancariasDTO entidad_bancaria, MonedasDTO moneda) {
        this.propuesta_id = propuesta_id;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.entidad_bancaria = new EntidadesBancariasDTO(entidad_bancaria);
        this.moneda = moneda;
    }
    
    public PropuestasPagoDTO(PropuestasPagoDTO propuesta_pago){
        this.propuesta_id = propuesta_pago.getPropuesta_id();
        this.fecha_hora_creacion = propuesta_pago.getFecha_hora_creacion();
        this.entidad_bancaria = new EntidadesBancariasDTO(propuesta_pago.getEntidad_bancaria());
        this.moneda = propuesta_pago.getMoneda();
    }
    /**
     * @return the propuesta_id
     */
    public String getPropuesta_id() {
        return propuesta_id;
    }

    /**
     * @param propuesta_id the propuesta_id to set
     */
    public void setPropuesta_id(String propuesta_id) {
        this.propuesta_id = propuesta_id;
    }

    /**
     * @return the fecha_hora_creacion
     */
    public Date getFecha_hora_creacion() {
        return fecha_hora_creacion;
    }

    /**
     * @param fecha_hora_creacion the fecha_hora_creacion to set
     */
    public void setFecha_hora_creacion(Date fecha_hora_creacion) {
        this.fecha_hora_creacion = fecha_hora_creacion;
    }
    
    /**
     * @return the entidad_bancaria_id
     */
    public EntidadesBancariasDTO getEntidad_bancaria() {
        return entidad_bancaria;
    }

    /**
     * @param entidad_bancaria the entidad_bancaria_id to set
     */
    public void setEntidad_bancaria(EntidadesBancariasDTO entidad_bancaria) {
        this.entidad_bancaria = entidad_bancaria;
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
    
    
}
