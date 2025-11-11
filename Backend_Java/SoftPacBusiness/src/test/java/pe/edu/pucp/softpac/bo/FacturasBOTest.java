/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpac.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.softpac.model.FacturasDTO;

/**
 *
 * @author USUARIO
 */
public class FacturasBOTest {
    private FacturasBO facturasBOTest;
    
    public FacturasBOTest(){
        facturasBOTest = new FacturasBO();
        
    }
    
//    @Test
//    public void listarFiltrosTest() throws ParseException{
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date fecha = sdf.parse("2024-10-20 23:59:59");
//        ArrayList<FacturasDTO> facturasGenerales = facturasBOTest.listarPendientesPorCriterios(1, fecha);
//        
//        if(facturasGenerales == null) System.out.println("Lista nula");
//        
//        for(var f : facturasGenerales){
//            System.out.println(f.getFactura_id());
//            System.out.println(f.getFecha_limite_pago());
//        }
//        
//    }
}
