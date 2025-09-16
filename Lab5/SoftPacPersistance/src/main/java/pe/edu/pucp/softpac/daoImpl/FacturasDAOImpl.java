package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.FacturasDAO;
import pe.edu.pucp.softpac.model.FacturasDTO;
import java.sql.SQLException;
import java.sql.Timestamp;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;

public class FacturasDAOImpl extends DAOImplBase implements FacturasDAO {

    private FacturasDTO factura;
    private ArrayList<FacturasDTO> facturas;

    public FacturasDAOImpl() {
        super("PA_FACTURAS");
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("FACTURA_ID", true,true));
        this.listaColumnas.add(new Columna("NUMERO_FACTURA", false,false));
        this.listaColumnas.add(new Columna("FECHA_EMISION", false,false));
        this.listaColumnas.add(new Columna("FECHA_RECEPCION", false,false));
        this.listaColumnas.add(new Columna("FECHA_LIMITE_PAGO", false,false));
        this.listaColumnas.add(new Columna("ESTADO", false,false));
        this.listaColumnas.add(new Columna("MONTO_TOTAL", false,false));
        this.listaColumnas.add(new Columna("MONTO_IGV", false,false));
        this.listaColumnas.add(new Columna("MONTO_RESTANTE", false,false));
        this.listaColumnas.add(new Columna("REGIMEN_FISCAL", false,false));
        this.listaColumnas.add(new Columna("TASA_IVA", false,false));
        this.listaColumnas.add(new Columna("OTROS_TRIBUTOS", false,false));
        this.listaColumnas.add(new Columna("MONEDA_ID", false,false));
        this.listaColumnas.add(new Columna("ACREEDOR_ID", false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.factura.getNumero_factura());
        this.statement.setTimestamp(2,Timestamp.valueOf(this.factura.getFecha_emision()));
        this.statement.setTimestamp(3,Timestamp.valueOf(this.factura.getFecha_recepcion()));
        this.statement.setTimestamp(4,Timestamp.valueOf(this.factura.getFecha_limite_pago()));
        this.statement.setString(5, this.factura.getEstado());
        this.statement.setBigDecimal(6, this.factura.getMonto_total());
        this.statement.setBigDecimal(7, this.factura.getMonto_igv());
        this.statement.setBigDecimal(8, this.factura.getMonto_restante());
        this.statement.setString(9, this.factura.getRegimen_fiscal());
        this.statement.setBigDecimal(10, this.factura.getTasa_iva());
        this.statement.setBigDecimal(11, this.factura.getOtros_tributos());
        this.statement.setInt(12, this.factura.getMoneda().getMoneda_id());
        this.statement.setInt(13, this.factura.getAcreedor().getAcreedor_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.factura.getNumero_factura());
        this.statement.setTimestamp(2,Timestamp.valueOf(this.factura.getFecha_emision()));
        this.statement.setTimestamp(3,Timestamp.valueOf(this.factura.getFecha_recepcion()));
        this.statement.setTimestamp(4,Timestamp.valueOf(this.factura.getFecha_limite_pago()));
        this.statement.setString(5, this.factura.getEstado());
        this.statement.setBigDecimal(6, this.factura.getMonto_total());
        this.statement.setBigDecimal(7, this.factura.getMonto_igv());
        this.statement.setBigDecimal(8, this.factura.getMonto_restante());
        this.statement.setString(9, this.factura.getRegimen_fiscal());
        this.statement.setBigDecimal(10, this.factura.getTasa_iva());
        this.statement.setBigDecimal(11, this.factura.getOtros_tributos());
        this.statement.setInt(12, this.factura.getMoneda().getMoneda_id());
        this.statement.setInt(13, this.factura.getAcreedor().getAcreedor_id());
        this.statement.setInt(14, this.factura.getFactura_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.factura.getFactura_id());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,this.factura.getFactura_id());
    }
    
    @Override
    protected String generarSQLParaObtenerPorId() {
        return "SELECT * FROM VW_FACTURA_COMPLETA WHERE FACTURA_ID = ?";
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        DetallesFacturaDTO detalleFactura = null;
        MonedasDTO moneda = null;
        AcreedoresDTO acreedor = null;
        PaisesDTO pais = null;
        this.factura = null;
        int i=0;
        while (this.resultSet.next()) {
            if (i==0) {
                this.factura = new FacturasDTO();
                this.factura.setFactura_id(this.resultSet.getInt(1));
                this.factura.setNumero_factura(this.resultSet.getString(2));
                this.factura.setFecha_emision(this.resultSet.getTimestamp(3).toLocalDateTime());
                this.factura.setFecha_recepcion(this.resultSet.getTimestamp(4).toLocalDateTime());
                this.factura.setFecha_limite_pago(this.resultSet.getTimestamp(5).toLocalDateTime());
                this.factura.setEstado(this.resultSet.getString(6));
                this.factura.setMonto_total(this.resultSet.getBigDecimal(7));
                this.factura.setMonto_igv(this.resultSet.getBigDecimal(8));
                this.factura.setMonto_restante(this.resultSet.getBigDecimal(9));
                this.factura.setRegimen_fiscal(this.resultSet.getString(10));
                this.factura.setTasa_iva(this.resultSet.getBigDecimal(11));
                this.factura.setOtros_tributos(this.resultSet.getBigDecimal(12));
                moneda = new MonedasDTO();
                moneda.setMoneda_id(this.resultSet.getInt(13));
                moneda.setNombre(this.resultSet.getString(14));
                moneda.setCodigo_iso(this.resultSet.getString(15));
                moneda.setSimbolo(this.resultSet.getString(16));
                acreedor = new AcreedoresDTO();
                acreedor.setAcreedor_id(this.resultSet.getInt(17));
                //18 S 19 S 20 S 21 S 22 I 23 B
                acreedor.setRazon_social(this.resultSet.getString(18));
                acreedor.setRuc(this.resultSet.getString(19));
                acreedor.setDireccion_fiscal(this.resultSet.getString(20));
                acreedor.setCondicion(this.resultSet.getString(21));
                acreedor.setPlazo_de_pago(this.resultSet.getInt(22));
                acreedor.setActivo(this.resultSet.getString(23).charAt(0)=='S'?true:false);
                pais=new PaisesDTO();
                pais.setPais_id(this.resultSet.getInt(24));
                pais.setNombre(this.resultSet.getString(25));
                acreedor.setPais(pais);
                factura.setMoneda(moneda);
                factura.setAcreedor(acreedor);
                i++;
            }
            detalleFactura = new DetallesFacturaDTO();
            detalleFactura.setDetalle_factura_id(this.resultSet.getInt(26));
            detalleFactura.setSubtotal(this.resultSet.getBigDecimal(27));
            detalleFactura.setDescripcion(this.resultSet.getString(28));
            factura.addDetalle_Factura(detalleFactura);
        }
    }        

    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        facturas = new ArrayList<>();
        MonedasDTO moneda = null;
        AcreedoresDTO acreedor = null;
        while (this.resultSet.next()) {
            FacturasDTO f = new FacturasDTO();
            f.setFactura_id(this.resultSet.getInt(1));
            f.setNumero_factura(this.resultSet.getString(2));
            f.setFecha_emision(this.resultSet.getTimestamp(3).toLocalDateTime());
            f.setFecha_recepcion(this.resultSet.getTimestamp(4).toLocalDateTime());
            f.setFecha_limite_pago(this.resultSet.getTimestamp(5).toLocalDateTime());
            f.setEstado(this.resultSet.getString(6));
            f.setMonto_total(this.resultSet.getBigDecimal(7));
            f.setMonto_igv(this.resultSet.getBigDecimal(8));
            f.setMonto_restante(this.resultSet.getBigDecimal(9));
            f.setRegimen_fiscal(this.resultSet.getString(10));
            f.setTasa_iva(this.resultSet.getBigDecimal(11));
            f.setOtros_tributos(this.resultSet.getBigDecimal(12));
            moneda = new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(13));
            acreedor = new AcreedoresDTO();
            acreedor.setAcreedor_id(this.resultSet.getInt(14));
            f.setMoneda(moneda);
            f.setAcreedor(acreedor);
            facturas.add(f);
        }
    }
    
    @Override
    public Integer insertar(FacturasDTO factura) {
        this.factura = factura;
        return super.insertar();
    }

    @Override
    public FacturasDTO obtenerPorId(Integer facturaId) {
        this.factura = new FacturasDTO();
        this.factura.setFactura_id(facturaId);
        super.obtenerPorId();
        return this.factura;
    }

    @Override
    public ArrayList<FacturasDTO> listarTodos() {
        super.listarTodosQuery();
        return facturas;
    }

    @Override
    public Integer modificar(FacturasDTO factura) {
        this.factura = factura;
        return super.modificar();
    }

    @Override
    public Integer eliminar(FacturasDTO factura) {
        this.factura = factura;
        return super.eliminar();
    }
}
