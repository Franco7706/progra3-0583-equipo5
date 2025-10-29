package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public interface AcreedoresDAO {
    public Integer insertar(AcreedoresDTO acreedor);
    
    public AcreedoresDTO obtenerPorId(Integer acreedorId);
    
    public List<AcreedoresDTO> listarTodos();
    
    public Integer modificar(AcreedoresDTO acreedor);
    
    public Integer eliminar(AcreedoresDTO acreedor);

    public Integer eliminarLogico(AcreedoresDTO acreedor);
}
