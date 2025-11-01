package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.daoImpl.EntidadesBancariasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PropuestasPagoDAOImpl;
import pe.edu.pucp.softpac.daoImpl.MonedasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.AcreedoresDAOImpl;
import pe.edu.pucp.softpac.daoImpl.CuentasPropiasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.CuentasAcreedorDAOImpl;
import pe.edu.pucp.softpac.daoImpl.FacturasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;
import pe.edu.pucp.softpac.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PropuestasPagoDAOTest {

    // ======= Fixtures adicionales para detalles de propuesta =======
    protected static MonedasDAO monedasDAO;
    protected static MonedasDTO testMoneda;
    protected static AcreedoresDAO acreedoresDAO;
    protected static AcreedoresDTO testAcreedor;

    protected static CuentasPropiasDAO cuentasPropiasDAO;
    protected static CuentasPropiasDTO testCuentaPropia;
    protected static CuentasAcreedorDAO cuentasAcreedorDAO;
    protected static CuentasAcreedorDTO testCuentaAcreedor;

    protected static FacturasDAO facturasDAO;
    protected static FacturasDTO testFacturaRef;
    protected static FacturasDTO testFacturaRef2;
    protected static FacturasDTO testFacturaRef3;

    protected static UsuariosDTO testUsuarioEliminacion;

    protected static PropuestasPagoDAO dao;
    protected static PropuestasPagoDTO testPropuesta;
    private static Integer generatedId;

    protected static PaisesDTO testPais;
    protected static PaisesDAO paisesDAO;
    protected static EntidadesBancariasDTO testEntidad;
    protected static EntidadesBancariasDAO entidadesBancariasDAO;
    protected static UsuariosDTO testUsuario;
    protected static UsuariosDAO usuariosDAO;

    @BeforeAll
    static void init(){
            // DAOs base
        monedasDAO = new MonedasDAOImpl();
        acreedoresDAO = new AcreedoresDAOImpl();
        cuentasPropiasDAO = new CuentasPropiasDAOImpl();
        cuentasAcreedorDAO = new CuentasAcreedorDAOImpl();
        facturasDAO = new FacturasDAOImpl();

        paisesDAO = new PaisesDAOImpl();
        testPais = new PaisesDTO(0,"PaisPruebaPropuesta","XP","+");
        Integer idPais = paisesDAO.insertar(testPais);
        testPais.setPais_id(idPais);

        entidadesBancariasDAO = new EntidadesBancariasDAOImpl();
        testEntidad = new EntidadesBancariasDTO();
        testEntidad.setNombre("Test");
        testEntidad.setFormato_aceptado("x");
        testEntidad.setCodigo_swift("000");
        testEntidad.setPais(testPais);
        Integer idBanco = entidadesBancariasDAO.insertar(testEntidad);
        testEntidad.setEntidad_bancaria_id(idBanco);

        usuariosDAO = new UsuariosDAOImpl();
        testUsuario = new UsuariosDTO();
        testUsuario.setUsuario_id(1001);
        testUsuario.setCorreo_electronico("correo@prueba.com");
        testUsuario.setNombre_de_usuario("usuario_prueba");
        testUsuario.setActivo(true);
        testUsuario.setSuperusuario(false);
        testUsuario.setPassword_hash("123456");
        usuariosDAO.insertar(testUsuario);

        // Usuario genérico para eliminación lógica
        testUsuarioEliminacion = new UsuariosDTO();
        testUsuarioEliminacion.setUsuario_id(1901);
        testUsuarioEliminacion.setCorreo_electronico("eliminacion@softpac.test");
        testUsuarioEliminacion.setNombre_de_usuario("Usuario de eliminación");
        testUsuarioEliminacion.setActivo(true);
        testUsuarioEliminacion.setSuperusuario(true);
        testUsuarioEliminacion.setPassword_hash("elim123");
        usuariosDAO.insertar(testUsuarioEliminacion);

        // Moneda
        testMoneda = new MonedasDTO();
        testMoneda.setNombre("Moneda Tst");
        testMoneda.setCodigo_iso("TP1");
        testMoneda.setSimbolo("T$");
        testMoneda.setMoneda_id(monedasDAO.insertar(testMoneda));

        // Acreedor
        testAcreedor = new AcreedoresDTO();
        testAcreedor.setRazon_social("Acreedor Tst SAC");
        testAcreedor.setRuc("20999999991");
        testAcreedor.setDireccion_fiscal("Calle Tst 123");
        testAcreedor.setCondicion("HABIDO");
        testAcreedor.setPlazo_de_pago(15);
        testAcreedor.setActivo(true);
        testAcreedor.setPais(testPais);
        testAcreedor.setAcreedor_id(acreedoresDAO.insertar(testAcreedor));

        // Cuenta propia
        testCuentaPropia = new CuentasPropiasDTO();
        testCuentaPropia.setSaldo_disponible(new java.math.BigDecimal("10000.00"));
        testCuentaPropia.setTipo_cuenta("AHO");
        testCuentaPropia.setNumero_cuenta("0001-TP");
        testCuentaPropia.setCci("CCI-TP-0001");
        testCuentaPropia.setActiva(true);
        testCuentaPropia.setEntidad_bancaria(testEntidad);
        testCuentaPropia.setMoneda(testMoneda);
        testCuentaPropia.setCuenta_bancaria_id(cuentasPropiasDAO.insertar(testCuentaPropia));

        // Cuenta acreedor
        testCuentaAcreedor = new CuentasAcreedorDTO();
        testCuentaAcreedor.setTipo_cuenta("CTE");
        testCuentaAcreedor.setNumero_cuenta("0002-TP");
        testCuentaAcreedor.setCci("CCI-TP-0002");
        testCuentaAcreedor.setActiva(true);
        testCuentaAcreedor.setAcreedor(testAcreedor);
        testCuentaAcreedor.setEntidad_bancaria(testEntidad);
        testCuentaAcreedor.setMoneda(testMoneda);
        testCuentaAcreedor.setCuenta_bancaria_id(cuentasAcreedorDAO.insertar(testCuentaAcreedor));

        // Factura de referencia para los detalles de propuesta
        java.math.BigDecimal montoBruto = new java.math.BigDecimal("200.00");
        java.math.BigDecimal montoIgv = new java.math.BigDecimal("36.00");
        java.math.BigDecimal otrosTributos = new java.math.BigDecimal("0.00");
        java.math.BigDecimal total = new java.math.BigDecimal("236.00");

        testFacturaRef = new FacturasDTO();
        testFacturaRef.setFactura_id(0);
        testFacturaRef.setNumero_factura("PF-" + System.nanoTime());
        testFacturaRef.setFecha_emision(new Date(2025,1,1,10,0));
        testFacturaRef.setFecha_recepcion(new Date(2025,1,2,10,0));
        testFacturaRef.setFecha_limite_pago(new Date(2025,2,1,23,59));
        testFacturaRef.setEstado("PENDIENTE");
        testFacturaRef.setMonto_total(total);
        testFacturaRef.setMonto_igv(montoIgv);
        testFacturaRef.setMonto_restante(total);
        testFacturaRef.setRegimen_fiscal("MYPE_TRIBUTARIO");
        testFacturaRef.setTasa_iva(new java.math.BigDecimal("18.00"));
        testFacturaRef.setOtros_tributos(otrosTributos);
        testFacturaRef.setAcreedor(testAcreedor);
        testFacturaRef.setMoneda(testMoneda);
        testFacturaRef.setFactura_id(facturasDAO.insertar(testFacturaRef));

        testFacturaRef2 = new FacturasDTO();
        testFacturaRef2.setFactura_id(0);
        testFacturaRef2.setNumero_factura("PF2-" + System.nanoTime());
        testFacturaRef2.setFecha_emision(new Date(2025,1,1,10,0));
        testFacturaRef2.setFecha_recepcion(new Date(2025,1,2,10,0));
        testFacturaRef2.setFecha_limite_pago(new Date(2025,2,1,23,59));
        testFacturaRef2.setEstado("PENDIENTE");
        testFacturaRef2.setMonto_total(total);
        testFacturaRef2.setMonto_igv(montoIgv);
        testFacturaRef2.setMonto_restante(total);
        testFacturaRef2.setRegimen_fiscal("MYPE_TRIBUTARIO");
        testFacturaRef2.setTasa_iva(new java.math.BigDecimal("25.00"));
        testFacturaRef2.setOtros_tributos(otrosTributos);
        testFacturaRef2.setAcreedor(testAcreedor);
        testFacturaRef2.setMoneda(testMoneda);
        testFacturaRef2.setFactura_id(facturasDAO.insertar(testFacturaRef2));
        
        testFacturaRef3 = new FacturasDTO();
        testFacturaRef3.setFactura_id(0);
        testFacturaRef3.setNumero_factura("PF3-" + System.nanoTime());
        testFacturaRef3.setFecha_emision(new Date(2025,1,1,10,0));
        testFacturaRef3.setFecha_recepcion(new Date(2025,1,2,10,0));
        testFacturaRef3.setFecha_limite_pago(new Date(2025,2,1,23,59));
        testFacturaRef3.setEstado("PENDIENTE");
        testFacturaRef3.setMonto_total(total);
        testFacturaRef3.setMonto_igv(montoIgv);
        testFacturaRef3.setMonto_restante(total);
        testFacturaRef3.setRegimen_fiscal("MYPE_TRIBUTARIO");
        testFacturaRef3.setTasa_iva(new java.math.BigDecimal("30.00"));
        testFacturaRef3.setOtros_tributos(otrosTributos);
        testFacturaRef3.setAcreedor(testAcreedor);
        testFacturaRef3.setMoneda(testMoneda);
        testFacturaRef3.setFactura_id(facturasDAO.insertar(testFacturaRef3));
    }

    @AfterAll
    static void deleteDependencies(){
        // Eliminar propuesta residual si quedó
        try { if (generatedId != null && generatedId > 0) { testPropuesta.setPropuesta_id(generatedId); dao.eliminar(testPropuesta); } } catch (Exception ignored) {}
        // Eliminar factura de referencia
        try { if (testFacturaRef != null && testFacturaRef.getFactura_id() != null) { facturasDAO.eliminar(testFacturaRef); } } catch (Exception ignored) {}
        try { if (testFacturaRef2 != null && testFacturaRef2.getFactura_id() != null) { facturasDAO.eliminar(testFacturaRef2); } } catch (Exception ignored) {}
        try { if (testFacturaRef3 != null && testFacturaRef3.getFactura_id() != null) { facturasDAO.eliminar(testFacturaRef3); } } catch (Exception ignored) {}
        // Eliminar cuentas
        try { if (testCuentaAcreedor != null && testCuentaAcreedor.getCuenta_bancaria_id() != null) { cuentasAcreedorDAO.eliminar(testCuentaAcreedor); } } catch (Exception ignored) {}
        try { if (testCuentaPropia != null && testCuentaPropia.getCuenta_bancaria_id() != null) { cuentasPropiasDAO.eliminar(testCuentaPropia); } } catch (Exception ignored) {}
        // Eliminar acreedor y moneda
        try { if (testAcreedor != null && testAcreedor.getAcreedor_id() != null) { acreedoresDAO.eliminar(testAcreedor); } } catch (Exception ignored) {}
        try { if (testMoneda != null && testMoneda.getMoneda_id() != null) { monedasDAO.eliminar(testMoneda); } } catch (Exception ignored) {}
        // Entidad, país y usuarios
        try { entidadesBancariasDAO.eliminar(testEntidad); } catch (Exception ignored) {}
        try { paisesDAO.eliminar(testPais); } catch (Exception ignored) {}
        try { usuariosDAO.eliminar(testUsuario); } catch (Exception ignored) {}
        try { usuariosDAO.eliminar(testUsuarioEliminacion); } catch (Exception ignored) {}
    }

    @BeforeEach
    void setUp() {
        dao = new PropuestasPagoDAOImpl();

        testPropuesta = new PropuestasPagoDTO();
        testPropuesta.setPropuesta_id(0);
        testPropuesta.setFecha_hora_creacion(new Date());
        testPropuesta.setEstado("PENDIENTE");
        testPropuesta.setEntidad_bancaria(testEntidad);
        testPropuesta.setFecha_hora_creacion(new Date());
        testPropuesta.setFecha_hora_modificacion(new Date());
        testPropuesta.setUsuario_creacion(testUsuario);
        testPropuesta.setUsuario_modificacion(testUsuario);
    }

    @AfterEach
    void tearDown() {
        if (generatedId > 0) {
            try {
                testPropuesta.setPropuesta_id(generatedId);
                dao.eliminar(testPropuesta);
            } catch (Exception e) {
                // Ignorar excepciones en limpieza
            }
            generatedId = 0;
        }
    }

    @Test
    @Order(1)
    @DisplayName("Contrato: debe insertar una propuesta y devolver su ID")
    void testInsertar() {
        Integer nuevoId = dao.insertar(testPropuesta);

        assertNotNull(nuevoId, "El ID generado no debe ser null");
        assertTrue(nuevoId > 0, "El ID generado debe ser mayor que 0");
        generatedId = nuevoId;
    }

    @Test
    @Order(2)
    @DisplayName("Contrato: debe obtener una propuesta por su ID")
    void testObtenerPorId() {
        generatedId = dao.insertar(testPropuesta);

        PropuestasPagoDTO propuesta = dao.obtenerPorId(generatedId);

        assertNotNull(propuesta, "La propuesta obtenida no debe ser null");
        assertEquals(generatedId, propuesta.getPropuesta_id(), "El ID de la propuesta no coincide");
        assertEquals("PENDIENTE", propuesta.getEstado(), "El estado inicial debe ser PENDIENTE");

        assertNull(dao.obtenerPorId(-1), "Debe devolver null para un ID inexistente");
    }

    @Test
    @Order(3)
    @DisplayName("Contrato: debe modificar una propuesta existente")
    void testModificar() {
        generatedId = dao.insertar(testPropuesta);

        testPropuesta.setPropuesta_id(generatedId);
        testPropuesta.setEstado("APROBADO");

        int resultado = dao.modificar(testPropuesta);
        assertTrue(resultado > 0, "Modificar debe afectar al menos una fila");

        PropuestasPagoDTO propuestaModificada = dao.obtenerPorId(generatedId);
        assertNotNull(propuestaModificada, "La propuesta modificada no debe ser null");
        assertEquals("APROBADO", propuestaModificada.getEstado(), "El estado debe actualizarse a APROBADO");
    }

    @Test
    @Order(4)
    @DisplayName("Contrato: debe listar todas las propuestas")
    void testListarTodos() {
        generatedId = dao.insertar(testPropuesta);

        ArrayList<PropuestasPagoDTO> lista = (ArrayList<PropuestasPagoDTO>) dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser null");
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
        assertTrue(lista.stream().anyMatch(p -> Objects.equals(p.getPropuesta_id(), generatedId)),
                 "La lista debe contener la propuesta insertada");
    }

    @Test
    @Order(5)
    @DisplayName("Contrato: debe eliminar una propuesta")
    void testEliminar() {
        generatedId = dao.insertar(testPropuesta);
        testPropuesta.setPropuesta_id(generatedId);

        int resultado = dao.eliminar(testPropuesta);
        assertTrue(resultado > 0, "Eliminar debe afectar al menos una fila");

        PropuestasPagoDTO propuestaEliminada = dao.obtenerPorId(generatedId);
        assertNull(propuestaEliminada, "Después de eliminar, obtenerPorId debe devolver null");

        generatedId = 0; // Evita que tearDown intente borrar otra vez
    }
    @Test
    @Order(6)
    @DisplayName("Contrato: debe realizar eliminación lógica de una propuesta")
    void testEliminarLogico() {
        generatedId = dao.insertar(testPropuesta);
        testPropuesta.setPropuesta_id(generatedId);
        testPropuesta.setFecha_eliminacion(new Date());
        testPropuesta.setUsuario_eliminacion(testUsuarioEliminacion);

        int result = dao.eliminarLogico(testPropuesta);
        assertTrue(result > 0, "Eliminar lógico debe afectar al menos una fila");

        PropuestasPagoDTO propuesta = dao.obtenerPorId(generatedId);
        assertNotNull(propuesta, "La propuesta debe existir tras eliminación lógica");

        // Debe excluirse de listarTodos
        ArrayList<PropuestasPagoDTO> lista = (ArrayList<PropuestasPagoDTO>) dao.listarTodos();
        assertTrue(lista.stream().noneMatch(p -> Objects.equals(p.getPropuesta_id(), generatedId)));

    }
    // ===================== TESTS DE CASCADA EN PROPUESTAS =====================
    @Test
    @Order(8)
    @DisplayName("Cascada: Al inserta propuesta con detalles se insertan en PA_DETALLES_PROPUESTA")
    void testCascada_InsertarPropuesta_Con_Detalles() {
        // Insertamos propuesta base
        generatedId = dao.insertar(testPropuesta);
        assertTrue(generatedId > 0);
        testPropuesta.setPropuesta_id(generatedId);

        // Creamos 2 detalles (IDs nulos)
        DetallesPropuestaDTO dp1 = new DetallesPropuestaDTO();
        dp1.setDetalle_propuesta_id(null);
        dp1.setMonto_pago(new java.math.BigDecimal("50.00"));
        dp1.setForma_pago('T');
        dp1.setPropuesta_pago(testPropuesta);
        dp1.setFactura(testFacturaRef);
        dp1.setCuenta_propia(testCuentaPropia);
        dp1.setCuenta_acreedor(testCuentaAcreedor);

        DetallesPropuestaDTO dp2 = new DetallesPropuestaDTO();
        dp2.setDetalle_propuesta_id(null);
        dp2.setMonto_pago(new java.math.BigDecimal("75.00"));
        dp2.setForma_pago('T');
        dp2.setPropuesta_pago(testPropuesta);
        dp2.setFactura(testFacturaRef2);
        dp2.setCuenta_propia(testCuentaPropia);
        dp2.setCuenta_acreedor(testCuentaAcreedor);

        ArrayList<DetallesPropuestaDTO> detalles = new ArrayList<>();
        detalles.add(dp1); detalles.add(dp2);
        testPropuesta.setDetalles_propuesta(detalles);

        // Inserción de detalles a través de modificar (id null => insertar)
        assertTrue(dao.modificar(testPropuesta) >= 0);

        PropuestasPagoDTO withDet = dao.obtenerPorId(generatedId);
        assertNotNull(withDet);
        assertNotNull(withDet.getDetalles_propuesta());
        assertEquals(2, withDet.getDetalles_propuesta().size());
    }

    @Test
    @Order(8)
    @DisplayName("Cascada: modificar con detalles de propuesta inserta en PA_DETALLES_PROPUESTA")
    void testCascada_InsertarDetalles_Propuesta() {

        // Creamos 2 detalles (IDs nulos)
        DetallesPropuestaDTO dp1 = new DetallesPropuestaDTO();
        dp1.setDetalle_propuesta_id(null);
        dp1.setMonto_pago(new java.math.BigDecimal("50.00"));
        dp1.setForma_pago('T');
        dp1.setPropuesta_pago(testPropuesta);
        dp1.setFactura(testFacturaRef);
        dp1.setCuenta_propia(testCuentaPropia);
        dp1.setCuenta_acreedor(testCuentaAcreedor);

        DetallesPropuestaDTO dp2 = new DetallesPropuestaDTO();
        dp2.setDetalle_propuesta_id(null);
        dp2.setMonto_pago(new java.math.BigDecimal("75.00"));
        dp2.setForma_pago('T');
        dp2.setPropuesta_pago(testPropuesta);
        dp2.setFactura(testFacturaRef2);
        dp2.setCuenta_propia(testCuentaPropia);
        dp2.setCuenta_acreedor(testCuentaAcreedor);

        ArrayList<DetallesPropuestaDTO> detalles = new ArrayList<>();
        detalles.add(dp1); detalles.add(dp2);
        testPropuesta.setDetalles_propuesta(detalles);


        // Insertamos propuesta base con detalles
        generatedId = dao.insertar(testPropuesta);
        assertTrue(generatedId > 0);
        testPropuesta.setPropuesta_id(generatedId);

        // Verificamos que existen los detalles y coinciden con los insertados
        PropuestasPagoDTO withDet = dao.obtenerPorId(generatedId);
        assertNotNull(withDet);
        assertNotNull(withDet.getDetalles_propuesta());
        assertEquals(2, withDet.getDetalles_propuesta().size());
        assertEquals(dp1.getMonto_pago(), withDet.getDetalles_propuesta().get(0).getMonto_pago());
    }

    @Test
    @Order(9)
    @DisplayName("Cascada: en modificar, detalles se actualizan, los con fecha_eliminacion se eliminan lógicamente y los nuevos se insertan")
    void testCascada_Modificar_Propuesta() {
        // Base: insertar propuesta y 2 detalles
        generatedId = dao.insertar(testPropuesta);
        assertTrue(generatedId > 0);
        testPropuesta.setPropuesta_id(generatedId);

        DetallesPropuestaDTO dp1 = new DetallesPropuestaDTO();
        dp1.setMonto_pago(new java.math.BigDecimal("10.00"));
        dp1.setForma_pago('T');
        dp1.setPropuesta_pago(testPropuesta);
        dp1.setFactura(testFacturaRef);
        dp1.setCuenta_propia(testCuentaPropia);
        dp1.setCuenta_acreedor(testCuentaAcreedor);

        DetallesPropuestaDTO dp2 = new DetallesPropuestaDTO();
        dp2.setMonto_pago(new java.math.BigDecimal("20.00"));
        dp2.setForma_pago('T');
        dp2.setPropuesta_pago(testPropuesta);
        dp2.setFactura(testFacturaRef2);
        dp2.setCuenta_propia(testCuentaPropia);
        dp2.setCuenta_acreedor(testCuentaAcreedor);

        ArrayList<DetallesPropuestaDTO> detalles = new ArrayList<>();
        detalles.add(dp1); detalles.add(dp2);
        testPropuesta.setDetalles_propuesta(detalles);
        assertTrue(dao.modificar(testPropuesta) >= 0);

        // Obtener IDs generados
        PropuestasPagoDTO obt = dao.obtenerPorId(generatedId);
        assertNotNull(obt);
        assertEquals(2, obt.getDetalles_propuesta().size());
        DetallesPropuestaDTO ed1 = obt.getDetalles_propuesta().get(0);
        DetallesPropuestaDTO ed2 = obt.getDetalles_propuesta().get(1);

        // Preparar cambios
        ed1.setMonto_pago(new java.math.BigDecimal("25.00"));

        ed2.setFecha_eliminacion(new Date());
        ed2.setUsuario_eliminacion(testUsuarioEliminacion);

        DetallesPropuestaDTO dp3 = new DetallesPropuestaDTO();
        dp3.setDetalle_propuesta_id(null);
        dp3.setMonto_pago(new java.math.BigDecimal("15.00"));
        dp3.setForma_pago('T');
        dp3.setPropuesta_pago(testPropuesta);
        dp3.setFactura(testFacturaRef3);
        dp3.setCuenta_propia(testCuentaPropia);
        dp3.setCuenta_acreedor(testCuentaAcreedor);

        ArrayList<DetallesPropuestaDTO> nuevos = new ArrayList<>();
        nuevos.add(ed1); nuevos.add(ed2); nuevos.add(dp3);
        testPropuesta.setDetalles_propuesta(nuevos);
        assertTrue(dao.modificar(testPropuesta) >= 0);

        PropuestasPagoDTO ver = dao.obtenerPorId(generatedId);
        assertNotNull(ver);
        assertEquals(2, ver.getDetalles_propuesta().size());
        assertTrue(ver.getDetalles_propuesta().stream().anyMatch(d -> d.getMonto_pago()!=null && d.getMonto_pago().compareTo(new java.math.BigDecimal("25.00"))==0));
        assertTrue(ver.getDetalles_propuesta().stream().anyMatch(d -> d.getMonto_pago()!=null && d.getMonto_pago().compareTo(new java.math.BigDecimal("15.00"))==0));
    }

    @Test
    @Order(10)
    @DisplayName("Cascada: al eliminar físicamente la propuesta, se eliminan físicamente sus detalles")
    void testCascada_EliminarFisico_Propuesta() {
        // Insertamos propuesta y detalles
        generatedId = dao.insertar(testPropuesta);
        assertTrue(generatedId > 0);
        testPropuesta.setPropuesta_id(generatedId);

        DetallesPropuestaDTO dp1 = new DetallesPropuestaDTO();
        dp1.setMonto_pago(new java.math.BigDecimal("11.00"));
        dp1.setForma_pago('T');
        dp1.setPropuesta_pago(testPropuesta);
        dp1.setFactura(testFacturaRef);
        dp1.setCuenta_propia(testCuentaPropia);
        dp1.setCuenta_acreedor(testCuentaAcreedor);

        ArrayList<DetallesPropuestaDTO> detalles = new ArrayList<>();
        detalles.add(dp1);
        testPropuesta.setDetalles_propuesta(detalles);
        assertTrue(dao.modificar(testPropuesta) >= 0);

        // Poblar lista con los detalles actuales (para cascada de eliminación)
        PropuestasPagoDTO withDet = dao.obtenerPorId(generatedId);
        testPropuesta.setDetalles_propuesta(withDet.getDetalles_propuesta());

        assertTrue(dao.eliminar(testPropuesta) > 0);
        assertNull(dao.obtenerPorId(generatedId));
        generatedId = 0;
    }
}
