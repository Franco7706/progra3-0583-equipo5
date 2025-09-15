package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import pe.edu.pucp.softpac.dao.DetallesPropuestaDAO;
import pe.edu.pucp.softpac.dao.PropuestasPagoDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;

public class PropuestasPagoDAOImpl extends DAOImplBase implements PropuestasPagoDAO {

    private PropuestasPagoDTO propuestaPago;
    private ArrayList<PropuestasPagoDTO> propuestasPago;
    private DetallesPropuestaDAO daoDetalle;

    public PropuestasPagoDAOImpl() {
        super("PA_PROPUESTAS_DE_PAGO");
        this.retornarLlavePrimaria=true;
        daoDetalle = new DetallesPropuestaDAOImpl();
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
        return "SELECT * FROM VW_PROPUESTA_COMPLETA WHERE P1_PROPUESTA_DE_PAGO_ID = ?";
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,this.propuestaPago.getPropuesta_id());
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        DetallesPropuestaDTO detallePropuesta = null;
        EntidadesBancariasDTO entidadBancaria = null;
        PaisesDTO pais=null;
        CuentasPropiasDTO cuentaPropia=null;
        CuentasAcreedorDTO cuentaAcreedor=null;
        FacturasDTO factura=null;
        MonedasDTO moneda=null;
        AcreedoresDTO acreedor=null;
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
            detallePropuesta = new DetallesPropuestaDTO();
            detallePropuesta.setDetalle_propuesta_id(this.resultSet.getInt(10));
            if(this.resultSet.wasNull()) break;
            detallePropuesta.setMonto_pago(this.resultSet.getBigDecimal(11));
            detallePropuesta.setForma_pago(this.resultSet.getString(12).charAt(0));

            factura = new FacturasDTO();
            detallePropuesta.setFactura(factura);
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

            moneda=new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(25));
            factura.setMoneda(moneda);

            acreedor=new AcreedoresDTO();
            acreedor.setAcreedor_id(this.resultSet.getInt(26));
            factura.setAcreedor(acreedor);

            cuentaAcreedor=new CuentasAcreedorDTO();
            detallePropuesta.setCuenta_acreedor(cuentaAcreedor);
            cuentaAcreedor.setCuenta_bancaria_id(this.resultSet.getInt(27));
            cuentaAcreedor.setTipo_cuenta(resultSet.getString(28));
            cuentaAcreedor.setNumero_cuenta(resultSet.getString(29));
            cuentaAcreedor.setCci(resultSet.getString(30));
            cuentaAcreedor.setActiva(resultSet.getString(31).charAt(0)=='S'?true:false);

            acreedor=new AcreedoresDTO();
            acreedor.setAcreedor_id(this.resultSet.getInt(32));
            cuentaAcreedor.setAcreedor(acreedor);

            entidadBancaria=new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(33));
            cuentaAcreedor.setEntidad_bancaria(entidadBancaria);

            moneda=new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(34));
            cuentaAcreedor.setMoneda(moneda);
            entidadBancaria.setNombre(resultSet.getString(35));
            entidadBancaria.setCodigo_swift(resultSet.getString(36));

            pais = new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(37));
            pais.setNombre(this.resultSet.getString(38));
            entidadBancaria.setPais(pais);

            cuentaPropia = new CuentasPropiasDTO();
            detallePropuesta.setCuenta_propia(cuentaPropia);
            cuentaPropia.setCuenta_bancaria_id(this.resultSet.getInt(39));
            cuentaPropia.setSaldo_disponible(this.resultSet.getBigDecimal(40));
            cuentaPropia.setTipo_cuenta(resultSet.getString(41));
            cuentaPropia.setNumero_cuenta(resultSet.getString(42));
            cuentaPropia.setCci(resultSet.getString(43));
            cuentaPropia.setActiva(resultSet.getString(44).charAt(0)=='S'?true:false);

            entidadBancaria = new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(45));
            cuentaPropia.setEntidad_bancaria(entidadBancaria);

            moneda=new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(46));
            cuentaPropia.setMoneda(moneda);
            entidadBancaria.setNombre(resultSet.getString(47));
            entidadBancaria.setCodigo_swift(resultSet.getString(48));

            pais=new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(49));
            pais.setNombre(this.resultSet.getString(50));
            entidadBancaria.setPais(pais);

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
    public Integer insertar(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago = propuestaPago;
        Integer resultado = super.insertar();
        for(DetallesPropuestaDTO detalle : this.propuestaPago.getDetalles_propuesta()){
            daoDetalle.insertar(detalle);
        }
        return resultado;
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
        Integer resultado = super.modificar();
        return resultado;
    }

    @Override
    public Integer eliminar(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago=propuestaPago;
        for(DetallesPropuestaDTO detalle : this.propuestaPago.getDetalles_propuesta()){
            daoDetalle.eliminar(detalle);
        }
        return super.eliminar();
    }
}
