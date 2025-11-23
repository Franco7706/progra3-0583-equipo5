package pe.edu.pucp.softpac.daoImpl;

import java.util.ArrayList;

import pe.edu.pucp.softpac.dao.DetallesFacturaDAO;
import pe.edu.pucp.softpac.dao.FacturasDAO;
import pe.edu.pucp.softpac.daoImpl.exception.DAODetalleException;
import pe.edu.pucp.softpac.model.FacturasDTO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;

public class FacturasDAOImpl extends DAOImplBase implements FacturasDAO {

    private FacturasDTO factura;
    private List<FacturasDTO> facturas;

    public FacturasDAOImpl() {
        super("PA_FACTURAS");
        this.retornarLlavePrimaria = true;
        this.ejecutaOperacionesEnCascada = true;
        this.seEliminaLogicamente = true;
        super.incluirColumnasDeEliminacionLogica();
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
        this.statement.setTimestamp(2,new Timestamp(this.factura.getFecha_emision().getTime()));
        this.statement.setTimestamp(3,new Timestamp(this.factura.getFecha_recepcion().getTime()));
        this.statement.setTimestamp(4,new Timestamp(this.factura.getFecha_limite_pago().getTime()));
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
        this.statement.setTimestamp(2,new Timestamp(this.factura.getFecha_emision().getTime()));
        this.statement.setTimestamp(3,new Timestamp(this.factura.getFecha_recepcion().getTime()));
        this.statement.setTimestamp(4,new Timestamp(this.factura.getFecha_limite_pago().getTime()));
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
        return "SELECT\n" +
                "  /*  1 */ FACTURA_ID,\n" +
                "  /*  2 */ NUMERO_FACTURA,\n" +
                "  /*  3 */ FECHA_EMISION,\n" +
                "  /*  4 */ FECHA_RECEPCION,\n" +
                "  /*  5 */ FECHA_LIMITE_PAGO,\n" +
                "  /*  6 */ ESTADO,\n" +
                "  /*  7 */ MONTO_TOTAL,\n" +
                "  /*  8 */ MONTO_IGV,\n" +
                "  /*  9 */ MONTO_RESTANTE,\n" +
                "  /* 10 */ REGIMEN_FISCAL,\n" +
                "  /* 11 */ TASA_IVA,\n" +
                "  /* 12 */ OTROS_TRIBUTOS,\n" +
                "  /* Moneda */\n" +
                "  /* 13 */ MONEDA_ID,\n" +
                "  /* 14 */ MONEDA_NOMBRE,\n" +
                "  /* 15 */ MONEDA_CODIGO_ISO,\n" +
                "  /* 16 */ MONEDA_SIMBOLO,\n" +
                "  /* Acreedor */\n" +
                "  /* 17 */ ACREEDOR_ID,\n" +
                "  /* 18 */ ACREEDOR_RAZON_SOCIAL,\n" +
                "  /* 19 */ ACREEDOR_RUC,\n" +
                "  /* 20 */ ACREEDOR_DIRECCION_FISCAL,\n" +
                "  /* 21 */ ACREEDOR_CONDICION,\n" +
                "  /* 22 */ ACREEDOR_PLAZO_DE_PAGO,\n" +
                "  /* 23 */ ACREEDOR_ACTIVO,\n" +
                "  /* País del acreedor */\n" +
                "  /* 24 */ PAIS_ID,\n" +
                "  /* 25 */ PAIS_NOMBRE,\n" +
                "  /* Detalles (pueden ser NULL si no hay detalles) */\n" +
                "  /* 26 */ DETALLE_FACTURA_ID,\n" +
                "  /* 27 */ DETALLE_SUBTOTAL,\n" +
                "  /* 28 */ DETALLE_DESCRIPCION\n" +
                "  FROM VW_FACTURA_COMPLETA WHERE FACTURA_ID = ?";
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        DetallesFacturaDTO detalleFactura;
        MonedasDTO moneda;
        AcreedoresDTO acreedor;
        PaisesDTO pais;
        this.factura = null;
        int i=0;
        while (this.resultSet.next()) {
            if (i==0) {
                this.factura = new FacturasDTO();
                this.factura.setFactura_id(this.resultSet.getInt(1));
                this.factura.setNumero_factura(this.resultSet.getString(2));
                this.factura.setFecha_emision(this.resultSet.getTimestamp(3));
                this.factura.setFecha_recepcion(this.resultSet.getTimestamp(4));
                this.factura.setFecha_limite_pago(this.resultSet.getTimestamp(5));
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
            if(detalleFactura.getDetalle_factura_id()!=0)
                factura.addDetalle_Factura(detalleFactura);
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
    public Integer modificar(FacturasDTO factura) {
        this.factura = factura;
        return super.modificar();
    }

    @Override
    public Integer eliminar(FacturasDTO factura) {
        this.factura = factura;
        return super.eliminar();
    }

    @Override
    public Integer eliminarLogico(FacturasDTO factura) {
        this.factura = factura;
        return super.eliminarLogico();
    }

    @Override
    protected void recuperarAutoGeneradoParaInsercionDeDetalle(Integer resultado) {
        this.factura.setFactura_id(resultado);
    }
    @Override
    protected void ejecutarCascadaParaInsercion(){
        try{
            DetallesFacturaDAO daoDetalle = new DetallesFacturaDAOImpl(this.getConexion());
            for(DetallesFacturaDTO detalle : this.factura.getDetalles_Factura()){
                daoDetalle.insertar(detalle);
            }
        }
        catch(DAODetalleException e){
            throw e;
        }
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException {
        this.statement.setTimestamp(1,new Timestamp(this.factura.getFecha_eliminacion().getTime()));
        this.statement.setInt(2,this.factura.getUsuario_eliminacion().getUsuario_id());
        this.statement.setString(3, "Eliminada");
        this.statement.setInt(4,this.factura.getFactura_id());
    }

    @Override
    protected String generarSQLParaEliminacionLogica() {
        return "UPDATE PA_FACTURAS SET FECHA_ELIMINACION=?, USUARIO_ELIMINACION=?,\n"
                + "ESTADO=? WHERE FACTURA_ID=?";
    }

    @Override
    protected void ejecutarCascadaParaModificacion(){
        try{
            DetallesFacturaDAO daoDetalle = new DetallesFacturaDAOImpl(this.getConexion());
            for(DetallesFacturaDTO detalle : this.factura.getDetalles_Factura()){
                if(detalle.getDetalle_factura_id()==null){
                    daoDetalle.insertar(detalle);
                }
                else{
                    daoDetalle.modificar(detalle);
                    if(detalle.getFecha_eliminacion()!=null){
                        daoDetalle.eliminarLogico(detalle);
                    }
                }
            }
        }
        catch(DAODetalleException e){
            throw e;
        }
    }


    @Override
    protected void ejecutarCascadaParaEliminacion(){
        try{
            DetallesFacturaDAO daoDetalle = new DetallesFacturaDAOImpl(this.getConexion());
            daoDetalle.eliminarPorFactura(this.factura.getFactura_id());
        }
        catch(DAODetalleException e){
            throw e;
        }
    }
    

    

    
    
    @Override
    protected String generarSQLCustom1(){
        return "SELECT f.FACTURA_ID, f.NUMERO_FACTURA, f.FECHA_EMISION, f.FECHA_RECEPCION, f.FECHA_LIMITE_PAGO,\n" +
            "f.ESTADO, f.MONTO_TOTAL, f.MONTO_IGV, f.MONTO_RESTANTE, f.REGIMEN_FISCAL, f.TASA_IVA, f.OTROS_TRIBUTOS, f.MONEDA_ID, m.CODIGO_ISO, \n" +
            "f.ACREEDOR_ID, a.RAZON_SOCIAL, p.pais_id as PAIS_ID \n" +
            "FROM PA_FACTURAS f join PA_ACREEDORES a on f.ACREEDOR_ID = a.ACREEDOR_ID join PA_PAISES p on p.PAIS_ID = a.PAIS_ID"
                + " join PA_MONEDAS m on f.MONEDA_ID = m.MONEDA_ID";
    }
    
    @Override
    public List<FacturasDTO> listarFiltros(){
        super.queryCustom1();
        return facturas;
    } 
    
    @Override
    protected void extraerResultSetCustom1() throws SQLException {
        facturas = new ArrayList<>();
        MonedasDTO moneda;
        AcreedoresDTO acreedor;
        PaisesDTO pais;
        while (this.resultSet.next()) {
            FacturasDTO f = new FacturasDTO();
            f.setFactura_id(this.resultSet.getInt(1));
            f.setNumero_factura(this.resultSet.getString(2));
            f.setFecha_emision(this.resultSet.getTimestamp(3));
            f.setFecha_recepcion(this.resultSet.getTimestamp(4));
            f.setFecha_limite_pago(this.resultSet.getTimestamp(5));
            f.setEstado(this.resultSet.getString(6));
            f.setMonto_total(this.resultSet.getBigDecimal(7));
            f.setMonto_igv(this.resultSet.getBigDecimal(8));
            f.setMonto_restante(this.resultSet.getBigDecimal(9));
            f.setRegimen_fiscal(this.resultSet.getString(10));
            f.setTasa_iva(this.resultSet.getBigDecimal(11));
            f.setOtros_tributos(this.resultSet.getBigDecimal(12));
            moneda = new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(13));
            moneda.setCodigo_iso(this.resultSet.getString(14));
            acreedor = new AcreedoresDTO();
            acreedor.setAcreedor_id(this.resultSet.getInt(15));
            acreedor.setRazon_social(this.resultSet.getString(16));
            pais = new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(17));
            acreedor.setPais(pais);
            f.setMoneda(moneda);
            f.setAcreedor(acreedor);
            facturas.add(f);
        }
    }
    
       
    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException {
        
    }
    
    /////
    
    @Override
    public List<FacturasDTO> listarTodos() {
        
        super.queryCustom2();
        return facturas;
    }
    
    @Override
    protected String generarSQLCustom2(){
        return "SELECT f.FACTURA_ID, f.NUMERO_FACTURA, f.FECHA_EMISION, f.FECHA_RECEPCION, f.FECHA_LIMITE_PAGO,\n" +
        "f.ESTADO, f.MONTO_TOTAL, f.MONTO_IGV, f.MONTO_RESTANTE, f.REGIMEN_FISCAL, f.TASA_IVA, f.OTROS_TRIBUTOS, f.MONEDA_ID, m.CODIGO_ISO,\n" +
        "f.ACREEDOR_ID, a.RAZON_SOCIAL, p.PAIS_ID as PAIS_ID, p.NOMBRE\n" +
        "FROM PA_FACTURAS f JOIN PA_ACREEDORES a ON f.ACREEDOR_ID = a.ACREEDOR_ID JOIN PA_PAISES p ON p.PAIS_ID = a.PAIS_ID " +
        "JOIN PA_MONEDAS m ON f.MONEDA_ID = m.MONEDA_ID\n" +
        "WHERE f.ESTADO = 'Pendiente' OR f.FECHA_RECEPCION >= ?";
    }
    
    @Override
    protected void extraerResultSetCustom2() throws SQLException {
        facturas = new ArrayList<>();
        MonedasDTO moneda;
        AcreedoresDTO acreedor;
        PaisesDTO pais;
        while (this.resultSet.next()) {
            FacturasDTO f = new FacturasDTO();
            f.setFactura_id(this.resultSet.getInt(1));
            f.setNumero_factura(this.resultSet.getString(2));
            f.setFecha_emision(this.resultSet.getTimestamp(3));
            f.setFecha_recepcion(this.resultSet.getTimestamp(4));
            f.setFecha_limite_pago(this.resultSet.getTimestamp(5));
            f.setEstado(this.resultSet.getString(6));
            f.setMonto_total(this.resultSet.getBigDecimal(7));
            f.setMonto_igv(this.resultSet.getBigDecimal(8));
            f.setMonto_restante(this.resultSet.getBigDecimal(9));
            f.setRegimen_fiscal(this.resultSet.getString(10));
            f.setTasa_iva(this.resultSet.getBigDecimal(11));
            f.setOtros_tributos(this.resultSet.getBigDecimal(12));
            moneda = new MonedasDTO();
            moneda.setMoneda_id(this.resultSet.getInt(13));
            moneda.setCodigo_iso(this.resultSet.getString(14));
            acreedor = new AcreedoresDTO();
            acreedor.setAcreedor_id(this.resultSet.getInt(15));
            acreedor.setRazon_social(this.resultSet.getString(16));
            pais = new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(17));
            pais.setNombre(this.resultSet.getString(18));
            acreedor.setPais(pais);
            f.setMoneda(moneda);
            f.setAcreedor(acreedor);
            facturas.add(f);
        }
    }
    
       
    @Override
    protected void incluirValorDeParametrosCustom2() throws SQLException {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MONTH, -6);
        java.sql.Timestamp fechaSeisMesesAtras = new java.sql.Timestamp(cal.getTimeInMillis());

        this.statement.setTimestamp(1, fechaSeisMesesAtras);
    }
    

    
}
