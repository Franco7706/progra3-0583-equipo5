package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.DetallesPropuestaDAO;
import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;

public class DetallesPropuestaDAOImpl extends DAOImplBase implements DetallesPropuestaDAO {

    private DetallesPropuestaDTO detallesPropuestaDTO;

    public DetallesPropuestaDAOImpl() {
        super("PA_DETALLES_PROPUESTA");
        this.retornarLlavePrimaria=true;
    }

    @Override
    protected void configurarListaDeColumnas() {

    }

    @Override
    public Integer insertar(DetallesPropuestaDTO detallePropuesta) {
        return 0;
    }

    @Override
    public DetallesPropuestaDTO obtenerPorId(Integer detallePropuestaId) {
        return null;
    }

    @Override
    public Integer modificar(DetallesPropuestaDTO detallePropuesta) {
        return 0;
    }

    @Override
    public Integer eliminar(DetallesPropuestaDTO detallePropuesta) {
        return 0;
    }
}
