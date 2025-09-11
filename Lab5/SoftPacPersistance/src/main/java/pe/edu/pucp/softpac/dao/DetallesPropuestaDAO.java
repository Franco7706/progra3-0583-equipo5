package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;

public interface DetallesPropuestaDAO {
    public Integer insertar(DetallesPropuestaDTO almacen);
    
    public DetallesPropuestaDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<DetallesPropuestaDTO> listarTodos();
    
    public Integer modificar(DetallesPropuestaDTO almacen);
    
    public Integer eliminar(DetallesPropuestaDTO almacen);
}
