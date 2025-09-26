package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.UsuarioPaisAccesoDAO;
import pe.edu.pucp.softpac.dao.UsuariosDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioPaisAccesoDAOImpl extends DAOImplBase implements UsuarioPaisAccesoDAO {
    private UsuarioPaisAccesoDTO usuarioPaisAcceso;

    public UsuarioPaisAccesoDAOImpl() {
        super("PA_USUARIO_PAIS_ACCESO");
        this.usuarioPaisAcceso=null;
        this.retornarLlavePrimaria=true;
        this.esDetalle = true;
    }
    public UsuarioPaisAccesoDAOImpl(Connection conexion) {
        super("PA_USUARIO_PAIS_ACCESO");
        this.usuarioPaisAcceso=null;
        this.retornarLlavePrimaria=true;
        this.esDetalle = true;
        this.conexion=conexion;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("USUARIO_ID", true, false));
        this.listaColumnas.add(new Columna("ACCESO", false, false));
        this.listaColumnas.add(new Columna("PAIS_ID", true, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1,this.usuarioPaisAcceso.getUsuario().getUsuario_id());
        this.statement.setString(2,this.usuarioPaisAcceso.getAcceso()?"S":"N");
        this.statement.setInt(3,this.usuarioPaisAcceso.getPais().getPais_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1,this.usuarioPaisAcceso.getAcceso()?"S":"N");
        this.statement.setInt(2,this.usuarioPaisAcceso.getUsuario().getUsuario_id());
        this.statement.setInt(3,this.usuarioPaisAcceso.getPais().getPais_id());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,this.usuarioPaisAcceso.getUsuario().getUsuario_id());
        this.statement.setInt(2,this.usuarioPaisAcceso.getPais().getPais_id());
    }

    @Override
    protected String generarSQLParaModificacion() {
        return "UPDATE PA_USUARIO_PAIS_ACCESO SET ACCESO=? WHERE USUARIO_ID=? AND PAIS_ID=?";
    }

    @Override
    protected String generarSQLParaObtenerPorId() {
        return "SELECT USUARIO_ID, ACCESO, PAIS_ID FROM PA_USUARIO_PAIS_ACCESO WHERE USUARIO_ID=? AND PAIS_ID=?";
    }


    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,this.usuarioPaisAcceso.getUsuario().getUsuario_id());
        this.statement.setInt(2,this.usuarioPaisAcceso.getPais().getPais_id());
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        this.usuarioPaisAcceso=null;
        if(this.resultSet.next()){
            this.usuarioPaisAcceso = new UsuarioPaisAccesoDTO();
            UsuariosDTO usuario = new UsuariosDTO();
            usuario.setUsuario_id(this.resultSet.getInt("USUARIO_ID"));
            this.usuarioPaisAcceso.setUsuario(usuario);
            PaisesDTO pais = new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt("PAIS_ID"));
            this.usuarioPaisAcceso.setPais(pais);
            this.usuarioPaisAcceso.setAcceso(this.resultSet.getString("ACCESO").equals("S"));
        }
    }

    @Override
    public Integer insertar(UsuarioPaisAccesoDTO usuarioPaisAcceso) {
        this.usuarioPaisAcceso=usuarioPaisAcceso;
        return super.insertar();
    }

    @Override
    public UsuarioPaisAccesoDTO obtenerPorUsuarioYPais(Integer usuarioId, Integer paisId) {
        this.usuarioPaisAcceso=new UsuarioPaisAccesoDTO();
        UsuariosDTO usuario = new UsuariosDTO();
        usuario.setUsuario_id(usuarioId);
        this.usuarioPaisAcceso.setUsuario(usuario);
        PaisesDTO pais = new PaisesDTO();
        pais.setPais_id(paisId);
        this.usuarioPaisAcceso.setPais(pais);
        super.obtenerPorId();
        return this.usuarioPaisAcceso;
    }

    @Override
    public Integer modificar(UsuarioPaisAccesoDTO usuarioPaisAcceso) {
        this.usuarioPaisAcceso=usuarioPaisAcceso;
        return super.modificar();
    }

    @Override
    public Integer eliminar(UsuarioPaisAccesoDTO usuarioPaisAcceso) {
        this.usuarioPaisAcceso=usuarioPaisAcceso;
        return super.eliminar();
    }
}
