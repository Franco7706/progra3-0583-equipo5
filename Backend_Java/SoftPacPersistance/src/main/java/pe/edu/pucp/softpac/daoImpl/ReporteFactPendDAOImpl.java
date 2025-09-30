package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import pe.edu.pucp.softpac.dao.ReporteFactPendDAO;
import pe.edu.pucp.softpac.daoImpl.util.ReporteFacturasPendientesParametrosBuilder;
import pe.edu.pucp.softpac.daoImpl.util.ReporteFacturasPendientesParametros;


public class ReporteFactPendDAOImpl extends DAOImplBase implements ReporteFactPendDAO {

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

    
}