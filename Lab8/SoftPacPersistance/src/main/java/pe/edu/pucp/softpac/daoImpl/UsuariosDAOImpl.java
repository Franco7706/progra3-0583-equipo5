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
import java.sql.Timestamp;
import java.util.ArrayList;

public class UsuariosDAOImpl extends DAOImplBase implements UsuariosDAO {

    private UsuariosDTO usuario;
    private ArrayList<UsuariosDTO> usuarios;

    public UsuariosDAOImpl() {
        super("PA_USUARIOS");
        this.usuario = null;
        this.retornarLlavePrimaria = false; // USUARIO_ID no es auto_increment
        this.ejecutaOperacionesEnCascada = true;
        this.seEliminaLogicamente = true;
        this.listarEliminados = true;
        super.incluirColumnasDeEliminacionLogica();
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
        UsuariosDTO usuarioEliminacion=null;
        int i=0;
        while (this.resultSet.next()) {
            if (i==0){
                usuario = new UsuariosDTO();
                usuario.setUsuario_id(this.resultSet.getInt(1));
                usuario.setCorreo_electronico(this.resultSet.getString(2));
                usuario.setActivo(this.resultSet.getString(3).equals("S"));
                usuario.setPassword_hash(this.resultSet.getString(4));
                usuario.setSuperusuario(this.resultSet.getString(5).equals("S"));
                if(this.resultSet.getTimestamp(6)!=null){
                    usuario.setFecha_eliminacion(this.resultSet.getTimestamp(6).toLocalDateTime());
                    usuarioEliminacion=new UsuariosDTO();
                    usuario.setUsuario_eliminacion(usuarioEliminacion);
                    usuarioEliminacion.setUsuario_id(this.resultSet.getInt(7));
                }
                i=1;
            }
            pais=new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(8));
            if(this.resultSet.wasNull()) break;
            pais.setNombre(this.resultSet.getString(9));
            pais.setCodigo_iso(this.resultSet.getString(10));
            pais.setCodigo_telefonico(this.resultSet.getString(11));
            usuarioPaisAcceso = new UsuarioPaisAccesoDTO();
            usuarioPaisAcceso.setAcceso(this.resultSet.getString(12).equals("S"));
            usuarioPaisAcceso.setPais(pais);
            usuarioPaisAcceso.setUsuario(usuario);
            this.usuario.addUsuario_pais(usuarioPaisAcceso);
        }
    }

    @Override
    protected String generarSQLParaObtenerPorId() {
        return "SELECT u.USUARIO_ID, u.CORREO_ELECTRONICO, u.ACTIVO, u.PASSWORD_HASH, u.SUPERUSUARIO, u.FECHA_ELIMINACION, u.USUARIO_ELIMINACION," +
                " p.PAIS_ID, p.NOMBRE, p.CODIGO_ISO, p.CODIGO_TELEFONICO, upa.ACCESO" +
                " FROM PA_USUARIOS u LEFT JOIN PA_USUARIO_PAIS_ACCESO upa ON u.USUARIO_ID = upa.USUARIO_ID" +
                " LEFT JOIN PA_PAISES p ON p.PAIS_ID = upa.PAIS_ID WHERE u.USUARIO_ID = ?";
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
    protected String generarSQLCustom1() {
        return "SELECT u.USUARIO_ID, u.CORREO_ELECTRONICO, u.ACTIVO, u.PASSWORD_HASH, u.SUPERUSUARIO, u.FECHA_ELIMINACION, u.USUARIO_ELIMINACION," +
                " p.PAIS_ID, p.NOMBRE, p.CODIGO_ISO, p.CODIGO_TELEFONICO, upa.ACCESO" +
                " FROM PA_USUARIOS u LEFT JOIN PA_USUARIO_PAIS_ACCESO upa ON u.USUARIO_ID = upa.USUARIO_ID" +
                " LEFT JOIN PA_PAISES p ON p.PAIS_ID = upa.PAIS_ID  WHERE u.CORREO_ELECTRONICO = ?";
    }

    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException {
        this.statement.setString(1,this.usuario.getCorreo_electronico());
    }
    @Override
    protected void extraerResultSetCustom1() throws SQLException {
        this.extraerResultSetParaObtenerPorId();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException {
        this.statement.setTimestamp(1, Timestamp.valueOf(this.usuario.getFecha_eliminacion()));
        this.statement.setInt(2, this.usuario.getUsuario_eliminacion().getUsuario_id());
        this.statement.setInt(3,this.usuario.getUsuario_id());
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
    public UsuariosDTO obtenerPorCorreo(String correo){
        this.usuario = new UsuariosDTO();
        this.usuario.setCorreo_electronico(correo);
        super.queryCustom1();
        return this.usuario;
    }

    @Override
    public Integer eliminarLogico(UsuariosDTO usuario){
        this.usuario=usuario;
        return super.eliminarLogico();
    }

    @Override
    protected void ejecutarCascadaParaEliminacion() {
        try {
            UsuarioPaisAccesoDAO usuarioPaisAccesoDAO = new UsuarioPaisAccesoDAOImpl(this.getConexion());
            for(UsuarioPaisAccesoDTO usu_pais_acceso : this.usuario.getUsuario_pais()){
                usuarioPaisAccesoDAO.eliminar(usu_pais_acceso);
            }
        }
        catch (DAODetalleException e) {
            throw e;
        }
    }

    @Override
    protected void ejecutarCascadaParaInsercion() {
        try {
            UsuarioPaisAccesoDAO usuarioPaisAccesoDAO = new UsuarioPaisAccesoDAOImpl(this.getConexion());
            for(UsuarioPaisAccesoDTO usu_pais_acceso : this.usuario.getUsuario_pais()){
                usuarioPaisAccesoDAO.insertar(usu_pais_acceso);
            }
        }
        catch (DAODetalleException e) {
            throw e;
        }
    }
    @Override
    protected void ejecutarCascadaParaModificacion() {
        try {
            UsuarioPaisAccesoDAO usuarioPaisAccesoDAO = new UsuarioPaisAccesoDAOImpl(this.getConexion());
            for(UsuarioPaisAccesoDTO usu_pais_acceso : this.usuario.getUsuario_pais()){
                usuarioPaisAccesoDAO.modificar(usu_pais_acceso);
            }
        }
        catch (DAODetalleException e) {
            throw e;
        }
    }

}