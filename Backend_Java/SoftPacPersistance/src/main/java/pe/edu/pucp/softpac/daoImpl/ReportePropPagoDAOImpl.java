package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import pe.edu.pucp.softpac.dao.ReportePropPagoDAO;
import pe.edu.pucp.softpac.daoImpl.util.ReportePropuestasPagoParametrosBuilder;
import pe.edu.pucp.softpac.daoImpl.util.ReportePropuestasPagoParametros;


public class ReportePropPagoDAOImpl extends DAOImplBase implements ReportePropPagoDAO {

    public ReportePropPagoDAOImpl() {
        super("PA_REPORTE_PROPUESTAS_DE_PAGO");
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        
    }
    
    //METODO PRINCIPAL. LLAMARÁ A EL PROCEDURE ALMACENADO: "pa_sp_reporte_propuestas_de_pago(país,entidad bancaría)"
    @Override
    public void generarReportePropuestasDePago(Integer pais_id, Integer entidad_bancaria_id){
        
        Object parametros = new ReportePropuestasPagoParametrosBuilder()
            .conIdPais(pais_id)
            .conIdBanco(entidad_bancaria_id)
            .build();
    
        String sql = "{call pa_sp_reporte_propuestas_de_pago(?, ?)}";
        Boolean conTransaccion = true;

        this.ejecutarProcedimientoAlmacenado(
            sql, this::incluirValorDeParametrosParaGenerarReportePropuestasPendientes, parametros, conTransaccion);
    }
    
    private void incluirValorDeParametrosParaGenerarReportePropuestasPendientes(Object objetoParametros){
        ReportePropuestasPagoParametros parametros = (ReportePropuestasPagoParametros) objetoParametros;
        try {            
            this.statement.setInt(1, parametros.getIdPais());
            this.statement.setInt(2, parametros.getIdBanco());
        } catch (SQLException ex) {
            System.getLogger(ReportePropPagoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    
}