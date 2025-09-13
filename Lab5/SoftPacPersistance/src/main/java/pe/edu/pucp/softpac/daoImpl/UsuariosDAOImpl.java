package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.UsuariosDAO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.util.ArrayList;

public class UsuariosDAOImpl extends DAOImplBase implements UsuariosDAO {

    private UsuariosDTO usuario;

    public UsuariosDAOImpl() {
        super("");
    }

    @Override
    protected void configurarListaDeColumnas() {

    }

    @Override
    public Integer insertar(UsuariosDTO usuario) {
        return 0;
    }

    @Override
    public UsuariosDTO obtenerPorId(Integer usuarioId) {
        return null;
    }

    @Override
    public ArrayList<UsuariosDTO> listarTodos() {
        return null;
    }

    @Override
    public Integer modificar(UsuariosDTO usuario) {
        return 0;
    }

    @Override
    public Integer eliminar(UsuariosDTO usuario) {
        return 0;
    }
}
