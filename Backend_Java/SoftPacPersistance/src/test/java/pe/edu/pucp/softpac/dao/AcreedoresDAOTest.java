package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.daoImpl.AcreedoresDAOImpl;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;
import pe.edu.pucp.softpac.dao.UsuariosDAO;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AcreedoresDAOTest {

    protected static AcreedoresDAO dao;
    protected static AcreedoresDTO testAcreedor;
    private static int generatedId;

    protected static PaisesDTO testPais;
    protected static PaisesDAO paisesDAO;

    protected static UsuariosDAO usuariosDAO;
    protected static UsuariosDTO testUsuarioEliminacion;

    @BeforeAll
    static void init(){
        paisesDAO = new PaisesDAOImpl();
        testPais = new PaisesDTO(0,"PaisPruebaAcreedor","XA","+");
        Integer idPais = paisesDAO.insertar(testPais);
        testPais.setPais_id(idPais);

        usuariosDAO = new UsuariosDAOImpl();
        testUsuarioEliminacion = new UsuariosDTO();
        testUsuarioEliminacion.setUsuario_id(1002);
        testUsuarioEliminacion.setCorreo_electronico("eliminacion@softpac.test");
        testUsuarioEliminacion.setNombre_de_usuario("Usuario de eliminación");
        testUsuarioEliminacion.setActivo(true);
        testUsuarioEliminacion.setSuperusuario(true);
        testUsuarioEliminacion.setPassword_hash("elim123");
        usuariosDAO.insertar(testUsuarioEliminacion);
    }
    @AfterAll
    static void cleanup(){
        try { usuariosDAO.eliminar(testUsuarioEliminacion); } catch (Exception ignored) {}
        try { paisesDAO.eliminar(testPais); } catch (Exception ignored) {};
    }

    @BeforeEach
    void setUp() {
        dao = new AcreedoresDAOImpl();
        testAcreedor = new AcreedoresDTO();
        testAcreedor.setAcreedor_id(0);
        testAcreedor.setRazon_social("Proveedor de Prueba S.A.");
        testAcreedor.setRuc("12345678910");
        testAcreedor.setDireccion_fiscal("Av. Siempre Viva 742");
        testAcreedor.setCondicion("ACTIVO");
        testAcreedor.setPlazo_de_pago(30);
        testAcreedor.setActivo(true);

        testAcreedor.setPais(testPais);
    }

    @AfterEach
    void tearDown() {
        if (generatedId > 0) {
            try {
                testAcreedor.setAcreedor_id(generatedId);
                dao.eliminar(testAcreedor);
            } catch (Exception e) {
                // ignorar errores de limpieza
            }
            generatedId = 0;
        }
    }

    @Test
    @Order(1)
    @DisplayName("Contrato: debe insertar un acreedor y devolver su ID")
    void testInsertar() {
        Integer nuevoId = dao.insertar(testAcreedor);
        assertNotNull(nuevoId, "El ID insertado no debe ser null");
        assertTrue(nuevoId > 0, "El ID insertado debe ser mayor que 0");
        generatedId = nuevoId;
    }

    @Test
    @Order(2)
    @DisplayName("Contrato: debe obtener un acreedor por su ID")
    void testObtenerPorId() {
        generatedId = dao.insertar(testAcreedor);
        AcreedoresDTO acreedor = dao.obtenerPorId(generatedId);

        assertNotNull(acreedor, "El acreedor obtenido no debe ser null");
        assertEquals(generatedId, acreedor.getAcreedor_id(), "El ID no coincide");
        assertEquals("Proveedor de Prueba S.A.", acreedor.getRazon_social(),"La Razon Social no coincide");

        assertNull(dao.obtenerPorId(-1), "Debe devolver null para un ID inexistente");
    }

    @Test
    @Order(3)
    @DisplayName("Contrato: debe modificar un acreedor existente")
    void testModificar() {
        generatedId = dao.insertar(testAcreedor);
        testAcreedor.setAcreedor_id(generatedId);
        testAcreedor.setRazon_social("Proveedor Modificado SAC");

        int filas = dao.modificar(testAcreedor);
        assertTrue(filas > 0, "Modificar debe afectar al menos una fila");

        AcreedoresDTO modificado = dao.obtenerPorId(generatedId);
        assertNotNull(modificado);
        assertEquals("Proveedor Modificado SAC", modificado.getRazon_social(), "La razón social no se actualizó");
    }

    @Test
    @Order(4)
    @DisplayName("Contrato: debe listar todos los acreedores")
    void testListarTodos() {
        generatedId = dao.insertar(testAcreedor);
        ArrayList<AcreedoresDTO> lista = (ArrayList<AcreedoresDTO>) dao.listarTodos();

        assertNotNull(lista, "La lista no debe ser null");
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
        assertTrue(lista.stream().anyMatch(a -> a.getAcreedor_id() == generatedId),
                "La lista debe contener el acreedor insertado");
    }

    @Test
    @Order(5)
    @DisplayName("Contrato: debe eliminar un acreedor")
    void testEliminar() {
        generatedId = dao.insertar(testAcreedor);
        testAcreedor.setAcreedor_id(generatedId);

        int filas = dao.eliminar(testAcreedor);
        assertTrue(filas > 0, "Eliminar debe afectar al menos una fila");

        AcreedoresDTO eliminado = dao.obtenerPorId(generatedId);
        assertNull(eliminado, "Después de eliminar, obtenerPorId debe devolver null");

        generatedId = 0; // para que tearDown no intente eliminar de nuevo
    }

    @Test
    @Order(6)
    @DisplayName("Contrato: debe realizar eliminación lógica de un acreedor")
    void testEliminarLogico() {
        generatedId = dao.insertar(testAcreedor);
        testAcreedor.setAcreedor_id(generatedId);
        testAcreedor.setFecha_eliminacion(new Date());
        testAcreedor.setUsuario_eliminacion(testUsuarioEliminacion);

        Integer res = dao.eliminarLogico(testAcreedor);
        assertTrue(res > 0);

        AcreedoresDTO acreedor = dao.obtenerPorId(generatedId);
        assertNotNull(acreedor);

        // Debe excluirse de listarTodos
        ArrayList<AcreedoresDTO> lista = (ArrayList<AcreedoresDTO>) dao.listarTodos();
        assertTrue(lista.stream().noneMatch(a -> a.getAcreedor_id() == generatedId));
    }
}
