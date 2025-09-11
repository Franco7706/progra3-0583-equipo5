package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;

public interface CuentasPropiasDAO {
    public Integer insertar(CuentasPropiasDTO almacen);
    
    public CuentasPropiasDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<CuentasPropiasDTO> listarTodos();
    
    public Integer modificar(CuentasPropiasDTO almacen);
    
    public Integer eliminar(CuentasPropiasDTO almacen);
}
