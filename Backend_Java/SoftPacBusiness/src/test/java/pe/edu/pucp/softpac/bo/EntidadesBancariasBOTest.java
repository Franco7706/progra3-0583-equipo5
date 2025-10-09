package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para EntidadesBancariasBO
 * RF003: Consultar entidades bancarias con formato
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntidadesBancariasBOTest {
    
    private static EntidadesBancariasBO entidadesBO;
    
    @BeforeAll
    static void init() {
        entidadesBO = new EntidadesBancariasBO();
        System.out.println("=== Iniciando Tests de EntidadesBancariasBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de EntidadesBancariasBO ===\n");
    }
    
    /**
     * Test 1: Listar todas las entidades bancarias
     * Verifica RF003 - Consultar entidades bancarias
     */
    @Test
    @Order(1)
    @DisplayName("Debe listar todas las entidades bancarias")
    void testListarTodasLasEntidades() {
        System.out.println("Test 1: Listar Todas las Entidades Bancarias");
        
        ArrayList<EntidadesBancariasDTO> entidades = entidadesBO.listarTodos();
        
        assertNotNull(entidades, "La lista de entidades no debe ser nula");
        assertFalse(entidades.isEmpty(), "Debe haber al menos una entidad");
        
        System.out.println("✓ Total de entidades bancarias: " + entidades.size());
        
        for (EntidadesBancariasDTO e : entidades) {
            System.out.println("  - " + e.getNombre() + 
                             " | País: " + e.getPais().getNombre() +
                             " | Formato: " + e.getFormato_aceptado());
        }
    }
    
    /**
     * Test 2: Obtener entidad por ID
     * Verifica RF003 - Consulta específica
     */
    @Test
    @Order(2)
    @DisplayName("Debe obtener una entidad bancaria por su ID")
    void testObtenerEntidadPorId() {
        System.out.println("Test 2: Obtener Entidad Por ID");
        
        Integer entidadId = 1; // BCP
        EntidadesBancariasDTO entidad = entidadesBO.obtenerPorId(entidadId);
        
        assertNotNull(entidad, "La entidad no debe ser nula");
        assertEquals(entidadId, entidad.getEntidad_bancaria_id(), 
                    "El ID debe coincidir");
        assertNotNull(entidad.getNombre(), "Debe tener nombre");
        assertNotNull(entidad.getPais(), "Debe tener país");
        assertNotNull(entidad.getFormato_aceptado(), 
                     "Debe tener formato bancario");
        
        System.out.println("✓ Entidad encontrada: " + entidad.getNombre());
        System.out.println("  País: " + entidad.getPais().getNombre());
        System.out.println("  Formato: " + entidad.getFormato_aceptado());
    }
    
    /**
     * Test 3: Validar formatos bancarios
     * Verifica RF003 - Formato bancario específico por entidad
     */
    @Test
    @Order(3)
    @DisplayName("Debe validar formatos bancarios disponibles")
    void testValidarFormatosBancarios() {
        System.out.println("Test 3: Validar Formatos Bancarios");
        
        ArrayList<EntidadesBancariasDTO> entidades = entidadesBO.listarTodos();
        
        int csv = 0, xml = 0, txt = 0, otros = 0;
        
        for (EntidadesBancariasDTO e : entidades) {
            if (e.getFormato_aceptado() != null) {
                String formato = e.getFormato_aceptado().toLowerCase();
                if (formato.contains("csv")) {
                    csv++;
                } else if (formato.contains("xml")) {
                    xml++;
                } else if (formato.contains("txt")) {
                    txt++;
                } else {
                    otros++;
                }
            }
        }
        
        System.out.println("✓ Distribución de formatos:");
        System.out.println("  - CSV: " + csv);
        System.out.println("  - XML: " + xml);
        System.out.println("  - TXT: " + txt);
        System.out.println("  - Otros: " + otros);
        
        assertTrue((csv + xml + txt + otros) > 0,
                  "Debe haber al menos un formato definido");
    }
    
    /**
     * Test 4: Agrupar entidades por país
     * Verifica RF003 - País asociado a la entidad
     */
    @Test
    @Order(4)
    @DisplayName("Debe agrupar entidades bancarias por país")
    void testAgruparPorPais() {
        System.out.println("Test 4: Agrupar Entidades Por País");
        
        ArrayList<EntidadesBancariasDTO> entidades = entidadesBO.listarTodos();
        
        int peru = 0, colombia = 0, mexico = 0, otros = 0;
        
        for (EntidadesBancariasDTO e : entidades) {
            if (e.getPais() != null && e.getPais().getNombre() != null) {
                String pais = e.getPais().getNombre();
                if (pais.equalsIgnoreCase("Perú")) {
                    peru++;
                } else if (pais.equalsIgnoreCase("Colombia")) {
                    colombia++;
                } else if (pais.equalsIgnoreCase("México")) {
                    mexico++;
                } else {
                    otros++;
                }
            }
        }
        
        System.out.println("✓ Entidades por país:");
        System.out.println("  - Perú: " + peru);
        System.out.println("  - Colombia: " + colombia);
        System.out.println("  - México: " + mexico);
        System.out.println("  - Otros: " + otros);
    }
}