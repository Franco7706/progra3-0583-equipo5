/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpac.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import pe.edu.pucp.softpac.dao.ReportePropPagoDAO;
import pe.edu.pucp.softpac.daoImpl.ReportePropPagoDAOImpl;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;
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
        Date hoy = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoy);
        calendar.add(Calendar.DAY_OF_YEAR,-dias_desde);
        Date inicio = calendar.getTime();
        return reportePropPagoDAO.listarPorFiltros(pais_id, entidad_bancaria_id, inicio, hoy);
    }
}
