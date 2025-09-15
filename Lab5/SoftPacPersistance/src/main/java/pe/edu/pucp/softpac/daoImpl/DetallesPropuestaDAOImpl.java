package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.DetallesPropuestaDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;

import java.sql.SQLException;

public class DetallesPropuestaDAOImpl extends DAOImplBase implements DetallesPropuestaDAO {

    private DetallesPropuestaDTO detallePropuesta;

    public DetallesPropuestaDAOImpl() {
        super("PA_DETALLES_PROPUESTA");
        this.retornarLlavePrimaria=true;
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
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1,this.detallePropuesta.getDetalle_propuesta_id());
    }

    @Override
    protected String generarSQLParaObtenerPorId(){
        return "SELECT * FROM VW_DETALLE_PROPUESTA_COMPLETO WHERE DETALLE_PROPUESTA_ID=?";
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException{
        this.statement.setInt(1, this.detallePropuesta.getDetalle_propuesta_id());
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException{
        this.detallePropuesta=null;
        PropuestasPagoDTO propuesta=null;
        PaisesDTO pais=null;
        EntidadesBancariasDTO entidadBancaria=null;
        CuentasAcreedorDTO cuentaAcreedor=null;
        CuentasPropiasDTO cuentaPropia=null;
        FacturasDTO factura=null;
        MonedasDTO moneda=null;
        AcreedoresDTO acreedor=null;
        if(this.resultSet.next()) {
            this.detallePropuesta = new DetallesPropuestaDTO();
            propuesta = new PropuestasPagoDTO();
            pais = new PaisesDTO();
            entidadBancaria = new EntidadesBancariasDTO();
            cuentaAcreedor = new CuentasAcreedorDTO();
            cuentaPropia = new CuentasPropiasDTO();
            factura = new FacturasDTO();
            this.detallePropuesta.setPropuesta_pago(propuesta);
            this.detallePropuesta.setFactura(factura);
            this.detallePropuesta.setCuenta_acreedor(cuentaAcreedor);
            this.detallePropuesta.setCuenta_propia(cuentaPropia);

            propuesta.addDetalle_Propuesta(this.detallePropuesta);
            propuesta.setPropuesta_id(this.resultSet.getInt(1));
            propuesta.setFecha_hora_creacion(this.resultSet.getTimestamp(2).toLocalDateTime());
            propuesta.setEstado(this.resultSet.getString(3));

            entidadBancaria = new EntidadesBancariasDTO();
            propuesta.setEntidad_bancaria(entidadBancaria);
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(4));
            entidadBancaria.setNombre(this.resultSet.getString(5));
            entidadBancaria.setCodigo_swift(resultSet.getString(6));

            pais = new PaisesDTO();
            entidadBancaria.setPais(pais);
            pais.setPais_id(this.resultSet.getInt(7));
            pais.setNombre(this.resultSet.getString(8));
            pais.setCodigo_iso(this.resultSet.getString(9));
            pais.setCodigo_telefonico(this.resultSet.getString(10));

            this.detallePropuesta.setDetalle_propuesta_id(this.resultSet.getInt(11));
            this.detallePropuesta.setMonto_pago(this.resultSet.getBigDecimal(12));
            this.detallePropuesta.setForma_pago(this.resultSet.getString(13).charAt(0));

            factura.setFactura_id(this.resultSet.getInt(14));
            factura.setNumero_factura(resultSet.getString(15));
            factura.setFecha_emision(resultSet.getTimestamp(16).toLocalDateTime());
            factura.setFecha_recepcion(resultSet.getTimestamp(17).toLocalDateTime());
            factura.setFecha_limite_pago(resultSet.getTimestamp(18).toLocalDateTime());
            factura.setEstado(this.resultSet.getString(19));
            factura.setMonto_total(this.resultSet.getBigDecimal(20));
            factura.setMonto_igv(this.resultSet.getBigDecimal(21));
            factura.setMonto_restante(this.resultSet.getBigDecimal(22));
            factura.setRegimen_fiscal(this.resultSet.getString(23));
            factura.setTasa_iva(this.resultSet.getBigDecimal(24));
            factura.setOtros_tributos(this.resultSet.getBigDecimal(25));
            moneda = new MonedasDTO();
            factura.setMoneda(moneda);
            moneda.setMoneda_id(this.resultSet.getInt(26));
            acreedor = new AcreedoresDTO();
            factura.setAcreedor(acreedor);
            acreedor.setAcreedor_id(this.resultSet.getInt(27));

            cuentaAcreedor.setCuenta_bancaria_id(this.resultSet.getInt(28));
            cuentaAcreedor.setTipo_cuenta(resultSet.getString(29));
            cuentaAcreedor.setNumero_cuenta(resultSet.getString(30));
            cuentaAcreedor.setCci(resultSet.getString(31));
            cuentaAcreedor.setActiva(resultSet.getString(32).equals("S"));
            moneda = new MonedasDTO();
            cuentaAcreedor.setMoneda(moneda);
            moneda.setMoneda_id(this.resultSet.getInt(33));
            acreedor = new AcreedoresDTO();
            cuentaAcreedor.setAcreedor(acreedor);
            acreedor.setAcreedor_id(this.resultSet.getInt(34));
            entidadBancaria = new EntidadesBancariasDTO();
            cuentaAcreedor.setEntidad_bancaria(entidadBancaria);
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(35));
            entidadBancaria.setNombre(this.resultSet.getString(36));
            entidadBancaria.setCodigo_swift(resultSet.getString(37));
            pais = new PaisesDTO();
            entidadBancaria.setPais(pais);
            pais.setPais_id(this.resultSet.getInt(38));
            pais.setNombre(this.resultSet.getString(39));
            pais.setCodigo_iso(this.resultSet.getString(40));
            pais.setCodigo_telefonico(this.resultSet.getString(41));

            cuentaPropia.setCuenta_bancaria_id(this.resultSet.getInt(42));
            cuentaPropia.setSaldo_disponible(this.resultSet.getBigDecimal(43));
            cuentaPropia.setTipo_cuenta(resultSet.getString(44));
            cuentaPropia.setNumero_cuenta(resultSet.getString(45));
            cuentaPropia.setCci(resultSet.getString(46));
            cuentaPropia.setActiva(resultSet.getString(47).equals("S"));
            moneda = new MonedasDTO();
            cuentaPropia.setMoneda(moneda);
            moneda.setMoneda_id(this.resultSet.getInt(48));
            entidadBancaria = new EntidadesBancariasDTO();
            cuentaPropia.setEntidad_bancaria(entidadBancaria);
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(49));
            entidadBancaria.setNombre(this.resultSet.getString(50));
            entidadBancaria.setCodigo_swift(resultSet.getString(51));
            pais = new PaisesDTO();
            entidadBancaria.setPais(pais);
            pais.setPais_id(this.resultSet.getInt(52));
            pais.setNombre(this.resultSet.getString(53));
            pais.setCodigo_iso(this.resultSet.getString(54));
            pais.setCodigo_telefonico(this.resultSet.getString(55));
        }
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
    public Integer eliminar(DetallesPropuestaDTO detallePropuesta) {
        this.detallePropuesta=detallePropuesta;
        return super.eliminar();
    }
}
