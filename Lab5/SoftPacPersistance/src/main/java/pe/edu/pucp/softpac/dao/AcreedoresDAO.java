package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.AcreedoresDTO;

public interface AcreedoresDAO {
    public Integer insertar(AcreedoresDTO acreedor);
    
    public AcreedoresDTO obtenerPorId(Integer acreedorId);
    
    public ArrayList<AcreedoresDTO> listarTodos();
    
    public Integer modificar(AcreedoresDTO acreedor);
    
    public Integer eliminar(AcreedoresDTO acreedor);
}
