package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.softpac.dao.MonedasDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;

public class MonedasDAOImpl extends DAOImplBase implements MonedasDAO {

    private MonedasDTO moneda;
    private List<MonedasDTO> monedas;

    public MonedasDAOImpl() {
        super("PA_MONEDAS");
        this.moneda = null;
        this.retornarLlavePrimaria = true; // MONEDA_ID es auto_increment
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("MONEDA_ID", true, true)); // PK, auto_increment
        this.listaColumnas.add(new Columna("NOMBRE", false, false));
        this.listaColumnas.add(new Columna("CODIGO_ISO", false, false));
        this.listaColumnas.add(new Columna("SIMBOLO", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.moneda.getNombre());
        this.statement.setString(2, this.moneda.getCodigo_iso());
        this.statement.setString(3, this.moneda.getSimbolo());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.moneda.getNombre());
        this.statement.setString(2, this.moneda.getCodigo_iso());
        this.statement.setString(3, this.moneda.getSimbolo());
        this.statement.setInt(4, this.moneda.getMoneda_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.moneda.getMoneda_id());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.moneda.getMoneda_id());
    }
    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        this.moneda=null;
        if (this.resultSet.next()) {
            this.moneda = new MonedasDTO();
            this.moneda.setMoneda_id(this.resultSet.getInt("MONEDA_ID"));
            this.moneda.setNombre(this.resultSet.getString("NOMBRE"));
            this.moneda.setCodigo_iso(this.resultSet.getString("CODIGO_ISO"));
            this.moneda.setSimbolo(this.resultSet.getString("SIMBOLO"));
        }
    }

    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        monedas=new ArrayList<>();
        while (this.resultSet.next()) {
            MonedasDTO m = new MonedasDTO();
            m.setMoneda_id(this.resultSet.getInt("MONEDA_ID"));
            m.setNombre(this.resultSet.getString("NOMBRE"));
            m.setCodigo_iso(this.resultSet.getString("CODIGO_ISO"));
            m.setSimbolo(this.resultSet.getString("SIMBOLO"));
            monedas.add(m);
        }
    }
    
    @Override
    public MonedasDTO obtenerPorDivisa(String divisaMoneda){
        this.moneda = new MonedasDTO();
        this.moneda.setCodigo_iso(divisaMoneda);
        super.queryCustom1();
        return this.moneda;
    }
    
    @Override
    protected String generarSQLCustom1() {
        return "SELECT MONEDA_ID, NOMBRE, CODIGO_ISO, SIMBOLO " +
               "FROM PA_MONEDAS " +
               "WHERE CODIGO_ISO = ?";
    }
    
    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException {
        this.statement.setString(1, this.moneda.getCodigo_iso());
    }
    
    @Override
    protected void extraerResultSetCustom1() throws SQLException {
        this.moneda = null;
        if (this.resultSet.next()) {
            this.moneda = new MonedasDTO();
            this.moneda.setMoneda_id(this.resultSet.getInt("MONEDA_ID"));
            this.moneda.setNombre(this.resultSet.getString("NOMBRE"));
            this.moneda.setCodigo_iso(this.resultSet.getString("CODIGO_ISO"));
            this.moneda.setSimbolo(this.resultSet.getString("SIMBOLO"));
        }
    }

    @Override
    public Integer insertar(MonedasDTO moneda) {
        this.moneda = moneda;
        return super.insertar();
    }

    @Override
    public MonedasDTO obtenerPorId(Integer monedaId) {
        this.moneda=new MonedasDTO();
        this.moneda.setMoneda_id(monedaId);
        super.obtenerPorId();
        return this.moneda;
    }

    @Override
    public List<MonedasDTO> listarTodos() {
        super.listarTodosQuery();
        return this.monedas;
    }

    @Override
    public Integer modificar(MonedasDTO moneda) {
        this.moneda = moneda;
        return super.modificar();
    }

    @Override
    public Integer eliminar(MonedasDTO moneda) {
        this.moneda = moneda;
        return super.eliminar();
    }
}