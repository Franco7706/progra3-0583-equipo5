package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softpac.bo.UsuariosBO;
import pe.edu.pucp.softpac.model.UsuarioPaisAccesoDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

@WebService(serviceName = "UsuariosWS")
public class UsuariosWS {

    private UsuariosBO usuariosBO;

    public UsuariosWS() {
        this.usuariosBO = new UsuariosBO();
    }

    @WebMethod(operationName = "autenticarUsuarioPorNombreUsuario")
    public UsuariosDTO autenticarUsuarioPorNombreUsuario(
            @WebParam(name = "nombre_Usuario") String nombreUsuario,
            @WebParam(name = "password") String password) {
        return usuariosBO.autenticarUsuarioPorNombreUsuario(nombreUsuario, password);
    }

    @WebMethod(operationName = "autenticarUsuarioPorCorreo")
    public UsuariosDTO autenticarUsuarioPorCorreo(
            @WebParam(name = "correo") String correo,
            @WebParam(name = "password") String password) {
        return usuariosBO.autenticarUsuarioPorCorreo(correo, password);
    }

    @WebMethod(operationName = "listarUsuarios")
    public ArrayList<UsuariosDTO> listarUsuarios() {
        return usuariosBO.listarTodos();
    }

    @WebMethod(operationName = "obtenerUsuario")
    public UsuariosDTO obtenerUsuario(@WebParam(name = "usuario_id") Integer usuario_id) {
        return usuariosBO.obtenerPorId(usuario_id);
    }

    @WebMethod(operationName = "insertarUsuario")
    public Integer insertarUsuario(@WebParam(name = "nuevoUsuario") UsuariosDTO nuevoUsuario,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual) {
        return usuariosBO.insertarUsuario(nuevoUsuario, usuarioActual);
    }

    @WebMethod(operationName = "modificarAccesoUsuario")
    public Integer modificarAccesoUsuario(
            @WebParam(name = "usuarioId") int usuarioId,
            @WebParam(name = "nuevoNombreUsuario") String nuevoNombreUsuario,
            @WebParam(name = "activo") Boolean activo,
            @WebParam(name = "paisesIds") List<Integer> paisesIds,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual,
            @WebParam(name = "nuevaContrasenha") String nuevaContrasenha) {
        return usuariosBO.modificarAccesoUsuario(usuarioId, nuevoNombreUsuario, activo, paisesIds,
                usuarioActual, nuevaContrasenha);
    }

    @WebMethod(operationName = "eliminarUsuario")
    public Integer eliminarUsuario(
            @WebParam(name = "usuario") UsuariosDTO usuario,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual) {
        return usuariosBO.eliminarUsuario(usuario, usuarioActual);
    }

    @WebMethod(operationName = "modificarUsuario")
    public Integer modificarUsuario(@WebParam(name = "usuario") UsuariosDTO usuario) {
        return usuariosBO.modificarUsuario(usuario);
    }
}