package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.EntidadesBancariasDAO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;

public class EntidadesBancariasDAOImpl extends DAOImplBase implements EntidadesBancariasDAO {

    private EntidadesBancariasDTO entidadBancaria;

    public EntidadesBancariasDAOImpl() {
        super("");
    }

    @Override
    protected void configurarListaDeColumnas() {

    }

    @Override
    public Integer insertar(EntidadesBancariasDTO entidadBancaria) {
        return 0;
    }

    @Override
    public EntidadesBancariasDTO obtenerPorId(Integer entidadBancariaId) {
        return null;
    }

    @Override
    public ArrayList<EntidadesBancariasDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(EntidadesBancariasDTO entidadBancaria) {
        return 0;
    }

    @Override
    public Integer eliminar(EntidadesBancariasDTO entidadBancaria) {
        return 0;
    }
}
