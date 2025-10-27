package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.MonedasDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softpac.daoImpl.MonedasDAOImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MonedasDAOTest {

    protected static MonedasDAO dao;
    protected static MonedasDTO testMoneda;
    private static int generatedId;

    /**
     * Método abstracto que las clases hijas deben implementar para proveer
     * la instancia concreta del DAO que se va a probar.
     * @return Una implementación de MonedasDAO.
     */
    
    @BeforeEach
    void setUp() {
        dao = new MonedasDAOImpl();
        testMoneda = new MonedasDTO(0, "Peso de Contrato", "CDP", "C$");
    }

    @AfterEach
    void tearDown() {
        if (generatedId > 0) {
            try {
                testMoneda.setMoneda_id(generatedId);
                dao.eliminar(testMoneda);
            } catch (Exception e) {
                // Ignorar
            }
            generatedId = 0;
        }
    }

    @Test
    @Order(1)
    @DisplayName("Contrato: debe insertar una moneda y devolver su ID")
    void testInsertar() {
        Integer nuevoId = dao.insertar(testMoneda);
        assertNotNull(nuevoId);
        assertTrue(nuevoId > 0, "insertar debe devolver un ID autogenerado positivo.");
        generatedId = nuevoId;
    }

    @Test
    @Order(2)
    @DisplayName("Contrato: debe obtener una moneda por su ID")
    void testObtenerPorId() {
        generatedId = dao.insertar(testMoneda);
        MonedasDTO moneda = dao.obtenerPorId(generatedId);
        assertNotNull(moneda);
        assertEquals(generatedId, moneda.getMoneda_id(),"obtener por ID incorrecto.");

        assertNull(dao.obtenerPorId(-1), "obtenerPorId debe devolver nulo para un ID inexistente.");
    }
    
    @Test
    @Order(3)
    @DisplayName("Contrato: debe modificar una moneda existente")
    void testModificar() {
        generatedId = dao.insertar(testMoneda);
        testMoneda.setMoneda_id(generatedId);
        testMoneda.setNombre("Sol de Contrato");
        
        int resultado = dao.modificar(testMoneda);
        assertTrue(resultado > 0);
        
        MonedasDTO monedaModificada = dao.obtenerPorId(generatedId);
        assertEquals("Sol de Contrato", monedaModificada.getNombre());
    }

    @Test
    @Order(4)
    @DisplayName("Contrato: debe listar todas las monedas")
    void testListarTodos() {
        generatedId = dao.insertar(testMoneda);
        ArrayList<MonedasDTO> lista = (ArrayList<MonedasDTO>) dao.listarTodos();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.stream().anyMatch(m -> m.getMoneda_id() == generatedId));
    }
    
    @Test
    @Order(5)
    @DisplayName("Contrato: debe eliminar una moneda")
    void testEliminar() {
        generatedId = dao.insertar(testMoneda);
        testMoneda.setMoneda_id(generatedId);
        
        int resultado = dao.eliminar(testMoneda);
        assertTrue(resultado > 0);
        
        assertNull(dao.obtenerPorId(generatedId), "La moneda debe ser nula después de eliminarla.");
        generatedId = 0; // Para evitar que el tearDown intente borrarla de nuevo
    }
}