package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.ReporteFactPendBO;
import pe.edu.pucp.softpac.model.ReporteFactPendDTO;

@WebService(serviceName = "ReporteFactPendWS")
public class ReporteFactPendWS {
    
    private ReporteFactPendBO reporteFactPendBO;
    
    public ReporteFactPendWS(){
        this.reporteFactPendBO = new ReporteFactPendBO();
    }
    
    @WebMethod(operationName = "generarReporteFacturasPendientes")
    public void generarReporteFacturasPendientes(
            @WebParam(name = "acreedor_id") Integer acreedor_id,
            @WebParam(name = "pais_id") Integer pais_id,
            @WebParam(name = "moneda_id") Integer moneda_id) {
        reporteFactPendBO.generarReporteFacturasPendientes(acreedor_id, pais_id, moneda_id);
    }
    
    @WebMethod(operationName = "listarPorFiltros")
    public ArrayList<ReporteFactPendDTO> listarPorFiltros(
            @WebParam(name = "acreedor_id") Integer acreedor_id,
            @WebParam(name = "pais_id") Integer pais_id,
            @WebParam(name = "moneda_id") Integer moneda_id) {
        return reporteFactPendBO.listarPorFiltros(acreedor_id, pais_id, moneda_id);
    }
}