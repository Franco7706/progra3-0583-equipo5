package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import pe.edu.pucp.softpac.dao.ReportePropPagoDAO;
import pe.edu.pucp.softpac.daoImpl.util.ReportePropuestasPagoParametrosBuilder;
import pe.edu.pucp.softpac.daoImpl.util.ReportePropuestasPagoParametros;
import pe.edu.pucp.softpac.model.ReportePagoDTO;
import pe.edu.pucp.softpac.model.ReportePropPagoDTO;


public class ReportePropPagoDAOImpl extends DAOImplBase implements ReportePropPagoDAO {
    
    private ArrayList<ReportePropPagoDTO> reportePropPago;
    private ReportePropuestasPagoParametros parametros;
    
    public ReportePropPagoDAOImpl() {
        super("PA_REPORTE_PROPUESTAS_DE_PAGO");
    }
    
    @Override
    protected void configurarListaDeColumnas() {
    
    }
    
//    //METODO PRINCIPAL. LLAMARÁ A EL PROCEDURE ALMACENADO: "pa_sp_reporte_propuestas_de_pago(país,entidad bancaría)"
//    @Override
//    public void generarReportePropuestasDePago(Integer pais_id, Integer entidad_bancaria_id){
//        
//        Object parametros = new ReportePropuestasPagoParametrosBuilder()
//            .conIdPais(pais_id)
//            .conIdBanco(entidad_bancaria_id)
//            .build();
//    
//        String sql = "{call pa_sp_reporte_propuestas_de_pago(?, ?)}";
//        Boolean conTransaccion = true;
//
//        this.ejecutarProcedimientoAlmacenado(
//            sql, this::incluirValorDeParametrosParaGenerarReportePropuestasPendientes, parametros, conTransaccion);
//    }
//    
//    private void incluirValorDeParametrosParaGenerarReportePropuestasPendientes(Object objetoParametros){
//        ReportePropuestasPagoParametros parametros = (ReportePropuestasPagoParametros) objetoParametros;
//        try {            
//            this.statement.setInt(1, parametros.getIdPais());
//            this.statement.setInt(2, parametros.getIdBanco());
//        } catch (SQLException ex) {
//            System.getLogger(ReportePropPagoDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
//        }
//    }

    @Override
    public ArrayList<ReportePropPagoDTO> listarPorFiltros(Integer pais_id, Integer entidad_bancaria_id, LocalDateTime fecha_inicio, LocalDateTime fecha_fin) {
        this.parametros = new ReportePropuestasPagoParametrosBuilder().
                conIdPais(pais_id).conIdBanco(entidad_bancaria_id).conFechaInicio(fecha_inicio).conFechaFin(fecha_fin).build();
        super.queryCustom1();
        return reportePropPago;
    }
    
    @Override
    protected void extraerResultSetCustom1() throws SQLException {
        if (resultSet == null) {
            this.reportePropPago = new ArrayList<>();
            return;
        }
        Map<Integer, ReportePropPagoDTO> propuestasMap = new LinkedHashMap<>();
        
        while (resultSet.next()) {
            int idPropuesta = resultSet.getInt("idPropuesta");
            
            // Si la propuesta no existe en el map, crearla
            ReportePropPagoDTO propuesta = propuestasMap.get(idPropuesta);
            if (propuesta == null) {
                propuesta = new ReportePropPagoDTO();
                propuesta.setIdPropuesta(idPropuesta);
                propuesta.setEstado(resultSet.getString("estado"));
                propuesta.setFechaCreacion(resultSet.getTimestamp("fechaCreacion"));
                propuesta.setUsuarioCreador(resultSet.getString("usuarioCreador"));
                propuesta.setCorreoUsuarioCreador(resultSet.getString("correoUsuarioCreador"));
                propuesta.setPais(resultSet.getString("pais"));
                propuesta.setBancoPropuesta(resultSet.getString("bancoPropuesta"));
                
                if (propuesta.getPagos() == null) {
                    propuesta.setPagos(new ArrayList<>());
                }
                
                propuestasMap.put(idPropuesta, propuesta);
            }
            
            // Crear y agregar el pago (detalle) si existe
            String numeroFactura = resultSet.getString("numeroFactura");
            if (numeroFactura != null) {  // Verificar que existe un detalle
                ReportePagoDTO pago = new ReportePagoDTO();
                pago.setNumeroFactura(numeroFactura);
                pago.setAcreedor(resultSet.getString("acreedor"));
                pago.setMoneda(resultSet.getString("moneda"));
                pago.setMonto(resultSet.getBigDecimal("monto"));
                pago.setCuentaOrigen(resultSet.getString("cuentaOrigen"));
                pago.setBancoOrigen(resultSet.getString("bancoOrigen"));
                pago.setCuentaDestino(resultSet.getString("cuentaDestino"));
                pago.setBancoDestino(resultSet.getString("bancoDestino"));
                pago.setFormaPago(resultSet.getString("formaPago"));
                
                propuesta.addPago(pago);
            }
        }
        this.reportePropPago = new ArrayList<>(propuestasMap.values());
    }
    
