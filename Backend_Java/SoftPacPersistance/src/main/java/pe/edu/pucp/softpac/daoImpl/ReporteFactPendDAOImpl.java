package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softpac.dao.ReporteFactPendDAO;
import pe.edu.pucp.softpac.daoImpl.util.ReporteFacturasPendientesParametros;
import pe.edu.pucp.softpac.daoImpl.util.ReporteFacturasPendientesParametrosBuilder;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.FacturasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;
import pe.edu.pucp.softpac.model.ReporteFactPendDTO;


public class ReporteFactPendDAOImpl extends DAOImplBase implements ReporteFactPendDAO {

    private ReporteFactPendDTO reporte;
    
    public ReporteFactPendDAOImpl() {
        super("PA_REPORTES_FACTURAS_PENDIENTES");
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        //no se incluirán las columnas pues no se generará el SQL genérico
        //sino un SQL específico para este reporte
    }
    
    //METODO PRINCIPAL. LLAMARÁ A EL PROCEDURE ALMACENADO: "pa_sp_reporte_facturas_pendientes(acreedor,país,moneda)"
    @Override
    public void generarReporteFacturasPendientes(Integer acreedor_id, Integer pais_id, Integer moneda_id){
        
        Object parametros = new ReporteFacturasPendientesParametrosBuilder()
            .conIdAcreedor(acreedor_id)
            .conIdPais(pais_id)
            .conIdMoneda(moneda_id)
            .build();
    
        String sql = "{call pa_sp_reporte_facturas_pendientes(?, ?, ?)}";
        Boolean conTransaccion = true;

        this.ejecutarProcedimientoAlmacenado(
            sql, this::incluirValorDeParametrosParaGenerarReporteFacturasPendientes, parametros, conTransaccion);
    }
    
    private void incluirValorDeParametrosParaGenerarReporteFacturasPendientes(Object objetoParametros){
        ReporteFacturasPendientesParametros parametros = (ReporteFacturasPendientesParametros) objetoParametros;
        try {            
            this.statement.setInt(1, parametros.getIdAcreedor());
            this.statement.setInt(2, parametros.getIdPais());
            this.statement.setInt(3, parametros.getIdMoneda());
        } catch (SQLException ex) {
            System.getLogger(ReporteFactPendDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    @Override
    public ArrayList<ReporteFactPendDTO> listarPorFiltros(Integer acreedor_id, Integer pais_id, Integer moneda_id) {
        Object parametros = new ReporteFacturasPendientesParametrosBuilder()
            .conIdAcreedor(acreedor_id)
            .conIdPais(pais_id)
            .conIdMoneda(moneda_id)
            .build();
        
        String sql = this.generarSQLParaListarPorFiltros();        
        return (ArrayList<ReporteFactPendDTO>) super.listarTodos(sql, this::incluirValorDeParametrosParaListarPorFiltros, parametros);
    }

    private String generarSQLParaListarPorFiltros() {
        String sql = "SELECT ";

        sql = sql.concat("r.ACREEDOR_ID, ");
        sql = sql.concat("r.CODIGO_MONEDA, ");
        sql = sql.concat("r.DIAS_VENCIMIENTO, ");
        sql = sql.concat("r.FACTURA_ID, ");
        sql = sql.concat("r.FECHA_LIMITE_PAGO, ");
        sql = sql.concat("r.MONEDA_ID, ");
        sql = sql.concat("r.MONTO_RESTANTE, ");
        sql = sql.concat("r.NOMBRE_PAIS, ");
        sql = sql.concat("r.NUMERO_FACTURA, ");
        sql = sql.concat("r.PAIS_ID, ");
        sql = sql.concat("r.RANGO_VENCIMIENTO, ");
        sql = sql.concat("r.RAZON_SOCIAL ");

        sql = sql.concat("FROM PA_REPORTES_FACTURAS_PENDIENTES r ");

        sql = sql.concat("WHERE (? = 0 OR r.ACREEDOR_ID = ?) ");
        sql = sql.concat("AND (? = 0 OR r.PAIS_ID = ?) ");
        sql = sql.concat("AND (? = 0 OR r.MONEDA_ID = ?) ");

        sql = sql.concat("ORDER BY r.RANGO_VENCIMIENTO");
        
        return sql;
    }
    

    private void incluirValorDeParametrosParaListarPorFiltros(Object objetoParametros) {
        ReporteFacturasPendientesParametros parametros = (ReporteFacturasPendientesParametros) objetoParametros;
        try {            
            //TODO
            this.statement.setInt(1, parametros.getIdAcreedor());
            this.statement.setInt(2, parametros.getIdAcreedor());
            this.statement.setInt(3, parametros.getIdPais());
            this.statement.setInt(4, parametros.getIdPais());
            this.statement.setInt(5, parametros.getIdMoneda());
            this.statement.setInt(6, parametros.getIdMoneda());
        } catch (SQLException ex) {
            System.getLogger(ReporteFactPendDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.reporte = new ReporteFactPendDTO();

        // 🔹 Campos propios del reporte
        this.reporte.setDias_vencimiento(this.resultSet.getInt("DIAS_VENCIMIENTO"));
        this.reporte.setRango_vencimiento(this.resultSet.getString("RANGO_VENCIMIENTO"));
//
//    // 🔹 Acreedor
      AcreedoresDTO acreedor = new AcreedoresDTO();
      acreedor.setAcreedor_id(this.resultSet.getInt("ACREEDOR_ID"));
      acreedor.setRazon_social(this.resultSet.getString("RAZON_SOCIAL"));
      this.reporte.setAcreedor(acreedor);
//
//    // 🔹 País (desde PA_PAISES)
      PaisesDTO pais = new PaisesDTO();
      pais.setPais_id(this.resultSet.getInt("PAIS_ID"));
      pais.setNombre(this.resultSet.getString("NOMBRE_PAIS"));
      this.reporte.setPais(pais);

    // 🔹 Moneda (desde PA_MONEDAS)
      MonedasDTO moneda = new MonedasDTO();
      moneda.setMoneda_id(this.resultSet.getInt("MONEDA_ID"));
      moneda.setCodigo_iso(this.resultSet.getString("CODIGO_MONEDA"));
      this.reporte.setMoneda(moneda);

    // 🔹 Factura
      FacturasDTO factura = new FacturasDTO();
      factura.setFactura_id(this.resultSet.getInt("FACTURA_ID"));
      factura.setNumero_factura(this.resultSet.getString("NUMERO_FACTURA"));
      factura.setFecha_limite_pago(this.resultSet.getTimestamp("FECHA_LIMITE_PAGO"));
      factura.setMonto_restante(this.resultSet.getBigDecimal("MONTO_RESTANTE"));
      this.reporte.setFactura(factura);

    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.reporte);
    }

    
}