package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.PropuestasPagoDAO;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;

public class PropuestasPagoDAOImpl extends DAOImplBase implements PropuestasPagoDAO {

    private PropuestasPagoDTO propuestaPago;

    public PropuestasPagoDAOImpl() {
        super("");
    }

    @Override
    protected void configurarListaDeColumnas() {

    }

    @Override
    public Integer insertar(PropuestasPagoDTO propuestaPago) {
        return 0;
    }

    @Override
    public PropuestasPagoDTO obtenerPorId(Integer propuestaPagoId) {
        return null;
    }

    @Override
    public ArrayList<PropuestasPagoDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(PropuestasPagoDTO propuestaPago) {
        return 0;
    }

    @Override
    public Integer eliminar(PropuestasPagoDTO propuestaPago) {
        return 0;
    }
}
