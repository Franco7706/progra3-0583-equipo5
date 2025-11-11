package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softpac.bo.FacturasBO;
import pe.edu.pucp.softpac.model.DetallesFacturaDTO;
import pe.edu.pucp.softpac.model.FacturasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

@WebService(serviceName = "FacturasWS")
public class FacturasWS {
    
    private FacturasBO facturasBO;
    
    public FacturasWS(){
        this.facturasBO = new FacturasBO();
    }
    
    @WebMethod(operationName = "listarFacturas")
    public ArrayList<FacturasDTO> listarFacturas() {
        return facturasBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarPendientes")
    public ArrayList<FacturasDTO> listarPendientes() {
        return facturasBO.listarPendientes();
    }
    
    @WebMethod(operationName = "obtenerFactura")
    public FacturasDTO obtenerFactura(@WebParam(name = "factura_id") Integer factura_id) {
        return facturasBO.obtenerPorId(factura_id);
    }
    
    @WebMethod(operationName = "insertarFactura")
    public Integer insertarFactura(@WebParam(name = "factura") FacturasDTO factura) {
        return facturasBO.insertar(factura);
    }
    
    @WebMethod(operationName = "modificarFactura")
    public Integer modificarFactura(@WebParam(name = "factura") FacturasDTO factura) {
        return facturasBO.modificar(factura);
    }
    
    @WebMethod(operationName = "eliminarFactura")
    public Integer eliminarFactura(
            @WebParam(name = "factura") FacturasDTO factura,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual) {
        return facturasBO.eliminarLogico(factura, usuarioActual);
    }
    
    @WebMethod(operationName = "insertarDetalleFactura")
    public Integer insertarDetalleFactura(@WebParam(name = "detalle") DetallesFacturaDTO detalle) {
        return facturasBO.insertarDetalle(detalle);
    }
    
    @WebMethod(operationName = "modificarDetalleFactura")
    public Integer modificarDetalleFactura(@WebParam(name = "detalle") DetallesFacturaDTO detalle) {
        return facturasBO.modificarDetalle(detalle);
    }
    
    @WebMethod(operationName = "eliminarDetalleFactura")
    public Integer eliminarDetalleFactura(
            @WebParam(name = "detalle") DetallesFacturaDTO detalle,
            @WebParam(name = "usuarioActual") UsuariosDTO usuarioActual) {
        return facturasBO.eliminarDetalle(detalle, usuarioActual);
    }
    
    @WebMethod(operationName = "listarPendientesPorCriterios")
    public ArrayList<FacturasDTO> listarPendientesPorCriterios(@WebParam(name = "pais_id")Integer paisId,@WebParam(name = "fechaLimite") Date fechaLimite){
        return facturasBO.listarPendientesPorCriterios(paisId, fechaLimite);
    }
    
}