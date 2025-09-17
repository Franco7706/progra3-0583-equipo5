package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.UsuariosDAO;
import pe.edu.pucp.softpac.dao.UsuarioPaisAccesoDAO;
import pe.edu.pucp.softpac.daoImpl.util.Tipo_Operacion;
import pe.edu.pucp.softpac.model.PaisesDTO;
import pe.edu.pucp.softpac.model.UsuarioPaisAccesoDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;

import pe.edu.pucp.softpac.daoImpl.exception.DAODetalleException;
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
        PaisesDTO pais=null;
        UsuarioPaisAccesoDTO usuarioPaisAcceso=null;
        if (this.resultSet.next()) {
            usuario = new UsuariosDTO();
            usuario.setUsuario_id(this.resultSet.getInt(1));
            usuario.setCorreo_electronico(this.resultSet.getString(2));
            usuario.setActivo(this.resultSet.getString(3).equals("S"));
            usuario.setPassword_hash(this.resultSet.getString(4));
            usuario.setSuperusuario(this.resultSet.getString(5).equals("S"));
            pais = new PaisesDTO();
            usuarioPaisAcceso = new UsuarioPaisAccesoDTO();
            pais.setPais_id(this.resultSet.getInt(6));
            pais.setNombre(this.resultSet.getString(7));
            pais.setCodigo_iso(this.resultSet.getString(8));
            pais.setCodigo_telefonico(this.resultSet.getString(9));
            usuarioPaisAcceso = new UsuarioPaisAccesoDTO();
            usuarioPaisAcceso.setAcceso(this.resultSet.getString(10).equals("S"));
            usuarioPaisAcceso.setPais(pais);
            usuarioPaisAcceso.setUsuario(usuario);
        }
    }

    @Override
    protected String generarSQLParaObtenerPorId() {
        return "SELECT u.USUARIO_ID, u.CORREO_ELECTRONICO, u.ACTIVO, u.PASSWORD_HASH, u.SUPERUSUARIO, p.PAIS_ID," +
                " p.NOMBRE AS PAIS, p.CODIGO_ISO, p.CODIGO_TELEFONICO, upa.ACCESO" +
                " FROM PA_USUARIOS u JOIN PA_USUARIO_PAIS_ACCESO upa ON upa.USUARIO_ID = u.USUARIO_ID" +
                " JOIN PA_PAISES p ON p.PAIS_ID = upa.PAIS_ID WHERE u.USUARIO_ID = ?" +
                " AND p.PAIS_ID   = ?";
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

    @Override
    protected void ejecutarCascadaParaEliminacion() throws SQLException {
        try {
            UsuarioPaisAccesoDAO usuarioPaisAccesoDAO = new UsuarioPaisAccesoDAOImpl(this.getConexion());
            UsuarioPaisAccesoDTO usu = new UsuarioPaisAccesoDTO();
            usu.setUsuario(this.usuario);
            usu.eliminar(usu);
        }
        catch (DAODetalleException e) {
            throw e;
        }
    }

    /*@Override
    protected void ejecutarCascadaParaInsercion() {
        super.ejecutarCascadaParaInsercion();
    }*/
}