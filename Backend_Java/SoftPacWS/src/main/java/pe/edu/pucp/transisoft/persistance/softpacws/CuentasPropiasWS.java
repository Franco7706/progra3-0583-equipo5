package pe.edu.pucp.transisoft.persistance.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.math.BigDecimal;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.CuentasPropiasBO;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

@WebService(serviceName = "CuentasPropiasWS")
public class CuentasPropiasWS {
    
    private CuentasPropiasBO cuentasPropiasBO;
    
    public CuentasPropiasWS(){
        this.cuentasPropiasBO = new CuentasPropiasBO();
    }
    
    @WebMethod(operationName = "insertarCuentaPropia")
    public Integer insertarCuentaPropia(
            @WebParam(name = "saldo_disponible") BigDecimal saldo_disponible,
            @WebParam(name = "tipo_de_cuenta") String tipo_de_cuenta,
            @WebParam(name = "numero_cuenta") String numero_cuenta,
            @WebParam(name = "cci") String cci,
            @WebParam(name = "activa") String activa,
            @WebParam(name = "entidad_bancaria_id") Integer entidad_bancaria_id,
            @WebParam(name = "moneda_id") Integer moneda_id) {
        return cuentasPropiasBO.insertar(saldo_disponible, tipo_de_cuenta, 
                numero_cuenta, cci, activa, entidad_bancaria_id, moneda_id);
    }
    
    @WebMethod(operationName = "modificarCuentaPropia")
    public Integer modificarCuentaPropia(
            @WebParam(name = "cuentas_propias_id") Integer cuentas_propias_id,
            @WebParam(name = "saldo_disponible") BigDecimal saldo_disponible,
            @WebParam(name = "tipo_de_cuenta") String tipo_de_cuenta,
            @WebParam(name = "numero_cuenta") String numero_cuenta,
            @WebParam(name = "cci") String cci,
            @WebParam(name = "activa") String activa,
            @WebParam(name = "entidad_bancaria_id") Integer entidad_bancaria_id,
            @WebParam(name = "moneda_id") Integer moneda_id) {
        return cuentasPropiasBO.modificar(cuentas_propias_id, saldo_disponible, 
                tipo_de_cuenta, numero_cuenta, cci, activa, 
                entidad_bancaria_id, moneda_id);
    }
    
    @WebMethod(operationName = "eliminarCuentaPropia")
    public Integer eliminarCuentaPropia(
            @WebParam(name = "cuenta") CuentasPropiasDTO cuenta,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual) {
        return cuentasPropiasBO.eliminarLogico(cuenta, usuarioActual);
    }
    
    @WebMethod(operationName = "listarCuentasPropias")
    public ArrayList<CuentasPropiasDTO> listarCuentasPropias() {
        return cuentasPropiasBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarCuentasActivas")
    public ArrayList<CuentasPropiasDTO> listarCuentasActivas() {
        return cuentasPropiasBO.listarActivas();
    }
    
    @WebMethod(operationName = "obtenerCuentaPropiaPorId")
    public CuentasPropiasDTO obtenerCuentaPropiaPorId(
            @WebParam(name = "cuentas_propias_id") Integer cuentas_propias_id) {
        return cuentasPropiasBO.obtenerPorId(cuentas_propias_id);
    }
    
    @WebMethod(operationName = "tieneSaldoSuficiente")
    public boolean tieneSaldoSuficiente(
            @WebParam(name = "cuentas_propias_id") Integer cuentas_propias_id,
            @WebParam(name = "montoRequerido") BigDecimal montoRequerido) {
        return cuentasPropiasBO.tieneSaldoSuficiente(cuentas_propias_id, montoRequerido);
    }
}