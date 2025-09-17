package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import pe.edu.pucp.softpac.dao.CuentasAcreedorDAO;
import pe.edu.pucp.softpac.dao.DetallesPropuestaDAO;
import pe.edu.pucp.softpac.dao.PropuestasPagoDAO;
import pe.edu.pucp.softpac.daoImpl.exception.DAODetalleException;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;

public class PropuestasPagoDAOImpl extends DAOImplBase implements PropuestasPagoDAO {

    private PropuestasPagoDTO propuestaPago;
    private ArrayList<PropuestasPagoDTO> propuestasPago;

    public PropuestasPagoDAOImpl() {
        super("PA_PROPUESTAS_DE_PAGO");
        this.retornarLlavePrimaria=true;
        this.ejecutaOperacionesEnCascada=true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("PROPUESTA_DE_PAGO_ID", true, true ));
        this.listaColumnas.add(new Columna("FECHA_HORA_CREACION", false, false));
        this.listaColumnas.add(new Columna("ESTADO", false, false));
        this.listaColumnas.add(new Columna("ENTIDAD_BANCARIA_ID", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setTimestamp(1,java.sql.Timestamp.valueOf(this.propuestaPago.getFecha_hora_creacion()));
        this.statement.setString(2,this.propuestaPago.getEstado());
        this.statement.setInt(3,this.propuestaPago.getEntidad_bancaria().getEntidad_bancaria_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setTimestamp(1,java.sql.Timestamp.valueOf(this.propuestaPago.getFecha_hora_creacion()));
        this.statement.setString(2,this.propuestaPago.getEstado());
        this.statement.setInt(3,this.propuestaPago.getEntidad_bancaria().getEntidad_bancaria_id());
        this.statement.setInt(4,this.propuestaPago.getPropuesta_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,this.propuestaPago.getPropuesta_id());
    }

    @Override
    protected String generarSQLParaObtenerPorId(){
        return "SELECT \n" +
                "    -- Propuesta\n" +
                "    P1_PROPUESTA_DE_PAGO_ID,\n" +
                "    P2_FECHA_HORA_CREACION,\n" +
                "    P3_ESTADO_PROPUESTA,\n" +
                "    P4_ENTIDAD_BANCARIA_PROPUESTA,\n" +
                "    P5_ENTIDAD_BANCARIA_NOMBRE,\n" +
                "    P6_ENTIDAD_BANCARIA_FORMATO,\n" +
                "    P7_ENTIDAD_BANCARIA_SWIFT,\n" +
                "    P8_ENTIDAD_BANCARIA_PAIS_ID,\n" +
                "    P9_ENTIDAD_BANCARIA_PAIS_NOMBRE,\n" +
                "    -- Detalle de propuesta\n" +
                "    D1_DETALLE_PROPUESTA_ID,\n" +
                "    D2_MONTO_PAGO,\n" +
                "    D3_FORMA_PAGO,\n" +
                "    -- Factura\n" +
                "    F1_FACTURA_ID,\n" +
                "    F2_NUMERO_FACTURA,\n" +
                "    F3_FECHA_EMISION,\n" +
                "    F4_FECHA_RECEPCION,\n" +
                "    F5_FECHA_LIMITE_PAGO,\n" +
                "    F6_ESTADO_FACTURA,\n" +
                "    F7_MONTO_TOTAL,\n" +
                "    F8_MONTO_IGV,\n" +
                "    F9_MONTO_RESTANTE,\n" +
                "    F10_REGIMEN_FISCAL,\n" +
                "    F11_TASA_IVA,\n" +
                "    F12_OTROS_TRIBUTOS,\n" +
                "    F13_MONEDA_ID,\n" +
                "    F14_ACREEDOR_ID,\n" +
                "    -- Cuenta acreedor (destino)\n" +
                "    CA1_CUENTA_ACREEDOR_ID,\n" +
                "    CA2_TIPO_CUENTA,\n" +
                "    CA3_NUMERO_CUENTA,\n" +
                "    CA4_CCI,\n" +
                "    CA5_ACTIVO,\n" +
                "    CA6_ACREEDOR_ID,\n" +
                "    CA7_ENTIDAD_BANCARIA_ID,\n" +
                "    CA8_MONEDA_ID,\n" +
                "    CA9_ENTIDAD_BANCARIA_NOMBRE,\n" +
                "    CA10_ENTIDAD_BANCARIA_SWIFT,\n" +
                "    CA11_ENTIDAD_BANCARIA_PAIS_ID,\n" +
                "    CA12_ENTIDAD_BANCARIA_PAIS_NOMBRE,\n" +
                "    -- Cuenta propia (origen)\n" +
                "    CP1_CUENTA_PROPIA_ID,\n" +
                "    CP2_SALDO_DISPONIBLE,\n" +
                "    CP3_TIPO_CUENTA,\n" +
                "    CP4_NUMERO_CUENTA,\n" +
                "    CP5_CCI,\n" +
                "    CP6_ACTIVO,\n" +
                "    CP7_ENTIDAD_BANCARIA_ID,\n" +
                "    CP8_MONEDA_ID,\n" +
                "    CP9_ENTIDAD_BANCARIA_NOMBRE,\n" +
                "    CP10_ENTIDAD_BANCARIA_SWIFT,\n" +
                "    CP11_ENTIDAD_BANCARIA_PAIS_ID,\n" +
                "    CP12_ENTIDAD_BANCARIA_PAIS_NOMBRE\n" +
                "    FROM VW_PROPUESTA_COMPLETA WHERE P1_PROPUESTA_DE_PAGO_ID = ?";
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,this.propuestaPago.getPropuesta_id());
    }
    private FacturasDTO insertarFactura() throws SQLException {
        FacturasDTO factura = new FacturasDTO();
        MonedasDTO moneda = new MonedasDTO();
        AcreedoresDTO acreedor = new AcreedoresDTO();
        factura.setFactura_id(this.resultSet.getInt(13));
        factura.setNumero_factura(resultSet.getString(14));
        factura.setFecha_emision(resultSet.getDate(15).toLocalDate().atStartOfDay());
        factura.setFecha_recepcion(resultSet.getDate(16).toLocalDate().atStartOfDay());
        factura.setFecha_limite_pago(resultSet.getDate(17).toLocalDate().atStartOfDay());
        factura.setEstado(this.resultSet.getString(18));
        factura.setMonto_total(this.resultSet.getBigDecimal(19));
        factura.setMonto_igv(this.resultSet.getBigDecimal(20));
        factura.setMonto_restante(this.resultSet.getBigDecimal(21));
        factura.setRegimen_fiscal(this.resultSet.getString(22));
        factura.setTasa_iva(this.resultSet.getBigDecimal(23));
        factura.setOtros_tributos(this.resultSet.getBigDecimal(24));

        moneda.setMoneda_id(this.resultSet.getInt(25));
        factura.setMoneda(moneda);

        acreedor.setAcreedor_id(this.resultSet.getInt(26));
        factura.setAcreedor(acreedor);

        return factura;
    }

    private CuentasAcreedorDTO insertarCuentaAcreedor() throws SQLException {
        EntidadesBancariasDTO entidadBancaria = new EntidadesBancariasDTO();
        PaisesDTO pais = new PaisesDTO();
        CuentasAcreedorDTO cuentaAcreedor = new CuentasAcreedorDTO();
        MonedasDTO moneda = new MonedasDTO();
        AcreedoresDTO acreedor = new AcreedoresDTO();

        cuentaAcreedor.setCuenta_bancaria_id(this.resultSet.getInt(27));
        cuentaAcreedor.setTipo_cuenta(resultSet.getString(28));
        cuentaAcreedor.setNumero_cuenta(resultSet.getString(29));
        cuentaAcreedor.setCci(resultSet.getString(30));
        cuentaAcreedor.setActiva(resultSet.getString(31).charAt(0)=='S'?true:false);

        acreedor.setAcreedor_id(this.resultSet.getInt(32));
        cuentaAcreedor.setAcreedor(acreedor);

        entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(33));
        cuentaAcreedor.setEntidad_bancaria(entidadBancaria);

        moneda.setMoneda_id(this.resultSet.getInt(34));
        cuentaAcreedor.setMoneda(moneda);
        entidadBancaria.setNombre(resultSet.getString(35));
        entidadBancaria.setCodigo_swift(resultSet.getString(36));

        pais.setPais_id(this.resultSet.getInt(37));
        pais.setNombre(this.resultSet.getString(38));
        entidadBancaria.setPais(pais);
        return cuentaAcreedor;
    }
    private CuentasPropiasDTO insertarCuentaPropia() throws SQLException {
        EntidadesBancariasDTO entidadBancaria = new EntidadesBancariasDTO();
        PaisesDTO pais = new PaisesDTO();
        CuentasPropiasDTO cuentaPropia = new CuentasPropiasDTO();
        MonedasDTO moneda = new MonedasDTO();

        cuentaPropia.setCuenta_bancaria_id(this.resultSet.getInt(39));
        cuentaPropia.setSaldo_disponible(this.resultSet.getBigDecimal(40));
        cuentaPropia.setTipo_cuenta(resultSet.getString(41));
        cuentaPropia.setNumero_cuenta(resultSet.getString(42));
        cuentaPropia.setCci(resultSet.getString(43));
        cuentaPropia.setActiva(resultSet.getString(44).charAt(0)=='S'?true:false);

        entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(45));
        cuentaPropia.setEntidad_bancaria(entidadBancaria);

