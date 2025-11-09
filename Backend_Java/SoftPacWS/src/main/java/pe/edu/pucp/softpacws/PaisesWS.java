package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softpac.bo.PaisesBO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;


@WebService(serviceName = "PaisesWS")
public class PaisesWS {

    
    private PaisesBO paisesBO;
    
    public PaisesWS(){
        this.paisesBO = new PaisesBO();
    }
    
    
    //LISTA PAISES.
    @WebMethod(operationName = "listarPaises")
    public ArrayList<PaisesDTO> listarPaises() {
        return paisesBO.listarTodos();
    }
    
    @WebMethod(operationName = "obtenerPaisPorId")
    public PaisesDTO obtenerPaisPorId(@WebParam(name = "pais_id") Integer pais_id) {
        return this.paisesBO.obtenerPorId(pais_id);
    }
    
    
}
