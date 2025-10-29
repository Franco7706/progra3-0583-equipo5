package pe.edu.pucp.softpac.dao;

import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.util.ArrayList;
import java.util.List;

public interface UsuariosDAO {
    public Integer insertar(UsuariosDTO usuario);

    public UsuariosDTO obtenerPorId(Integer usuarioId);

    public List<UsuariosDTO> listarTodos();

    public Integer modificar(UsuariosDTO usuario);

    public Integer eliminar(UsuariosDTO usuario);

    public UsuariosDTO obtenerPorCorreo(String correo);

    public Integer eliminarLogico(UsuariosDTO usuario);
    
    //Nuevo: Para UsuariosDTO ObtenerPorNombreUsuario(string nombreUsuario);
    public UsuariosDTO obtenerPorNombreDeUsuario(String nombreDeUsuario);
    
}
