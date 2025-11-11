package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.softpac.dao.CuentasAcreedorDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;

public class CuentasAcreedorDAOImpl extends DAOImplBase implements CuentasAcreedorDAO {

    private CuentasAcreedorDTO cuentaAcreedor;
    private List<CuentasAcreedorDTO> cuentasAcreedores;

    public CuentasAcreedorDAOImpl() {
        super("PA_CUENTAS_ACREEDOR");
        this.cuentaAcreedor=null;
        this.retornarLlavePrimaria=true;
        this.seEliminaLogicamente=true;
        super.incluirColumnasDeEliminacionLogica();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("CUENTA_ACREEDOR_ID", true,true));
        this.listaColumnas.add(new Columna("TIPO_CUENTA", false,false));
        this.listaColumnas.add(new Columna("NUMERO_CUENTA", false,false));
        this.listaColumnas.add(new Columna("CCI", false,false));
        this.listaColumnas.add(new Columna("ACTIVO", false,false));
        this.listaColumnas.add(new Columna("ACREEDOR_ID", false,false));
        this.listaColumnas.add(new Columna("ENTIDAD_BANCARIA_ID", false,false));
        this.listaColumnas.add(new Columna("MONEDA_ID", false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.cuentaAcreedor.getTipo_cuenta());
        this.statement.setString(2, this.cuentaAcreedor.getNumero_cuenta());
        this.statement.setString(3, this.cuentaAcreedor.getCci());
        this.statement.setString(4, this.cuentaAcreedor.getActiva() ? "S":"N");
        this.statement.setInt(5, this.cuentaAcreedor.getAcreedor().getAcreedor_id());
        this.statement.setInt(6, this.cuentaAcreedor.getEntidad_bancaria().getEntidad_bancaria_id());
        this.statement.setInt(7, this.cuentaAcreedor.getMoneda().getMoneda_id());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.cuentaAcreedor.getTipo_cuenta());
        this.statement.setString(2, this.cuentaAcreedor.getNumero_cuenta());
        this.statement.setString(3, this.cuentaAcreedor.getCci());
        this.statement.setString(4, this.cuentaAcreedor.getActiva() ? "S":"N");
        this.statement.setInt(5, this.cuentaAcreedor.getAcreedor().getAcreedor_id());
        this.statement.setInt(6, this.cuentaAcreedor.getEntidad_bancaria().getEntidad_bancaria_id());
        this.statement.setInt(7, this.cuentaAcreedor.getMoneda().getMoneda_id());
        this.statement.setInt(8,this.cuentaAcreedor.getCuenta_bancaria_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.cuentaAcreedor.getCuenta_bancaria_id());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.cuentaAcreedor.getCuenta_bancaria_id());
        this.cuentaAcreedor = null;
    }
    @Override
    protected String generarSQLParaObtenerPorId(){
        return "SELECT c.CUENTA_ACREEDOR_ID, c.TIPO_CUENTA, c.NUMERO_CUENTA," +
                " c.CCI, c.ACTIVO, c.ACREEDOR_ID, a.RAZON_SOCIAL, a.RUC, " +
                "a.DIRECCION_FISCAL, a.CONDICION, a.PLAZO_DE_PAGO, a.ACTIVO, " +
                "c.ENTIDAD_BANCARIA_ID, e.NOMBRE, e.FORMATO_ACEPTADO, e.CODIGO_SWIFT, " +
                "c.MONEDA_ID, m.NOMBRE, m.CODIGO_ISO, m.SIMBOLO " +
                "FROM PA_CUENTAS_ACREEDOR c JOIN PA_ACREEDORES a ON c.ACREEDOR_ID=a.ACREEDOR_ID" +
                " JOIN PA_ENTIDADES_BANCARIAS e ON c.ENTIDAD_BANCARIA_ID=e.ENTIDAD_BANCARIA_ID" +
                " JOIN PA_MONEDAS m ON c.MONEDA_ID=m.MONEDA_ID WHERE c.CUENTA_ACREEDOR_ID = ?";
    }
    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        this.cuentaAcreedor = null;
        MonedasDTO moneda = null;
        EntidadesBancariasDTO entidadBancaria = null;
        AcreedoresDTO acreedor = null;
        if(this.resultSet.next()){
            this.cuentaAcreedor = new CuentasAcreedorDTO();
            this.cuentaAcreedor.setCuenta_bancaria_id(this.resultSet.getInt(1));
            this.cuentaAcreedor.setTipo_cuenta(this.resultSet.getString(2));
            this.cuentaAcreedor.setNumero_cuenta(this.resultSet.getString(3));
            this.cuentaAcreedor.setCci(this.resultSet.getString(4));
            this.cuentaAcreedor.setActiva(this.resultSet.getString(5).equals("S"));
            acreedor = new AcreedoresDTO();
            acreedor.setAcreedor_id(this.resultSet.getInt(6));
            acreedor.setRazon_social(this.resultSet.getString(7));
            acreedor.setRuc(this.resultSet.getString(8));
            acreedor.setDireccion_fiscal(this.resultSet.getString(9));
            acreedor.setCondicion(this.resultSet.getString(10));
            acreedor.setPlazo_de_pago(this.resultSet.getInt(11));
            acreedor.setActivo(this.resultSet.getString(12).equals("S"));
            
            entidadBancaria = new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(13));
            entidadBancaria.setNombre(this.resultSet.getString(14));
            entidadBancaria.setFormato_aceptado(this.resultSet.getString(15));
            entidadBancaria.setCodigo_swift(this.resultSet.getString(16));
            
            moneda = new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(17));
            moneda.setNombre(this.resultSet.getString(18));
            moneda.setCodigo_iso(this.resultSet.getString(19));
            moneda.setSimbolo(this.resultSet.getString(20));
            
            this.cuentaAcreedor.setAcreedor(acreedor);
            this.cuentaAcreedor.setEntidad_bancaria(entidadBancaria);
            this.cuentaAcreedor.setMoneda(moneda);
        }
    }
    
    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        cuentasAcreedores = new ArrayList<>();
        MonedasDTO moneda = null;
        EntidadesBancariasDTO entidadBancaria = null;
        AcreedoresDTO acreedor = null;
        while(this.resultSet.next()){
            CuentasAcreedorDTO cuenta = new CuentasAcreedorDTO();
            cuenta = new CuentasAcreedorDTO();
            cuenta.setCuenta_bancaria_id(this.resultSet.getInt(1));
            cuenta.setTipo_cuenta(this.resultSet.getString(2));
            cuenta.setNumero_cuenta(this.resultSet.getString(3));
            cuenta.setCci(this.resultSet.getString(4));
            cuenta.setActiva(this.resultSet.getString(5).equals("S"));
            
            acreedor = new AcreedoresDTO();
            acreedor.setAcreedor_id(this.resultSet.getInt(6));

            entidadBancaria = new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(7));
            
            moneda = new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(8));
            
            cuenta.setAcreedor(acreedor);
            cuenta.setEntidad_bancaria(entidadBancaria);
            cuenta.setMoneda(moneda);
            cuentasAcreedores.add(cuenta);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.cuentaAcreedor.getFecha_eliminacion().getTime()));
        this.statement.setInt(2,this.cuentaAcreedor.getUsuario_eliminacion().getUsuario_id());
        this.statement.setInt(3,this.cuentaAcreedor.getCuenta_bancaria_id());
    }

    @Override
    public Integer insertar(CuentasAcreedorDTO cuentaAcreedor) {
        this.cuentaAcreedor = cuentaAcreedor;
        return super.insertar();
    }
    

    @Override
    public CuentasAcreedorDTO obtenerPorId(Integer cuentaAcreedorId) {
        this.cuentaAcreedor = new CuentasAcreedorDTO();
        this.cuentaAcreedor.setCuenta_bancaria_id(cuentaAcreedorId);
        super.obtenerPorId();
        return this.cuentaAcreedor;
    }

    @Override
    public List<CuentasAcreedorDTO> listarTodos() {
        super.listarTodosQuery();
        return this.cuentasAcreedores;
    }

    @Override
    public Integer modificar(CuentasAcreedorDTO cuentaAcreedor) {
        this.cuentaAcreedor = cuentaAcreedor;
        return super.modificar();
    }

    @Override
    public Integer eliminar(CuentasAcreedorDTO cuentaAcreedor) {
        this.cuentaAcreedor = cuentaAcreedor;
        return super.eliminar();
    }

    @Override
    public Integer eliminarLogico(CuentasAcreedorDTO cuentaAcreedor) {
        this.cuentaAcreedor = cuentaAcreedor;
        return super.eliminarLogico();
    }
    
    public ArrayList<CuentasAcreedorDTO> obtenerPorAcreedor(Integer acreedor_id){
        this.cuentaAcreedor = new CuentasAcreedorDTO();
        this.cuentaAcreedor.setCuenta_bancaria_id(acreedor_id);
        super.queryCustom1();
        return (ArrayList<CuentasAcreedorDTO>) this.cuentasAcreedores;
    }
    
    @Override
    public String generarSQLCustom1(){
        return "SELECT c.CUENTA_ACREEDOR_ID, c.TIPO_CUENTA, c.NUMERO_CUENTA, " +
           "c.CCI, c.ACTIVO, c.ACREEDOR_ID, a.RAZON_SOCIAL, a.RUC, " +
           "a.DIRECCION_FISCAL, a.CONDICION, a.PLAZO_DE_PAGO, a.ACTIVO, a.PAIS_ID, " +
           "c.ENTIDAD_BANCARIA_ID, e.NOMBRE, e.FORMATO_ACEPTADO, e.CODIGO_SWIFT, e.PAIS_ID, " +
           "c.MONEDA_ID, m.NOMBRE, m.CODIGO_ISO, m.SIMBOLO " +
           "FROM PA_CUENTAS_ACREEDOR c " +
           "JOIN PA_ACREEDORES a ON c.ACREEDOR_ID = a.ACREEDOR_ID " +
           "JOIN PA_ENTIDADES_BANCARIAS e ON c.ENTIDAD_BANCARIA_ID = e.ENTIDAD_BANCARIA_ID " +
           "JOIN PA_MONEDAS m ON c.MONEDA_ID = m.MONEDA_ID " +
           "WHERE a.ACREEDOR_ID = ? " +
           "AND c.FECHA_ELIMINACION IS NULL AND c.USUARIO_ELIMINACION IS NULL " +
           "AND c.ACTIVO = 'S'";
    }
    
    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException{
        this.statement.setInt(1,this.cuentaAcreedor.getCuenta_bancaria_id());
    }
    
    @Override
    protected void extraerResultSetCustom1() throws SQLException{
        this.cuentasAcreedores = new ArrayList<>();
        MonedasDTO moneda;
        EntidadesBancariasDTO entidadBancaria;
        AcreedoresDTO acreedor;
        PaisesDTO pais;
        while(this.resultSet.next()){
            this.cuentaAcreedor = new CuentasAcreedorDTO();
            this.cuentaAcreedor.setCuenta_bancaria_id(this.resultSet.getInt(1));
            this.cuentaAcreedor.setTipo_cuenta(this.resultSet.getString(2));
            this.cuentaAcreedor.setNumero_cuenta(this.resultSet.getString(3));
            this.cuentaAcreedor.setCci(this.resultSet.getString(4));
            this.cuentaAcreedor.setActiva(this.resultSet.getString(5).equals("S"));
            acreedor = new AcreedoresDTO();
            acreedor.setAcreedor_id(this.resultSet.getInt(6));
            acreedor.setRazon_social(this.resultSet.getString(7));
            acreedor.setRuc(this.resultSet.getString(8));
            acreedor.setDireccion_fiscal(this.resultSet.getString(9));
            acreedor.setCondicion(this.resultSet.getString(10));
            acreedor.setPlazo_de_pago(this.resultSet.getInt(11));
            acreedor.setActivo(this.resultSet.getString(12).equals("S"));
            pais = new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(13));
            acreedor.setPais(pais);
            
            entidadBancaria = new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(14));
            entidadBancaria.setNombre(this.resultSet.getString(15));
            entidadBancaria.setFormato_aceptado(this.resultSet.getString(16));
            entidadBancaria.setCodigo_swift(this.resultSet.getString(17));
            pais = new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(18));
            entidadBancaria.setPais(pais);
            
            moneda = new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(19));
            moneda.setNombre(this.resultSet.getString(20));
            moneda.setCodigo_iso(this.resultSet.getString(21));
            moneda.setSimbolo(this.resultSet.getString(22));
            
            this.cuentaAcreedor.setAcreedor(acreedor);
            this.cuentaAcreedor.setEntidad_bancaria(entidadBancaria);
            this.cuentaAcreedor.setMoneda(moneda);
        
            this.cuentasAcreedores.add(this.cuentaAcreedor);
        }
    }
}