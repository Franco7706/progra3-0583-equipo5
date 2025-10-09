package pe.edu.pucp.softpac.bo;

import java.util.ArrayList;
import java.util.Comparator;
import pe.edu.pucp.softpac.dao.PropuestasPagoDAO;
import pe.edu.pucp.softpac.daoImpl.PropuestasPagoDAOImpl;
import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;

public class PropuestaPagoBO {
    
    private PropuestasPagoDAO propuestasDAO;
    
    public PropuestaPagoBO(){
        propuestasDAO = new PropuestasPagoDAOImpl();
    }
    
    public Integer insertar (PropuestasPagoDTO propuestaPago){
        return propuestasDAO.insertar(propuestaPago);
    }
    
    public Integer modificar (PropuestasPagoDTO propuestaPago){
        return propuestasDAO.modificar(propuestaPago);
    }
    
    public Integer insertarDetalle(DetallesPropuestaDTO detalle){
        return propuestasDAO.insertar(detalle.getPropuesta_pago());
    }
    
    public Integer modificarDetalle(DetallesPropuestaDTO detalle){
        return propuestasDAO.modificar(detalle.getPropuesta_pago());
    }
    
    
    public ArrayList<PropuestasPagoDTO> listarUltimasPorUsuario(Integer usuarioId, Integer cantidad){
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();
        ArrayList<PropuestasPagoDTO> propuestasUltimas = new ArrayList<>();
        
        for(PropuestasPagoDTO propuesta: propuestas){
            if(propuesta.getUsuario_creacion() != null && propuesta.getUsuario_creacion().getUsuario_id() == usuarioId){
                propuestasUltimas.add(propuesta);
            }
        }
        
        
        //Ordenamos por las fechas más recientes
        propuestasUltimas.sort(Comparator.comparing(PropuestasPagoDTO::getFecha_hora_creacion).reversed());
        
        // Limitar cantidad
        if (propuestasUltimas.size() > cantidad) {
            return new ArrayList<>(propuestasUltimas.subList(0, cantidad));
        }
          
        return propuestasUltimas;
    
    }
    
    public ArrayList<PropuestasPagoDTO> listarTodasPorUsuario(Integer usuarioId){
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();
        ArrayList<PropuestasPagoDTO> propuestasUsuarios = new ArrayList<>();
        
        for(PropuestasPagoDTO propuesta: propuestas){
            if(propuesta.getUsuario_creacion() != null && propuesta.getUsuario_creacion().getUsuario_id() == usuarioId){
                propuestasUsuarios.add(propuesta);
            }
        }
        
        return propuestasUsuarios;
    }
    
    public ArrayList<PropuestasPagoDTO> listarActividadPorUsuario(Integer usuarioId){
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio");
        }

        ArrayList<PropuestasPagoDTO> propuestasActividad = new ArrayList<>();
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();

        for(PropuestasPagoDTO propuesta : propuestas){
            boolean tieneActividad = false;

            if(propuesta.getUsuario_creacion() != null && 
               propuesta.getUsuario_creacion().getUsuario_id().equals(usuarioId)){
                tieneActividad = true;
            }

            if(propuesta.getUsuario_modificacion() != null && 
               propuesta.getUsuario_modificacion().getUsuario_id().equals(usuarioId)){
                tieneActividad = true;
            }

            if(propuesta.getUsuario_eliminacion() != null && 
               propuesta.getUsuario_eliminacion().getUsuario_id().equals(usuarioId)){
                tieneActividad = true;
            }

            if(tieneActividad){
                propuestasActividad.add(propuesta);
            }
        }

        return propuestasActividad;
    }   
    
}
