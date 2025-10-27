package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.daoImpl.EntidadesBancariasDAOImpl;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntidadesBancariasDAOTest {

    protected static EntidadesBancariasDAO dao;
    protected static EntidadesBancariasDTO testEntidad;
    private static int generatedId;

    protected static PaisesDTO testPais;
    protected static PaisesDAO paisesDAO;
    @BeforeAll
    static void init(){
        paisesDAO = new PaisesDAOImpl();
        testPais = new PaisesDTO(0,"PaisPruebaEntidad","XE","+");
        Integer idPais = paisesDAO.insertar(testPais);
        testPais.setPais_id(idPais);
    }
    @AfterAll
    static void deleteDependencies(){
        paisesDAO.eliminar(testPais);
    }
    @BeforeEach
    void setUp() {
        dao = new EntidadesBancariasDAOImpl();
        testEntidad = new EntidadesBancariasDTO();
        testEntidad.setEntidad_bancaria_id(0); // autogenerado
        testEntidad.setNombre("Banco de Prueba");
        testEntidad.setFormato_aceptado("XML");
        testEntidad.setCodigo_swift("BPRUEBA1");

        testEntidad.setPais(testPais);
    }

    @AfterEach
    void tearDown() {
        if (generatedId > 0) {
            try {
                testEntidad.setEntidad_bancaria_id(generatedId);
                dao.eliminar(testEntidad);
            } catch (Exception e) {
                // ignorar errores de limpieza
            }
            generatedId = 0;
        }
    }

    @Test
    @Order(1)
    @DisplayName("Contrato: debe insertar una entidad bancaria y devolver su ID")
    void testInsertar() {
        Integer nuevoId = dao.insertar(testEntidad);
        assertNotNull(nuevoId);
        assertTrue(nuevoId > 0, "insertar debe devolver un ID autogenerado positivo.");
        generatedId = nuevoId;
    }

    @Test
    @Order(2)
    @DisplayName("Contrato: debe obtener una entidad bancaria por su ID")
    void testObtenerPorId() {
        generatedId = dao.insertar(testEntidad);
        EntidadesBancariasDTO entidad = dao.obtenerPorId(generatedId);
        assertNotNull(entidad);
        assertEquals(generatedId, entidad.getEntidad_bancaria_id(), "obtenerPorId devolvió ID incorrecto.");

        assertNull(dao.obtenerPorId(-1), "obtenerPorId debe devolver nulo para un ID inexistente.");
    }

    @Test
    @Order(3)
    @DisplayName("Contrato: debe modificar una entidad bancaria existente")
    void testModificar() {
        generatedId = dao.insertar(testEntidad);
        testEntidad.setEntidad_bancaria_id(generatedId);
        testEntidad.setNombre("Banco Modificado");

        int resultado = dao.modificar(testEntidad);
        assertTrue(resultado > 0);

        EntidadesBancariasDTO entidadModificada = dao.obtenerPorId(generatedId);
        assertEquals("Banco Modificado", entidadModificada.getNombre());
    }

    @Test
    @Order(4)
    @DisplayName("Contrato: debe listar todas las entidades bancarias")
    void testListarTodos() {
        generatedId = dao.insertar(testEntidad);
        ArrayList<EntidadesBancariasDTO> lista = (ArrayList<EntidadesBancariasDTO>) dao.listarTodos();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.stream().anyMatch(e -> e.getEntidad_bancaria_id() == generatedId));
    }

    @Test
    @Order(5)
    @DisplayName("Contrato: debe eliminar una entidad bancaria")
    void testEliminar() {
        generatedId = dao.insertar(testEntidad);
        testEntidad.setEntidad_bancaria_id(generatedId);

        int resultado = dao.eliminar(testEntidad);
        assertTrue(resultado > 0);

        assertNull(dao.obtenerPorId(generatedId), "La entidad bancaria debe ser nula después de eliminarla.");
        generatedId = 0; // evitar doble borrado en tearDown
    }
}
