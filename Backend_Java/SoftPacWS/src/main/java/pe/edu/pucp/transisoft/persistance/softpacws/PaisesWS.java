package pe.edu.pucp.transisoft.persistance.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softpac.bo.PaisesBO;
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
    
}
