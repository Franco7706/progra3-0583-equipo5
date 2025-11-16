package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.math.BigDecimal;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.CuentasPropiasBO;
import pe.edu.pucp.softpac.bo.KardexCuentasPropiasBO;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.KardexCuentasPropiasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

@WebService(serviceName = "CuentasPropiasWS")
public class CuentasPropiasWS {
    
    private CuentasPropiasBO cuentasPropiasBO;
    private KardexCuentasPropiasBO kardexCuentasPropiasBO;
    
    public CuentasPropiasWS(){
        this.cuentasPropiasBO = new CuentasPropiasBO();
        this.kardexCuentasPropiasBO = new KardexCuentasPropiasBO();
    }
    
    @WebMethod(operationName = "insertarCuentaPropiaParametros")
    public Integer insertarCuentaPropiaParametros(
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
    
    @WebMethod(operationName = "insertarCuentaPropia")
    public Integer insertarCuentaPropia(
            @WebParam(name = "cuenta_propia") CuentasPropiasDTO cuentasPropiasDTO) {
        return cuentasPropiasBO.insertar(cuentasPropiasDTO);
    }
    
    @WebMethod(operationName = "modificarCuentaPropiaParametros")
    public Integer modificarCuentaPropiaParametros(
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
    
    @WebMethod(operationName = "modificarCuentaPropia")
    public Integer modificarCuentaPropia(
            @WebParam(name = "cuenta_propia") CuentasPropiasDTO cuenta_propia,
            @WebParam(name = "usuario_id") Integer usuario_id) {
        return cuentasPropiasBO.modificar(cuenta_propia,usuario_id);
    }
    
    @WebMethod(operationName = "eliminarCuentaPropia")
    public Integer eliminarCuentaPropia(
            @WebParam(name = "cuenta") CuentasPropiasDTO cuenta,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual) {
        return cuentasPropiasBO.eliminarLogico(cuenta, usuarioActual);
    }
    
    @WebMethod(operationName = "eliminarCuentaPropiaParametros")
    public Integer eliminarCuentaPropiaParametros(
            @WebParam(name = "cuenta_bancaria_id") Integer cuenta_bancaria_id,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual) {
        return cuentasPropiasBO.eliminarLogico(cuenta_bancaria_id, usuarioActual);
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
    
    @WebMethod(operationName = "listarPorEntidadBancaria")
    public ArrayList<CuentasPropiasDTO> listarPorEntidadBancaria(Integer entidad_bancaria_id) {
        return cuentasPropiasBO.listarPorEntidadBancaria(entidad_bancaria_id);
    }
    
    @WebMethod(operationName = "obtenerKardexCuentaPropiaPorUsuario")
    public ArrayList<KardexCuentasPropiasDTO>obtenerKardexCuentaPropiaPorUsuario(int usuarioId){
        return kardexCuentasPropiasBO.obtenerPorUsuario(usuarioId);
    }
    
    @WebMethod(operationName = "obtenerKardexCuentaPropiaPorCuenta")
    public ArrayList<KardexCuentasPropiasDTO>obtenerKardexCuentaPropiaPorCuenta(int cuentaPropiaId){
        return kardexCuentasPropiasBO.obtenerPorCuentaPropia(cuentaPropiaId);
    }
}