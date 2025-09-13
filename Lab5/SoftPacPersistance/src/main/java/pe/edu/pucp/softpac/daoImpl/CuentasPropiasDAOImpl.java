package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.CuentasPropiasDAO;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;

public class CuentasPropiasDAOImpl extends DAOImplBase implements CuentasPropiasDAO {

    private CuentasPropiasDTO cuentaPropia;

    public CuentasPropiasDAOImpl() {
        super("");
    }

    @Override
    protected void configurarListaDeColumnas() {

    }

    @Override
    public Integer insertar(CuentasPropiasDTO cuentaPropia) {
        return 0;
    }

    @Override
    public CuentasPropiasDTO obtenerPorId(Integer cuentaPropiaId) {
        return null;
    }

    @Override
    public ArrayList<CuentasPropiasDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(CuentasPropiasDTO cuentaPropia) {
        return 0;
    }

    @Override
    public Integer eliminar(CuentasPropiasDTO cuentaPropia) {
        return 0;
    }
}
