package pe.edu.pucp.softpac.bo;

import java.util.ArrayList;
import java.util.Comparator;
import pe.edu.pucp.softpac.dao.PropuestasPagoDAO;
import pe.edu.pucp.softpac.daoImpl.PropuestasPagoDAOImpl;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;
import pe.edu.pucp.softpac.model.FacturasDTO;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;

public class PropuestaPagoBO {
    
    private PropuestasPagoDAO propuestasDAO;
    
    public PropuestaPagoBO(){
        propuestasDAO = new PropuestasPagoDAOImpl();
    }
    
    public Integer insertar (PropuestasPagoDTO propuestaPago){
        return propuestasDAO.insertar(propuestaPago);
    }
    
    public Integer modificar (PropuestasPagoDTO propuestaPago){
        return propuestasDAO.modificar(propuestaPago);
    }
    
    public Integer insertarDetalle(DetallesPropuestaDTO detalle){
        return propuestasDAO.insertar(detalle.getPropuesta_pago());
    }
    
    public Integer modificarDetalle(DetallesPropuestaDTO detalle){
        return propuestasDAO.modificar(detalle.getPropuesta_pago());
    }
    
    
    public ArrayList<PropuestasPagoDTO> listarUltimasPorUsuario(Integer usuarioId, Integer cantidad){
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();
        ArrayList<PropuestasPagoDTO> propuestasUltimas = new ArrayList<>();
        
        for(PropuestasPagoDTO propuesta: propuestas){
            if(propuesta.getUsuario_creacion() != null && propuesta.getUsuario_creacion().getUsuario_id() == usuarioId){
                propuestasUltimas.add(propuesta);
            }
        }
        
        
        //Ordenamos por las fechas más recientes
        propuestasUltimas.sort(Comparator.comparing(PropuestasPagoDTO::getFecha_hora_creacion).reversed());
        
        // Limitar cantidad
        if (propuestasUltimas.size() > cantidad) {
            return new ArrayList<>(propuestasUltimas.subList(0, cantidad));
        }
          
        return propuestasUltimas;
    
    }
    
    public ArrayList<PropuestasPagoDTO> listarTodasPorUsuario(Integer usuarioId){
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();
        ArrayList<PropuestasPagoDTO> propuestasUsuarios = new ArrayList<>();
        
        for(PropuestasPagoDTO propuesta: propuestas){
            if(propuesta.getUsuario_creacion() != null && propuesta.getUsuario_creacion().getUsuario_id() == usuarioId){
                propuestasUsuarios.add(propuesta);
            }
        }
        
        return propuestasUsuarios;
    }
    
    public ArrayList<PropuestasPagoDTO> listarActividadPorUsuario(Integer usuarioId){
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio");
        }

        ArrayList<PropuestasPagoDTO> propuestasActividad = new ArrayList<>();
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();

        for(PropuestasPagoDTO propuesta : propuestas){
            boolean tieneActividad = false;

            if(propuesta.getUsuario_creacion() != null && 
               propuesta.getUsuario_creacion().getUsuario_id().equals(usuarioId)){
                tieneActividad = true;
            }

            if(propuesta.getUsuario_modificacion() != null && 
               propuesta.getUsuario_modificacion().getUsuario_id().equals(usuarioId)){
                tieneActividad = true;
            }

            if(propuesta.getUsuario_eliminacion() != null && 
               propuesta.getUsuario_eliminacion().getUsuario_id().equals(usuarioId)){
                tieneActividad = true;
            }

            if(tieneActividad){
                propuestasActividad.add(propuesta);
            }
        }

        return propuestasActividad;
    }
    
    public ArrayList<PropuestasPagoDTO> ListarConFiltros(Integer bancoId, String estado) {
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();
        ArrayList<PropuestasPagoDTO> propuestasFiltros = new ArrayList<>();
        
        for(PropuestasPagoDTO propuesta: propuestas){
            if((bancoId == null || (propuesta.getEntidad_bancaria() != null &&
                    propuesta.getEntidad_bancaria().getEntidad_bancaria_id().equals(bancoId))) &&
                    (estado.isEmpty() || propuesta.getEstado().equals(estado))){
                propuestasFiltros.add(propuesta);
            }
        }
        
        return propuestasFiltros;
    }

    public PropuestasPagoDTO GenerarDetallesParciales(ArrayList<Integer> facturasSeleccionadas, Integer bancoId) {
        FacturasBO facturasBO = new FacturasBO();
        EntidadesBancariasBO bancosBO = new EntidadesBancariasBO();
        PropuestasPagoDTO propuestaParcial = new PropuestasPagoDTO();
        CuentasAcreedorBO cuentasAcreedorBO = new CuentasAcreedorBO();
        propuestaParcial.setEntidad_bancaria(bancosBO.obtenerPorId(bancoId));
        propuestaParcial.setEstado("Pendiente");
        propuestaParcial.setDetalles_propuesta(new ArrayList<>());
        for (int i = 0; i < facturasSeleccionadas.size(); i++)
        {
            FacturasDTO factura = facturasBO.obtenerPorId(facturasSeleccionadas.get(i));
            if (factura == null || factura.getAcreedor() == null || factura.getAcreedor().getPais() == null)
            {
                continue;
            }
            ArrayList<CuentasAcreedorDTO> cuentasAcreedor = cuentasAcreedorBO.obtenerPorAcreedor(factura.getAcreedor().getAcreedor_id());
            for (CuentasAcreedorDTO cuenta: cuentasAcreedor)
            {
                if (cuenta.getEntidad_bancaria().getPais().getPais_id() == propuestaParcial.getEntidad_bancaria().getPais().getPais_id())
                {
                    DetallesPropuestaDTO detalle = new DetallesPropuestaDTO();
                    detalle.setFactura(factura);
                    detalle.setCuenta_acreedor(cuenta);
                    detalle.setMonto_pago(factura.getMonto_restante());
                    detalle.setForma_pago('T'); // Transferencia
                    propuestaParcial.addDetalle_Propuesta(detalle);
                    break; // Solo agregar una cuenta por factura
                }
            }
        }

        return propuestaParcial;
    }
    
    
    
    
}
