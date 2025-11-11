package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.ReportePropPagoBO;
import pe.edu.pucp.softpac.model.ReportePropPagoDTO;

@WebService(serviceName = "ReportePropPagoWS")
public class ReportePropPagoWS {
    
    private ReportePropPagoBO reportePropPagoBO;
    
    public ReportePropPagoWS(){
        this.reportePropPagoBO = new ReportePropPagoBO();
    }
    
    
    @WebMethod(operationName = "listarReportePropuesta")
    public ArrayList<ReportePropPagoDTO> listarReporteConFiltros(
            @WebParam(name = "pais_id") Integer pais_id,
            @WebParam(name = "banco_id") Integer banco_id,
            @WebParam(name = "dias_desde") Integer dias_desde) {
        return reportePropPagoBO.listarPorFiltros(pais_id, banco_id, dias_desde);
    }
}