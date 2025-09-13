package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.FacturasDAO;
import pe.edu.pucp.softpac.model.FacturasDTO;

public class FacturasDAOImpl implements FacturasDAO {

    @Override
    public Integer insertar(FacturasDTO factura) {
        return 0;
    }

    @Override
    public FacturasDTO obtenerPorId(Integer facturaId) {
        return null;
    }

    @Override
    public ArrayList<FacturasDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(FacturasDTO factura) {
        return 0;
    }

    @Override
    public Integer eliminar(FacturasDTO factura) {
        return 0;
    }
}
