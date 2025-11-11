package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.EntidadesBancariasBO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;

@WebService(serviceName = "EntidadesBancariasWS")
public class EntidadesBancariasWS {
    
    private EntidadesBancariasBO entidadesBancariasBO;
    
    public EntidadesBancariasWS(){
        this.entidadesBancariasBO = new EntidadesBancariasBO();
    }
    
    @WebMethod(operationName = "listarEntidadesBancarias")
    public ArrayList<EntidadesBancariasDTO> listarEntidadesBancarias() {
        return entidadesBancariasBO.listarTodos();
    }
    
    @WebMethod(operationName = "obtenerEntidadBancariaPorId")
    public EntidadesBancariasDTO obtenerEntidadBancariaPorId(
            @WebParam(name = "entidades_bancarias_id") Integer entidades_bancarias_id) {
        return entidadesBancariasBO.obtenerPorId(entidades_bancarias_id);
    }
    
    @WebMethod(operationName = "obtenerEntidadBancariaPorNombre")
    public EntidadesBancariasDTO obtenerPorNombre(@WebParam(name = "nombre_entidad")String nombre){
        return this.entidadesBancariasBO.obtenerPorNombre(nombre);
    }
}