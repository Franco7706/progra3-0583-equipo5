package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.CuentasPropiasDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;

public class CuentasPropiasDAOImpl extends DAOImplBase implements CuentasPropiasDAO {

    private CuentasPropiasDTO cuentaPropia;
    private ArrayList<CuentasPropiasDTO> cuentasPropias;
    
    public CuentasPropiasDAOImpl() {
        super("PA_CUENTAS_PROPIAS");
        this.retornarLlavePrimaria = false;
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
        this.statement.setBoolean(5, this.cuentaPropia.getActiva());
        this.statement.setInt(6, this.cuentaPropia.getEntidad_bancaria().getEntidad_bancaria_id());
        this.statement.setInt(7, this.cuentaPropia.getMoneda().getMoneda_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setBigDecimal(1, this.cuentaPropia.getSaldo_disponible());
        this.statement.setString(2, this.cuentaPropia.getTipo_cuenta());
        this.statement.setString(3, this.cuentaPropia.getNumero_cuenta());
        this.statement.setString(4, this.cuentaPropia.getCci());
        this.statement.setBoolean(5, this.cuentaPropia.getActiva());
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
        return "SELECT c.cuenta_bancaria_id, c.saldo_disponible, c.tipo_cuenta, c.numero_cuenta, c.cci, c.activa, m.moneda_id," +
               " m.nombre      AS moneda_nombre, m.codigo_iso, m.simbolo, eb.entidad_bancaria_id, eb.nombre     AS entidad_nombre," +
               " eb.formato_aceptado, eb.codigo_swift FROM CuentasPropias c JOIN Monedas m ON c.moneda_id = m.moneda_id" +
               " JOIN EntidadesBancarias eb ON c.entidad_bancaria_id = eb.entidad_bancaria_id WHERE c.cuenta_bancaria_id = ?";
    }

    
    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        this.cuentaPropia = null;
        MonedasDTO moneda = null;
        EntidadesBancariasDTO entidadBancaria = null;
        if (this.resultSet.next()) {
            this.cuentaPropia = new CuentasPropiasDTO();
            this.cuentaPropia.setCuenta_bancaria_id(this.resultSet.getInt(1));
            this.cuentaPropia.setSaldo_disponible(this.resultSet.getBigDecimal(2));
            this.cuentaPropia.setTipo_cuenta(this.resultSet.getString(3));
            this.cuentaPropia.setNumero_cuenta(this.resultSet.getString(4));
            this.cuentaPropia.setCci(this.resultSet.getString(5));
            this.cuentaPropia.setActiva(this.resultSet.getBoolean(6));
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
            cuenta.setActiva(this.resultSet.getBoolean(6));
            moneda = new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(7));
            entidadBancaria=new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(8));
            cuenta.setMoneda(moneda);
            cuenta.setEntidad_bancaria(entidadBancaria);
            cuentasPropias.add(cuenta);
        }
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
    public ArrayList<CuentasPropiasDTO> listarTodos() {
        super.listarTodosQuery();
        return this.cuentasPropias;
    }

    @Override
    public Integer modificar(CuentasPropiasDTO cuentaPropia) {
        this.cuentaPropia = cuentaPropia;
        return super.modificar();
    }
//    No es posible eliminar cuentas bancarias, solo pueden ser activadas o desactivadas
//    @Override
//    public Integer eliminar(CuentasPropiasDTO cuentaPropia) {
//        this.cuentaPropia = cuentaPropia;
//        return super.eliminar();
//    }
}
