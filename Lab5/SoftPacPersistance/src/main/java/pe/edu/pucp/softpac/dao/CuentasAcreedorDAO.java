package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;

public interface CuentasAcreedorDAO {
    public Integer insertar(CuentasAcreedorDTO almacen);
    
    public CuentasAcreedorDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<CuentasAcreedorDTO> listarTodos();
    
    public Integer modificar(CuentasAcreedorDTO almacen);
    
    public Integer eliminar(CuentasAcreedorDTO almacen);
}
