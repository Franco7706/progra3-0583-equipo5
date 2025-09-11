package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;

public interface PropuestasPagoDAO {
    public Integer insertar(PropuestasPagoDTO almacen);
    
    public PropuestasPagoDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<PropuestasPagoDTO> listarTodos();
    
    public Integer modificar(PropuestasPagoDTO almacen);
    
    public Integer eliminar(PropuestasPagoDTO almacen);
}
