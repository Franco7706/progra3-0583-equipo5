package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.softpac.model.PropuestasPagoDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public interface PropuestasPagoDAO {
    public Integer insertar(PropuestasPagoDTO propuestaPago);
    
    public PropuestasPagoDTO obtenerPorId(Integer propuestaPagoId);
    
    public List<PropuestasPagoDTO> listarTodos();
    
    //public List<PropuestasPagoDTO> listarConPais();
    
    public Integer modificar(PropuestasPagoDTO propuestaPago);
    
    public Integer eliminar(PropuestasPagoDTO propuestaPago);

    public Integer eliminarLogico(PropuestasPagoDTO propuestaPago);
}
