package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;

public interface CuentasAcreedorDAO {
    public Integer insertar(CuentasAcreedorDTO cuentaAcreedor);
    
    public CuentasAcreedorDTO obtenerPorId(Integer cuentaAcreedorId);
    
    public ArrayList<CuentasAcreedorDTO> listarTodos();
    
    public Integer modificar(CuentasAcreedorDTO cuentaAcreedor);
    
    public Integer eliminar(CuentasAcreedorDTO cuentaAcreedor);
}
