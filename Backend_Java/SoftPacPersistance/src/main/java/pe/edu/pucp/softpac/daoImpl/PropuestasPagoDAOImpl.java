package pe.edu.pucp.softpac.daoImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.softpac.dao.DetallesPropuestaDAO;
import pe.edu.pucp.softpac.dao.PropuestasPagoDAO;
import pe.edu.pucp.softpac.daoImpl.exception.DAODetalleException;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;

public class PropuestasPagoDAOImpl extends DAOImplBase implements PropuestasPagoDAO {

    private PropuestasPagoDTO propuestaPago;
    private List<PropuestasPagoDTO> propuestasPago;

    public PropuestasPagoDAOImpl() {
        super("PA_PROPUESTAS_DE_PAGO");
        this.retornarLlavePrimaria = true;
        this.ejecutaOperacionesEnCascada = true;
        this.seEliminaLogicamente = true;
        super.incluirColumnasDeEliminacionLogica();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("PROPUESTA_DE_PAGO_ID", true, true));
        this.listaColumnas.add(new Columna("FECHA_HORA_CREACION", false, false));
        this.listaColumnas.add(new Columna("ESTADO", false, false));
        this.listaColumnas.add(new Columna("ENTIDAD_BANCARIA_ID", false, false));
        this.listaColumnas.add(new Columna("FECHA_HORA_MODIFICACION", false, false));
        this.listaColumnas.add(new Columna("USUARIO_MODIFICACION", false, false));
        this.listaColumnas.add(new Columna("USUARIO_CREACION", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.propuestaPago.getFecha_hora_creacion().getTime()));
        this.statement.setString(2, this.propuestaPago.getEstado());
        this.statement.setInt(3, this.propuestaPago.getEntidad_bancaria().getEntidad_bancaria_id());
        this.statement.setTimestamp(4, new Timestamp(this.propuestaPago.getFecha_hora_modificacion().getTime()));
        this.statement.setInt(5, this.propuestaPago.getUsuario_modificacion().getUsuario_id());
        this.statement.setInt(6, this.propuestaPago.getUsuario_creacion().getUsuario_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.propuestaPago.getFecha_hora_creacion().getTime()));
        this.statement.setString(2, this.propuestaPago.getEstado());
        this.statement.setInt(3, this.propuestaPago.getEntidad_bancaria().getEntidad_bancaria_id());
        this.statement.setTimestamp(4, new Timestamp(this.propuestaPago.getFecha_hora_modificacion().getTime()));
        this.statement.setInt(5, this.propuestaPago.getUsuario_modificacion().getUsuario_id());
        this.statement.setInt(6, this.propuestaPago.getUsuario_creacion().getUsuario_id());
        this.statement.setInt(7, this.propuestaPago.getPropuesta_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.propuestaPago.getPropuesta_id());
    }

    @Override
    protected String generarSQLParaObtenerPorId() {
        return "SELECT  "
                + " P1_PROPUESTA_DE_PAGO_ID, "
                + " P2_FECHA_HORA_CREACION, "
                + " P3_FECHA_HORA_MODIFICACION, "
                + " P4_USUARIO_CREACION, "
                + " P5_USUARIO_CREACION_CORREO, "
                + " P6_USUARIO_CREACION_NOMBRE_USUARIO, "
                + " P7_USUARIO_CREACION_NOMBRE, "
                + " P8_USUARIO_CREACION_APELLIDOS, "
                + " P9_USUARIO_CREACION_ACTIVO, "
                + " P10_USUARIO_CREACION_SUPERUSUARIO, "
                + " P11_USUARIO_MODIFICACION, "
                + " P12_USUARIO_MODIFICACION_CORREO, "
                + " P13_USUARIO_MODIFICACION_NOMBRE_USUARIO, "
                + " P14_USUARIO_MODIFICACION_NOMBRE, "
                + " P15_USUARIO_MODIFICACION_APELLIDOS, "
                + " P16_USUARIO_MODIFICACION_ACTIVO, "
                + " P17_USUARIO_MODIFICACION_SUPERUSUARIO, "
                + " P18_ESTADO_PROPUESTA, "
                + " P19_ENTIDAD_BANCARIA_PROPUESTA, "
                + " P20_ENTIDAD_BANCARIA_NOMBRE, "
                + " P21_ENTIDAD_BANCARIA_FORMATO, "
                + " P22_ENTIDAD_BANCARIA_SWIFT, "
                + " P23_ENTIDAD_BANCARIA_PAIS_ID, "
                + " P24_ENTIDAD_BANCARIA_PAIS_NOMBRE, "
                + "    -- Detalle de propuesta \n"
                + " D1_DETALLE_PROPUESTA_ID, "
                + " D2_MONTO_PAGO, "
                + " D3_FORMA_PAGO, "
                + " D4_ESTADO, "
                + "    -- Factura \n"
                + " F1_FACTURA_ID, "
                + " F2_NUMERO_FACTURA, "
                + " F3_FECHA_EMISION, "
                + " F4_FECHA_RECEPCION, "
                + " F5_FECHA_LIMITE_PAGO, "
                + " F6_ESTADO_FACTURA, "
                + " F7_MONTO_TOTAL, "
                + " F8_MONTO_IGV, "
                + " F9_MONTO_RESTANTE, "
                + " F10_REGIMEN_FISCAL, "
                + " F11_TASA_IVA, "
                + " F12_OTROS_TRIBUTOS, "
                + " F13_MONEDA_ID, "
                + " F14_ACREEDOR_ID, "
                + "    -- Cuenta acreedor (destino) \n"
                + " CA1_CUENTA_ACREEDOR_ID, "
                + " CA2_TIPO_CUENTA, "
                + " CA3_NUMERO_CUENTA, "
                + " CA4_CCI, "
                + " CA5_ACTIVO, "
                + " CA6_ACREEDOR_ID, "
                + " CA7_ENTIDAD_BANCARIA_ID, "
                + " CA8_MONEDA_ID, "
                + " CA9_ENTIDAD_BANCARIA_NOMBRE, "
                + " CA10_ENTIDAD_BANCARIA_SWIFT, "
                + " CA11_ENTIDAD_BANCARIA_PAIS_ID, "
                + " CA12_ENTIDAD_BANCARIA_PAIS_NOMBRE, "
                + "    -- Cuenta propia (origen) \n"
                + " CP1_CUENTA_PROPIA_ID, "
                + " CP2_SALDO_DISPONIBLE, "
                + " CP3_TIPO_CUENTA, "
                + " CP4_NUMERO_CUENTA, "
                + " CP5_CCI, "
                + " CP6_ACTIVO, "
                + " CP7_ENTIDAD_BANCARIA_ID, "
                + " CP8_MONEDA_ID, "
                + " CP9_ENTIDAD_BANCARIA_NOMBRE, "
                + " CP10_ENTIDAD_BANCARIA_SWIFT, "
                + " CP11_ENTIDAD_BANCARIA_PAIS_ID, "
                + " CP12_ENTIDAD_BANCARIA_PAIS_NOMBRE,"
                + " AC2_RAZON_SOCIAL_ACREEDOR, "
                + " MO1_MONEDA_CODIGO_ISO "
                + " FROM VW_PROPUESTA_COMPLETA WHERE P1_PROPUESTA_DE_PAGO_ID = ? ";
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.propuestaPago.getPropuesta_id());
    }

    /* Extracción de resultSet de obtener propuesta */
    private FacturasDTO insertarFactura() throws SQLException {
        FacturasDTO factura = new FacturasDTO();
        MonedasDTO moneda = new MonedasDTO();
        AcreedoresDTO acreedor = new AcreedoresDTO();

        factura.setFactura_id(this.resultSet.getInt("F1_FACTURA_ID"));
        factura.setNumero_factura(resultSet.getString("F2_NUMERO_FACTURA"));
        factura.setFecha_emision(resultSet.getTimestamp("F3_FECHA_EMISION"));
        factura.setFecha_recepcion(resultSet.getTimestamp("F4_FECHA_RECEPCION"));
        factura.setFecha_limite_pago(resultSet.getTimestamp("F5_FECHA_LIMITE_PAGO"));
        factura.setEstado(this.resultSet.getString("F6_ESTADO_FACTURA"));
        factura.setMonto_total(this.resultSet.getBigDecimal("F7_MONTO_TOTAL"));
        factura.setMonto_igv(this.resultSet.getBigDecimal("F8_MONTO_IGV"));
        factura.setMonto_restante(this.resultSet.getBigDecimal("F9_MONTO_RESTANTE"));
        factura.setRegimen_fiscal(this.resultSet.getString("F10_REGIMEN_FISCAL"));
        factura.setTasa_iva(this.resultSet.getBigDecimal("F11_TASA_IVA"));
        factura.setOtros_tributos(this.resultSet.getBigDecimal("F12_OTROS_TRIBUTOS"));

        moneda.setMoneda_id(this.resultSet.getInt("F13_MONEDA_ID"));
        moneda.setCodigo_iso(this.resultSet.getString("MO1_MONEDA_CODIGO_ISO"));
        factura.setMoneda(moneda);

        acreedor.setAcreedor_id(this.resultSet.getInt("F14_ACREEDOR_ID"));
        acreedor.setRazon_social(this.resultSet.getString("AC2_RAZON_SOCIAL_ACREEDOR"));
        factura.setAcreedor(acreedor);

        return factura;
    }

    private CuentasAcreedorDTO insertarCuentaAcreedor() throws SQLException {
        EntidadesBancariasDTO entidadBancaria = new EntidadesBancariasDTO();
        PaisesDTO pais = new PaisesDTO();
        CuentasAcreedorDTO cuentaAcreedor = new CuentasAcreedorDTO();
        MonedasDTO moneda = new MonedasDTO();
        AcreedoresDTO acreedor = new AcreedoresDTO();

        cuentaAcreedor.setCuenta_bancaria_id(this.resultSet.getInt("CA1_CUENTA_ACREEDOR_ID"));
        cuentaAcreedor.setTipo_cuenta(resultSet.getString("CA2_TIPO_CUENTA"));
        cuentaAcreedor.setNumero_cuenta(resultSet.getString("CA3_NUMERO_CUENTA"));
        cuentaAcreedor.setCci(resultSet.getString("CA4_CCI"));
        String activa = resultSet.getString("CA5_ACTIVO");
        cuentaAcreedor.setActiva(activa != null && activa.charAt(0) == 'S');

        acreedor.setAcreedor_id(this.resultSet.getInt("CA6_ACREEDOR_ID"));
        cuentaAcreedor.setAcreedor(acreedor);

        entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt("CA7_ENTIDAD_BANCARIA_ID"));
        cuentaAcreedor.setEntidad_bancaria(entidadBancaria);

        moneda.setMoneda_id(this.resultSet.getInt("CA8_MONEDA_ID"));
        cuentaAcreedor.setMoneda(moneda);

        entidadBancaria.setNombre(resultSet.getString("CA9_ENTIDAD_BANCARIA_NOMBRE"));
        entidadBancaria.setCodigo_swift(resultSet.getString("CA10_ENTIDAD_BANCARIA_SWIFT"));

        pais.setPais_id(this.resultSet.getInt("CA11_ENTIDAD_BANCARIA_PAIS_ID"));
        pais.setNombre(this.resultSet.getString("CA12_ENTIDAD_BANCARIA_PAIS_NOMBRE"));
        entidadBancaria.setPais(pais);

        return cuentaAcreedor;
    }

    private CuentasPropiasDTO insertarCuentaPropia() throws SQLException {
        EntidadesBancariasDTO entidadBancaria = new EntidadesBancariasDTO();
        PaisesDTO pais = new PaisesDTO();
        CuentasPropiasDTO cuentaPropia = new CuentasPropiasDTO();
        MonedasDTO moneda = new MonedasDTO();

        cuentaPropia.setCuenta_bancaria_id(this.resultSet.getInt("CP1_CUENTA_PROPIA_ID"));
        cuentaPropia.setSaldo_disponible(this.resultSet.getBigDecimal("CP2_SALDO_DISPONIBLE"));
        cuentaPropia.setTipo_cuenta(resultSet.getString("CP3_TIPO_CUENTA"));
        cuentaPropia.setNumero_cuenta(resultSet.getString("CP4_NUMERO_CUENTA"));
        cuentaPropia.setCci(resultSet.getString("CP5_CCI"));
        String activa = resultSet.getString("CP6_ACTIVO");
        cuentaPropia.setActiva(activa != null && activa.charAt(0) == 'S');

        entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt("CP7_ENTIDAD_BANCARIA_ID"));
        cuentaPropia.setEntidad_bancaria(entidadBancaria);

        moneda.setMoneda_id(this.resultSet.getInt("CP8_MONEDA_ID"));
        cuentaPropia.setMoneda(moneda);

        entidadBancaria.setNombre(resultSet.getString("CP9_ENTIDAD_BANCARIA_NOMBRE"));
        entidadBancaria.setCodigo_swift(resultSet.getString("CP10_ENTIDAD_BANCARIA_SWIFT"));

        pais.setPais_id(this.resultSet.getInt("CP11_ENTIDAD_BANCARIA_PAIS_ID"));
        pais.setNombre(this.resultSet.getString("CP12_ENTIDAD_BANCARIA_PAIS_NOMBRE"));
        entidadBancaria.setPais(pais);

        return cuentaPropia;
    }

    private UsuariosDTO insertarUsuarioCreacion() throws SQLException {
        UsuariosDTO usuario = new UsuariosDTO();

        usuario.setUsuario_id(this.resultSet.getInt("P4_USUARIO_CREACION"));
        if (this.resultSet.wasNull()) {
            return null;
        }

        usuario.setCorreo_electronico(this.resultSet.getString("P5_USUARIO_CREACION_CORREO"));
        usuario.setNombre_de_usuario(this.resultSet.getString("P6_USUARIO_CREACION_NOMBRE_USUARIO"));
        usuario.setNombre(this.resultSet.getString("P7_USUARIO_CREACION_NOMBRE"));
        usuario.setApellidos(this.resultSet.getString("P8_USUARIO_CREACION_APELLIDOS"));

        String activo = this.resultSet.getString("P9_USUARIO_CREACION_ACTIVO");
        usuario.setActivo(activo != null && activo.charAt(0) == 'S');

        String superusuario = this.resultSet.getString("P10_USUARIO_CREACION_SUPERUSUARIO");
        usuario.setSuperusuario(superusuario != null && superusuario.charAt(0) == 'S');

        return usuario;
    }

    private UsuariosDTO insertarUsuarioModificacion() throws SQLException {
        UsuariosDTO usuario = new UsuariosDTO();

        usuario.setUsuario_id(this.resultSet.getInt("P11_USUARIO_MODIFICACION"));
        if (this.resultSet.wasNull()) {
            return null;
        }

        usuario.setCorreo_electronico(this.resultSet.getString("P12_USUARIO_MODIFICACION_CORREO"));
        usuario.setNombre_de_usuario(this.resultSet.getString("P13_USUARIO_MODIFICACION_NOMBRE_USUARIO"));
        usuario.setNombre(this.resultSet.getString("P14_USUARIO_MODIFICACION_NOMBRE"));
        usuario.setApellidos(this.resultSet.getString("P15_USUARIO_MODIFICACION_APELLIDOS"));

        String activo = this.resultSet.getString("P16_USUARIO_MODIFICACION_ACTIVO");
        usuario.setActivo(activo != null && activo.charAt(0) == 'S');

        String superusuario = this.resultSet.getString("P17_USUARIO_MODIFICACION_SUPERUSUARIO");
        usuario.setSuperusuario(superusuario != null && superusuario.charAt(0) == 'S');

        return usuario;
    }

    private DetallesPropuestaDTO insertarDetalleDePropuesta() throws SQLException {
        DetallesPropuestaDTO detallePropuesta = new DetallesPropuestaDTO();

        detallePropuesta.setPropuesta_pago(propuestaPago);
        detallePropuesta.setDetalle_propuesta_id(this.resultSet.getInt("D1_DETALLE_PROPUESTA_ID"));
        if (this.resultSet.wasNull()) {
            return null;
        }

        detallePropuesta.setMonto_pago(this.resultSet.getBigDecimal("D2_MONTO_PAGO"));
        String formaPago = this.resultSet.getString("D3_FORMA_PAGO");
        if (formaPago != null && formaPago.length() > 0) {
            detallePropuesta.setForma_pago(formaPago.charAt(0));
        }
        detallePropuesta.setEstado(this.resultSet.getString("D4_ESTADO"));

        detallePropuesta.setFactura(insertarFactura());
        detallePropuesta.setCuenta_acreedor(insertarCuentaAcreedor());
        detallePropuesta.setCuenta_propia(insertarCuentaPropia());

        return detallePropuesta;
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        DetallesPropuestaDTO detallePropuesta;
        EntidadesBancariasDTO entidadBancaria;
        PaisesDTO pais;
        UsuariosDTO usuarioCreacion;
        UsuariosDTO usuarioModificacion;
        this.propuestaPago = null;
        int i = 0;

        while (this.resultSet.next()) {
            if (i == 0) {
                // Crear propuesta de pago
                this.propuestaPago = new PropuestasPagoDTO();
                this.propuestaPago.setPropuesta_id(this.resultSet.getInt("P1_PROPUESTA_DE_PAGO_ID"));
                this.propuestaPago.setFecha_hora_creacion(this.resultSet.getTimestamp("P2_FECHA_HORA_CREACION"));
                this.propuestaPago.setFecha_hora_modificacion(this.resultSet.getTimestamp("P3_FECHA_HORA_MODIFICACION"));

                // Insertar usuarios
                usuarioCreacion = insertarUsuarioCreacion();
                this.propuestaPago.setUsuario_creacion(usuarioCreacion);

                usuarioModificacion = insertarUsuarioModificacion();
                this.propuestaPago.setUsuario_modificacion(usuarioModificacion);

                this.propuestaPago.setEstado(this.resultSet.getString("P18_ESTADO_PROPUESTA"));

                // Entidad bancaria de la propuesta
                entidadBancaria = new EntidadesBancariasDTO();
                entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt("P19_ENTIDAD_BANCARIA_PROPUESTA"));
                entidadBancaria.setNombre(this.resultSet.getString("P20_ENTIDAD_BANCARIA_NOMBRE"));
                entidadBancaria.setFormato_aceptado(this.resultSet.getString("P21_ENTIDAD_BANCARIA_FORMATO"));
                entidadBancaria.setCodigo_swift(resultSet.getString("P22_ENTIDAD_BANCARIA_SWIFT"));

                pais = new PaisesDTO();
                pais.setPais_id(this.resultSet.getInt("P23_ENTIDAD_BANCARIA_PAIS_ID"));
                pais.setNombre(this.resultSet.getString("P24_ENTIDAD_BANCARIA_PAIS_NOMBRE"));
                entidadBancaria.setPais(pais);

                propuestaPago.setEntidad_bancaria(entidadBancaria);
                i++;
            }

            // Insertar detalle de propuesta
            detallePropuesta = insertarDetalleDePropuesta();
            if (detallePropuesta == null) {
                break;
            }
            propuestaPago.addDetalle_Propuesta(detallePropuesta);
        }
    }

