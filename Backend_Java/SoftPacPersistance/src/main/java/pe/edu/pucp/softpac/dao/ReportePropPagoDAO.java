package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softpac.model.ReportePropPagoDTO;


public interface ReportePropPagoDAO {
    
    //public void generarReportePropuestasDePago(Integer pais_id, Integer entidad_bancaria_id);
     
    public ArrayList<ReportePropPagoDTO> listarPorFiltros(Integer pais_id, Integer entidad_bancaria_id, Date fecha_inicio, Date fecha_fin);

}