package pe.edu.pucp.softpac.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softpac.dao.CuentasAcreedorDAO;
import pe.edu.pucp.softpac.daoImpl.CuentasAcreedorDAOImpl;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public class CuentasAcreedorBO {
    private CuentasAcreedorDAO cuentaAcreedorDAO;
   
    public CuentasAcreedorBO(){
        cuentaAcreedorDAO = new CuentasAcreedorDAOImpl();
    }
    
     public ArrayList<CuentasAcreedorDTO> obtenerPorAcreedor(Integer acreedor_id){
        if (acreedor_id == null || acreedor_id <= 0) {
            throw new IllegalArgumentException("El ID del acreedor es obligatorio");
        }
        return cuentaAcreedorDAO.obtenerPorAcreedor(acreedor_id);
    }
    
    
    
    public ArrayList<CuentasAcreedorDTO> listarTodos(){
        return (ArrayList<CuentasAcreedorDTO>) cuentaAcreedorDAO.listarTodos();
    }
    
    public CuentasAcreedorDTO obtenerPorId(Integer cuenta_acreedor_id){
        CuentasAcreedorDTO cuentaAcreedorDTO = new CuentasAcreedorDTO();
        cuentaAcreedorDTO.setCuenta_bancaria_id(cuenta_acreedor_id);
        return this.cuentaAcreedorDAO.obtenerPorId(cuenta_acreedor_id);
    }
    
    public Integer insertar(CuentasAcreedorDTO cuenta){
        return this.cuentaAcreedorDAO.insertar(cuenta);
    }
    
    public Integer modificar(CuentasAcreedorDTO cuenta){
        return this.cuentaAcreedorDAO.modificar(cuenta);
    }
    
    public Integer eliminarLogico(CuentasAcreedorDTO cuenta, UsuariosDTO usuarioActual){
        cuenta.setFecha_eliminacion(new Date());
        cuenta.setUsuario_eliminacion(usuarioActual);
        return cuentaAcreedorDAO.eliminarLogico(cuenta);
    }
    
    public Integer eliminarLogico(CuentasAcreedorDTO cuenta){
        
        return cuentaAcreedorDAO.eliminarLogico(cuenta);
    }    
    
}