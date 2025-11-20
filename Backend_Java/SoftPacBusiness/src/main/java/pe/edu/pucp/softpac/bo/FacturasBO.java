package pe.edu.pucp.softpac.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        factura.setFecha_eliminacion(new Date());
        factura.setUsuario_eliminacion(usuarioActual);
        
        return facturasDAO.eliminarLogico(factura);
    }
    
    public Integer insertarDetalle(DetallesFacturaDTO detalle){
        detalle.getFactura().addDetalle_Factura(detalle);
        return this.facturasDAO.modificar(detalle.getFactura());
    }
    
    public Integer modificarDetalle(DetallesFacturaDTO detalle){
        detalle.getFactura().addDetalle_Factura(detalle);
        return this.facturasDAO.modificar(detalle.getFactura());
    }
    
    public Integer eliminarDetalle(DetallesFacturaDTO detalle, UsuariosDTO usuarioActual){
        detalle.setFecha_eliminacion(new Date());
        detalle.setUsuario_eliminacion(usuarioActual);
        detalle.getFactura().addDetalle_Factura(detalle);
        return this.facturasDAO.modificar(detalle.getFactura());
    }   
    
    public Integer insertar(FacturasDTO factura){
        
        return this.facturasDAO.insertar(factura);
        
    }
    
    public Integer modificar(FacturasDTO factura){
        if(factura.getDetalles_Factura()!=null){
            for(DetallesFacturaDTO detalle : factura.getDetalles_Factura()){
                detalle.setFactura(factura);
            }
        }
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
    
    public ArrayList<FacturasDTO> listarPendientesPorCriterios(Integer paisId, Date fechaLimite) {
    ArrayList<FacturasDTO> resultado = new ArrayList<>();

        ArrayList<FacturasDTO> facturasGenerales = (ArrayList<FacturasDTO>) facturasDAO.listarFiltros();

        for (FacturasDTO factura : facturasGenerales) {
            if (
                factura != null
                && "Pendiente".equals(factura.getEstado())
                && factura.getAcreedor() != null
                && factura.getAcreedor().getPais() != null
                && factura.getAcreedor().getPais().getPais_id().equals(paisId)
                && factura.getFecha_limite_pago() != null
                && ( !factura.getFecha_limite_pago().after(fechaLimite) )
            ) {
                resultado.add(factura);
            }
        }
        return resultado;
    }
    
    public void vencerFacturas() {
        
        try {
            ArrayList<FacturasDTO> facturas = listarTodos();
        
            for (FacturasDTO factura : facturas)
            {
                if (factura.getFecha_limite_pago().before(new Date()) && "Pendiente".equals(factura.getEstado()))
                {
                    factura.setEstado("Vencida");
                    modificar(factura);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    
}