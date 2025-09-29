package pe.edu.pucp.softpac.bo;

import java.math.BigDecimal;
import pe.edu.pucp.softpac.dao.DetallesFacturaDAO;
import pe.edu.pucp.softpac.daoImpl.DetallesFacturaDAOImpl;
import pe.edu.pucp.softpac.model.DetallesFacturaDTO;

public class DetallesFacturaBO {
    private DetallesFacturaDAO detalleFacturaDAO;
    
    public DetallesFacturaBO(){
        detalleFacturaDAO = new DetallesFacturaDAOImpl();
    }
    
    public Integer insertar(DetallesFacturaDTO detalleFactura){
        if(detalleFactura.getSubtotal().compareTo(BigDecimal.ZERO) == -1){
            return null; //En caso se envíe un subtotal negativo
        }
        
        if(detalleFactura.getFactura() == null){
            return null;
        }
        
        DetallesFacturaDTO detallesFacturaDTO = new DetallesFacturaDTO(detalleFactura);
        
        return this.detalleFacturaDAO.insertar(detallesFacturaDTO);
       
    }
    
    public Integer modificar(DetallesFacturaDTO detalleFactura){
        if(detalleFactura.getSubtotal().compareTo(BigDecimal.ZERO) == -1){
            return null; //En caso se envíe un subtotal negativo
        }
        
        if(detalleFactura.getFactura() == null){
            return null;
        }
        
        DetallesFacturaDTO detallesFacturaDTO = new DetallesFacturaDTO(detalleFactura);
        
        return this.detalleFacturaDAO.modificar(detalleFactura);       
        
    }
    
    public DetallesFacturaDTO obtenerPorId(Integer detalle_factura_id){
        DetallesFacturaDTO detallesFacturaDTO = new DetallesFacturaDTO();
        detallesFacturaDTO.setDetalle_factura_id(detalle_factura_id);
        return this.detalleFacturaDAO.obtenerPorId(detalle_factura_id);
    }
    
    
    
    
}
