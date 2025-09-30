package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.PaisesDAO;
import pe.edu.pucp.softpac.model.PaisesDTO;

import java.util.ArrayList;

public class PaisesDAOImpl extends DAOImplBase implements PaisesDAO {

    private PaisesDTO pais;

    public PaisesDAOImpl() {
        super("");
    }

    @Override
    protected void configurarListaDeColumnas() {

    }

    @Override
    public Integer insertar(PaisesDTO pais) {
        return 0;
    }

    @Override
    public PaisesDTO obtenerPorId(Integer paisId) {
        return null;
    }

    @Override
    public ArrayList<PaisesDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(PaisesDTO pais) {
        return 0;
    }

    @Override
    public Integer eliminar(PaisesDTO pais) {
        return 0;
    }
}
