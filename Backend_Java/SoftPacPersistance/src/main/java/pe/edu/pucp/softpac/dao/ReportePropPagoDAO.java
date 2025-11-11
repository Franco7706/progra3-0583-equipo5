package pe.edu.pucp.softpac.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.softpac.model.ReportePropPagoDTO;


public interface ReportePropPagoDAO {
    
    //public void generarReportePropuestasDePago(Integer pais_id, Integer entidad_bancaria_id);
     
    public ArrayList<ReportePropPagoDTO> listarPorFiltros(Integer pais_id, Integer entidad_bancaria_id, LocalDateTime fecha_inicio, LocalDateTime fecha_fin);

}