package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.UsuariosDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;
import pe.edu.pucp.softpac.model.UsuarioPaisAccesoDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;
import pe.edu.pucp.softpac.daoImpl.UsuarioPaisAccesoDAOImpl;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuariosDAOTest {

    protected static UsuariosDAO dao;
    protected static UsuariosDTO testUsuario;
    protected static UsuariosDTO testUsuarioEliminacion;

    // Para pruebas con detalles (usuario_pais_acceso)
    protected static PaisesDAO paisesDAO;
    protected static UsuarioPaisAccesoDAO usuarioPaisAccesoDAO;
    protected static PaisesDTO paisA;
    protected static PaisesDTO paisB;

    @BeforeAll
    static void init() {
        // Obtenemos la implementación específica del DAO desde la clase hija.
        dao = new UsuariosDAOImpl();
        paisesDAO = new PaisesDAOImpl();
        usuarioPaisAccesoDAO = new UsuarioPaisAccesoDAOImpl();

        testUsuarioEliminacion = new UsuariosDTO(9999,"correo_eliminacion","usueliminacion",null,null,true,"32134",true);
        dao.insertar(testUsuarioEliminacion);

        // Crear dos países ficticios para los detalles
        paisA = new PaisesDTO();
        paisA.setNombre("Testlandia A");
        paisA.setCodigo_iso("TA");
        paisA.setCodigo_telefonico("+91");
        paisA.setPais_id(paisesDAO.insertar(paisA));

        paisB = new PaisesDTO();
        paisB.setNombre("Testlandia B");
        paisB.setCodigo_iso("TB");
        paisB.setCodigo_telefonico("+92");
        paisB.setPais_id(paisesDAO.insertar(paisB));
    }
    @AfterAll
    static void cleanup(){
        try {
            dao.eliminar(testUsuarioEliminacion);
            paisesDAO.eliminar(paisA);
            paisesDAO.eliminar(paisB);
        } catch (Exception ignored) {}
    }

    @BeforeEach
    void setUp() {

        // Creamos un usuario de prueba para las operaciones.
        testUsuario = new UsuariosDTO(999, "test.contract@pucp.edu.pe","usuarioprueba",null,null, true, "hashedpassword", false);
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
        ArrayList<UsuariosDTO> lista = (ArrayList<UsuariosDTO>) dao.listarTodos();
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
        testUsuario.setFecha_eliminacion(new Date());
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

    // ================== PRUEBAS CON usuario_pais_acceso ==================
    @Test
    @Order(8)
    @DisplayName("Cascada: insertar usuario debe insertar sus usuario_pais_acceso")
    void testInsercionConDetalles() {

        UsuarioPaisAccesoDTO d1 = new UsuarioPaisAccesoDTO();
        d1.setUsuario(testUsuario);
        d1.setPais(paisA);
        d1.setAcceso(true);

        UsuarioPaisAccesoDTO d2 = new UsuarioPaisAccesoDTO();
        d2.setUsuario(testUsuario);
        d2.setPais(paisB);
        d2.setAcceso(false);

        testUsuario.addUsuario_pais(d1);
        testUsuario.addUsuario_pais(d2);

        int filas = dao.insertar(testUsuario);
        assertTrue(filas > 0, "Insertar usuario con detalles debe afectar al menos 1 fila");

        // verificar detalles insertados
        UsuarioPaisAccesoDTO r1 = usuarioPaisAccesoDAO.obtenerPorUsuarioYPais(testUsuario.getUsuario_id(), paisA.getPais_id());
        UsuarioPaisAccesoDTO r2 = usuarioPaisAccesoDAO.obtenerPorUsuarioYPais(testUsuario.getUsuario_id(), paisB.getPais_id());
        assertNotNull(r1, "Debe existir el acceso para el país A");
        assertNotNull(r2, "Debe existir el acceso para el país B");
        assertTrue(r1.getAcceso(), "Acceso en país A debe ser true");
        assertFalse(r2.getAcceso(), "Acceso en país B debe ser false");
    }

    @Test
    @Order(9)
    @DisplayName("Cascada: modificar usuario debe modificar sus usuario_pais_acceso")
    void testModificacionConDetalles() {

        UsuarioPaisAccesoDTO d1 = new UsuarioPaisAccesoDTO();
        d1.setUsuario(testUsuario);
        d1.setPais(paisA);
        d1.setAcceso(false);

        UsuarioPaisAccesoDTO d2 = new UsuarioPaisAccesoDTO();
        d2.setUsuario(testUsuario);
        d2.setPais(paisB);
        d2.setAcceso(true);

        testUsuario.addUsuario_pais(d1);
        testUsuario.addUsuario_pais(d2);

        try { dao.eliminar(testUsuario); } catch (Exception ignored) {}
        dao.insertar(testUsuario);

        // Cambiamos los flags de acceso
        d1.setAcceso(true);
        d2.setAcceso(false);

        int filas = dao.modificar(testUsuario);
        assertTrue(filas > 0, "Modificar usuario con detalles debe afectar al menos 1 fila");

        UsuarioPaisAccesoDTO r1 = usuarioPaisAccesoDAO.obtenerPorUsuarioYPais(testUsuario.getUsuario_id(), paisA.getPais_id());
        UsuarioPaisAccesoDTO r2 = usuarioPaisAccesoDAO.obtenerPorUsuarioYPais(testUsuario.getUsuario_id(), paisB.getPais_id());
        assertNotNull(r1);
        assertNotNull(r2);
        assertTrue(r1.getAcceso(), "Acceso en país A debe actualizarse a true");
        assertFalse(r2.getAcceso(), "Acceso en país B debe actualizarse a false");
    }

    @Test
    @Order(10)
    @DisplayName("Cascada: eliminar usuario debe eliminar físicamente sus usuario_pais_acceso")
    void testEliminacionConDetalles() {

        UsuarioPaisAccesoDTO d1 = new UsuarioPaisAccesoDTO();
        d1.setUsuario(testUsuario);
        d1.setPais(paisA);
        d1.setAcceso(true);
        UsuarioPaisAccesoDTO d2 = new UsuarioPaisAccesoDTO();
        d2.setUsuario(testUsuario);
        d2.setPais(paisB);
        d2.setAcceso(true);
        testUsuario.addUsuario_pais(d1);
        testUsuario.addUsuario_pais(d2);

        try { dao.eliminar(testUsuario); } catch (Exception ignored) {}
        dao.insertar(testUsuario);

        int filas = dao.eliminar(testUsuario);
        assertTrue(filas > 0, "Eliminar usuario con detalles debe afectar al menos 1 fila");

        UsuarioPaisAccesoDTO r1 = usuarioPaisAccesoDAO.obtenerPorUsuarioYPais(2003, paisA.getPais_id());
        UsuarioPaisAccesoDTO r2 = usuarioPaisAccesoDAO.obtenerPorUsuarioYPais(2003, paisB.getPais_id());
        assertNull(r1, "Después de eliminar el usuario, el detalle del país A no debe existir");
        assertNull(r2, "Después de eliminar el usuario, el detalle del país B no debe existir");
    }

    @Test
    @Order(11)
    @DisplayName("Obtener por ID: debe traer también los usuario_pais_acceso en el ArrayList")
    void testObtenerPorIdIncluyeDetalles() {
        // Preparar usuario con dos accesos de país
        UsuarioPaisAccesoDTO d1a = new UsuarioPaisAccesoDTO();
        d1a.setUsuario(testUsuario);
        d1a.setPais(paisA);
        d1a.setAcceso(true);

        UsuarioPaisAccesoDTO d2a = new UsuarioPaisAccesoDTO();
        d2a.setUsuario(testUsuario);
        d2a.setPais(paisB);
        d2a.setAcceso(false);

        testUsuario.addUsuario_pais(d1a);
        testUsuario.addUsuario_pais(d2a);

        try { dao.eliminar(testUsuario); } catch (Exception ignored) {}
        dao.insertar(testUsuario);

        // Act: obtener por ID y verificar que venga con los detalles poblados
        UsuariosDTO recuperado = dao.obtenerPorId(testUsuario.getUsuario_id());
        assertNotNull(recuperado, "Debe obtener el usuario");
        assertNotNull(recuperado.getUsuario_pais(), "La lista usuario_pais no debe ser nula");
        assertEquals(2, recuperado.getUsuario_pais().size(), "El usuario debe tener 2 accesos de país");

        boolean encontradoA = false;
        boolean encontradoB = false;
        for (UsuarioPaisAccesoDTO d : recuperado.getUsuario_pais()) {
            if (Objects.equals(d.getPais().getPais_id(), paisA.getPais_id())) {
                encontradoA = true;
                assertTrue(d.getAcceso(), "Acceso en país A debe ser true");
            }
            if (Objects.equals(d.getPais().getPais_id(), paisB.getPais_id())) {
                encontradoB = true;
                assertFalse(d.getAcceso(), "Acceso en país B debe ser false");
            }
        }
        assertTrue(encontradoA, "Debe incluir detalle para país A");
        assertTrue(encontradoB, "Debe incluir detalle para país B");
    }

}
