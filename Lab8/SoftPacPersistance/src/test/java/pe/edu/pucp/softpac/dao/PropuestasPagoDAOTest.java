package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.daoImpl.EntidadesBancariasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PropuestasPagoDAOImpl;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;
import pe.edu.pucp.softpac.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PropuestasPagoDAOTest {

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
        testUsuario.setUsuario_id(1);
        testUsuario.setCorreo_electronico("correo@prueba.com");
        testUsuario.setActivo(true);
        testUsuario.setSuperusuario(false);
        testUsuario.setPassword_hash("123456");
        Integer idUsuario = usuariosDAO.insertar(testUsuario);
        testUsuario.setUsuario_id(idUsuario);
    }

    @BeforeEach
    void setUp() {
        dao = new PropuestasPagoDAOImpl();

        testPropuesta = new PropuestasPagoDTO();
        testPropuesta.setPropuesta_id(0);
        testPropuesta.setFecha_hora_creacion(LocalDateTime.now());
        testPropuesta.setEstado("PENDIENTE");
        testPropuesta.setEntidad_bancaria(testEntidad);
        testPropuesta.setFecha_hora_creacion(LocalDateTime.now());
        testPropuesta.setFecha_hora_modificacion(LocalDateTime.now());
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

    @AfterAll
    static void deleteDependencies(){
        entidadesBancariasDAO.eliminar(testEntidad);
        paisesDAO.eliminar(testPais);
        usuariosDAO.eliminar(testUsuario);
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

        ArrayList<PropuestasPagoDTO> lista = dao.listarTodos();
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
}
