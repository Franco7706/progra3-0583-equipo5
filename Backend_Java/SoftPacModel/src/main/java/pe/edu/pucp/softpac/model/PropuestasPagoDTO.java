package pe.edu.pucp.softpac.model;

import java.util.Date;
import java.util.ArrayList;

public class PropuestasPagoDTO extends EliminableDTOBase{
    private Integer propuesta_id;
    private UsuariosDTO usuario_creacion;
    private Date fecha_hora_creacion;
    private String estado;
    private EntidadesBancariasDTO entidad_bancaria;
    private ArrayList<DetallesPropuestaDTO> detalles_propuesta;
    private Date fecha_hora_modificacion;
    private UsuariosDTO usuario_modificacion;

    public PropuestasPagoDTO() {
        super();
        propuesta_id=0;
        fecha_hora_creacion=null;
        usuario_creacion=null;
        estado="I";
        entidad_bancaria=new EntidadesBancariasDTO();
        detalles_propuesta=new ArrayList<>();
        fecha_hora_modificacion=null;
        usuario_modificacion=null;
    }
    public PropuestasPagoDTO(Integer propuesta_id, Date fecha_hora_creacion,UsuariosDTO usuario_creacion, String estado,
                             EntidadesBancariasDTO entidad_bancaria, Date fecha_hora_modificacion, UsuariosDTO usuario_modificacion) {
        this.propuesta_id=propuesta_id;
        this.fecha_hora_creacion=fecha_hora_creacion;
        this.usuario_creacion=new UsuariosDTO(usuario_creacion);
        this.estado=estado;
        this.entidad_bancaria=entidad_bancaria;
        this.detalles_propuesta=new ArrayList<>();
        this.fecha_hora_modificacion=fecha_hora_modificacion;
        this.usuario_modificacion=new UsuariosDTO(usuario_modificacion);
    }
    public PropuestasPagoDTO(Integer propuesta_id, Date fecha_hora_creacion,UsuariosDTO usuario_creacion, String estado,
                             EntidadesBancariasDTO entidad_bancaria, ArrayList<DetallesPropuestaDTO> detalles_propuesta,
                             Date fecha_hora_modificacion, UsuariosDTO usuario_modificacion) {
        this.propuesta_id=propuesta_id;
        this.fecha_hora_creacion=fecha_hora_creacion;
        this.usuario_creacion=new UsuariosDTO(usuario_modificacion);
        this.estado=estado;
        this.entidad_bancaria=entidad_bancaria;
        this.detalles_propuesta=detalles_propuesta;
        this.fecha_hora_modificacion=fecha_hora_creacion;
        this.usuario_modificacion=new UsuariosDTO(usuario_modificacion);
    }
    public PropuestasPagoDTO(PropuestasPagoDTO propuestaPago) {
        super(propuestaPago);
        this.propuesta_id=propuestaPago.getPropuesta_id();
        this.fecha_hora_creacion=propuestaPago.getFecha_hora_creacion();
        this.usuario_creacion=new UsuariosDTO(propuestaPago.getUsuario_creacion());
        this.estado=propuestaPago.getEstado();
        this.entidad_bancaria=new EntidadesBancariasDTO(propuestaPago.getEntidad_bancaria());
        this.detalles_propuesta=propuestaPago.getDetalles_propuesta();
        this.fecha_hora_modificacion=propuestaPago.getFecha_hora_modificacion();
        this.usuario_modificacion=new UsuariosDTO(propuestaPago.getUsuario_modificacion());
    }
    public Integer getPropuesta_id() {
        return propuesta_id;
    }

    public void setPropuesta_id(Integer propuesta_id) {
        this.propuesta_id = propuesta_id;
    }

    public Date getFecha_hora_creacion() {
        return fecha_hora_creacion;
    }

    public void setFecha_hora_creacion(Date fecha_hora_creacion) {
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

    /**
     * @return the usuario_creacion
     */
    public UsuariosDTO getUsuario_creacion() {
        return usuario_creacion;
    }

    /**
     * @param usuario_creacion the usuario_creacion to set
     */
    public void setUsuario_creacion(UsuariosDTO usuario_creacion) {
        this.usuario_creacion = usuario_creacion;
    }

    /**
     * @return the fecha_hora_modificacion
     */
    public Date getFecha_hora_modificacion() {
        return fecha_hora_modificacion;
    }

    /**
     * @param fecha_hora_modificacion the fecha_hora_modificacion to set
     */
    public void setFecha_hora_modificacion(Date fecha_hora_modificacion) {
        this.fecha_hora_modificacion = fecha_hora_modificacion;
    }

    /**
     * @return the usuario_modificacion
     */
    public UsuariosDTO getUsuario_modificacion() {
        return usuario_modificacion;
    }

    /**
     * @param usuario_modificacion the usuario_modificacion to set
     */
    public void setUsuario_modificacion(UsuariosDTO usuario_modificacion) {
        this.usuario_modificacion = usuario_modificacion;
    }
}
