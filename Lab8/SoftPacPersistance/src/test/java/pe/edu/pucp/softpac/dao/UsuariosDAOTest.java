package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuariosDAOTest {

    protected static UsuariosDAO dao;
    protected static UsuariosDTO testUsuario;
    protected static UsuariosDTO testUsuarioEliminacion;

    @BeforeAll
    static void init() {
        // Obtenemos la implementación específica del DAO desde la clase hija.
        dao = new UsuariosDAOImpl();
        testUsuarioEliminacion = new UsuariosDTO(9999,"correo_eliminacion",true,"32134",true);
        dao.insertar(testUsuarioEliminacion);
    }
    @AfterAll
    static void cleanup(){
        dao.eliminar(testUsuarioEliminacion);
    }

    @BeforeEach
    void setUp() {

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

    @Test
    @Order(6)
    @DisplayName("Contrato: debe eliminar un usuario")
    void testEliminarLogico() {
        dao.insertar(testUsuario);
        testUsuario.setFecha_eliminacion(LocalDateTime.now());
        testUsuario.setUsuario_eliminacion(testUsuarioEliminacion);
        int resultado = dao.eliminarLogico(testUsuario);
        assertTrue(resultado > 0);

        UsuariosDTO usuarioEliminado = dao.obtenerPorId(testUsuario.getUsuario_id());
        assertNotNull(usuarioEliminado, "El usuario no debe ser nulo después de eliminarlo lógicamente.");
        assertNotNull(usuarioEliminado.getFecha_eliminacion(), "La fecha de eliminación no puede ser nula");
    }

    @Test
    @Order(7)
    @DisplayName("Contrato: debe obtener un usuario por su correo electrónico")
    void testObtenerPorCorreo() {
        dao.insertar(testUsuario);
        UsuariosDTO usuarioEncontrado = dao.obtenerPorCorreo("test.contract@pucp.edu.pe");
        assertNotNull(usuarioEncontrado);

        UsuariosDTO usuarioNoEncontrado = dao.obtenerPorCorreo("test.correofalso");
        assertNull(usuarioNoEncontrado, "obtenerPorCorreo debe devolver nulo para un correo inexistente.");
    }
}