    @Override
    protected String generarSQLCustom1(){
        return """
        SELECT 
            p.PROPUESTA_DE_PAGO_ID         AS idPropuesta,
            p.ESTADO                       AS estado,
            p.FECHA_HORA_CREACION          AS fechaCreacion,
            CONCAT(uc.NOMBRE, ' ', uc.APELLIDOS) AS usuarioCreador,
            uc.CORREO_ELECTRONICO          AS correoUsuarioCreador,
            pbp.NOMBRE                     AS pais,
            ebp.NOMBRE                     AS bancoPropuesta,
            f.NUMERO_FACTURA               AS numeroFactura,
            a.RAZON_SOCIAL                 AS acreedor,
            mf.CODIGO_ISO                  AS moneda,
            dp.MONTO_PAGO                  AS monto,
            cp.NUMERO_CUENTA               AS cuentaOrigen,
            ebo.NOMBRE                     AS bancoOrigen,
            ca.NUMERO_CUENTA               AS cuentaDestino,
            eba.NOMBRE                     AS bancoDestino,
            dp.FORMA_PAGO                  AS formaPago
        FROM PA_PROPUESTAS_DE_PAGO p
        JOIN PA_ENTIDADES_BANCARIAS ebp ON ebp.ENTIDAD_BANCARIA_ID = p.ENTIDAD_BANCARIA_ID
        JOIN PA_PAISES pbp ON pbp.PAIS_ID = ebp.PAIS_ID
        LEFT JOIN PA_USUARIOS uc ON uc.USUARIO_ID = p.USUARIO_CREACION
        LEFT JOIN PA_DETALLES_PROPUESTA dp ON dp.PROPUESTA_DE_PAGO_ID = p.PROPUESTA_DE_PAGO_ID
        LEFT JOIN PA_FACTURAS f ON f.FACTURA_ID = dp.FACTURA_ID
        LEFT JOIN PA_ACREEDORES a ON a.ACREEDOR_ID = f.ACREEDOR_ID
        LEFT JOIN PA_MONEDAS mf ON mf.MONEDA_ID = f.MONEDA_ID
        LEFT JOIN PA_CUENTAS_ACREEDOR ca ON ca.CUENTA_ACREEDOR_ID = dp.CUENTA_ACREEDOR_ID
        LEFT JOIN PA_ENTIDADES_BANCARIAS eba ON eba.ENTIDAD_BANCARIA_ID = ca.ENTIDAD_BANCARIA_ID
        LEFT JOIN PA_CUENTAS_PROPIAS cp ON cp.CUENTA_PROPIA_ID = dp.CUENTA_PROPIA_ID
        LEFT JOIN PA_ENTIDADES_BANCARIAS ebo ON ebo.ENTIDAD_BANCARIA_ID = cp.ENTIDAD_BANCARIA_ID
        WHERE dp.FECHA_ELIMINACION IS NULL
          AND ebp.PAIS_ID = ?
          AND p.ENTIDAD_BANCARIA_ID = ?
          AND p.FECHA_HORA_CREACION >= ?
          AND p.FECHA_HORA_CREACION < ?
        ORDER BY p.PROPUESTA_DE_PAGO_ID, dp.DETALLE_PROPUESTA_ID
        """;
    }
    
    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException {
        this.statement.setInt(1, parametros.getIdPais());
        this.statement.setInt(2, parametros.getIdBanco());
        this.statement.setTimestamp(3, Timestamp.valueOf(parametros.getFechaInicio()));
        this.statement.setTimestamp(4, Timestamp.valueOf(parametros.getFechaFin()));
    }
}