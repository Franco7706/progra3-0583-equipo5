package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public interface CuentasPropiasDAO {
    public Integer insertar(CuentasPropiasDTO cuentaPropia);
    
    public CuentasPropiasDTO obtenerPorId(Integer cuentaPropiaId);
    
    public List<CuentasPropiasDTO> listarTodos();
    
    public Integer modificar(CuentasPropiasDTO cuentaPropia);
    
    public Integer eliminar(CuentasPropiasDTO cuentaPropia);

    public Integer eliminarLogico(CuentasPropiasDTO cuentaPropia);
}
