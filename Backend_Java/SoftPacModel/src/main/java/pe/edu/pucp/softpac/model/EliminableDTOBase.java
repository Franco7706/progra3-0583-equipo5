
package pe.edu.pucp.softpac.model;

import java.util.Date;

public abstract class EliminableDTOBase {
    
    private UsuariosDTO usuario_eliminacion;
    private Date fecha_eliminacion;
    
    public EliminableDTOBase(){
        usuario_eliminacion = null;
        fecha_eliminacion = null;
    }
    
    public EliminableDTOBase(EliminableDTOBase eliminable){
        this.fecha_eliminacion = eliminable.getFecha_eliminacion();
        this.usuario_eliminacion = eliminable.getUsuario_eliminacion();
    }
    
    /**
     * @return the usuario_eliminacion
     */
    public UsuariosDTO getUsuario_eliminacion() {
        return usuario_eliminacion;
    }

    /**
     * @param usuario_eliminacion the usuario_eliminacion to set
     */
    public void setUsuario_eliminacion(UsuariosDTO usuario_eliminacion) {
        this.usuario_eliminacion = usuario_eliminacion;
    }

    /**
     * @return the fecha_eliminacion
     */
    public Date getFecha_eliminacion() {
        return fecha_eliminacion;
    }

    /**
     * @param fecha_eliminacion the fecha_eliminacion to set
     */
    public void setFecha_eliminacion(Date fecha_eliminacion) {
        this.fecha_eliminacion = fecha_eliminacion;
    }
     
    
}
