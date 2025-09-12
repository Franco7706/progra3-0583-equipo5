package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.FacturasDTO;

public interface FacturasDAO {
    public Integer insertar(FacturasDTO factura);
    
    public FacturasDTO obtenerPorId(Integer facturaId);
    
    public ArrayList<FacturasDTO> listarTodos();
    
    public Integer modificar(FacturasDTO factura);
    
    public Integer eliminar(FacturasDTO factura);
}
