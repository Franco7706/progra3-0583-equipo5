package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.DetallesFacturaDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public interface DetallesFacturaDAO {
    public Integer insertar(DetallesFacturaDTO detallePropuesta);

    public DetallesFacturaDTO obtenerPorId(Integer detallePropuestaId);

    public Integer modificar(DetallesFacturaDTO detallePropuesta);

    public Integer eliminarPorFactura(Integer facturaId);

    public Integer eliminarLogico(DetallesFacturaDTO detalleFactura);
}
