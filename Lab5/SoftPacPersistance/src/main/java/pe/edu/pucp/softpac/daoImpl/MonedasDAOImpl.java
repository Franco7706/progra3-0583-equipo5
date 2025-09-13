package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.MonedasDAO;
import pe.edu.pucp.softpac.model.MonedasDTO;

public class MonedasDAOImpl extends DAOImplBase implements MonedasDAO {

    private MonedasDTO moneda;

    public MonedasDAOImpl() {
        super("");
    }

    @Override
    protected void configurarListaDeColumnas() {

    }

    @Override
    public Integer insertar(MonedasDTO moneda) {
        return 0;
    }

    @Override
    public MonedasDTO obtenerPorId(Integer monedaId) {
        return null;
    }

    @Override
    public ArrayList<MonedasDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(MonedasDTO moneda) {
        return 0;
    }

    @Override
    public Integer eliminar(MonedasDTO moneda) {
        return 0;
    }
}
