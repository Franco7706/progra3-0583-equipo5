package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.daoImpl.*;
import pe.edu.pucp.softpac.dao.UsuariosDAO;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;
import pe.edu.pucp.softpac.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static pe.edu.pucp.softpac.dao.CuentasAcreedorDAOTest.entidadesDAO;
import static pe.edu.pucp.softpac.dao.CuentasAcreedorDAOTest.monedasDAO;
import static pe.edu.pucp.softpac.dao.CuentasAcreedorDAOTest.paisesDAO;
import static pe.edu.pucp.softpac.dao.CuentasAcreedorDAOTest.testEntidad;
import static pe.edu.pucp.softpac.dao.CuentasAcreedorDAOTest.testMoneda;
import static pe.edu.pucp.softpac.dao.CuentasAcreedorDAOTest.testPais;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CuentasPropiasDAOTest {

    protected static CuentasPropiasDAO dao;
    protected static CuentasPropiasDTO testCuenta;
    private static Integer generatedId = 0;

    // Dependencias (igual patrón que CuentasAcreedorDAOTest pero sin Acreedor)
    protected static PaisesDAO paisesDAO;
    protected static PaisesDTO testPais;

    protected static MonedasDAO monedasDAO;
    protected static MonedasDTO testMoneda;

    protected static EntidadesBancariasDAO entidadesDAO;
    protected static EntidadesBancariasDTO testEntidad;

    protected static UsuariosDAO usuariosDAO;
    protected static UsuariosDTO testUsuarioEliminacion;

    @BeforeAll
    static void init() {
        // País
        paisesDAO = new PaisesDAOImpl();
        testPais = new PaisesDTO(0, "PaisCuentaAcreedor", "PC", "+51");
        testPais.setPais_id(paisesDAO.insertar(testPais));

        // Moneda
        monedasDAO = new MonedasDAOImpl();
        testMoneda = new MonedasDTO(0, "MonedaCA", "MC", "C$");
        testMoneda.setMoneda_id(monedasDAO.insertar(testMoneda));

        // Entidad bancaria
        entidadesDAO = new EntidadesBancariasDAOImpl();
        testEntidad = new EntidadesBancariasDTO();
        testEntidad.setNombre("Banco CA");
        testEntidad.setFormato_aceptado("FMT");
        testEntidad.setCodigo_swift("SWCA");
        testEntidad.setPais(testPais);
        testEntidad.setEntidad_bancaria_id(entidadesDAO.insertar(testEntidad));

        // Usuario genérico para eliminación lógica
        usuariosDAO = new UsuariosDAOImpl();
        testUsuarioEliminacion = new UsuariosDTO();
        testUsuarioEliminacion.setUsuario_id(1003);
        testUsuarioEliminacion.setCorreo_electronico("eliminacion@softpac.test");
        testUsuarioEliminacion.setNombre_de_usuario("Usuario de eliminación");
        testUsuarioEliminacion.setActivo(true);
        testUsuarioEliminacion.setSuperusuario(true);
        testUsuarioEliminacion.setPassword_hash("elim123");
        usuariosDAO.insertar(testUsuarioEliminacion);
    }

    @BeforeEach
    void setUp() {
        dao = new CuentasPropiasDAOImpl();

        testCuenta = new CuentasPropiasDTO();
        testCuenta.setTipo_cuenta("Corriente");
        testCuenta.setNumero_cuenta("CP-" + System.nanoTime());
        testCuenta.setCci("CCI-CP-" + System.nanoTime());
        testCuenta.setActiva(true);
        testCuenta.setEntidad_bancaria(testEntidad);
        testCuenta.setMoneda(testMoneda);

        generatedId = 0; // base para tearDown
    }

    @AfterEach
    void tearDown() {
        // No se elimina físicamente: solo marcar inactiva si se creó
        if (generatedId != null && generatedId > 0) {
            try {
                testCuenta.setCuenta_bancaria_id(generatedId);
                dao.eliminar(testCuenta);
            } catch (Exception ignored) {
            }
            generatedId = 0;
        }
    }

    @AfterAll
    static void cleanup() {
        // Intentar borrar dependencias (si no hay referencias). Si hay FK, se ignora.
        try { entidadesDAO.eliminar(testEntidad); } catch (Exception ignored) {}
        try { monedasDAO.eliminar(testMoneda); } catch (Exception ignored) {}
        try { paisesDAO.eliminar(testPais); } catch (Exception ignored) {}
        try { usuariosDAO.eliminar(testUsuarioEliminacion); } catch (Exception ignored) {}
    }

    @Test
    @Order(1)
    @DisplayName("Debe insertar una cuenta propia y devolver su ID")
    void testInsertar() {
        Integer nuevoId = dao.insertar(testCuenta);
        assertNotNull(nuevoId);
        assertTrue(nuevoId > 0);
        generatedId = nuevoId;
    }

    @Test
    @Order(2)
    @DisplayName("Debe obtener una cuenta propia por su ID")
    void testObtenerPorId() {
        generatedId = dao.insertar(testCuenta);

        CuentasPropiasDTO cuenta = dao.obtenerPorId(generatedId);
        assertNotNull(cuenta, "No se encontró la cuenta recién insertada");
        assertEquals(generatedId, cuenta.getCuenta_bancaria_id());
        assertEquals("Corriente", cuenta.getTipo_cuenta());

        assertNull(dao.obtenerPorId(-1));
    }

    @Test
    @Order(3)
    @DisplayName("Debe modificar una cuenta propia existente (activar/inactivar + cambio de tipo)")
    void testModificar() {
        generatedId = dao.insertar(testCuenta);
        testCuenta.setCuenta_bancaria_id(generatedId);
        testCuenta.setTipo_cuenta("Ahorros");
        testCuenta.setActiva(false); // “eliminar lógico”

        int result = dao.modificar(testCuenta);
        assertTrue(result > 0, "modificar devolvió 0");

        CuentasPropiasDTO modificado = dao.obtenerPorId(generatedId);
        assertEquals("Ahorros", modificado.getTipo_cuenta());
    }

    @Test
    @Order(4)
    @DisplayName("Debe listar todas las cuentas propias")
    void testListarTodos() {
        generatedId = dao.insertar(testCuenta);

        ArrayList<CuentasPropiasDTO> lista = (ArrayList<CuentasPropiasDTO>) dao.listarTodos();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(
                lista.stream().anyMatch(c -> c.getCuenta_bancaria_id() != null && c.getCuenta_bancaria_id().equals(generatedId)),
                "listarTodos no contiene la cuenta insertada"
        );
    }

    @Test
    @Order(5)
    @DisplayName("Debe eliminar lógicamente una cuenta propia")
    void testEliminarLogico() {
        generatedId = dao.insertar(testCuenta);
        testCuenta.setCuenta_bancaria_id(generatedId);
        testCuenta.setFecha_eliminacion(new Date());
        testCuenta.setUsuario_eliminacion(testUsuarioEliminacion);

        Integer res = dao.eliminarLogico(testCuenta);
        assertTrue(res > 0);

        CuentasPropiasDTO cuenta = dao.obtenerPorId(generatedId);
        assertNotNull(cuenta);
        // Debe excluirse de listarTodos
        ArrayList<CuentasPropiasDTO> lista = (ArrayList<CuentasPropiasDTO>) dao.listarTodos();
        assertTrue(lista.stream().noneMatch(c -> Objects.equals(c.getCuenta_bancaria_id(), generatedId)));

    }

}
