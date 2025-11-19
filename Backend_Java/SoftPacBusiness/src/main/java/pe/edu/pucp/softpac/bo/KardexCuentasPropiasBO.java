package pe.edu.pucp.softpac.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softpac.dao.CuentasPropiasDAO;
import pe.edu.pucp.softpac.dao.KardexCuentasPropiasDAO;
import pe.edu.pucp.softpac.daoImpl.CuentasPropiasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.KardexCuentasPropiasDAOImpl;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.KardexCuentasPropiasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;


public class KardexCuentasPropiasBO {
    private KardexCuentasPropiasDAO kardexCuentasPropiasDAO;

    public KardexCuentasPropiasBO() {
        kardexCuentasPropiasDAO = new KardexCuentasPropiasDAOImpl();
    }
    
    
    public ArrayList<KardexCuentasPropiasDTO>obtenerPorUsuario(int usuarioId){
        return kardexCuentasPropiasDAO.obtenerPorUsuario(usuarioId);
    }
    public ArrayList<KardexCuentasPropiasDTO>obtenerPorCuentaPropia(int cuentaPropiaId){
        return kardexCuentasPropiasDAO.obtenerPorCuentaPropia(cuentaPropiaId);
    }
    
    public void RegistrarModificacion(CuentasPropiasDTO cuentaPropia,int usuarioModificacionId){
        CuentasPropiasDAO cuentasPropiasDAO = new CuentasPropiasDAOImpl();
        CuentasPropiasDTO cuentaOriginal = cuentasPropiasDAO.obtenerPorId(cuentaPropia.getCuenta_bancaria_id());
        BigDecimal saldoModificacion = cuentaPropia.getSaldo_disponible().subtract(cuentaOriginal.getSaldo_disponible());
        if(saldoModificacion.compareTo(BigDecimal.ZERO)!=0){
            if(insertar(cuentaOriginal.getCuenta_bancaria_id(),new Date(),saldoModificacion,cuentaPropia.getSaldo_disponible(),usuarioModificacionId)==1){
                System.out.println("Se registró un cambio de saldo en la cuenta propia " + cuentaOriginal.getCuenta_bancaria_id());
            }
            else{
                System.out.println("Sucedió un error al registrar un cambio de saldo en la cuenta propia " + cuentaOriginal.getCuenta_bancaria_id());
            }
        }
    }
    
    public int insertar(int cuentaPropiaId,Date fechaModificacion,BigDecimal saldoModificacion,BigDecimal saldoResultante,int usuarioModificacionId){
        CuentasPropiasDTO cuentaPropia = new CuentasPropiasDTO();
        cuentaPropia.setCuenta_bancaria_id(cuentaPropiaId);
        UsuariosDTO usuarioModificacion = new UsuariosDTO();
        usuarioModificacion.setUsuario_id(usuarioModificacionId);
        return kardexCuentasPropiasDAO.insertar(new KardexCuentasPropiasDTO(cuentaPropia,fechaModificacion,saldoModificacion,saldoResultante,
        usuarioModificacion));
    }
    
}
