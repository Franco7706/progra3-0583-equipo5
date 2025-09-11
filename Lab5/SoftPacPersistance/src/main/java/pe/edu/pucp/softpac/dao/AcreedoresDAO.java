package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.AcreedoresDTO;

public interface AcreedoresDAO {
    public Integer insertar(AcreedoresDTO almacen);
    
    public AcreedoresDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<AcreedoresDTO> listarTodos();
    
    public Integer modificar(AcreedoresDTO almacen);
    
    public Integer eliminar(AcreedoresDTO almacen);
}
