package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.DetallesFacturaDTO;

public interface DetallesFacturaDAO {
    
    public Integer insertar(DetallesFacturaDTO almacen);
    
    public DetallesFacturaDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<DetallesFacturaDTO> listarTodos();
    
    public Integer modificar(DetallesFacturaDTO almacen);
    
    public Integer eliminar(DetallesFacturaDTO almacen);
}
