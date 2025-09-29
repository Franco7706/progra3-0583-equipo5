package pe.edu.pucp.softpac.bo;

import java.math.BigDecimal;
import pe.edu.pucp.softpac.dao.CuentasPropiasDAO;
import pe.edu.pucp.softpac.daoImpl.CuentasPropiasDAOImpl;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;

public class CuentasPropiasBO {
    private CuentasPropiasDAO cuentasPropiasDAO;
    
    public CuentasPropiasBO(){
        cuentasPropiasDAO = new CuentasPropiasDAOImpl();
    }
    
    public Integer insertar(BigDecimal saldo_disponible, String tipo_de_cuenta, 
            String numero_cuenta, String cci, String activa, Integer entidad_bancaria_id,
            Integer moneda_id){
        CuentasPropiasDTO cuentasPropiasDTO = new CuentasPropiasDTO();
        cuentasPropiasDTO.setTipo_cuenta(tipo_de_cuenta);
        cuentasPropiasDTO.setNumero_cuenta(numero_cuenta);
        cuentasPropiasDTO.setCci(cci);
        cuentasPropiasDTO.setActiva(activa.equals("S") ? Boolean.TRUE : Boolean.FALSE);
        MonedasDTO moneda = new MonedasDTO();
        
        moneda.setMoneda_id(moneda_id);
        
        cuentasPropiasDTO.setCuenta_bancaria_id(entidad_bancaria_id);
        cuentasPropiasDTO.setMoneda(moneda);
        
        return this.cuentasPropiasDAO.insertar(cuentasPropiasDTO);
    }
    
    public Integer modificar(Integer cuentas_propias_id, BigDecimal saldo_disponible, String tipo_de_cuenta, 
            String numero_cuenta, String cci, String activa, Integer entidad_bancaria_id,
            Integer moneda_id){
        CuentasPropiasDTO cuentasPropiasDTO = new CuentasPropiasDTO();
        cuentasPropiasDTO.setCuenta_bancaria_id(entidad_bancaria_id);
        cuentasPropiasDTO.setTipo_cuenta(tipo_de_cuenta);
        cuentasPropiasDTO.setNumero_cuenta(numero_cuenta);
        cuentasPropiasDTO.setCci(cci);
        cuentasPropiasDTO.setActiva(activa.equals("S") ? Boolean.TRUE : Boolean.FALSE);
        MonedasDTO moneda = new MonedasDTO();
        
        moneda.setMoneda_id(moneda_id);
        
        cuentasPropiasDTO.setCuenta_bancaria_id(entidad_bancaria_id);
        cuentasPropiasDTO.setMoneda(moneda);
        
        return this.cuentasPropiasDAO.modificar(cuentasPropiasDTO);
    }
    
    public CuentasPropiasDTO obtenerPorId(Integer cuentas_propias_id){
        CuentasPropiasDTO cuentasPropiasDTO = new CuentasPropiasDTO();
        cuentasPropiasDTO.setCuenta_bancaria_id(cuentas_propias_id);
        return this.cuentasPropiasDAO.obtenerPorId(cuentas_propias_id);
    }
    
}
