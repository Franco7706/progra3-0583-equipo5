package pe.edu.pucp.softpac.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PropuestasPagoDTO {
    private Integer propuesta_id;
    private LocalDateTime fecha_hora_creacion;
    private String estado;
    private EntidadesBancariasDTO entidad_bancaria;
    private ArrayList<DetallesPropuestaDTO> detalles_propuesta;

    public PropuestasPagoDTO() {
        propuesta_id=0;
        fecha_hora_creacion=null;
        estado="I";
        entidad_bancaria=new EntidadesBancariasDTO();
        detalles_propuesta=new ArrayList<>();
    }
    public PropuestasPagoDTO(Integer propuesta_id, LocalDateTime fecha_hora_creacion, String estado,
                             EntidadesBancariasDTO entidad_bancaria) {
        this.propuesta_id=propuesta_id;
        this.fecha_hora_creacion=fecha_hora_creacion;
        this.estado=estado;
        this.entidad_bancaria=entidad_bancaria;
        this.detalles_propuesta=new ArrayList<>();
    }
    public PropuestasPagoDTO(Integer propuesta_id, LocalDateTime fecha_hora_creacion, String estado,
                             EntidadesBancariasDTO entidad_bancaria, ArrayList<DetallesPropuestaDTO> detalles_propuesta) {
        this.propuesta_id=propuesta_id;
        this.fecha_hora_creacion=fecha_hora_creacion;
        this.estado=estado;
        this.entidad_bancaria=entidad_bancaria;
        this.detalles_propuesta=detalles_propuesta;
    }
    public PropuestasPagoDTO(PropuestasPagoDTO propuestaPago) {
        this.propuesta_id=propuestaPago.getPropuesta_id();
        this.fecha_hora_creacion=propuestaPago.getFecha_hora_creacion();
        this.estado=propuestaPago.getEstado();
        this.entidad_bancaria=new EntidadesBancariasDTO(entidad_bancaria);
        this.detalles_propuesta=propuestaPago.getDetalles_propuesta();
    }
    public Integer getPropuesta_id() {
        return propuesta_id;
    }

    public void setPropuesta_id(Integer propuesta_id) {
        this.propuesta_id = propuesta_id;
    }

    public LocalDateTime getFecha_hora_creacion() {
        return fecha_hora_creacion;
    }

    public void setFecha_hora_creacion(LocalDateTime fecha_hora_creacion) {
        this.fecha_hora_creacion = fecha_hora_creacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EntidadesBancariasDTO getEntidad_bancaria() {
        return entidad_bancaria;
    }

    public void setEntidad_bancaria(EntidadesBancariasDTO entidad_bancaria) {
        this.entidad_bancaria = entidad_bancaria;
    }

    public DetallesPropuestaDTO getDetalle_Propuesta(Integer index) {
        return detalles_propuesta.get(index);
    }

    public void addDetalle_Propuesta(DetallesPropuestaDTO tipo){
        if(detalles_propuesta==null){
            detalles_propuesta=new ArrayList<>();
        }
        this.detalles_propuesta.add(tipo);
    }

    public ArrayList<DetallesPropuestaDTO> getDetalles_propuesta() {
        return detalles_propuesta;
    }

    public void setDetalles_propuesta(ArrayList<DetallesPropuestaDTO> detalles_propuesta) {
        this.detalles_propuesta = detalles_propuesta;
    }
}
