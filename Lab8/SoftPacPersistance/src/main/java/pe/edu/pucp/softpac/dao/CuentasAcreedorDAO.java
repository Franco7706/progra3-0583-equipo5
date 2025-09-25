package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public interface CuentasAcreedorDAO {
    public Integer insertar(CuentasAcreedorDTO cuentaAcreedor);
    
    public CuentasAcreedorDTO obtenerPorId(Integer cuentaAcreedorId);
    
    public ArrayList<CuentasAcreedorDTO> listarTodos();
    
    public Integer modificar(CuentasAcreedorDTO cuentaAcreedor);
    
    public Integer eliminar(CuentasAcreedorDTO cuentaAcreedor);

    public Integer eliminarLogico(CuentasAcreedorDTO cuentaAcreedor);
}
