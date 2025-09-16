package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuariosDAOTest {

    protected static UsuariosDAO dao;
    protected static UsuariosDTO testUsuario;


    @BeforeEach
    void setUp() {
        // Obtenemos la implementación específica del DAO desde la clase hija.
        dao = new UsuariosDAOImpl();

        // Creamos un usuario de prueba para las operaciones.
        testUsuario = new UsuariosDTO(999, "test.contract@pucp.edu.pe", true, "hashedpassword", false);
        
        // Limpieza previa para asegurar un estado inicial limpio.
        // Se envuelve en try-catch por si el usuario no existe.
        try {
            dao.eliminar(testUsuario);
        } catch (Exception e) {
            // Ignorar si no existe
        }
    }

    @AfterEach
    void tearDown() {
        // Limpieza posterior para no afectar otras pruebas.
        try {
            dao.eliminar(testUsuario);
        } catch (Exception e) {
            // Ignorar si ya fue eliminado por el propio test.
        }
    }

    @Test
    @Order(1)
    @DisplayName("Contrato: debe insertar un nuevo usuario")
    void testInsertar() {
        int resultado = dao.insertar(testUsuario);
        assertTrue(resultado > 0, "La inserción debe devolver un resultado positivo.");

        UsuariosDTO usuarioInsertado = dao.obtenerPorId(testUsuario.getUsuario_id());
        assertNotNull(usuarioInsertado, "Se debe poder obtener el usuario después de insertarlo.");
        assertEquals(testUsuario.getCorreo_electronico(), usuarioInsertado.getCorreo_electronico());
    }

    @Test
    @Order(2)
    @DisplayName("Contrato: debe obtener un usuario por su ID")
    void testObtenerPorId() {
        dao.insertar(testUsuario);
        UsuariosDTO usuarioEncontrado = dao.obtenerPorId(999);
        assertNotNull(usuarioEncontrado);

        UsuariosDTO usuarioNoEncontrado = dao.obtenerPorId(0);
        assertNull(usuarioNoEncontrado, "obtenerPorId debe devolver nulo para un ID inexistente.");
    }

    @Test
    @Order(3)
    @DisplayName("Contrato: debe modificar un usuario existente")
    void testModificar() {
        dao.insertar(testUsuario);
        testUsuario.setCorreo_electronico("updated.contract@pucp.edu.pe");

        int resultado = dao.modificar(testUsuario);
        assertTrue(resultado > 0);

        UsuariosDTO usuarioModificado = dao.obtenerPorId(999);
        assertEquals("updated.contract@pucp.edu.pe", usuarioModificado.getCorreo_electronico());
    }

    @Test
    @Order(4)
    @DisplayName("Contrato: debe listar todos los usuarios")
    void testListarTodos() {
        dao.insertar(testUsuario);
        ArrayList<UsuariosDTO> lista = dao.listarTodos();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.stream().anyMatch(u -> u.getUsuario_id() == 999));
    }

    @Test
    @Order(5)
    @DisplayName("Contrato: debe eliminar un usuario")
    void testEliminar() {
        dao.insertar(testUsuario);
        int resultado = dao.eliminar(testUsuario);
        assertTrue(resultado > 0);

        UsuariosDTO usuarioEliminado = dao.obtenerPorId(testUsuario.getUsuario_id());
        assertNull(usuarioEliminado, "El usuario debe ser nulo después de eliminarlo.");
    }
}