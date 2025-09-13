package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.util.ArrayList;

public interface UsuariosDAO {
    public Integer insertar(UsuariosDTO usuario);

    public UsuariosDTO obtenerPorId(Integer usuarioId);

    public ArrayList<UsuariosDTO> listarTodos();

    public Integer modificar(UsuariosDTO usuario);

    public Integer eliminar(UsuariosDTO usuario);
}
