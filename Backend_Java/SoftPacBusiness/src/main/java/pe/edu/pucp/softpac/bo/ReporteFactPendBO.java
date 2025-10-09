package pe.edu.pucp.softpac.bo;

//Los import de las clases que necesito. DAO, DAOImpl y DTO.
import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.ReporteFactPendDAO;
import pe.edu.pucp.softpac.daoImpl.ReporteFactPendDAOImpl;
import pe.edu.pucp.softpac.model.ReporteFactPendDTO;
//import pe.edu.pucp.softpac.model.;

public class ReporteFactPendBO {

    private ReporteFactPendDAO reporteFactPendDAO;
    
    public ReporteFactPendBO(){
        reporteFactPendDAO = new ReporteFactPendDAOImpl();
    }
    
    public void generarReporteFacturasPendientes(Integer acreedor_id, Integer pais_id, Integer moneda_id){
        reporteFactPendDAO.generarReporteFacturasPendientes(acreedor_id, pais_id, moneda_id);
    }
    
    // Nuevo método que retorna la lista de facturas filtradas
    public ArrayList<ReporteFactPendDTO> listarPorFiltros(Integer acreedor_id, Integer pais_id, Integer moneda_id) {
        return reporteFactPendDAO.listarPorFiltros(acreedor_id, pais_id, moneda_id);
    }

    
}