/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.AcreedoresBO;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

/**
 *
 * @author USUARIO
 */
@WebService(serviceName = "AcreedoresWS")
public class AcreedoresWS {

    private AcreedoresBO acreedoresBO;
    
    public AcreedoresWS(){
        acreedoresBO = new AcreedoresBO();
    }
    
    @WebMethod(operationName = "insertarAcreedores")
    public Integer insertarAcreedores(@WebParam(name = "razon_social")String razon_social,@WebParam(name = "ruc") String ruc,@WebParam(name = "direccion_fiscal") String direccion_fiscal,
            @WebParam(name = "condicion")String condicion,@WebParam(name = "plazo_de_pago") Integer plazo_de_pago,@WebParam(name = "activo") String activo,
            @WebParam(name = "id_pais")Integer id_pais){
  
       return this.acreedoresBO.insertar(razon_social,ruc,direccion_fiscal,condicion,plazo_de_pago,activo,id_pais);
    }
    
    @WebMethod(operationName = "modificarAcreedores")
    public Integer modificarAcreedores(@WebParam(name = "id_acreedor")Integer id_acreedor,@WebParam(name = "razon_social")String razon_social,@WebParam(name = "ruc") String ruc,
            @WebParam(name = "direccion_fiscal") String direccion_fiscal,
            @WebParam(name = "condicion")String condicion,@WebParam(name = "plazo_de_pago") Integer plazo_de_pago,@WebParam(name = "activo") String activo,
            @WebParam(name = "id_pais")Integer id_pais){
  
       return this.acreedoresBO.modificar(id_acreedor,razon_social,ruc,direccion_fiscal,condicion,plazo_de_pago,activo,id_pais);
    }
    
    @WebMethod(operationName = "eliminarAcreedor")
    public Integer eliminarAcreedor(@WebParam(name = "acreedor")AcreedoresDTO acreedor,@WebParam(name = "usuario")UsuariosDTO usuarioActual){
  
       return this.acreedoresBO.eliminarLogico(acreedor,usuarioActual);
    }
    
    @WebMethod(operationName = "obtenerAcreedor")
    public AcreedoresDTO obtenerAcreedor(@WebParam(name = "id_acreedor")Integer id_acreedor){
  
       return this.acreedoresBO.obtenerPorId(id_acreedor);
    }
    
    @WebMethod(operationName = "listarAcreedores")
    public ArrayList<AcreedoresDTO> listarTodos(){
  
       return this.acreedoresBO.listarTodos();
    }
    
}
