package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.PaisesDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para PaisesBO
 * Gestión de países del sistema
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaisesBOTest {
    
    private static PaisesBO paisesBO;
    
    @BeforeAll
    static void init() {
        paisesBO = new PaisesBO();
        System.out.println("=== Iniciando Tests de PaisesBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de PaisesBO ===\n");
    }
    
    /**
     * Test 1: Listar todos los países
     */
    @Test
    @Order(1)
    @DisplayName("Debe listar todos los países")
    void testListarTodosLosPaises() {
        System.out.println("Test 1: Listar Todos los Países");
        
        ArrayList<PaisesDTO> paises = paisesBO.listarTodos();
        
        assertNotNull(paises, "La lista de países no debe ser nula");
        assertFalse(paises.isEmpty(), "Debe haber al menos un país");
        
        System.out.println("✓ Total de países: " + paises.size());
        
        for (PaisesDTO p : paises) {
            System.out.println("  - " + p.getNombre() + 
                             " | Código: " + p.getCodigo_iso());
        }
    }
    
    /**
     * Test 2: Validar países principales
     */
    @Test
    @Order(2)
    @DisplayName("Debe validar países principales del sistema")
    void testValidarPaisesPrincipales() {
        System.out.println("Test 2: Validar Países Principales");
        
        ArrayList<PaisesDTO> paises = paisesBO.listarTodos();
        
        boolean tienePeru = false;
        boolean tieneColombia = false;
        boolean tieneMexico = false;
        
        for (PaisesDTO p : paises) {
            if ("Perú".equalsIgnoreCase(p.getNombre())) {
                tienePeru = true;
            }
            if ("Colombia".equalsIgnoreCase(p.getNombre())) {
                tieneColombia = true;
            }
            if ("México".equalsIgnoreCase(p.getNombre())) {
                tieneMexico = true;
            }
        }
        
        assertTrue(tienePeru, "Debe existir Perú");
        assertTrue(tieneColombia, "Debe existir Colombia");
        assertTrue(tieneMexico, "Debe existir México");
        
        System.out.println("✓ Países principales validados:");
        System.out.println("  - Perú: " + (tienePeru ? "✓" : "✗"));
        System.out.println("  - Colombia: " + (tieneColombia ? "✓" : "✗"));
        System.out.println("  - México: " + (tieneMexico ? "✓" : "✗"));
    }
}