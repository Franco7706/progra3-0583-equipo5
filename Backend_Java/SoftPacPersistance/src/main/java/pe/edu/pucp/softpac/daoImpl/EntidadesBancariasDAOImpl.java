package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softpac.dao.EntidadesBancariasDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;

public class EntidadesBancariasDAOImpl extends DAOImplBase implements EntidadesBancariasDAO {

    private EntidadesBancariasDTO entidadBancaria;
    private List<EntidadesBancariasDTO> entidadesBancarias;

    public EntidadesBancariasDAOImpl() {
        super("PA_ENTIDADES_BANCARIAS");
        this.retornarLlavePrimaria=true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ENTIDAD_BANCARIA_ID",true,true));
        this.listaColumnas.add(new Columna("NOMBRE",false,false));
        this.listaColumnas.add(new Columna("FORMATO_ACEPTADO",false,false));
        this.listaColumnas.add(new Columna("CODIGO_SWIFT",false,false));
        this.listaColumnas.add(new Columna("PAIS_ID",false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1,this.entidadBancaria.getNombre());
        this.statement.setString(2,this.entidadBancaria.getFormato_aceptado());
        this.statement.setString(3,this.entidadBancaria.getCodigo_swift());
        this.statement.setInt(4,this.entidadBancaria.getPais().getPais_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1,this.entidadBancaria.getNombre());
        this.statement.setString(2,this.entidadBancaria.getFormato_aceptado());
        this.statement.setString(3,this.entidadBancaria.getCodigo_swift());
        this.statement.setInt(4,this.entidadBancaria.getPais().getPais_id());
        this.statement.setInt(5,this.entidadBancaria.getEntidad_bancaria_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws  SQLException {
        this.statement.setInt(1,this.entidadBancaria.getEntidad_bancaria_id());
    }

    @Override
    protected String generarSQLParaObtenerPorId(){
        return "SELECT e.ENTIDAD_BANCARIA_ID, e.NOMBRE, e.FORMATO_ACEPTADO," +
                " e.CODIGO_SWIFT, e.PAIS_ID, p.NOMBRE, p.CODIGO_ISO, p.CODIGO_TELEFONICO" +
                " FROM PA_ENTIDADES_BANCARIAS e JOIN PA_PAISES p ON e.PAIS_ID=e.PAIS_ID" +
                " WHERE e.ENTIDAD_BANCARIA_ID=?";
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,this.entidadBancaria.getEntidad_bancaria_id());
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        this.entidadBancaria=null;
        PaisesDTO pais=null;
        if(this.resultSet.next()){
            this.entidadBancaria=new EntidadesBancariasDTO();
            this.entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(1));
            this.entidadBancaria.setNombre(this.resultSet.getString(2));
            this.entidadBancaria.setFormato_aceptado(this.resultSet.getString(3));
            this.entidadBancaria.setCodigo_swift(this.resultSet.getString(4));
            pais=new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(5));
            this.entidadBancaria.setPais(pais);
            pais.setNombre(this.resultSet.getString(6));
            pais.setCodigo_iso(this.resultSet.getString(7));
            pais.setCodigo_telefonico(this.resultSet.getString(8));
        }
    }

    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        entidadesBancarias = new ArrayList<>();
        PaisesDTO pais;
        while(this.resultSet.next()){
            EntidadesBancariasDTO e=new EntidadesBancariasDTO();
            e.setEntidad_bancaria_id(this.resultSet.getInt(1));
            e.setNombre(this.resultSet.getString(2));
            e.setFormato_aceptado(this.resultSet.getString(3));
            e.setCodigo_swift(this.resultSet.getString(4));
            pais=new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(5));
            pais.setNombre(this.resultSet.getString(6));
            e.setPais(pais);
            entidadesBancarias.add(e);
        }
    }

    @Override
    public Integer insertar(EntidadesBancariasDTO entidadBancaria) {
        this.entidadBancaria=entidadBancaria;
        return super.insertar();
    }

    @Override
    public EntidadesBancariasDTO obtenerPorId(Integer entidadBancariaId) {
        this.entidadBancaria=new EntidadesBancariasDTO();
        this.entidadBancaria.setEntidad_bancaria_id(entidadBancariaId);
        super.obtenerPorId();
        return this.entidadBancaria;
    }

    @Override
    public List<EntidadesBancariasDTO> listarTodos() {
        super.listarTodosQuery();
        return this.entidadesBancarias;
    }

    @Override
    public Integer modificar(EntidadesBancariasDTO entidadBancaria) {
        this.entidadBancaria=entidadBancaria;
        return super.modificar();
    }

    @Override
    public Integer eliminar(EntidadesBancariasDTO entidadBancaria) {
        this.entidadBancaria=entidadBancaria;
        return super.eliminar();
    }
    
    @Override
    public EntidadesBancariasDTO obtenerPorNombre(String nombre){
        this.entidadBancaria = new EntidadesBancariasDTO();
        this.entidadBancaria.setNombre(nombre);
        super.queryCustom1();
        return this.entidadBancaria;
    }
    
    @Override
    protected String generarSQLParaListarTodos(){
        return "SELECT e.ENTIDAD_BANCARIA_ID, e.NOMBRE, e.FORMATO_ACEPTADO, e.CODIGO_SWIFT, e.PAIS_ID,\n" +
            "p.NOMBRE\n" +
            "FROM PA_ENTIDADES_BANCARIAS e join PA_PAISES p on e.PAIS_ID = p.PAIS_ID";
    }
    
    @Override
    protected String generarSQLCustom1() {
        return "SELECT ENTIDAD_BANCARIA_ID, NOMBRE, FORMATO_ACEPTADO, CODIGO_SWIFT, PAIS_ID " +
               "FROM PA_ENTIDADES_BANCARIAS " +
               "WHERE NOMBRE = ?";
    }

    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException {
        this.statement.setString(1, this.entidadBancaria.getNombre());
    }

    @Override
    protected void extraerResultSetCustom1() throws SQLException {
        this.entidadBancaria = null;
        if (this.resultSet.next()) {
            this.entidadBancaria = new EntidadesBancariasDTO();
            this.entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt("ENTIDAD_BANCARIA_ID"));
            this.entidadBancaria.setNombre(this.resultSet.getString("NOMBRE"));
            this.entidadBancaria.setFormato_aceptado(this.resultSet.getString("FORMATO_ACEPTADO"));
            this.entidadBancaria.setCodigo_swift(this.resultSet.getString("CODIGO_SWIFT"));

            PaisesDTO pais = new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt("PAIS_ID"));
            this.entidadBancaria.setPais(pais);
        }
    }

}