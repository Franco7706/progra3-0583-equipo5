package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;

public interface DetallesPropuestaDAO {
    public Integer insertar(DetallesPropuestaDTO detallePropuesta);

    public Integer modificar(DetallesPropuestaDTO detallePropuesta);

    public Integer eliminarPorPropuesta(Integer propuestaId);

    public Integer eliminarLogico(DetallesPropuestaDTO detallePropuesta);
}
