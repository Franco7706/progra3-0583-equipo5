package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.softpac.dao.CuentasPropiasDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;

public class CuentasPropiasDAOImpl extends DAOImplBase implements CuentasPropiasDAO {

    private CuentasPropiasDTO cuentaPropia;
    private List<CuentasPropiasDTO> cuentasPropias;
    
    public CuentasPropiasDAOImpl() {
        super("PA_CUENTAS_PROPIAS");
        this.retornarLlavePrimaria = true;
        this.seEliminaLogicamente = true;
        super.incluirColumnasDeEliminacionLogica();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("CUENTA_PROPIA_ID", true, true));
        this.listaColumnas.add(new Columna("SALDO_DISPONIBLE", false, false));
        this.listaColumnas.add(new Columna("TIPO_CUENTA", false, false));
        this.listaColumnas.add(new Columna("NUMERO_CUENTA", false, false));
        this.listaColumnas.add(new Columna("CCI", false, false));
        this.listaColumnas.add(new Columna("ACTIVO", false, false));
        this.listaColumnas.add(new Columna("ENTIDAD_BANCARIA_ID", false, false));
        this.listaColumnas.add(new Columna("MONEDA_ID", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setBigDecimal(1, this.cuentaPropia.getSaldo_disponible());
        this.statement.setString(2, this.cuentaPropia.getTipo_cuenta());
        this.statement.setString(3, this.cuentaPropia.getNumero_cuenta());
        this.statement.setString(4, this.cuentaPropia.getCci());
        this.statement.setString(5, this.cuentaPropia.getActiva()?"S":"N");
        this.statement.setInt(6, this.cuentaPropia.getEntidad_bancaria().getEntidad_bancaria_id());
        this.statement.setInt(7, this.cuentaPropia.getMoneda().getMoneda_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setBigDecimal(1, this.cuentaPropia.getSaldo_disponible());
        this.statement.setString(2, this.cuentaPropia.getTipo_cuenta());
        this.statement.setString(3, this.cuentaPropia.getNumero_cuenta());
        this.statement.setString(4, this.cuentaPropia.getCci());
        this.statement.setString(5, this.cuentaPropia.getActiva()?"S":"N");
        this.statement.setInt(6, this.cuentaPropia.getEntidad_bancaria().getEntidad_bancaria_id());
        this.statement.setInt(7, this.cuentaPropia.getMoneda().getMoneda_id());
        this.statement.setInt(8, this.cuentaPropia.getCuenta_bancaria_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.cuentaPropia.getCuenta_bancaria_id());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.cuentaPropia.getCuenta_bancaria_id());
        this.cuentaPropia = null;
    }

    @Override
    protected String generarSQLParaObtenerPorId() {
        return "SELECT \n" +
                "    c.CUENTA_PROPIA_ID, \n" +
                "    c.SALDO_DISPONIBLE, \n" +
                "    c.TIPO_CUENTA, \n" +
                "    c.NUMERO_CUENTA, \n" +
                "    c.CCI, \n" +
                "    c.ACTIVO, \n" +
                "    m.MONEDA_ID,\n" +
                "    m.NOMBRE      AS MONEDA_NOMBRE, \n" +
                "    m.CODIGO_ISO, \n" +
                "    m.SIMBOLO, \n" +
                "    eb.ENTIDAD_BANCARIA_ID, \n" +
                "    eb.NOMBRE     AS ENTIDAD_NOMBRE,\n" +
                "    eb.FORMATO_ACEPTADO, \n" +
                "    eb.CODIGO_SWIFT \n" +
                "FROM PA_CUENTAS_PROPIAS c \n" +
                "JOIN PA_MONEDAS m ON c.MONEDA_ID = m.MONEDA_ID \n" +
                "JOIN PA_ENTIDADES_BANCARIAS eb ON c.ENTIDAD_BANCARIA_ID = eb.ENTIDAD_BANCARIA_ID \n" +
                "WHERE c.CUENTA_PROPIA_ID = ?";
    }
    
    private void llenarCuentaPropia() throws SQLException{
        MonedasDTO moneda = null;
        EntidadesBancariasDTO entidadBancaria = null;
        this.cuentaPropia = new CuentasPropiasDTO();
        this.cuentaPropia.setCuenta_bancaria_id(this.resultSet.getInt(1));
        this.cuentaPropia.setSaldo_disponible(this.resultSet.getBigDecimal(2));
        this.cuentaPropia.setTipo_cuenta(this.resultSet.getString(3));
        this.cuentaPropia.setNumero_cuenta(this.resultSet.getString(4));
        this.cuentaPropia.setCci(this.resultSet.getString(5));
        this.cuentaPropia.setActiva(this.resultSet.getString(6).equals("S"));
        moneda = new MonedasDTO();
        moneda.setMoneda_id(this.resultSet.getInt(7));
        moneda.setNombre(this.resultSet.getString(8));
        moneda.setCodigo_iso(this.resultSet.getString(9));
        moneda.setSimbolo(this.resultSet.getString(10));
        entidadBancaria = new EntidadesBancariasDTO();
        entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(11));
        entidadBancaria.setNombre(this.resultSet.getString(12));
        entidadBancaria.setFormato_aceptado(this.resultSet.getString(13));
        entidadBancaria.setCodigo_swift(resultSet.getString(14));
        cuentaPropia.setMoneda(moneda);
        cuentaPropia.setEntidad_bancaria(entidadBancaria);
    }
    
    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        this.cuentaPropia = null;
        if (this.resultSet.next()) {
            llenarCuentaPropia();
        }
    }

    @Override
    protected String generarSQLParaListarTodos(){
        return "SELECT c.CUENTA_PROPIA_ID, c.SALDO_DISPONIBLE, c.TIPO_CUENTA, c.NUMERO_CUENTA, c.CCI, c.ACTIVO, c.ENTIDAD_BANCARIA_ID, c.MONEDA_ID, e.NOMBRE, m.CODIGO_ISO\n" +
                "FROM PA_CUENTAS_PROPIAS c LEFT JOIN PA_ENTIDADES_BANCARIAS e on e.ENTIDAD_BANCARIA_ID = c.ENTIDAD_BANCARIA_ID \n" +
                "LEFT JOIN PA_MONEDAS m on m.MONEDA_ID = c.MONEDA_ID WHERE FECHA_ELIMINACION IS NULL";
    }
    
    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        cuentasPropias = new ArrayList<>();
        MonedasDTO moneda = null;
        EntidadesBancariasDTO entidadBancaria = null;
        while (this.resultSet.next()) {
            CuentasPropiasDTO cuenta = new CuentasPropiasDTO();
            cuenta.setCuenta_bancaria_id(this.resultSet.getInt(1));
            cuenta.setSaldo_disponible(this.resultSet.getBigDecimal(2));
            cuenta.setTipo_cuenta(this.resultSet.getString(3));
            cuenta.setNumero_cuenta(this.resultSet.getString(4));
            cuenta.setCci(this.resultSet.getString(5));
            cuenta.setActiva(this.resultSet.getString(6).equals("S"));
            moneda = new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(8));
            entidadBancaria=new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(7));
            entidadBancaria.setNombre(this.resultSet.getString(9));
            moneda.setCodigo_iso(this.resultSet.getString(10));
            cuenta.setMoneda(moneda);
            cuenta.setEntidad_bancaria(entidadBancaria);
            cuentasPropias.add(cuenta);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.cuentaPropia.getFecha_eliminacion().getTime()));
        this.statement.setInt(2,this.cuentaPropia.getUsuario_eliminacion().getUsuario_id());
        this.statement.setInt(3,this.cuentaPropia.getCuenta_bancaria_id());
    }

    @Override
    public Integer insertar(CuentasPropiasDTO cuentaPropia) {
        this.cuentaPropia = cuentaPropia;
        return super.insertar();
    }

    @Override
    public CuentasPropiasDTO obtenerPorId(Integer cuentaPropiaId) {
        this.cuentaPropia = new CuentasPropiasDTO();
        this.cuentaPropia.setCuenta_bancaria_id(cuentaPropiaId);
        super.obtenerPorId();
        return this.cuentaPropia;
    }

    @Override
    public List<CuentasPropiasDTO> listarTodos() {
        //super.listarTodosQuery();
        super.listarTodosQuery();
        return this.cuentasPropias;
    }
    
    @Override
    public ArrayList<CuentasPropiasDTO> listarPorEntidadBancaria(Integer entidad_bancaria_id){
        this.cuentaPropia = new CuentasPropiasDTO();
        this.cuentaPropia.setCuenta_bancaria_id(entidad_bancaria_id);
        super.queryCustom1();
        return (ArrayList<CuentasPropiasDTO>) this.cuentasPropias;
    }
    
    @Override
    protected String generarSQLCustom1() {
        return "SELECT \n" +
                "    c.CUENTA_PROPIA_ID, \n" +
                "    c.SALDO_DISPONIBLE, \n" +
                "    c.TIPO_CUENTA, \n" +
                "    c.NUMERO_CUENTA, \n" +
                "    c.CCI, \n" +
                "    c.ACTIVO, \n" +
                "    m.MONEDA_ID,\n" +
                "    m.NOMBRE      AS MONEDA_NOMBRE, \n" +
                "    m.CODIGO_ISO, \n" +
                "    m.SIMBOLO, \n" +
                "    eb.ENTIDAD_BANCARIA_ID, \n" +
                "    eb.NOMBRE     AS ENTIDAD_NOMBRE,\n" +
                "    eb.FORMATO_ACEPTADO, \n" +
                "    eb.CODIGO_SWIFT \n" +
                "FROM PA_CUENTAS_PROPIAS c \n" +
                "JOIN PA_MONEDAS m ON c.MONEDA_ID = m.MONEDA_ID \n" +
                "JOIN PA_ENTIDADES_BANCARIAS eb ON c.ENTIDAD_BANCARIA_ID = eb.ENTIDAD_BANCARIA_ID \n" +
                "WHERE eb.ENTIDAD_BANCARIA_ID = ?";
    }

    
    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException{
        this.statement.setInt(1, this.cuentaPropia.getCuenta_bancaria_id());
    }
    
    @Override
    protected void extraerResultSetCustom1() throws SQLException{
        this.cuentasPropias = new ArrayList<>();
        while(this.resultSet.next()){
            llenarCuentaPropia();
            this.cuentasPropias.add(this.cuentaPropia);
        }
    }
    

    @Override
    public Integer modificar(CuentasPropiasDTO cuentaPropia) {
        this.cuentaPropia = cuentaPropia;
        return super.modificar();
    }
    @Override
    public Integer eliminar(CuentasPropiasDTO cuentaPropia) {
        this.cuentaPropia = cuentaPropia;
        return super.eliminar();
    }

    @Override
    public Integer eliminarLogico(CuentasPropiasDTO cuentaPropia) {
        this.cuentaPropia = cuentaPropia;
        return super.eliminarLogico();
    }
}
