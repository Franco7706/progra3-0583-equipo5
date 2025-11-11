
package pe.edu.pucp.softpac.bo;

import java.math.BigDecimal;
import java.util.Date;
import pe.edu.pucp.softpac.dao.TiposDeCambioDAO;
import pe.edu.pucp.softpac.daoImpl.TiposDeCambioDAOImpl;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.TiposDeCambioDTO;

public class TiposDeCambioBO {
    private TiposDeCambioDAO tiposDeCambioDAO;
    
    public TiposDeCambioBO(){
        tiposDeCambioDAO = new TiposDeCambioDAOImpl();
    }
    
    public Integer insertar(Date fecha, BigDecimal tasa_de_cambio, 
            Integer moneda_origen_id, Integer moneda_destino_id){
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        if (tasa_de_cambio == null || tasa_de_cambio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tasa de cambio debe ser mayor a 0");
        }
        if (moneda_origen_id == null || moneda_origen_id <= 0) {
            throw new IllegalArgumentException("La moneda origen es obligatoria");
        }
        if (moneda_destino_id == null || moneda_destino_id <= 0) {
            throw new IllegalArgumentException("La moneda destino es obligatoria");
        }
        if (moneda_origen_id.equals(moneda_destino_id)) {
            throw new IllegalArgumentException("Las monedas origen y destino deben ser diferentes");
        }
        
        TiposDeCambioDTO tiposDeCambioDTO = new TiposDeCambioDTO();
        tiposDeCambioDTO.setFecha(fecha);
        tiposDeCambioDTO.setTasa_de_cambio(tasa_de_cambio);
        
        MonedasDTO moneda_origen = new MonedasDTO();
        MonedasDTO moneda_destino = new MonedasDTO();
        moneda_origen.setMoneda_id(moneda_origen_id);
        moneda_destino.setMoneda_id(moneda_destino_id);
        
        tiposDeCambioDTO.setMoneda_origen(moneda_origen);
        tiposDeCambioDTO.setMoneda_destino(moneda_destino);
        return this.tiposDeCambioDAO.insertar(tiposDeCambioDTO);
        
    }
    
    public Integer modificar(Integer tipo_cambio_id, Date fecha, BigDecimal tasa_de_cambio, 
            Integer moneda_origen_id, Integer moneda_destino_id){
        TiposDeCambioDTO tiposDeCambioDTO = new TiposDeCambioDTO();
        tiposDeCambioDTO.setTipo_cambio_id(tipo_cambio_id);
        tiposDeCambioDTO.setFecha(fecha);
        tiposDeCambioDTO.setTasa_de_cambio(tasa_de_cambio);
        
        MonedasDTO moneda_origen = new MonedasDTO();
        MonedasDTO moneda_destino = new MonedasDTO();
        moneda_origen.setMoneda_id(moneda_origen_id);
        moneda_destino.setMoneda_id(moneda_destino_id);
        
        tiposDeCambioDTO.setMoneda_origen(moneda_origen);
        tiposDeCambioDTO.setMoneda_destino(moneda_destino);
        return this.tiposDeCambioDAO.modificar(tiposDeCambioDTO);
    }
    
    public TiposDeCambioDTO obtenerPorId(Integer tipo_de_cambio_id){
        TiposDeCambioDTO tiposDeCambioDTO = new TiposDeCambioDTO();
        tiposDeCambioDTO.setTipo_cambio_id(tipo_de_cambio_id);
        return this.tiposDeCambioDAO.obtenerPorId(tipo_de_cambio_id);
        
    }
       
}