//    @Override
//    protected String generarSQLParaListarTodos(){
//        return """
//           SELECT 
//               p.PROPUESTA_DE_PAGO_ID,
//               p.FECHA_HORA_CREACION,
//               p.FECHA_HORA_MODIFICACION,
//               p.ESTADO,
//               p.ENTIDAD_BANCARIA_ID,
//               p.USUARIO_CREACION,
//               p.USUARIO_MODIFICACION,
//
//               uc.NOMBRE AS USUARIO_CREACION_NOMBRE,
//               uc.APELLIDOS AS USUARIO_CREACION_APELLIDOS,
//               uc.NOMBRE_DE_USUARIO AS USUARIO_CREACION,
//
//               eb.NOMBRE AS BANCO_NOMBRE,
//
//               pa.PAIS_ID,
//               pa.NOMBRE AS PAIS_NOMBRE,
//
//               COUNT(dp.DETALLE_PROPUESTA_ID) AS CANTIDAD_DETALLES
//
//           FROM 
//               PA_PROPUESTAS_DE_PAGO p
//               INNER JOIN PA_USUARIOS uc 
//                   ON p.USUARIO_CREACION = uc.USUARIO_ID
//               INNER JOIN PA_ENTIDADES_BANCARIAS eb 
//                   ON p.ENTIDAD_BANCARIA_ID = eb.ENTIDAD_BANCARIA_ID
//               INNER JOIN PA_PAISES pa 
//                   ON eb.PAIS_ID = pa.PAIS_ID
//               LEFT JOIN PA_DETALLES_PROPUESTA dp 
//                   ON p.PROPUESTA_DE_PAGO_ID = dp.PROPUESTA_DE_PAGO_ID
//                   AND dp.FECHA_ELIMINACION IS NULL
//
//           WHERE 
//               p.FECHA_ELIMINACION IS NULL
//
//           GROUP BY 
//               p.PROPUESTA_DE_PAGO_ID,
//               p.FECHA_HORA_CREACION,
//               p.FECHA_HORA_MODIFICACION,
//               p.ESTADO,
//               p.ENTIDAD_BANCARIA_ID,
//               p.USUARIO_CREACION,
//               p.USUARIO_MODIFICACION,
//               uc.NOMBRE,
//               uc.APELLIDOS,
//               uc.NOMBRE_DE_USUARIO,
//               eb.NOMBRE,
//               pa.PAIS_ID,
//               pa.NOMBRE
//
//           ORDER BY 
//               p.PROPUESTA_DE_PAGO_ID DESC;
//           """;
//    }

    @Override
    protected String generarSQLCustom1() {
        return """
       SELECT 
           p.PROPUESTA_DE_PAGO_ID,
           p.FECHA_HORA_CREACION,
           p.FECHA_HORA_MODIFICACION,
           p.ESTADO,
           p.ENTIDAD_BANCARIA_ID,
           p.USUARIO_CREACION,
           p.USUARIO_MODIFICACION,

           uc.NOMBRE AS USUARIO_CREACION_NOMBRE,
           uc.APELLIDOS AS USUARIO_CREACION_APELLIDOS,
           uc.NOMBRE_DE_USUARIO AS USUARIO_CREACION,

           eb.NOMBRE AS BANCO_NOMBRE,

           pa.PAIS_ID,
           pa.NOMBRE AS PAIS_NOMBRE,

           COUNT(dp.DETALLE_PROPUESTA_ID) AS CANTIDAD_DETALLES

       FROM 
           PA_PROPUESTAS_DE_PAGO p
           INNER JOIN PA_USUARIOS uc 
               ON p.USUARIO_CREACION = uc.USUARIO_ID
           INNER JOIN PA_ENTIDADES_BANCARIAS eb 
               ON p.ENTIDAD_BANCARIA_ID = eb.ENTIDAD_BANCARIA_ID
           INNER JOIN PA_PAISES pa 
               ON eb.PAIS_ID = pa.PAIS_ID
           LEFT JOIN PA_DETALLES_PROPUESTA dp 
               ON p.PROPUESTA_DE_PAGO_ID = dp.PROPUESTA_DE_PAGO_ID
               AND dp.FECHA_ELIMINACION IS NULL

       WHERE 
           p.FECHA_ELIMINACION IS NULL
           AND (
               p.FECHA_HORA_CREACION >= ?
               OR UPPER(p.ESTADO) = 'PENDIENTE' 
           )

       GROUP BY 
           p.PROPUESTA_DE_PAGO_ID,
           p.FECHA_HORA_CREACION,
           p.FECHA_HORA_MODIFICACION,
           p.ESTADO,
           p.ENTIDAD_BANCARIA_ID,
           p.USUARIO_CREACION,
           p.USUARIO_MODIFICACION,
           uc.NOMBRE,
           uc.APELLIDOS,
           uc.NOMBRE_DE_USUARIO,
           eb.NOMBRE,
           pa.PAIS_ID,
           pa.NOMBRE

       ORDER BY 
           p.PROPUESTA_DE_PAGO_ID DESC;
       """;
    }

    @Override
    protected void extraerResultSetCustom1() throws SQLException {
        propuestasPago = new ArrayList<>();
        EntidadesBancariasDTO entidadBancaria;
        PaisesDTO pais;
        while (this.resultSet.next()) {
            PropuestasPagoDTO p = new PropuestasPagoDTO();
            p.setPropuesta_id(this.resultSet.getInt(1));
            p.setFecha_hora_creacion(this.resultSet.getTimestamp(2));
            p.setFecha_hora_modificacion(this.resultSet.getTimestamp(3));
            p.setEstado(this.resultSet.getString(4));
            entidadBancaria = new EntidadesBancariasDTO();
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(5));
            /*Añadido*/
            UsuariosDTO userCreacion = new UsuariosDTO();
            userCreacion.setUsuario_id(this.resultSet.getInt(6));
            UsuariosDTO userModificacion = new UsuariosDTO();
            userModificacion.setUsuario_id(this.resultSet.getInt(7));
            userCreacion.setNombre(this.resultSet.getString(8));
            userCreacion.setApellidos(this.resultSet.getString(9));
            userCreacion.setNombre_de_usuario(this.resultSet.getString(10));
            entidadBancaria.setNombre(this.resultSet.getString(11));
            pais = new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(12));
            pais.setNombre(this.resultSet.getString(13));
            entidadBancaria.setPais(pais);
            p.setEntidad_bancaria(entidadBancaria);
            p.setUsuario_creacion(userCreacion);
            p.setUsuario_modificacion(userModificacion);
            int cantDetalles = this.resultSet.getInt(14);
            for (int i = 0; i < cantDetalles; i++) {
                DetallesPropuestaDTO detalle = new DetallesPropuestaDTO();
                detalle.setForma_pago('-');
                detalle.setMonto_pago(BigDecimal.ONE);
                p.addDetalle_Propuesta(detalle);
            }
            propuestasPago.add(p);
        }
    }
    
    
    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.MONTH, -6);
        java.sql.Timestamp fechaSeisMesesAtras = new java.sql.Timestamp(cal.getTimeInMillis());
        this.statement.setTimestamp(1, fechaSeisMesesAtras);
    }

    @Override
    public Integer insertar(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago = propuestaPago;
        return super.insertar();
    }

    @Override
    public PropuestasPagoDTO obtenerPorId(Integer propuestaPagoId) {
        this.propuestaPago = new PropuestasPagoDTO();
        this.propuestaPago.setPropuesta_id(propuestaPagoId);
        super.obtenerPorId();
        if (this.propuestaPago == null) {
            this.propuestaPago = new PropuestasPagoDTO();
            this.propuestaPago.setDetalles_propuesta(new ArrayList<>());
        }
        return this.propuestaPago;
    }

    @Override
    public List<PropuestasPagoDTO> listarTodos() {
        super.queryCustom1();
        if (this.propuestasPago == null) {
            this.propuestasPago = new ArrayList<>();
        }
        return propuestasPago;
    }

    @Override
    public Integer modificar(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago = propuestaPago;
        return super.modificar();
    }

    @Override
    public Integer eliminar(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago = propuestaPago;
        return super.eliminar();
    }

    @Override
    public Integer eliminarLogico(PropuestasPagoDTO propuestaPago) {
        this.propuestaPago = propuestaPago;
        return super.eliminarLogico();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.propuestaPago.getFecha_eliminacion().getTime()));
        this.statement.setInt(2, this.propuestaPago.getUsuario_eliminacion().getUsuario_id());
        this.statement.setInt(3, this.propuestaPago.getPropuesta_id());
    }

    @Override
    protected void recuperarAutoGeneradoParaInsercionDeDetalle(Integer resultado) {
        this.propuestaPago.setPropuesta_id(resultado);
    }

    @Override
    protected void ejecutarCascadaParaInsercion() {
        try {
            DetallesPropuestaDAO daoDetalle = new DetallesPropuestaDAOImpl(this.getConexion());
            for (DetallesPropuestaDTO detalle : this.propuestaPago.getDetalles_propuesta()) {
                detalle.setPropuesta_pago(this.propuestaPago);
                daoDetalle.insertar(detalle);
            }
        } catch (DAODetalleException e) {
            throw e;
        }
    }

    @Override
    protected void ejecutarCascadaParaModificacion() {
        try {
            DetallesPropuestaDAO daoDetalle = new DetallesPropuestaDAOImpl(this.getConexion());
            for (DetallesPropuestaDTO detalle : this.propuestaPago.getDetalles_propuesta()) {
                if (detalle.getDetalle_propuesta_id() == 0) {
                    daoDetalle.insertar(detalle);
                } else {
                    daoDetalle.modificar(detalle);
                    if (detalle.getFecha_eliminacion() != null) {
                        daoDetalle.eliminarLogico(detalle);
                    }
                }
            }
        } catch (DAODetalleException e) {
            throw e;
        }
    }

    @Override
    protected void ejecutarCascadaParaEliminacion() {
        try {
            DetallesPropuestaDAO daoDetalle = new DetallesPropuestaDAOImpl(this.getConexion());
            daoDetalle.eliminarPorPropuesta(this.propuestaPago.getPropuesta_id());
        } catch (DAODetalleException e) {
            throw e;
        }
    }
}
