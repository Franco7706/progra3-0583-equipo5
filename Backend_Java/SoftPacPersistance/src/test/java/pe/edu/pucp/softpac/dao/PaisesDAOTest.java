package pe.edu.pucp.softpac.dao;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;
import pe.edu.pucp.softpac.model.PaisesDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaisesDAOTest {

    protected static PaisesDAO dao;
    protected static PaisesDTO testPais;
    private static int generatedId;

    @BeforeEach
    void setUp() {
        dao = new PaisesDAOImpl();
        testPais = new PaisesDTO();
        testPais.setPais_id(0); // se genera al insertar
        testPais.setNombre("Peru");
        testPais.setCodigo_iso("PE"); // ISO para Perú
        testPais.setCodigo_telefonico("+51");
    }

    @AfterEach
    void tearDown() {
        if (generatedId > 0) {
            try {
                testPais.setPais_id(generatedId);
                dao.eliminar(testPais);
            } catch (Exception e) {
                // ignorar errores en limpieza
            }
            generatedId = 0;
        }
    }

    @Test
    @Order(1)
    @DisplayName("Contrato: debe insertar Perú y devolver su ID")
    void testInsertar() {
        Integer nuevoId = dao.insertar(testPais);
        assertNotNull(nuevoId, "El ID insertado no debe ser null");
        assertTrue(nuevoId > 0, "El ID insertado debe ser mayor que 0");
        generatedId = nuevoId;
    }

    @Test
    @Order(2)
    @DisplayName("Contrato: debe obtener Perú por su ID")
    void testObtenerPorId() {
        generatedId = dao.insertar(testPais);
        PaisesDTO pais = dao.obtenerPorId(generatedId);

        assertNotNull(pais, "El país obtenido no debe ser null");
        assertEquals(generatedId, pais.getPais_id(), "El ID no coincide");
        assertEquals("Peru", pais.getNombre(), "El nombre no coincide");

        assertNull(dao.obtenerPorId(-1), "Debe devolver null para un ID inexistente");
    }

    @Test
    @Order(3)
    @DisplayName("Contrato: debe modificar un país existente (Perú → Perú Editado)")
    void testModificar() {
        generatedId = dao.insertar(testPais);
        testPais.setPais_id(generatedId);
        testPais.setNombre("Peru Editado");

        int filas = dao.modificar(testPais);
        assertTrue(filas > 0, "Modificar debe afectar al menos una fila");

        PaisesDTO modificado = dao.obtenerPorId(generatedId);
        assertNotNull(modificado);
        assertEquals("Peru Editado", modificado.getNombre(), "El nombre no se actualizó");
    }

    @Test
    @Order(4)
    @DisplayName("Contrato: debe listar todos los países e incluir a Perú")
    void testListarTodos() {
        generatedId = dao.insertar(testPais);
        ArrayList<PaisesDTO> lista = (ArrayList<PaisesDTO>) dao.listarTodos();

        assertNotNull(lista, "La lista no debe ser null");
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");
        assertTrue(lista.stream().anyMatch(p -> p.getPais_id() == generatedId && "Peru".equals(p.getNombre())),
                "La lista debe contener a Perú");
    }

    @Test
    @Order(5)
    @DisplayName("Contrato: debe eliminar Perú de la base de datos")
    void testEliminar() {
        generatedId = dao.insertar(testPais);
        testPais.setPais_id(generatedId);

        int filas = dao.eliminar(testPais);
        assertTrue(filas > 0, "Eliminar debe afectar al menos una fila");

        PaisesDTO eliminado = dao.obtenerPorId(generatedId);
        assertNull(eliminado, "Después de eliminar, obtenerPorId debe devolver null");

        generatedId = 0; // evitar que tearDown lo intente eliminar otra vez
    }
}
