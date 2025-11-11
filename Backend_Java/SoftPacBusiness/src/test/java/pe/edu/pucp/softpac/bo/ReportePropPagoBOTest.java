/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.softpac.bo;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softpac.model.ReportePropPagoDTO;

/**
 *
 * @author Usuario
 */
public class ReportePropPagoBOTest {
    
    private ReportePropPagoBO reportePropPagoBO;
    
    public ReportePropPagoBOTest() {
        reportePropPagoBO = new ReportePropPagoBO();
    }

    @Test
    public void testListarPorFiltros() {
        ArrayList<ReportePropPagoDTO> reporte = reportePropPagoBO.listarPorFiltros(1,1,30);
        for (ReportePropPagoDTO prop : reporte){
            System.out.println(prop.getFechaCreacion());
        }
    }
    
}
