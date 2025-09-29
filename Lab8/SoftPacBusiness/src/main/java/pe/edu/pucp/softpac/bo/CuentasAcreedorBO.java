package pe.edu.pucp.softpac.bo;

import pe.edu.pucp.softpac.dao.CuentasAcreedorDAO;
import pe.edu.pucp.softpac.daoImpl.CuentasAcreedorDAOImpl;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;

public class CuentasAcreedorBO {
    private CuentasAcreedorDAO cuentaAcreedorDAO;
    
    public CuentasAcreedorBO(){
        cuentaAcreedorDAO = new CuentasAcreedorDAOImpl();
    }
    
    public Integer insertar(String tipo_de_cuenta, String numero_cuenta,
            String cci, String activa, Integer acreedor_id, Integer entidad_bancaria_id,
            Integer moneda_id){
        CuentasAcreedorDTO cuentasAcreedorDTO = new CuentasAcreedorDTO();
        cuentasAcreedorDTO.setTipo_cuenta(tipo_de_cuenta);
        cuentasAcreedorDTO.setNumero_cuenta(numero_cuenta);
        cuentasAcreedorDTO.setCci(cci);
        cuentasAcreedorDTO.setActiva(activa.equals("S") ? Boolean.TRUE : Boolean.FALSE);
        AcreedoresDTO acreedor = new AcreedoresDTO();
        MonedasDTO moneda = new MonedasDTO();
        
        acreedor.setAcreedor_id(acreedor_id);
        moneda.setMoneda_id(moneda_id);
        
        cuentasAcreedorDTO.setCuenta_bancaria_id(entidad_bancaria_id);
        cuentasAcreedorDTO.setAcreedor(acreedor);
        cuentasAcreedorDTO.setMoneda(moneda);
        
        return this.cuentaAcreedorDAO.insertar(cuentasAcreedorDTO);
    }
    
    
    public Integer modificar(Integer cuenta_acreedor_id, String tipo_de_cuenta, String numero_cuenta,
            String cci, String activa, Integer acreedor_id, Integer entidad_bancaria_id,
            Integer moneda_id){
        CuentasAcreedorDTO cuentasAcreedorDTO = new CuentasAcreedorDTO();
        cuentasAcreedorDTO.setCuenta_bancaria_id(entidad_bancaria_id);
        cuentasAcreedorDTO.setTipo_cuenta(tipo_de_cuenta);
        cuentasAcreedorDTO.setNumero_cuenta(numero_cuenta);
        cuentasAcreedorDTO.setCci(cci);
        cuentasAcreedorDTO.setActiva(activa.equals("S") ? Boolean.TRUE : Boolean.FALSE);
        AcreedoresDTO acreedor = new AcreedoresDTO();
        MonedasDTO moneda = new MonedasDTO();
        
        acreedor.setAcreedor_id(acreedor_id);
        moneda.setMoneda_id(moneda_id);
        
        cuentasAcreedorDTO.setCuenta_bancaria_id(entidad_bancaria_id);
        cuentasAcreedorDTO.setAcreedor(acreedor);
        cuentasAcreedorDTO.setMoneda(moneda);
        
        return this.cuentaAcreedorDAO.modificar(cuentasAcreedorDTO);
    }
    
    public CuentasAcreedorDTO obtenerPorId(Integer cuenta_acreedor_id){
        CuentasAcreedorDTO cuentaAcreedorDTO = new CuentasAcreedorDTO();
        cuentaAcreedorDTO.setCuenta_bancaria_id(cuenta_acreedor_id);
        return this.cuentaAcreedorDAO.obtenerPorId(cuenta_acreedor_id);
    }
    
}