        moneda.setMoneda_id(this.resultSet.getInt(46));
        cuentaPropia.setMoneda(moneda);
        entidadBancaria.setNombre(resultSet.getString(47));
        entidadBancaria.setCodigo_swift(resultSet.getString(48));

        pais.setPais_id(this.resultSet.getInt(49));
        pais.setNombre(this.resultSet.getString(50));
        entidadBancaria.setPais(pais);
        return cuentaPropia;
    }
    private DetallesPropuestaDTO insertarDetalleDePropuesta() throws SQLException {

        DetallesPropuestaDTO detallePropuesta = null;
        EntidadesBancariasDTO entidadBancaria = null;
        PaisesDTO pais=null;
        CuentasPropiasDTO cuentaPropia=null;
        CuentasAcreedorDTO cuentaAcreedor=null;
        FacturasDTO factura=null;
        MonedasDTO moneda=null;
        AcreedoresDTO acreedor=null;

        detallePropuesta = new DetallesPropuestaDTO();
        detallePropuesta.setPropuesta_pago(propuestaPago);
        detallePropuesta.setDetalle_propuesta_id(this.resultSet.getInt(10));
        if(this.resultSet.wasNull()) return null;
        detallePropuesta.setMonto_pago(this.resultSet.getBigDecimal(11));
        detallePropuesta.setForma_pago(this.resultSet.getString(12).charAt(0));

        detallePropuesta.setFactura(insertarFactura());
        detallePropuesta.setCuenta_acreedor(insertarCuentaAcreedor());
        detallePropuesta.setCuenta_propia(insertarCuentaPropia());

        return detallePropuesta;
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        DetallesPropuestaDTO detallePropuesta = null;
        EntidadesBancariasDTO entidadBancaria = null;
        PaisesDTO pais=null;
        this.propuestaPago=null;
        int i=0;
        while (this.resultSet.next()) {
            if(i==0){
                this.propuestaPago=new PropuestasPagoDTO();
                this.propuestaPago.setPropuesta_id(this.resultSet.getInt(1));
                this.propuestaPago.setFecha_hora_creacion(this.resultSet.getTimestamp(2).toLocalDateTime());
                this.propuestaPago.setEstado(this.resultSet.getString(3));
                entidadBancaria = new EntidadesBancariasDTO();
                entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(4));
                entidadBancaria.setNombre(this.resultSet.getString(5));
                entidadBancaria.setFormato_aceptado(this.resultSet.getString(6));
                entidadBancaria.setCodigo_swift(resultSet.getString(7));
                pais=new PaisesDTO();
                pais.setPais_id(this.resultSet.getInt(8));
                pais.setNombre(this.resultSet.getString(9));
                entidadBancaria.setPais(pais);
                propuestaPago.setEntidad_bancaria(entidadBancaria);
                i++;
            }
            detallePropuesta = insertarDetalleDePropuesta();
            if(detallePropuesta==null) break;
            propuestaPago.addDetalle_Propuesta(detallePropuesta);
        }
    }

    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException{
        propuestasPago=new ArrayList<>();
        EntidadesBancariasDTO entidadBancaria=null;
        while(this.resultSet.next()){
            PropuestasPagoDTO p=new PropuestasPagoDTO();
            p.setPropuesta_id(this.resultSet.getInt(1));
            p.setFecha_hora_creacion(this.resultSet.getTimestamp(2).toLocalDateTime());
            p.setEstado(this.resultSet.getString(3));
            entidadBancaria=new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(4));
            p.setEntidad_bancaria(entidadBancaria);
            propuestasPago.add(p);
        }
    }

    @Override
    protected void ejecutarCascadaParaInsercion(){
        try{
            DetallesPropuestaDAO daoDetalle =  new DetallesPropuestaDAOImpl(this.getConexion());
            for(DetallesPropuestaDTO detalle : this.propuestaPago.getDetalles_propuesta()){
                daoDetalle.insertar(detalle);
            }
        }
        catch(DAODetalleException e){
            throw e;
        }
    }

    @Override
    protected void ejecutarCascadaParaModificacion(){
        try{
            DetallesPropuestaDAO daoDetalle =  new DetallesPropuestaDAOImpl(this.getConexion());
            for(DetallesPropuestaDTO detalle : this.propuestaPago.getDetalles_propuesta()){
                daoDetalle.modificar(detalle);
            }
        }
        catch(DAODetalleException e){
            throw e;
        }
    }


    @Override
    protected void ejecutarCascadaParaEliminacion(){
        try{
            DetallesPropuestaDAO daoDetalle =  new DetallesPropuestaDAOImpl(this.getConexion());
            for(DetallesPropuestaDTO detalle : this.propuestaPago.getDetalles_propuesta()){
                daoDetalle.eliminar(detalle);
            }
        }
        catch(DAODetalleException e){
            throw e;
        }
    }

    @Override
    public Integer insertar(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago = propuestaPago;
        return super.insertar();
    }

    @Override
    public PropuestasPagoDTO obtenerPorId(Integer propuestaPagoId) {
        this.propuestaPago=new PropuestasPagoDTO();
        this.propuestaPago.setPropuesta_id(propuestaPagoId);
        super.obtenerPorId();
        return this.propuestaPago;
    }

    @Override
    public ArrayList<PropuestasPagoDTO> listarTodos() {
        super.listarTodosQuery();
        return propuestasPago;
    }

    @Override
    public Integer modificar(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago=propuestaPago;
        return super.modificar();
    }

    @Override
    public Integer eliminar(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago=propuestaPago;
        return super.eliminar();
    }
}
