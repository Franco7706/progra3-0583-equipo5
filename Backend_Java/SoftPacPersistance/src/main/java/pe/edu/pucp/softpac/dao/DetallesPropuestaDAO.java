package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public interface DetallesPropuestaDAO {
    public Integer insertar(DetallesPropuestaDTO detallePropuesta);

    public DetallesPropuestaDTO obtenerPorId(Integer detallePropuestaId);

    public Integer modificar(DetallesPropuestaDTO detallePropuesta);

    public Integer eliminarPorPropuesta(Integer propuestaId);

    public Integer eliminarLogico(DetallesPropuestaDTO detallePropuesta);
}
