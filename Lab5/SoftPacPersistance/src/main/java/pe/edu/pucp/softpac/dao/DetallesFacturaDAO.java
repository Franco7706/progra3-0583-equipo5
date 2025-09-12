package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.DetallesFacturaDTO;

public interface DetallesFacturaDAO {
    
    public Integer insertar(DetallesFacturaDTO detalleFactura);
    
    public DetallesFacturaDTO obtenerPorId(Integer detalleFacturaId);
    
    public ArrayList<DetallesFacturaDTO> listarTodos();
    
    public Integer modificar(DetallesFacturaDTO detalleFactura);
    
    public Integer eliminar(DetallesFacturaDTO detalleFactura);
}
