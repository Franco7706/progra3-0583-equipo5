package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.CuentasAcreedorBO;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

@WebService(serviceName = "CuentasAcreedorWS")
public class CuentasAcreedorWS {
    
    private CuentasAcreedorBO cuentasAcreedorBO;
    
    public CuentasAcreedorWS(){
        this.cuentasAcreedorBO = new CuentasAcreedorBO();
    }
    
    @WebMethod(operationName = "obtenerCuentasPorAcreedor")
    public ArrayList<CuentasAcreedorDTO> obtenerCuentasPorAcreedor(
            @WebParam(name = "acreedor_id") Integer acreedor_id) {
        return cuentasAcreedorBO.obtenerPorAcreedor(acreedor_id);
    }
    
    @WebMethod(operationName = "listarCuentasAcreedor")
    public ArrayList<CuentasAcreedorDTO> listarCuentasAcreedor() {
        return cuentasAcreedorBO.listarTodos();
    }
    
    @WebMethod(operationName = "obtenerCuentaAcreedor")
    public CuentasAcreedorDTO obtenerCuentaAcreedor(
            @WebParam(name = "cuenta_acreedor_id") Integer cuenta_acreedor_id) {
        return cuentasAcreedorBO.obtenerPorId(cuenta_acreedor_id);
    }
    
    @WebMethod(operationName = "insertarCuentasAcreedor")
    public Integer insertarCuentasAcreedor(@WebParam(name = "cuenta") CuentasAcreedorDTO cuenta) {
        return cuentasAcreedorBO.insertar(cuenta);
    }
    
    @WebMethod(operationName = "modificarCuentasAcreedor")
    public Integer modificarCuentasAcreedor(@WebParam(name = "cuenta") CuentasAcreedorDTO cuenta) {
        return cuentasAcreedorBO.modificar(cuenta);
    }
    
    @WebMethod(operationName = "eliminarCuentaAcreedorParametros")
    public Integer eliminarCuentaAcreedorParametros(
            @WebParam(name = "cuenta") CuentasAcreedorDTO cuenta,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual) {
        return cuentasAcreedorBO.eliminarLogico(cuenta, usuarioActual);
    }
    
    @WebMethod(operationName = "eliminarCuentaAcreedor")
    public Integer eliminarCuentaAcreedor(
            @WebParam(name = "cuenta_acreedor") CuentasAcreedorDTO cuenta) {
        return cuentasAcreedorBO.eliminarLogico(cuenta);
    }
}