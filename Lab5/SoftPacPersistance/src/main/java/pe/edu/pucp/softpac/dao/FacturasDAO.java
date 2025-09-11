package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.FacturasDTO;

public interface FacturasDAO {
    public Integer insertar(FacturasDTO almacen);
    
    public FacturasDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<FacturasDTO> listarTodos();
    
    public Integer modificar(FacturasDTO almacen);
    
    public Integer eliminar(FacturasDTO almacen);
}
