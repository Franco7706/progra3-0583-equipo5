package pe.edu.pucp.softpac.bo;

import pe.edu.pucp.softpac.bo.util.EmailUtil;
import pe.edu.pucp.softpac.bo.util.TipoOperacionUsuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pe.edu.pucp.softpac.dao.CuentasPropiasDAO;
import pe.edu.pucp.softpac.dao.FacturasDAO;
import pe.edu.pucp.softpac.dao.PropuestasPagoDAO;
import pe.edu.pucp.softpac.daoImpl.CuentasPropiasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.FacturasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PropuestasPagoDAOImpl;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.DetallesPropuestaDTO;
import pe.edu.pucp.softpac.model.FacturasDTO;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public class PropuestaPagoBO {

    private PropuestasPagoDAO propuestasDAO;

    public PropuestaPagoBO() {
        propuestasDAO = new PropuestasPagoDAOImpl();
    }

    public Integer insertar(PropuestasPagoDTO propuestaPago) {
        return propuestasDAO.insertar(propuestaPago);
    }

    public Integer modificar(PropuestasPagoDTO propuestaPago) {
        for (DetallesPropuestaDTO detalle : propuestaPago.getDetalles_propuesta()) {
            detalle.setPropuesta_pago(propuestaPago);
        }
        return propuestasDAO.modificar(propuestaPago);
    }

    public Integer insertarDetalle(DetallesPropuestaDTO detalle) {
        return propuestasDAO.insertar(detalle.getPropuesta_pago());
    }

    public Integer modificarDetalle(DetallesPropuestaDTO detalle) {
        return propuestasDAO.modificar(detalle.getPropuesta_pago());
    }

    public ArrayList<PropuestasPagoDTO> listarUltimasPorUsuario(Integer usuarioId, Integer cantidad) {
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();
        ArrayList<PropuestasPagoDTO> propuestasUltimas = new ArrayList<>();

        for (PropuestasPagoDTO propuesta : propuestas) {
            if (propuesta.getUsuario_creacion() != null && propuesta.getUsuario_creacion().getUsuario_id() == usuarioId) {
                propuestasUltimas.add(propuesta);
            }
        }

        propuestasUltimas.sort(Comparator.comparing(PropuestasPagoDTO::getFecha_hora_creacion).reversed());

        if (propuestasUltimas.size() > cantidad) {
            return new ArrayList<>(propuestasUltimas.subList(0, cantidad));
        }

        return propuestasUltimas;

    }

    public ArrayList<PropuestasPagoDTO> listarTodasPorUsuario(Integer usuarioId) {
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();
        ArrayList<PropuestasPagoDTO> propuestasUsuarios = new ArrayList<>();

        for (PropuestasPagoDTO propuesta : propuestas) {
            if (propuesta.getUsuario_creacion() != null && propuesta.getUsuario_creacion().getUsuario_id() == usuarioId) {
                propuestasUsuarios.add(propuesta);
            }
        }

        return propuestasUsuarios;
    }

    public PropuestasPagoDTO obtenerPorId(Integer propuesta_id) {
        PropuestasPagoDTO propuesta = this.propuestasDAO.obtenerPorId(propuesta_id);
        for (DetallesPropuestaDTO detalle : propuesta.getDetalles_propuesta()) {
            detalle.setPropuesta_pago(null);
        }
        return propuesta;
    }

    public ArrayList<PropuestasPagoDTO> listarActividadPorUsuario(Integer usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio");
        }

        ArrayList<PropuestasPagoDTO> propuestasActividad = new ArrayList<>();
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();

        for (PropuestasPagoDTO propuesta : propuestas) {
            boolean tieneActividad = false;

            if (propuesta.getUsuario_creacion() != null
                    && propuesta.getUsuario_creacion().getUsuario_id().equals(usuarioId)) {
                tieneActividad = true;
            }

            if (propuesta.getUsuario_modificacion() != null
                    && propuesta.getUsuario_modificacion().getUsuario_id().equals(usuarioId)) {
                tieneActividad = true;
            }

            if (propuesta.getUsuario_eliminacion() != null
                    && propuesta.getUsuario_eliminacion().getUsuario_id().equals(usuarioId)) {
                tieneActividad = true;
            }

            if (tieneActividad) {
                propuestasActividad.add(propuesta);
            }
        }

        return propuestasActividad;
    }

    public ArrayList<PropuestasPagoDTO> ListarConFiltros(Integer pais_id, Integer bancoId, String estado) {
        ArrayList<PropuestasPagoDTO> propuestas = (ArrayList<PropuestasPagoDTO>) propuestasDAO.listarTodos();
        ArrayList<PropuestasPagoDTO> propuestasFiltros = new ArrayList<>();

        for (PropuestasPagoDTO propuesta : propuestas) {
            if ((pais_id == 0 || (propuesta.getEntidad_bancaria() != null
                    && propuesta.getEntidad_bancaria().getPais().getPais_id().equals(pais_id)))
                    && (bancoId == 0 || (propuesta.getEntidad_bancaria() != null
                    && propuesta.getEntidad_bancaria().getEntidad_bancaria_id().equals(bancoId)))
                    && (estado.isEmpty() || propuesta.getEstado().equals(estado))) {
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
        for (int i = 0; i < facturasSeleccionadas.size(); i++) {
            FacturasDTO factura = facturasBO.obtenerPorId(facturasSeleccionadas.get(i));
            if (factura == null || factura.getAcreedor() == null || factura.getAcreedor().getPais() == null) {
                continue;
            }
            ArrayList<CuentasAcreedorDTO> cuentasAcreedor = cuentasAcreedorBO.obtenerPorAcreedor(factura.getAcreedor().getAcreedor_id());
            for (CuentasAcreedorDTO cuenta : cuentasAcreedor) {
                if (cuenta.getEntidad_bancaria().getPais().getPais_id() == propuestaParcial.getEntidad_bancaria().getPais().getPais_id()) {
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

    public PropuestasPagoDTO GenerarDetallesPropuesta(PropuestasPagoDTO propuestaPagoParcial, ArrayList<Integer> cuentasSeleccionadas) {
        CuentasPropiasDAO cuentasPropiasDAO = new CuentasPropiasDAOImpl();
        // 1️ Obtener las cuentas seleccionadas
        var cuentas = new ArrayList<CuentasPropiasDTO>();
        for (Integer id : cuentasSeleccionadas) {
            var cuenta = cuentasPropiasDAO.obtenerPorId(id);
            if (cuenta.getActiva()) {
                cuentas.add(cuenta);
            }
        }

        // 2️ Limpiar cualquier asignación previa
        for (var detalle : propuestaPagoParcial.getDetalles_propuesta()) {
            detalle.setCuenta_propia(null);
        }

        // 3️ Agrupar los pagos por moneda
        Map<Integer, List<DetallesPropuestaDTO>> pagosPorMoneda = propuestaPagoParcial.getDetalles_propuesta().stream()
                .collect(Collectors.groupingBy(
                        d -> d.getFactura().getMoneda().getMoneda_id()
                ));

        // 4️ Aplicar algoritmo greedy por moneda
        for (Map.Entry<Integer, List<DetallesPropuestaDTO>> grupoEntry : pagosPorMoneda.entrySet()) {

            Integer moneda_id = grupoEntry.getKey();
            List<DetallesPropuestaDTO> pagosDelGrupo = grupoEntry.getValue();

            // Filtra las cuentas por la Moneda y las ordena por SaldoDisponible (descendente)
            List<CuentasPropiasDTO> cuentasMoneda = cuentas.stream()
                    .filter(c -> c.getMoneda().getMoneda_id().equals(moneda_id))
                    // Ordena descendente por SaldoDisponible: Comparator.reverseOrder()
                    .sorted(Comparator.comparing(CuentasPropiasDTO::getSaldo_disponible).reversed())
                    .collect(Collectors.toList());

            // Ordena los pagos del grupo por MontoPago (descendente)
            List<DetallesPropuestaDTO> pagosOrdenados = pagosDelGrupo.stream()
                    .sorted(Comparator.comparing(DetallesPropuestaDTO::getMonto_pago).reversed())
                    .collect(Collectors.toList());

            for (DetallesPropuestaDTO pago : pagosOrdenados) {

                // 4. Busca la primera cuenta que pueda cubrir el monto del pago
                CuentasPropiasDTO cuentaAsignada = cuentasMoneda.stream().filter(c -> c.getSaldo_disponible().compareTo(pago.getMonto_pago()) >= 0).findFirst().orElse(null);

                // 5. Asignación y actualización del saldo
                if (cuentaAsignada != null) {

                    // pago.CuentaPropia = cuenta;
                    pago.setCuenta_propia(cuentaAsignada);

                    // cuenta.SaldoDisponible -= pago.MontoPago;
                    BigDecimal nuevoSaldo = cuentaAsignada.getSaldo_disponible().subtract(pago.getMonto_pago());
                    cuentaAsignada.setSaldo_disponible(nuevoSaldo);

                }
            }
        }

        return propuestaPagoParcial;
    }

    public Integer confirmarEnvioPropuesta(int propuestaId, UsuariosDTO usuario) {
        FacturasDAO facturasDAO = new FacturasDAOImpl();

        class AgrupadoCuenta {

            CuentasPropiasDTO cuenta;
            List<DetallesPropuestaDTO> detalles;
            BigDecimal total;
        }
        PropuestasPagoDTO propuesta = propuestasDAO.obtenerPorId(propuestaId);
        if (!"en revisión".equals(propuesta.getEstado().toLowerCase())) {
            return 0;
        }
        propuesta.setUsuario_modificacion(usuario);
        propuesta.setFecha_hora_modificacion(new Date());
        Boolean pagosActualizados = false;
        for (DetallesPropuestaDTO detalle : propuesta.getDetalles_propuesta()) {
            if (detalle.getFactura().getMonto_restante().compareTo(BigDecimal.ZERO) <= 0 || !"pendiente".equals(detalle.getFactura().getEstado().toLowerCase())) {
                pagosActualizados = true;
                detalle.setFecha_eliminacion(new Date());
                detalle.setUsuario_eliminacion(usuario);
            } else if (detalle.getFactura().getMonto_restante().compareTo(detalle.getMonto_pago()) < 0) {
                pagosActualizados = true;
                detalle.setMonto_pago(detalle.getFactura().getMonto_restante());
            }
        }
        Map<Integer, AgrupadoCuenta> resultado
                = propuesta.getDetalles_propuesta()
                        .stream()
                        .collect(Collectors.groupingBy(
                                d -> d.getCuenta_propia().getCuenta_bancaria_id(),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        lista -> {
                                            AgrupadoCuenta a = new AgrupadoCuenta();
                                            a.detalles = lista;
                                            a.cuenta = lista.get(0).getCuenta_propia();
                                            a.total = lista.stream()
                                                    .map(DetallesPropuestaDTO::getMonto_pago)
                                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                                            return a;
                                        }
                                )
                        ));

        for (Map.Entry<Integer, AgrupadoCuenta> entry : resultado.entrySet()) {
            AgrupadoCuenta agrupado = entry.getValue();

            CuentasPropiasDTO cuenta = agrupado.cuenta;

            BigDecimal total = agrupado.total;

            if (cuenta.getSaldo_disponible().compareTo(total) < 0) {
                if (pagosActualizados) {
                    propuestasDAO.modificar(propuesta);
                }
                return 0;
            }
        }
        for (Map.Entry<Integer, AgrupadoCuenta> entry : resultado.entrySet()) {
            AgrupadoCuenta agrupado = entry.getValue();

            CuentasPropiasDTO cuenta = agrupado.cuenta;
            List<DetallesPropuestaDTO> detalles = agrupado.detalles;
            BigDecimal total = agrupado.total;

            for (DetallesPropuestaDTO detalle : detalles) {
                detalle.getFactura().setMonto_restante(detalle.getFactura().getMonto_restante().subtract(detalle.getMonto_pago()));
                if (detalle.getFactura().getMonto_restante().compareTo(new BigDecimal("0.01")) < 0) {
                    detalle.getFactura().setEstado("Pagada");
                    detalle.getFactura().setMonto_restante(BigDecimal.ZERO);
                }
                detalle.setEstado("Pagado");
                facturasDAO.modificar(detalle.getFactura());
            }
            cuenta.setSaldo_disponible(cuenta.getSaldo_disponible().subtract(total));
            CuentasPropiasBO cuentasPropiasBO = new CuentasPropiasBO();
            cuentasPropiasBO.modificar(cuenta, usuario.getUsuario_id());
        }
        propuesta.setEstado("Enviada");
        propuestasDAO.modificar(propuesta);
        return 1;
    }

    public Integer rechazarPropuesta(Integer propuestaId, UsuariosDTO usuarioAdmin) {
        try {
            // Obtener la propuesta
            PropuestasPagoDTO propuesta = propuestasDAO.obtenerPorId(propuestaId);

            if (propuesta == null) {
                return 0;
            }

            // Verificar que esté en estado "En revisión"
            if (!"en revisión".equalsIgnoreCase(propuesta.getEstado())) {
                return 0;
            }

            // Guardar referencia al usuario creador antes de modificar
            UsuariosDTO usuarioCreador = propuesta.getUsuario_creacion();

            // Cambiar estado a "Pendiente"
            propuesta.setEstado("Pendiente");
            propuesta.setUsuario_modificacion(usuarioAdmin);
            propuesta.setFecha_hora_modificacion(new Date());

            // Modificar en base de datos
            Integer resultado = propuestasDAO.modificar(propuesta);

            // Si fue exitoso, enviar correo
            if (resultado > 0 && usuarioCreador != null) {
                EmailUtil emailUtil = new EmailUtil();
                emailUtil.enviarCorreoPropuesta(
                        propuesta,
                        usuarioCreador,
                        usuarioAdmin,
                        TipoOperacionUsuario.RECHAZAR_PROPUESTA
                );
            }

            return resultado;

        } catch (Exception e) {
            System.err.println("Error al rechazar propuesta: " + e.getMessage());
            return 0;
        }
    }
}
