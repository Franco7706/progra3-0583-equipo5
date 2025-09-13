package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.AcreedoresDAO;
import pe.edu.pucp.softpac.model.AcreedoresDTO;

public class AcreedoresDAOImpl implements AcreedoresDAO {

    @Override
    public Integer insertar(AcreedoresDTO acreedor) {
        return 0;
    }

    @Override
    public AcreedoresDTO obtenerPorId(Integer acreedorId) {
        return null;
    }

    @Override
    public ArrayList<AcreedoresDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(AcreedoresDTO acreedor) {
        return 0;
    }

    @Override
    public Integer eliminar(AcreedoresDTO acreedor) {
        return 0;
    }
}
