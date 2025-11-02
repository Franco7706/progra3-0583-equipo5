package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.softpac.model.FacturasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public interface FacturasDAO {
    public Integer insertar(FacturasDTO factura);
    
    public FacturasDTO obtenerPorId(Integer facturaId);
    
    public List<FacturasDTO> listarTodos();
    
    public Integer modificar(FacturasDTO factura);
    
    public Integer eliminar(FacturasDTO factura);

    public Integer eliminarLogico(FacturasDTO factura);
    
    public List<FacturasDTO>listarFiltros();
    
}
