/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpac.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.ReportePropPagoDAO;
import pe.edu.pucp.softpac.daoImpl.ReportePropPagoDAOImpl;
import pe.edu.pucp.softpac.model.ReportePropPagoDTO;

/**
 *
 * @author Usuario
 */
public class ReportePropPagoBO {
    private ReportePropPagoDAO reportePropPagoDAO;
    
    public ReportePropPagoBO(){
        this.reportePropPagoDAO = new ReportePropPagoDAOImpl();
    }
    
    public ArrayList<ReportePropPagoDTO> listarPorFiltros(Integer pais_id, Integer entidad_bancaria_id, Integer dias_desde){
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime inicio = hoy.minusDays(dias_desde);
        return reportePropPagoDAO.listarPorFiltros(pais_id, entidad_bancaria_id, inicio, hoy);
    }
}
