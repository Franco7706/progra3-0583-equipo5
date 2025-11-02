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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CuentasAcreedorDAOTest {

    protected static CuentasAcreedorDAO dao;
    protected static CuentasAcreedorDTO testCuenta;
    private static Integer generatedId;

    // Dependencias
    protected static PaisesDAO paisesDAO;
    protected static PaisesDTO testPais;

    protected static MonedasDAO monedasDAO;
    protected static MonedasDTO testMoneda;

    protected static EntidadesBancariasDAO entidadesDAO;
    protected static EntidadesBancariasDTO testEntidad;

    protected static AcreedoresDAO acreedoresDAO;
    protected static AcreedoresDTO testAcreedor;

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

        // Acreedor
        acreedoresDAO = new AcreedoresDAOImpl();
        testAcreedor = new AcreedoresDTO();
        testAcreedor.setRazon_social("Acreedor CA");
        testAcreedor.setRuc("12345678901");
        testAcreedor.setDireccion_fiscal("Calle Falsa 123");
        testAcreedor.setCondicion("Activo");
        testAcreedor.setPlazo_de_pago(30);
        testAcreedor.setActivo(true);
        testAcreedor.setPais(testPais);
        testAcreedor.setAcreedor_id(acreedoresDAO.insertar(testAcreedor));

        // Usuario genérico para eliminación lógica
        usuariosDAO = new UsuariosDAOImpl();
        testUsuarioEliminacion = new UsuariosDTO();
        testUsuarioEliminacion.setUsuario_id(999);
        testUsuarioEliminacion.setCorreo_electronico("eliminacion@softpac.test");
        testUsuarioEliminacion.setNombre_de_usuario("Usuario de eliminación");
        testUsuarioEliminacion.setActivo(true);
        testUsuarioEliminacion.setSuperusuario(true);
        testUsuarioEliminacion.setPassword_hash("elim123");
        usuariosDAO.insertar(testUsuarioEliminacion);
    }

    @AfterAll
    static void cleanup() {
        try { acreedoresDAO.eliminar(testAcreedor); } catch (Exception ignored) {}
        try { entidadesDAO.eliminar(testEntidad); } catch (Exception ignored) {}
        try { monedasDAO.eliminar(testMoneda); } catch (Exception ignored) {}
        try { paisesDAO.eliminar(testPais); } catch (Exception ignored) {}
        try { usuariosDAO.eliminar(testUsuarioEliminacion); } catch (Exception ignored) {}
    }

    @BeforeEach
    void setUp() {
        dao = new CuentasAcreedorDAOImpl();

        testCuenta = new CuentasAcreedorDTO();
        testCuenta.setTipo_cuenta("Corriente");
        testCuenta.setNumero_cuenta("1234567890");
        testCuenta.setCci("CCI123");
        testCuenta.setActiva(true);
        testCuenta.setAcreedor(testAcreedor);
        testCuenta.setEntidad_bancaria(testEntidad);
        testCuenta.setMoneda(testMoneda);
    }

    @AfterEach
    void tearDown() {
        if (generatedId != null && generatedId > 0) {
            try {
                testCuenta.setCuenta_bancaria_id(generatedId);
                dao.eliminar(testCuenta);
            } catch (Exception ignored) {}
            generatedId = 0;
        }
    }

    @Test
    @Order(1)
    @DisplayName("Debe insertar una cuenta de acreedor y devolver su ID")
    void testInsertar() {
        Integer nuevoId = dao.insertar(testCuenta);
        assertNotNull(nuevoId);
        assertTrue(nuevoId > 0);
        generatedId = nuevoId;
    }

    @Test
    @Order(2)
    @DisplayName("Debe obtener una cuenta de acreedor por su ID")
    void testObtenerPorId() {
        generatedId = dao.insertar(testCuenta);

        CuentasAcreedorDTO cuenta = dao.obtenerPorId(generatedId);
        assertNotNull(cuenta);
        assertEquals(generatedId, cuenta.getCuenta_bancaria_id());
        assertEquals("Corriente", cuenta.getTipo_cuenta());

        assertNull(dao.obtenerPorId(-1));
    }

    @Test
    @Order(3)
    @DisplayName("Debe modificar una cuenta de acreedor existente")
    void testModificar() {
        generatedId = dao.insertar(testCuenta);
        testCuenta.setCuenta_bancaria_id(generatedId);
        testCuenta.setTipo_cuenta("Ahorros");

        int result = dao.modificar(testCuenta);
        assertTrue(result > 0);

        CuentasAcreedorDTO modificado = dao.obtenerPorId(generatedId);
        assertEquals("Ahorros", modificado.getTipo_cuenta());
    }

    @Test
    @Order(4)
    @DisplayName("Debe listar todas las cuentas de acreedor")
    void testListarTodos() {
        generatedId = dao.insertar(testCuenta);

        ArrayList<CuentasAcreedorDTO> lista =(ArrayList<CuentasAcreedorDTO>) dao.listarTodos();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.stream().anyMatch(c -> Objects.equals(c.getCuenta_bancaria_id(), generatedId)));
    }

    @Test
    @Order(5)
    @DisplayName("Debe eliminar una cuenta de acreedor")
    void testEliminar() {
        generatedId = dao.insertar(testCuenta);
        testCuenta.setCuenta_bancaria_id(generatedId);

        int result = dao.eliminar(testCuenta);
        assertTrue(result > 0);

        assertNull(dao.obtenerPorId(generatedId));
        generatedId = 0; // evita doble eliminación
    }

    @Test
    @Order(6)
    @DisplayName("Debe eliminar lógicamente una cuenta de acreedor")
    void testEliminarLogico() {
        generatedId = dao.insertar(testCuenta);
        testCuenta.setCuenta_bancaria_id(generatedId);
        testCuenta.setFecha_eliminacion(new Date());
        testCuenta.setUsuario_eliminacion(testUsuarioEliminacion);

        Integer res = dao.eliminarLogico(testCuenta);
        assertTrue(res > 0);

        CuentasAcreedorDTO cuenta = dao.obtenerPorId(generatedId);
        assertNotNull(cuenta);

        // Debe excluirse de listarTodos
        ArrayList<CuentasAcreedorDTO> lista = (ArrayList<CuentasAcreedorDTO>) dao.listarTodos();
        assertTrue(lista.stream().noneMatch(c -> Objects.equals(c.getCuenta_bancaria_id(), generatedId)));
    }
}
