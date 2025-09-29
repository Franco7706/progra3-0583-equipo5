package pe.edu.pucp.softpac.bo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import pe.edu.pucp.softpac.dao.FacturasDAO;
import pe.edu.pucp.softpac.daoImpl.FacturasDAOImpl;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.DetallesFacturaDTO;
import pe.edu.pucp.softpac.model.FacturasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;

public class FacturasBO {
    
    private FacturasDAO facturasDAO;
    private DetallesFacturaBO detallesFacturaBO;
    
    public FacturasBO(){
        facturasDAO = new FacturasDAOImpl();
        detallesFacturaBO = new DetallesFacturaBO();
    }
    
    public Integer insertar(String numero_factura, LocalDateTime fecha_emision,
            LocalDateTime fecha_recepcion, LocalDateTime fecha_limite_pago,
            String estado, BigDecimal monto_total, BigDecimal monto_igv,
            BigDecimal monto_restante, String regimen_fiscal, BigDecimal tasa_iva,
            BigDecimal otros_tributos, Integer acreedor_id, Integer moneda_id){
        FacturasDTO facturasDTO = new FacturasDTO();
        facturasDTO.setNumero_factura(numero_factura);
        facturasDTO.setFecha_emision(fecha_emision);
        facturasDTO.setFecha_recepcion(fecha_recepcion);
        facturasDTO.setFecha_limite_pago(fecha_limite_pago);
        facturasDTO.setMonto_total(monto_total);
        facturasDTO.setMonto_igv(monto_igv);
        facturasDTO.setMonto_restante(monto_restante);
        facturasDTO.setTasa_iva(tasa_iva);
        facturasDTO.setRegimen_fiscal(regimen_fiscal);
        facturasDTO.setOtros_tributos(otros_tributos);
        AcreedoresDTO acreedoresDTO = new AcreedoresDTO();
        MonedasDTO  monedasDTO = new MonedasDTO();
        acreedoresDTO.setAcreedor_id(acreedor_id);
        monedasDTO.setMoneda_id(moneda_id);
        
        facturasDTO.setAcreedor(acreedoresDTO);
        facturasDTO.setMoneda(monedasDTO);
        return this.facturasDAO.insertar(facturasDTO);
        
    }
    
    public Integer modificarConParametros(Integer factura_id, String numero_factura, LocalDateTime fecha_emision,
            LocalDateTime fecha_recepcion, LocalDateTime fecha_limite_pago,
            String estado, BigDecimal monto_total, BigDecimal monto_igv,
            BigDecimal monto_restante, String regimen_fiscal, BigDecimal tasa_iva,
            BigDecimal otros_tributos, Integer acreedor_id, Integer moneda_id){
        FacturasDTO facturaExistente = obtenerPorId(factura_id);
        if(facturaExistente == null){
            return 0; //No se puede hacer la modificación
        }
        
        if(facturaExistente.getEstado().equals("PAGADA")){
            return 0;
        }
        
        if(facturaExistente.getMonto_total() != facturaExistente.getMonto_restante()){
            return 0; //Pertenece a una propuesta de pago
        }
        
        //Realizamos la modificación
        FacturasDTO facturasDTO = new FacturasDTO();
        facturasDTO.setFactura_id(factura_id);
        facturasDTO.setNumero_factura(numero_factura);
        facturasDTO.setFecha_emision(fecha_emision);
        facturasDTO.setFecha_recepcion(fecha_recepcion);
        facturasDTO.setFecha_limite_pago(fecha_limite_pago);
        facturasDTO.setMonto_total(monto_total);
        facturasDTO.setMonto_igv(monto_igv);
        facturasDTO.setMonto_restante(monto_restante);
        facturasDTO.setTasa_iva(tasa_iva);
        facturasDTO.setRegimen_fiscal(regimen_fiscal);
        facturasDTO.setOtros_tributos(otros_tributos);
        AcreedoresDTO acreedoresDTO = new AcreedoresDTO();
        MonedasDTO  monedasDTO = new MonedasDTO();
        acreedoresDTO.setAcreedor_id(acreedor_id);
        monedasDTO.setMoneda_id(moneda_id);
        
        return modificarConDTO(facturasDTO);
                
    }
    
    public Integer modificarConDTO(FacturasDTO facturasDTO){
        FacturasDTO facturaExistente = obtenerPorId(facturasDTO.getFactura_id());
        if(facturaExistente == null){
            return 0; //No se puede hacer la modificación
        }
        
        if(facturaExistente.getEstado().equals("PAGADA")){
            return 0;
        }
        
        if(facturaExistente.getMonto_total() != facturaExistente.getMonto_restante()){
            return 0; //Pertenece a una propuesta de pago
        }
        
        facturasDTO.setFactura_id(facturasDTO.getFactura_id());
        facturasDTO.setNumero_factura(facturaExistente.getNumero_factura());
        facturasDTO.setFecha_emision(facturaExistente.getFecha_emision());
        facturasDTO.setFecha_recepcion(facturaExistente.getFecha_recepcion());
        facturasDTO.setFecha_limite_pago(facturaExistente.getFecha_limite_pago());
        facturasDTO.setMonto_igv(facturaExistente.getMonto_igv());
        facturasDTO.setMonto_total(facturaExistente.getMonto_total());
        facturasDTO.setMonto_restante(facturaExistente.getMonto_restante());
        facturasDTO.setOtros_tributos(facturaExistente.getOtros_tributos());
        facturasDTO.setRegimen_fiscal(facturaExistente.getRegimen_fiscal());
        facturasDTO.setTasa_iva(facturaExistente.getTasa_iva());
        facturasDTO.setAcreedor(facturaExistente.getAcreedor());
        facturasDTO.setMoneda(facturaExistente.getMoneda());
        
        return this.facturasDAO.modificar(facturasDTO);
        
    }
    
    public FacturasDTO obtenerPorId(Integer factura_id){
        FacturasDTO facturaDTO = new FacturasDTO();
        facturaDTO.setFactura_id(factura_id);
        return this.facturasDAO.obtenerPorId(factura_id);
    }
    
    public Integer agregarDetalle(Integer factura_id, ArrayList<DetallesFacturaDTO> detalles){
        FacturasDTO factura = new FacturasDTO();
        factura = obtenerPorId(factura_id);
        for(DetallesFacturaDTO detalle: detalles){
            detalle.setFactura(factura);
            
            if(detallesFacturaBO.insertar(detalle) == null){
                return null;
            }
            
            factura.addDetalle_Factura(detalle);
        }
                
        return 1; //Se logró colocar los detalles de todos
        
    }
    
    
    
    
}
