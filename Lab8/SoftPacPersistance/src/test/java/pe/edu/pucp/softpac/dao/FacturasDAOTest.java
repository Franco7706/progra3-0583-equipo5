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
        testFactura.setFecha_emision(LocalDateTime.of(2025, 1, 10, 9, 0, 0));
        testFactura.setFecha_recepcion(LocalDateTime.of(2025, 1, 11, 10, 30, 0));
        testFactura.setFecha_limite_pago(LocalDateTime.of(2025, 2, 10, 23, 59, 0));
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

        ArrayList<FacturasDTO> all = dao.listarTodos();
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
        testFactura.setFecha_eliminacion(LocalDateTime.now());
        testFactura.setUsuario_eliminacion(testUsuarioEliminacion);

        Integer res = dao.eliminarLogico(testFactura);
        assertTrue(res > 0);

        FacturasDTO fz = dao.obtenerPorId(generateId);
        assertNotNull(fz);
        // Debe excluirse de listarTodos
        ArrayList<FacturasDTO> lista = dao.listarTodos();
        assertTrue(lista.stream().noneMatch(f -> Objects.equals(f.getFactura_id(), generateId)));

    }

}
