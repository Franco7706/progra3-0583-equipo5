package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;

public interface DetallesPropuestaDAO {
    public Integer insertar(DetallesPropuestaDTO detallePropuesta);
    
    public DetallesPropuestaDTO obtenerPorId(Integer detallePropuestaId);
    
    public ArrayList<DetallesPropuestaDTO> listarTodos();
    
    public Integer modificar(DetallesPropuestaDTO detallePropuesta);
    
    public Integer eliminar(DetallesPropuestaDTO detallePropuesta);
}
