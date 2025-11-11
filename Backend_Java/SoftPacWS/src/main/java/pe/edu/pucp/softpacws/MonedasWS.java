package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.MonedasBO;
import pe.edu.pucp.softpac.model.MonedasDTO;

@WebService(serviceName = "MonedasWS")
public class MonedasWS {
    
    private MonedasBO monedasBO;
    
    public MonedasWS(){
        this.monedasBO = new MonedasBO();
    }
    
    @WebMethod(operationName = "listarMonedas")
    public ArrayList<MonedasDTO> listarMonedas() {
        return monedasBO.listarTodos();
    }
    
    @WebMethod(operationName = "obtenerMonedaPorID")
    public MonedasDTO obtenerMonedaPorID(@WebParam(name = "moneda_id") Integer moneda_id) {
        return monedasBO.obtenerPorID(moneda_id);
    }
    
    @WebMethod(operationName = "obtenerMonedaPorDivisa")
    public MonedasDTO obtenerPorDivisa(@WebParam(name = "moneda_divisa")String divisaMoneda){
        return monedasBO.obtenerPorDivisa(divisaMoneda);
    }
}