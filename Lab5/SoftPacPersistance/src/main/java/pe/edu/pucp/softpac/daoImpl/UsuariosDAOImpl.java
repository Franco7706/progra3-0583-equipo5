package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.UsuariosDAO;
import pe.edu.pucp.softpac.model.UsuariosDTO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.db.DBManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuariosDAOImpl extends DAOImplBase implements UsuariosDAO {

    private UsuariosDTO usuario;
    private ArrayList<UsuariosDTO> usuarios;

    public UsuariosDAOImpl() {
        super("PA_USUARIOS");
        this.usuario = null;
        this.retornarLlavePrimaria = false; // USUARIO_ID no es auto_increment
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("USUARIO_ID", true, false)); // PK, no auto_increment
        this.listaColumnas.add(new Columna("CORREO_ELECTRONICO", false, false));
        this.listaColumnas.add(new Columna("ACTIVO", false, false));
        this.listaColumnas.add(new Columna("PASSWORD_HASH", false, false));
        this.listaColumnas.add(new Columna("SUPERUSUARIO", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.usuario.getUsuario_id());
        this.statement.setString(2, this.usuario.getCorreo_electronico());
        this.statement.setString(3, this.usuario.getActivo() ? "S" : "N");
        this.statement.setString(4, this.usuario.getPassword_hash());
        this.statement.setString(5, this.usuario.getSuperusuario() ? "S" : "N");
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.usuario.getCorreo_electronico());
        this.statement.setString(2, this.usuario.getActivo() ? "S" : "N");
        this.statement.setString(3, this.usuario.getPassword_hash());
        this.statement.setString(4, this.usuario.getSuperusuario() ? "S" : "N");
        this.statement.setInt(5, this.usuario.getUsuario_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.usuario.getUsuario_id());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.usuario.getUsuario_id());
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        usuario=null;
        if (this.resultSet.next()) {
            usuario = new UsuariosDTO();
            usuario.setUsuario_id(this.resultSet.getInt("USUARIO_ID"));
            usuario.setCorreo_electronico(this.resultSet.getString("CORREO_ELECTRONICO"));
            usuario.setActivo(this.resultSet.getString("ACTIVO").equals("S"));
            usuario.setPassword_hash(this.resultSet.getString("PASSWORD_HASH"));
            usuario.setSuperusuario(this.resultSet.getString("SUPERUSUARIO").equals("S"));
        }
    }

    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        usuarios=new ArrayList<>();
        while (this.resultSet.next()) {
            UsuariosDTO u = new UsuariosDTO();
            u.setUsuario_id(this.resultSet.getInt("USUARIO_ID"));
            u.setCorreo_electronico(this.resultSet.getString("CORREO_ELECTRONICO"));
            u.setActivo(this.resultSet.getString("ACTIVO").equals("S"));
            u.setPassword_hash(this.resultSet.getString("PASSWORD_HASH"));
            u.setSuperusuario(this.resultSet.getString("SUPERUSUARIO").equals("S"));
            usuarios.add(u);
        }
    }

    @Override
    public Integer insertar(UsuariosDTO usuario) {
        this.usuario = usuario;
        return super.insertar();
    }

    @Override
    public UsuariosDTO obtenerPorId(Integer usuarioId) {
        this.usuario = new UsuariosDTO();
        this.usuario.setUsuario_id(usuarioId);
        super.obtenerPorId();
        return this.usuario;
    }

    @Override
    public ArrayList<UsuariosDTO> listarTodos() {
        super.listarTodosQuery();
        return usuarios;
    }

    @Override
    public Integer modificar(UsuariosDTO usuario) {
        this.usuario = usuario;
        return super.modificar();
    }

    @Override
    public Integer eliminar(UsuariosDTO usuario) {
        this.usuario = usuario;
        return super.eliminar();
    }
}