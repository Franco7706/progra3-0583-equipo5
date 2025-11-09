package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.math.BigDecimal;
import java.util.Date;
import pe.edu.pucp.softpac.bo.TiposDeCambioBO;
import pe.edu.pucp.softpac.model.TiposDeCambioDTO;

@WebService(serviceName = "TiposDeCambioWS")
public class TiposDeCambioWS {
    
    private TiposDeCambioBO tiposDeCambioBO;
    
    public TiposDeCambioWS(){
        this.tiposDeCambioBO = new TiposDeCambioBO();
    }
    
    @WebMethod(operationName = "insertarTipoDeCambio")
    public Integer insertarTipoDeCambio(
            @WebParam(name = "fecha") Date fecha,
            @WebParam(name = "tasa_de_cambio") BigDecimal tasa_de_cambio,
            @WebParam(name = "moneda_origen_id") Integer moneda_origen_id,
            @WebParam(name = "moneda_destino_id") Integer moneda_destino_id) {
        return tiposDeCambioBO.insertar(fecha, tasa_de_cambio, 
                moneda_origen_id, moneda_destino_id);
    }
    
    @WebMethod(operationName = "modificarTipoDeCambio")
    public Integer modificarTipoDeCambio(
            @WebParam(name = "tipo_cambio_id") Integer tipo_cambio_id,
            @WebParam(name = "fecha") Date fecha,
            @WebParam(name = "tasa_de_cambio") BigDecimal tasa_de_cambio,
            @WebParam(name = "moneda_origen_id") Integer moneda_origen_id,
            @WebParam(name = "moneda_destino_id") Integer moneda_destino_id) {
        return tiposDeCambioBO.modificar(tipo_cambio_id, fecha, tasa_de_cambio, 
                moneda_origen_id, moneda_destino_id);
    }
    
    @WebMethod(operationName = "obtenerTipoDeCambio")
    public TiposDeCambioDTO obtenerTipoDeCambio(
            @WebParam(name = "tipo_de_cambio_id") Integer tipo_de_cambio_id) {
        return tiposDeCambioBO.obtenerPorId(tipo_de_cambio_id);
    }
}