package pe.edu.pucp.softpacws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import pe.edu.pucp.softpac.bo.PropuestaPagoBO;
import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

@WebService(serviceName = "PropuestaPagoWS")
public class PropuestaPagoWS {

    private PropuestaPagoBO propuestaPagoBO;

    public PropuestaPagoWS() {
        this.propuestaPagoBO = new PropuestaPagoBO();
    }

    @WebMethod(operationName = "insertarPropuesta")
    public Integer insertarPropuesta(@WebParam(name = "propuestaPago") PropuestasPagoDTO propuestaPago) {
        return propuestaPagoBO.insertar(propuestaPago);
    }

    @WebMethod(operationName = "modificarPropuesta")
    public Integer modificarPropuesta(@WebParam(name = "propuestaPago") PropuestasPagoDTO propuestaPago) {
        return propuestaPagoBO.modificar(propuestaPago);
    }

    @WebMethod(operationName = "insertarDetallePropuesta")
    public Integer insertarDetallePropuesta(@WebParam(name = "detalle") DetallesPropuestaDTO detalle) {
        return propuestaPagoBO.insertarDetalle(detalle);
    }

    @WebMethod(operationName = "modificarDetallePropuesta")
    public Integer modificarDetallePropuesta(@WebParam(name = "detalle") DetallesPropuestaDTO detalle) {
        return propuestaPagoBO.modificarDetalle(detalle);
    }

    @WebMethod(operationName = "obtenerPropuesta")
    public PropuestasPagoDTO obtenerPropuesta(@WebParam(name = "propuesta_id") Integer propuesta_id) {
        return propuestaPagoBO.obtenerPorId(propuesta_id);
    }

    @WebMethod(operationName = "listarUltimasPropuestasPorUsuario")
    public ArrayList<PropuestasPagoDTO> listarUltimasPropuestasPorUsuario(
            @WebParam(name = "usuarioId") Integer usuarioId,
            @WebParam(name = "cantidad") Integer cantidad) {
        return propuestaPagoBO.listarUltimasPorUsuario(usuarioId, cantidad);
    }

    @WebMethod(operationName = "listarPropuestasPorUsuario")
    public ArrayList<PropuestasPagoDTO> listarPropuestasPorUsuario(
            @WebParam(name = "usuarioId") Integer usuarioId) {
        return propuestaPagoBO.listarTodasPorUsuario(usuarioId);
    }

    @WebMethod(operationName = "listarActividadPorUsuario")
    public ArrayList<PropuestasPagoDTO> listarActividadPorUsuario(
            @WebParam(name = "usuarioId") Integer usuarioId) {
        return propuestaPagoBO.listarActividadPorUsuario(usuarioId);
    }

    @WebMethod(operationName = "listarConFiltros")
    public ArrayList<PropuestasPagoDTO> listarConFiltros(@WebParam(name = "paisId") Integer pais_id,
            @WebParam(name = "bancoId") Integer bancoId, @WebParam(name = "estado") String estado) {
        return propuestaPagoBO.ListarConFiltros(pais_id, bancoId, estado);
    }

    @WebMethod(operationName = "generarDetallesParciales")
    public PropuestasPagoDTO GenerarDetallesParciales(@WebParam(name = "facturasSeleccionadas") ArrayList<Integer> facturasSeleccionadas, @WebParam(name = "bancoId") Integer bancoId) {
        return propuestaPagoBO.GenerarDetallesParciales(facturasSeleccionadas, bancoId);
    }

    @WebMethod(operationName = "generarDetallesPropuesta")
    public PropuestasPagoDTO GenerarDetallesPropuesta(@WebParam(name = "propuestaPagoParcial") PropuestasPagoDTO propuestaPagoParcial,
             @WebParam(name = "cuentasSeleccionadas") ArrayList<Integer> cuentasSeleccionadas) {
        return propuestaPagoBO.GenerarDetallesPropuesta(propuestaPagoParcial, cuentasSeleccionadas);
    }

    @WebMethod(operationName = "confirmarEnvioPropuesta")
    public Integer confirmarEnvioPropuesta(@WebParam(name = "propuestaId") int propuestaId, @WebParam(name = "usuario") UsuariosDTO usuario) {
        return propuestaPagoBO.confirmarEnvioPropuesta(propuestaId, usuario);
    }

    @WebMethod(operationName = "rechazarPropuesta")
    public Integer rechazarPropuesta(
            @WebParam(name = "propuestaId") Integer propuestaId,
            @WebParam(name = "usuarioAdmin") UsuariosDTO usuarioAdmin) {
        return propuestaPagoBO.rechazarPropuesta(propuestaId, usuarioAdmin);
    }
}
