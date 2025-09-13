package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.CuentasAcreedorDAO;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;

public class CuentasAcreedorDAOImpl implements CuentasAcreedorDAO {

    @Override
    public Integer insertar(CuentasAcreedorDTO cuentaAcreedor) {
        return 0;
    }

    @Override
    public CuentasAcreedorDTO obtenerPorId(Integer cuentaAcreedorId) {
        return null;
    }

    @Override
    public ArrayList<CuentasAcreedorDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(CuentasAcreedorDTO cuentaAcreedor) {
        return 0;
    }

    @Override
    public Integer eliminar(CuentasAcreedorDTO cuentaAcreedor) {
        return 0;
    }
}
