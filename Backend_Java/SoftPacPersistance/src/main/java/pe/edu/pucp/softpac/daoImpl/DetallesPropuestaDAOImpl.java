package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.DetallesPropuestaDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DetallesPropuestaDAOImpl extends DAOImplBase implements DetallesPropuestaDAO {

    private DetallesPropuestaDTO detallePropuesta;

    public DetallesPropuestaDAOImpl() {
        super("PA_DETALLES_PROPUESTA");
        this.retornarLlavePrimaria=true;
        this.esDetalle=true;
        this.seEliminaLogicamente=true;
        this.incluirColumnasDeEliminacionLogica();
    }

    public DetallesPropuestaDAOImpl(Connection conexion) {
        super("PA_DETALLES_PROPUESTA");
        this.retornarLlavePrimaria=true;
        this.esDetalle=true;
        this.conexion=conexion;
        this.seEliminaLogicamente=true;
        this.incluirColumnasDeEliminacionLogica();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("DETALLE_PROPUESTA_ID",true,true));
        this.listaColumnas.add(new Columna("MONTO_PAGO",false,false));
        this.listaColumnas.add(new Columna("FORMA_PAGO",false,false));
        this.listaColumnas.add(new Columna("PROPUESTA_DE_PAGO_ID",false,false));
        this.listaColumnas.add(new Columna("FACTURA_ID",false,false));
        this.listaColumnas.add(new Columna("CUENTA_ACREEDOR_ID",false,false));
        this.listaColumnas.add(new Columna("CUENTA_PROPIA_ID",false,false));
    }
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException{
        this.statement.setBigDecimal(1,this.detallePropuesta.getMonto_pago());
        this.statement.setString(2,String.valueOf(this.detallePropuesta.getForma_pago()));
        this.statement.setInt(3,this.detallePropuesta.getPropuesta_pago().getPropuesta_id());
        this.statement.setInt(4,this.detallePropuesta.getFactura().getFactura_id());
        this.statement.setInt(5,this.detallePropuesta.getCuenta_acreedor().getCuenta_bancaria_id());
        this.statement.setInt(6,this.detallePropuesta.getCuenta_propia().getCuenta_bancaria_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException{
        this.statement.setBigDecimal(1,this.detallePropuesta.getMonto_pago());
        this.statement.setString(2,String.valueOf(this.detallePropuesta.getForma_pago()));
        this.statement.setInt(3,this.detallePropuesta.getPropuesta_pago().getPropuesta_id());
        this.statement.setInt(4,this.detallePropuesta.getFactura().getFactura_id());
        this.statement.setInt(5,this.detallePropuesta.getCuenta_acreedor().getCuenta_bancaria_id());
        this.statement.setInt(6,this.detallePropuesta.getCuenta_propia().getCuenta_bancaria_id());
        this.statement.setInt(7,this.detallePropuesta.getDetalle_propuesta_id());
    }
    @Override
    protected String generarSQLParaEliminacion(){
        return "DELETE FROM PA_DETALLES_PROPUESTA WHERE PROPUESTA_DE_PAGO_ID=?";
    }
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1,this.detallePropuesta.getPropuesta_pago().getPropuesta_id());
    }

    @Override
    protected String generarSQLParaObtenerPorId(){
        return "SELECT \n" +
                "    -- Propuesta\n" +
                "    PROPUESTA_DE_PAGO_ID,\n" +
                "    FECHA_HORA_CREACION,\n" +
                "    ESTADO,\n" +
                "    PROPUESTA_ENTIDAD_ID,\n" +
                "    PROPUESTA_BANCO,\n" +
                "    PROPUESTA_SWIFT,\n" +
                "    PROPUESTA_PAIS_ID,\n" +
                "    PROPUESTA_PAIS_NOMBRE,\n" +
                "    PROPUESTA_PAIS_ISO,\n" +
                "    PROPUESTA_PAIS_TELEFONO,\n" +
                "    -- Detalle de propuesta\n" +
                "    DETALLE_PROPUESTA_ID,\n" +
                "    MONTO_PAGO,\n" +
                "    FORMA_PAGO,\n" +
                "    -- Factura\n" +
                "    FACTURA_ID,\n" +
                "    NUMERO_FACTURA,\n" +
                "    FECHA_EMISION,\n" +
                "    FECHA_RECEPCION,\n" +
                "    FECHA_LIMITE_PAGO,\n" +
                "    FACTURA_ESTADO,\n" +
                "    MONTO_TOTAL,\n" +
                "    MONTO_IGV,\n" +
                "    MONTO_RESTANTE,\n" +
                "    REGIMEN_FISCAL,\n" +
                "    TASA_IVA,\n" +
                "    OTROS_TRIBUTOS,\n" +
                "    FACTURA_MONEDA_ID,\n" +
                "    FACTURA_ACREEDOR_ID,\n" +
                "    -- Cuenta del acreedor (destino)\n" +
                "    CUENTA_ACREEDOR_ID,\n" +
                "    CUENTA_ACREEDOR_TIPO,\n" +
                "    CUENTA_ACREEDOR_NUMERO,\n" +
                "    CUENTA_ACREEDOR_CCI,\n" +
                "    CUENTA_ACREEDOR_ACTIVA,\n" +
                "    CUENTA_ACREEDOR_ACREEDOR_ID,\n" +
                "    CUENTA_ACREEDOR_MONEDA_ID,\n" +
                "    BANCO_ACREEDOR_ID,\n" +
                "    BANCO_ACREEDOR_NOMBRE,\n" +
                "    BANCO_ACREEDOR_SWIFT,\n" +
                "    BANCO_ACREEDOR_PAIS_ID,\n" +
                "    BANCO_ACREEDOR_PAIS_NOMBRE,\n" +
                "    BANCO_ACREEDOR_PAIS_ISO,\n" +
                "    BANCO_ACREEDOR_PAIS_TELEFONO,\n" +
                "    -- Cuenta propia (origen)\n" +
                "    CUENTA_PROPIA_ID,\n" +
                "    SALDO_DISPONIBLE,\n" +
                "    CUENTA_PROPIA_TIPO,\n" +
                "    CUENTA_PROPIA_NUMERO,\n" +
                "    CUENTA_PROPIA_CCI,\n" +
                "    CUENTA_PROPIA_ACTIVA,\n" +
                "    CUENTA_PROPIA_MONEDA_ID,\n" +
                "    BANCO_PROPIO_ID,\n" +
                "    BANCO_PROPIO_NOMBRE,\n" +
                "    BANCO_PROPIO_SWIFT,\n" +
                "    BANCO_PROPIO_PAIS_ID,\n" +
                "    BANCO_PROPIO_PAIS_NOMBRE,\n" +
                "    BANCO_PROPIO_PAIS_ISO,\n" +
                "    BANCO_PROPIO_PAIS_TELEFONO\n" +
                "    FROM VW_DETALLE_PROPUESTA_COMPLETO WHERE DETALLE_PROPUESTA_ID=?";
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException{
        this.statement.setInt(1, this.detallePropuesta.getDetalle_propuesta_id());
    }

    private PropuestasPagoDTO insertarPropuesta() throws SQLException{
        PropuestasPagoDTO propuesta=new PropuestasPagoDTO();
        PaisesDTO pais=new PaisesDTO();
        EntidadesBancariasDTO entidadBancaria=new EntidadesBancariasDTO();

        propuesta.addDetalle_Propuesta(this.detallePropuesta);
        propuesta.setPropuesta_id(this.resultSet.getInt(1));
        propuesta.setFecha_hora_creacion(this.resultSet.getTimestamp(2));
        propuesta.setEstado(this.resultSet.getString(3));

        propuesta.setEntidad_bancaria(entidadBancaria);
        entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(4));
        entidadBancaria.setNombre(this.resultSet.getString(5));
        entidadBancaria.setCodigo_swift(resultSet.getString(6));

        entidadBancaria.setPais(pais);
        pais.setPais_id(this.resultSet.getInt(7));
        pais.setNombre(this.resultSet.getString(8));
        pais.setCodigo_iso(this.resultSet.getString(9));
        pais.setCodigo_telefonico(this.resultSet.getString(10));
        return  propuesta;
    }

    private FacturasDTO insertarFactura() throws SQLException{
        FacturasDTO factura=new FacturasDTO();
        MonedasDTO moneda=new MonedasDTO();
        AcreedoresDTO acreedor=new AcreedoresDTO();

        factura.setFactura_id(this.resultSet.getInt(14));
        factura.setNumero_factura(resultSet.getString(15));
        factura.setFecha_emision(resultSet.getTimestamp(16));
        factura.setFecha_recepcion(resultSet.getTimestamp(17));
        factura.setFecha_limite_pago(resultSet.getTimestamp(18));
        factura.setEstado(this.resultSet.getString(19));
        factura.setMonto_total(this.resultSet.getBigDecimal(20));
        factura.setMonto_igv(this.resultSet.getBigDecimal(21));
        factura.setMonto_restante(this.resultSet.getBigDecimal(22));
        factura.setRegimen_fiscal(this.resultSet.getString(23));
        factura.setTasa_iva(this.resultSet.getBigDecimal(24));
        factura.setOtros_tributos(this.resultSet.getBigDecimal(25));

        factura.setMoneda(moneda);
        moneda.setMoneda_id(this.resultSet.getInt(26));

        factura.setAcreedor(acreedor);
        acreedor.setAcreedor_id(this.resultSet.getInt(27));
        return factura;
    }

    private CuentasAcreedorDTO insertarCuentaAcreedor() throws SQLException{
        MonedasDTO moneda=new MonedasDTO();
        PaisesDTO pais=new PaisesDTO();
        EntidadesBancariasDTO entidadBancaria=new EntidadesBancariasDTO();
        CuentasAcreedorDTO cuentaAcreedor=new CuentasAcreedorDTO();
        AcreedoresDTO acreedor=new AcreedoresDTO();

        cuentaAcreedor.setCuenta_bancaria_id(this.resultSet.getInt(28));
        cuentaAcreedor.setTipo_cuenta(resultSet.getString(29));
        cuentaAcreedor.setNumero_cuenta(resultSet.getString(30));
        cuentaAcreedor.setCci(resultSet.getString(31));
        cuentaAcreedor.setActiva(resultSet.getString(32).equals("S"));

        cuentaAcreedor.setMoneda(moneda);
        moneda.setMoneda_id(this.resultSet.getInt(33));

        cuentaAcreedor.setAcreedor(acreedor);
        acreedor.setAcreedor_id(this.resultSet.getInt(34));

        cuentaAcreedor.setEntidad_bancaria(entidadBancaria);
        entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(35));
        entidadBancaria.setNombre(this.resultSet.getString(36));
        entidadBancaria.setCodigo_swift(resultSet.getString(37));

        entidadBancaria.setPais(pais);
        pais.setPais_id(this.resultSet.getInt(38));
        pais.setNombre(this.resultSet.getString(39));
        pais.setCodigo_iso(this.resultSet.getString(40));
        pais.setCodigo_telefonico(this.resultSet.getString(41));
        return cuentaAcreedor;
    }

    private CuentasPropiasDTO insertarCuentaPropia() throws SQLException{

        PaisesDTO pais=new PaisesDTO();
        EntidadesBancariasDTO entidadBancaria=new EntidadesBancariasDTO();
        CuentasPropiasDTO cuentaPropia=new CuentasPropiasDTO();
        MonedasDTO moneda=new MonedasDTO();

        cuentaPropia.setCuenta_bancaria_id(this.resultSet.getInt(42));
        cuentaPropia.setSaldo_disponible(this.resultSet.getBigDecimal(43));
        cuentaPropia.setTipo_cuenta(resultSet.getString(44));
        cuentaPropia.setNumero_cuenta(resultSet.getString(45));
        cuentaPropia.setCci(resultSet.getString(46));
        cuentaPropia.setActiva(resultSet.getString(47).equals("S"));

        cuentaPropia.setMoneda(moneda);
        moneda.setMoneda_id(this.resultSet.getInt(48));

        cuentaPropia.setEntidad_bancaria(entidadBancaria);
        entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(49));
        entidadBancaria.setNombre(this.resultSet.getString(50));
        entidadBancaria.setCodigo_swift(resultSet.getString(51));

        entidadBancaria.setPais(pais);
        pais.setPais_id(this.resultSet.getInt(52));
        pais.setNombre(this.resultSet.getString(53));
        pais.setCodigo_iso(this.resultSet.getString(54));
        pais.setCodigo_telefonico(this.resultSet.getString(55));
        return cuentaPropia;
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException{
        this.detallePropuesta=null;

        if(this.resultSet.next()) {
            this.detallePropuesta = new DetallesPropuestaDTO();

            this.detallePropuesta.setPropuesta_pago(insertarPropuesta());
            this.detallePropuesta.setFactura(insertarFactura());
            this.detallePropuesta.setCuenta_acreedor(insertarCuentaAcreedor());
            this.detallePropuesta.setCuenta_propia(insertarCuentaPropia());

            this.detallePropuesta.setDetalle_propuesta_id(this.resultSet.getInt(11));
            this.detallePropuesta.setMonto_pago(this.resultSet.getBigDecimal(12));
            this.detallePropuesta.setForma_pago(this.resultSet.getString(13).charAt(0));
        }
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException{
        this.statement.setTimestamp(1, new Timestamp(this.detallePropuesta.getFecha_eliminacion().getTime()));
        this.statement.setInt(2,this.detallePropuesta.getUsuario_eliminacion().getUsuario_id());
        this.statement.setInt(3,this.detallePropuesta.getDetalle_propuesta_id());
    }

    @Override
    public Integer insertar(DetallesPropuestaDTO detallePropuesta) {
        this.detallePropuesta=detallePropuesta;
        return super.insertar();
    }

    @Override
    public DetallesPropuestaDTO obtenerPorId(Integer detallePropuestaId) {
        this.detallePropuesta=new DetallesPropuestaDTO();
        this.detallePropuesta.setDetalle_propuesta_id(detallePropuestaId);
        super.obtenerPorId();
        return this.detallePropuesta;
    }

    @Override
    public Integer modificar(DetallesPropuestaDTO detallePropuesta) {
        this.detallePropuesta=detallePropuesta;
        return super.modificar();
    }

    @Override
    public Integer eliminarPorPropuesta(Integer propuestaId) {
        PropuestasPagoDTO propuesta=new PropuestasPagoDTO();
        propuesta.setPropuesta_id(propuestaId);
        this.detallePropuesta=new DetallesPropuestaDTO();
        this.detallePropuesta.setPropuesta_pago(propuesta);
        return super.eliminar();
    }

    @Override
    public Integer eliminarLogico(DetallesPropuestaDTO detallePropuesta) {
        this.detallePropuesta=detallePropuesta;
        return super.eliminarLogico();
    }
}
