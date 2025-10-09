package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.MonedasDTO;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MonedasBOTest {
    
    private static MonedasBO monedasBO;
    
    @BeforeAll
    static void init() {
        monedasBO = new MonedasBO();
        System.out.println("=== Iniciando Tests de MonedasBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de MonedasBO ===\n");
    }
    
    @Test
    @Order(1)
    @DisplayName("Debe listar todas las monedas")
    void testListarTodasLasMonedas() {
        System.out.println("Test 1: Listar Todas las Monedas");
        
        ArrayList<MonedasDTO> monedas = monedasBO.listarTodos();
        
        assertNotNull(monedas, "La lista de monedas no debe ser nula");
        assertFalse(monedas.isEmpty(), "Debe haber al menos una moneda");
        
        System.out.println("✓ Total de monedas: " + monedas.size());
        
        for (MonedasDTO m : monedas) {
            System.out.println("  - " + m.getNombre() + 
                             " (" + m.getCodigo_iso() + ")" +
                             " | Símbolo: " + m.getSimbolo());
        }
    }
    
    @Test
    @Order(2)
    @DisplayName("Debe obtener moneda por ID")
    void testObtenerMonedaPorId() {
        System.out.println("Test 2: Obtener Moneda Por ID");
        
        Integer monedaId = 1; // PEN
        MonedasDTO moneda = monedasBO.obtenerPorID(monedaId);
        
        assertNotNull(moneda, "La moneda no debe ser nula");
        assertEquals(monedaId, moneda.getMoneda_id(), "El ID debe coincidir");
        assertNotNull(moneda.getCodigo_iso(), "Debe tener código ISO");
        assertNotNull(moneda.getNombre(), "Debe tener nombre");
        
        System.out.println("✓ Moneda encontrada: " + moneda.getNombre());
        System.out.println("  Código ISO: " + moneda.getCodigo_iso());
        System.out.println("  Símbolo: " + moneda.getSimbolo());
    }
    
    @Test
    @Order(3)
    @DisplayName("Debe validar monedas principales")
    void testValidarMonedasPrincipales() {
        System.out.println("Test 3: Validar Monedas Principales");
        
        ArrayList<MonedasDTO> monedas = monedasBO.listarTodos();
        
        boolean tienePEN = false, tieneUSD = false, tieneCOP = false, tieneMXN = false;
        
        for (MonedasDTO m : monedas) {
            if ("PEN".equals(m.getCodigo_iso())) tienePEN = true;
            if ("USD".equals(m.getCodigo_iso())) tieneUSD = true;
            if ("COP".equals(m.getCodigo_iso())) tieneCOP = true;
            if ("MXN".equals(m.getCodigo_iso())) tieneMXN = true;
        }
        
        assertTrue(tienePEN, "Debe existir PEN (Soles)");
        assertTrue(tieneUSD, "Debe existir USD (Dólares)");
        
        System.out.println("✓ Monedas principales validadas:");
        System.out.println("  - PEN: " + (tienePEN ? "✓" : "✗"));
        System.out.println("  - USD: " + (tieneUSD ? "✓" : "✗"));
        System.out.println("  - COP: " + (tieneCOP ? "✓" : "✗"));
        System.out.println("  - MXN: " + (tieneMXN ? "✓" : "✗"));
    }
}