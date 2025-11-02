package pe.edu.pucp.softpac.dao;

import java.math.BigDecimal;
import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.daoImpl.MonedasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;
import pe.edu.pucp.softpac.daoImpl.AcreedoresDAOImpl;
import pe.edu.pucp.softpac.daoImpl.FacturasDAOImpl;
import pe.edu.pucp.softpac.dao.UsuariosDAO;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;
import pe.edu.pucp.softpac.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class FacturasDAOTest {

    protected static FacturasDAO dao;
    protected static FacturasDTO testFactura;

    private static Integer generateId;

    protected static MonedasDTO testMoneda;
    protected static MonedasDAO monedasDAO;
    protected static AcreedoresDTO testAcreedor;
    protected static AcreedoresDAO acreedoresDAO;
    protected static PaisesDTO testPais;
    protected static PaisesDAO paisesDAO;

    protected static UsuariosDAO usuariosDAO;
    protected static UsuariosDTO testUsuarioEliminacion;

    @BeforeAll
    public static void init() {
        dao = new FacturasDAOImpl();
        monedasDAO = new MonedasDAOImpl();
        acreedoresDAO = new AcreedoresDAOImpl();
        paisesDAO = new PaisesDAOImpl();

        // País
        testPais = new PaisesDTO();
        testPais.setNombre("Perú Test");
        testPais.setCodigo_iso("PE");
        testPais.setCodigo_telefonico("51");
        testPais.setPais_id(paisesDAO.insertar(testPais));

        // Moneda
        testMoneda = new MonedasDTO();
        testMoneda.setNombre("Sol Peruano Test");
        testMoneda.setCodigo_iso("PEN");
        testMoneda.setSimbolo("S/");
        testMoneda.setMoneda_id(monedasDAO.insertar(testMoneda));

        // Acreedor
        testAcreedor = new AcreedoresDTO();
        testAcreedor.setRazon_social("Proveedor Demo SAC Test");
        testAcreedor.setRuc("20123456789");
        testAcreedor.setDireccion_fiscal("Av. Siempre Viva 123");
        testAcreedor.setCondicion("HABIDO");
        testAcreedor.setPlazo_de_pago(30);
        testAcreedor.setActivo(true);
        testAcreedor.setPais(testPais);
        testAcreedor.setAcreedor_id(acreedoresDAO.insertar(testAcreedor));

        // Usuario genérico para eliminación lógica
        usuariosDAO = new UsuariosDAOImpl();
        testUsuarioEliminacion = new UsuariosDTO();
        testUsuarioEliminacion.setUsuario_id(1004);
        testUsuarioEliminacion.setCorreo_electronico("eliminacion@softpac.test");
        testUsuarioEliminacion.setNombre_de_usuario("Usuario de eliminación");
        testUsuarioEliminacion.setActivo(true);
        testUsuarioEliminacion.setSuperusuario(true);
        testUsuarioEliminacion.setPassword_hash("elim123");
        usuariosDAO.insertar(testUsuarioEliminacion);

        generateId = 0;
    }

    // ====== Limpieza de dependencias (último test) ======
    @AfterAll
    static void deleteDependencies() {
        try {
            if (testAcreedor != null && testAcreedor.getAcreedor_id() != null) {
                acreedoresDAO.eliminar(testAcreedor);
                testAcreedor = null;
            }
        } catch (Exception ignored) {
        }

        try {
            if (testMoneda != null && testMoneda.getMoneda_id() != null) {
                monedasDAO.eliminar(testMoneda);
                testMoneda = null;
            }
        } catch (Exception ignored) {
        }

        try {
            if (testPais != null && testPais.getPais_id() != null) {
                paisesDAO.eliminar(testPais);
                testPais = null;
            }
        } catch (Exception ignored) {
        }
        try { if (testUsuarioEliminacion != null) usuariosDAO.eliminar(testUsuarioEliminacion); } catch (Exception ignored) {}
    }

    // ===== Objeto base por test =====
    @BeforeEach
    void setUp() {
        // Montos base
        BigDecimal montoBruto = new BigDecimal("1000.00");
        BigDecimal tasaIgv = new BigDecimal("18.00");
        BigDecimal montoIgv = montoBruto.multiply(new BigDecimal("0.18")).setScale(2);
        BigDecimal otrosTributos = new BigDecimal("5.00");
        BigDecimal montoTotal = montoBruto.add(montoIgv).add(otrosTributos).setScale(2);

        testFactura = new FacturasDTO();
        testFactura.setFactura_id(0);

        testFactura.setNumero_factura("F001-TEST-" + System.nanoTime());
        testFactura.setFecha_emision(new Date(2025, 1, 10, 9, 0, 0));
        testFactura.setFecha_recepcion(new Date(2025, 1, 11, 10, 30, 0));
        testFactura.setFecha_limite_pago(new Date(2025, 2, 10, 23, 59, 0));
        testFactura.setEstado("PENDIENTE");
        testFactura.setMonto_total(montoTotal);
        testFactura.setMonto_igv(montoIgv);
        testFactura.setMonto_restante(montoTotal);
        testFactura.setRegimen_fiscal("MYPE_TRIBUTARIO");
        testFactura.setTasa_iva(tasaIgv);
        testFactura.setOtros_tributos(otrosTributos);

        testFactura.setAcreedor(testAcreedor);
        testFactura.setMoneda(testMoneda);
    }

    // ===== Limpieza por test =====
    @AfterEach
    void tearDown() {
        // Patrón exacto de tu captura
        if (generateId != null && generateId > 0) {
            try {
                testFactura.setFactura_id(generateId);
                dao.eliminar(testFactura);
            } catch (Exception e) {
                // Ignorar excepciones en limpieza
            }
            generateId = 0;
        }
    }

    // ====== TESTS ======
    @Test
    @Order(1)
    void testInsertarYObtenerPorId() {
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);

        FacturasDTO fx = dao.obtenerPorId(generateId);
        assertNotNull(fx);

        assertAll(
                () -> assertEquals(testFactura.getNumero_factura(), fx.getNumero_factura()),
                () -> assertEquals(0, testFactura.getMonto_total().compareTo(fx.getMonto_total())),
                () -> assertEquals(testAcreedor.getAcreedor_id(), fx.getAcreedor().getAcreedor_id()),
                () -> assertEquals(testMoneda.getMoneda_id(), fx.getMoneda().getMoneda_id()),
                () -> assertEquals(testFactura.getEstado(), fx.getEstado())
        );
    }

    @Test
    @Order(2)
    void testListarTodos() {
        // inserta una para asegurar presencia
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);

        ArrayList<FacturasDTO> all = (ArrayList<FacturasDTO>) dao.listarTodos();
        assertNotNull(all);
        assertTrue(
                all.stream().anyMatch(f -> f.getFactura_id() != null && f.getFactura_id().equals(generateId)),
                "listarTodos no contiene la factura insertada"
        );
    }

    @Test
    @Order(3)
    void testModificar() {
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);

        // Cambios: aumentar otros_tributos y recalcular totales
        BigDecimal nuevosOtros = testFactura.getOtros_tributos().add(new BigDecimal("10.00"));
        BigDecimal nuevoTotal = testFactura.getMonto_total().add(new BigDecimal("10.00"));
        testFactura.setFactura_id(generateId);
        testFactura.setOtros_tributos(nuevosOtros);
        testFactura.setMonto_total(nuevoTotal);
        testFactura.setMonto_restante(nuevoTotal); // si aún no hay pagos
        testFactura.setEstado("REPROGRAMADA");

        assertTrue(dao.modificar(testFactura)>0);

        FacturasDTO fy = dao.obtenerPorId(generateId);
        assertNotNull(fy);
        assertAll(
                () -> assertEquals(0, nuevosOtros.compareTo(fy.getOtros_tributos())),
                () -> assertEquals(0, nuevoTotal.compareTo(fy.getMonto_total())),
                () -> assertEquals("REPROGRAMADA", fy.getEstado())
        );
    }

    @Test
    @Order(4)
    void testEliminar() {
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);

        testFactura.setFactura_id(generateId);
        assertTrue(dao.eliminar(testFactura)>0);
        assertNull(dao.obtenerPorId(generateId));

        // Evitar eliminar otra vez en tearDown
        generateId = 0;
    }

    @Test
    @Order(5)
    @DisplayName("Contrato: debe eliminar lógicamente una factura")
    void testEliminarLogico() {
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);

        testFactura.setFactura_id(generateId);
        testFactura.setFecha_eliminacion(new Date());
        testFactura.setUsuario_eliminacion(testUsuarioEliminacion);

        Integer res = dao.eliminarLogico(testFactura);
        assertTrue(res > 0);

        FacturasDTO fz = dao.obtenerPorId(generateId);
        assertNotNull(fz);
        // Debe excluirse de listarTodos
        ArrayList<FacturasDTO> lista = (ArrayList<FacturasDTO>) dao.listarTodos();
        assertTrue(lista.stream().noneMatch(f -> Objects.equals(f.getFactura_id(), generateId)));

    }
    // ===================== TESTS DE CASCADA CON DETALLES =====================
    @Test
    @Order(6)
    @DisplayName("Cascada: al insertar con detalles se insertan en PA_DETALLES_FACTURA")
    void testCascada_InsertarConDetalles() {

        // 1) Preparamos 2 detalles nuevos (IDs nulos) y los asociamos a la factura
        DetallesFacturaDTO d1 = new DetallesFacturaDTO();
        d1.setDetalle_factura_id(null);
        d1.setSubtotal(new java.math.BigDecimal("500.00"));
        d1.setDescripcion("Servicio 1");
        d1.setFactura(testFactura);

        DetallesFacturaDTO d2 = new DetallesFacturaDTO();
        d2.setDetalle_factura_id(null);
        d2.setSubtotal(new java.math.BigDecimal("200.00"));
        d2.setDescripcion("Servicio 2");
        d2.setFactura(testFactura);
        ArrayList<DetallesFacturaDTO> detalles = new ArrayList<>();
        detalles.add(d1);
        detalles.add(d2);
        testFactura.setDetalles_Factura(detalles);

        // 2) Insertamos la factura para insertarla con sus detalles
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);
        testFactura.setFactura_id(generateId);

        // 3) Verificamos que obtenerPorId regrese los detalles (no eliminados lógicamente)
        FacturasDTO fx = dao.obtenerPorId(generateId);
        assertNotNull(fx);
        assertNotNull(fx.getDetalles_Factura());
        assertEquals(2, fx.getDetalles_Factura().size(), "Deben insertarse 2 detalles");
    }

    @Test
    @Order(7)
    @DisplayName("Cascada: modificar con detalles se insertan en PA_DETALLES_FACTURA")
    void testCascada_InsertarDetalles() {
        // 1) Insertamos la factura sin detalles inicialmente
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);
        testFactura.setFactura_id(generateId);

        // 2) Preparamos 2 detalles nuevos (IDs nulos) y los asociamos a la factura
        DetallesFacturaDTO d1 = new DetallesFacturaDTO();
        d1.setDetalle_factura_id(null);
        d1.setSubtotal(new java.math.BigDecimal("500.00"));
        d1.setDescripcion("Servicio 1");
        d1.setFactura(testFactura);

        DetallesFacturaDTO d2 = new DetallesFacturaDTO();
        d2.setDetalle_factura_id(null);
        d2.setSubtotal(new java.math.BigDecimal("200.00"));
        d2.setDescripcion("Servicio 2");
        d2.setFactura(testFactura);

        ArrayList<DetallesFacturaDTO> detalles = new ArrayList<>();
        detalles.add(d1);
        detalles.add(d2);
        testFactura.setDetalles_Factura(detalles);

        // 3) Disparamos la cascada de inserción de detalles mediante modificar (id null => insertar)
        assertTrue(dao.modificar(testFactura) >= 0);

        // 4) Verificamos que obtenerPorId regrese los detalles (no eliminados lógicamente)
        FacturasDTO fx = dao.obtenerPorId(generateId);
        assertNotNull(fx);
        assertNotNull(fx.getDetalles_Factura());
        assertEquals(2, fx.getDetalles_Factura().size(), "Deben insertarse 2 detalles");
    }

    @Test
    @Order(8)
    @DisplayName("Cascada: al modificar, detalles existentes se actualizan y con fecha_eliminacion se eliminan lógicamente; y los nuevos (id null) se insertan")
    void testCascada_Modificar_Actualizar_Insertar_EliminarLogico() {
        // Insertamos factura base
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);
        testFactura.setFactura_id(generateId);

        // Insertamos inicialmente 2 detalles (vía modificar)
        DetallesFacturaDTO d1 = new DetallesFacturaDTO();
        d1.setSubtotal(new java.math.BigDecimal("120.00"));
        d1.setDescripcion("Item A");
        d1.setFactura(testFactura);

        DetallesFacturaDTO d2 = new DetallesFacturaDTO();
        d2.setSubtotal(new java.math.BigDecimal("80.00"));
        d2.setDescripcion("Item B");
        d2.setFactura(testFactura);

        ArrayList<DetallesFacturaDTO> detalles = new ArrayList<>();
        detalles.add(d1);
        detalles.add(d2);
        testFactura.setDetalles_Factura(detalles);
        assertTrue(dao.modificar(testFactura) >= 0);

        // Obtenemos para conocer los IDs generados de los detalles
        FacturasDTO withDetails = dao.obtenerPorId(generateId);
        assertNotNull(withDetails);
        assertNotNull(withDetails.getDetalles_Factura());
        assertEquals(2, withDetails.getDetalles_Factura().size());

        DetallesFacturaDTO ed1 = withDetails.getDetalles_Factura().get(0);
        DetallesFacturaDTO ed2 = withDetails.getDetalles_Factura().get(1);

        // Preparamos modificación: ed1 se actualiza, ed2 se marca para eliminación lógica
        ed1.setDescripcion("Item A Mod");
        ed1.setSubtotal(new java.math.BigDecimal("130.00"));
        ed1.setFactura(testFactura);

        ed2.setFactura(testFactura);
        ed2.setFecha_eliminacion(new Date());
        ed2.setUsuario_eliminacion(testUsuarioEliminacion);

        // Además, añadimos un nuevo detalle (id null) para que inserte
        DetallesFacturaDTO d3 = new DetallesFacturaDTO();
        d3.setDetalle_factura_id(null);
        d3.setSubtotal(new java.math.BigDecimal("50.00"));
        d3.setDescripcion("Item C Nuevo");
        d3.setFactura(testFactura);

        ArrayList<DetallesFacturaDTO> nuevos = new ArrayList<>();
        nuevos.add(ed1);
        nuevos.add(ed2);
        nuevos.add(d3);
        testFactura.setDetalles_Factura(nuevos);

        assertTrue(dao.modificar(testFactura) >= 0);

        // Verificamos: obtenerPorId debe traer sólo los no eliminados (ed1 modificado y d3 nuevo)
        FacturasDTO ver = dao.obtenerPorId(generateId);
        assertNotNull(ver);
        assertNotNull(ver.getDetalles_Factura());
        assertEquals(2, ver.getDetalles_Factura().size(), "Debe haber 2 detalles activos tras la modificación");
        assertTrue(ver.getDetalles_Factura().stream().anyMatch(df -> "Item A Mod".equals(df.getDescripcion())));
        assertTrue(ver.getDetalles_Factura().stream().anyMatch(df -> "Item C Nuevo".equals(df.getDescripcion())));
    }

    @Test
    @Order(9)
    @DisplayName("Cascada: al eliminar físicamente la factura, se eliminan físicamente sus detalles")
    void testCascada_EliminarFisico() {
        // Creamos factura con 2 detalles
        generateId = dao.insertar(testFactura);
        assertTrue(generateId > 0);
        testFactura.setFactura_id(generateId);

        DetallesFacturaDTO d1 = new DetallesFacturaDTO();
        d1.setSubtotal(new java.math.BigDecimal("10.00"));
        d1.setDescripcion("Del A");
        d1.setFactura(testFactura);
        DetallesFacturaDTO d2 = new DetallesFacturaDTO();
        d2.setSubtotal(new java.math.BigDecimal("20.00"));
        d2.setDescripcion("Del B");
        d2.setFactura(testFactura);
        ArrayList<DetallesFacturaDTO> detalles = new ArrayList<>();
        detalles.add(d1); detalles.add(d2);
        testFactura.setDetalles_Factura(detalles);
        assertTrue(dao.modificar(testFactura) >= 0);

        // Poblamos la lista con los detalles actuales (necesario para cascada de eliminación)
        FacturasDTO withDet = dao.obtenerPorId(generateId);
        assertNotNull(withDet);
        testFactura.setDetalles_Factura(withDet.getDetalles_Factura());

        // Eliminación física en cascada
        assertTrue(dao.eliminar(testFactura) > 0);
        assertNull(dao.obtenerPorId(generateId));

        // Evitar doble delete
        generateId = 0;
    }

}
