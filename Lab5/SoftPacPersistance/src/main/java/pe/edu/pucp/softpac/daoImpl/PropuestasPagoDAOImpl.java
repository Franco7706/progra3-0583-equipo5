package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.PropuestasPagoDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;

public class PropuestasPagoDAOImpl extends DAOImplBase implements PropuestasPagoDAO {

    private PropuestasPagoDTO propuestaPago;

    public PropuestasPagoDAOImpl() {
        super("PA_PROPUESTAS_DE_PAGO");
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("PROPUESTA_PAGO_ID", true, true ));
        this.listaColumnas.add(new Columna("FECHA_HORA_CREACION", false, false));
        this.listaColumnas.add(new Columna("ESTADO", false, false));
        this.listaColumnas.add(new Columna("ENTIDAD_BANCARIA_ID", false, false));
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
