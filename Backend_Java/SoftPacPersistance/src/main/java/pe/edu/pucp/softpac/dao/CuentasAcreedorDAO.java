package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;

public interface CuentasAcreedorDAO {
    public Integer insertar(CuentasAcreedorDTO cuentaAcreedor);
    
    public CuentasAcreedorDTO obtenerPorId(Integer cuentaAcreedorId);
    
    public List<CuentasAcreedorDTO> listarTodos();
    
    public Integer modificar(CuentasAcreedorDTO cuentaAcreedor);
    
    public Integer eliminar(CuentasAcreedorDTO cuentaAcreedor);

    public Integer eliminarLogico(CuentasAcreedorDTO cuentaAcreedor);

    public ArrayList<CuentasAcreedorDTO> obtenerPorAcreedor(Integer acreedor_id);
}
