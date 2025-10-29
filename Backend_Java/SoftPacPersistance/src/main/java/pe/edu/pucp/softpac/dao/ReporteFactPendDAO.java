package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.ReporteFactPendDTO;


public interface ReporteFactPendDAO {
    
    public ArrayList<ReporteFactPendDTO> listarPorFiltros(Integer acreedor_id, Integer pais_id, Integer moneda_id);
    
    public void generarReporteFacturasPendientes(Integer acreedor_id, Integer pais_id, Integer moneda_id);
    
}
