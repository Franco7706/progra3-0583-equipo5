package pe.edu.pucp.softpac.bo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.FacturasDAO;
import pe.edu.pucp.softpac.daoImpl.FacturasDAOImpl;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.DetallesFacturaDTO;
import pe.edu.pucp.softpac.model.FacturasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public class FacturasBO {
    
    private FacturasDAO facturasDAO;
    
    
    public FacturasBO(){
        facturasDAO = new FacturasDAOImpl();
   
    }
    
    public ArrayList<FacturasDTO> listarTodos(){
        return (ArrayList<FacturasDTO>) facturasDAO.listarTodos();
    }
    
    public Integer eliminarLogico(FacturasDTO factura, UsuariosDTO usuarioActual){
        factura.setFecha_eliminacion(LocalDateTime.now());
        factura.setUsuario_eliminacion(usuarioActual);
        
        return facturasDAO.eliminarLogico(factura);
    }
    
    public Integer insertarDetalle(DetallesFacturaDTO detalle){
        return this.facturasDAO.insertar(detalle.getFactura());
    }
    
    public Integer modificarDetalle(DetallesFacturaDTO detalle){
        return this.facturasDAO.modificar(detalle.getFactura());
    }
    
    public Integer eliminarDetalle(DetallesFacturaDTO detalle, UsuariosDTO usuarioActual){
        detalle.getFactura().setFecha_eliminacion(LocalDateTime.now());
        detalle.getFactura().setUsuario_eliminacion(usuarioActual);
        return this.facturasDAO.eliminarLogico(detalle.getFactura());
    }   
    
    public Integer insertar(FacturasDTO factura){
        
        return this.facturasDAO.insertar(factura);
        
    }
    
    public Integer modificar(FacturasDTO factura){
        
        return this.facturasDAO.modificar(factura);
                
    }
    
    public ArrayList<FacturasDTO> listarPendientes(){
        ArrayList<FacturasDTO> facturasPendientes = new ArrayList<>();
        ArrayList<FacturasDTO> facturasGenerales = (ArrayList<FacturasDTO>) facturasDAO.listarTodos();
        for(FacturasDTO factura : facturasGenerales){
            if(factura.getEstado().equals("Pendiente")){
                facturasPendientes.add(factura);
            }
        }
        
        return facturasPendientes;
        
    }
 
    public FacturasDTO obtenerPorId(Integer factura_id){
        if (factura_id == null || factura_id <= 0) {
            throw new IllegalArgumentException("El ID de la factura es obligatorio");
        }
        
        FacturasDTO factura = new FacturasDTO();
        factura.setFactura_id(factura_id);
        return this.facturasDAO.obtenerPorId(factura_id);
    }
    
    
}