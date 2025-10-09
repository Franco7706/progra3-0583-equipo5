package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.TiposDeCambioDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para TiposDeCambioBO
 * Gestión de tipos de cambio entre monedas
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TiposDeCambioBOTest {
    
    private static TiposDeCambioBO tiposCambioBO;
    private static Integer tipoCambioIdTest;
    
    @BeforeAll
    static void init() {
        tiposCambioBO = new TiposDeCambioBO();
        System.out.println("=== Iniciando Tests de TiposDeCambioBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de TiposDeCambioBO ===\n");
    }
    
    /**
     * Test 1: Insertar tipo de cambio
     */
    @Test
    @Order(1)
    @DisplayName("Debe insertar un tipo de cambio")
    void testInsertarTipoDeCambio() {
        System.out.println("Test 1: Insertar Tipo de Cambio");
        
        LocalDateTime fecha = LocalDateTime.now();
        BigDecimal tasa = new BigDecimal("3.75"); // PEN a USD
        Integer monedaOrigenId = 1; // PEN
        Integer monedaDestinoId = 2; // USD
        
        Integer resultado = tiposCambioBO.insertar(
            fecha, tasa, monedaOrigenId, monedaDestinoId
        );
        
        assertNotNull(resultado, "El ID del tipo de cambio no debe ser nulo");
        assertTrue(resultado > 0, "El ID debe ser mayor a 0");
        tipoCambioIdTest = resultado;
        
        System.out.println("✓ Tipo de cambio insertado con ID: " + resultado);
        System.out.println("  Tasa: " + tasa);
    }
    
    /**
     * Test 2: Obtener tipo de cambio por ID
     */
    @Test
    @Order(2)
    @DisplayName("Debe obtener un tipo de cambio por su ID")
    void testObtenerTipoDeCambioPorId() {
        System.out.println("Test 2: Obtener Tipo de Cambio Por ID");
        
        if (tipoCambioIdTest == null) {
            tipoCambioIdTest = 1;
        }
        
        TiposDeCambioDTO tipoCambio = tiposCambioBO.obtenerPorId(tipoCambioIdTest);
        
        assertNotNull(tipoCambio, "El tipo de cambio no debe ser nulo");
        assertEquals(tipoCambioIdTest, tipoCambio.getTipo_cambio_id(), 
                    "El ID debe coincidir");
        assertNotNull(tipoCambio.getTasa_de_cambio(), 
                     "Debe tener tasa de cambio");
        assertNotNull(tipoCambio.getMoneda_origen(), 
                     "Debe tener moneda origen");
        assertNotNull(tipoCambio.getMoneda_destino(), 
                     "Debe tener moneda destino");
        
        System.out.println("✓ Tipo de cambio encontrado");
        System.out.println("  De: " + tipoCambio.getMoneda_origen().getCodigo_iso());
        System.out.println("  A: " + tipoCambio.getMoneda_destino().getCodigo_iso());
        System.out.println("  Tasa: " + tipoCambio.getTasa_de_cambio());
        System.out.println("  Fecha: " + tipoCambio.getFecha());
    }
    
    /**
     * Test 3: Modificar tipo de cambio
     */
    @Test
    @Order(3)
    @DisplayName("Debe modificar un tipo de cambio existente")
    void testModificarTipoDeCambio() {
        System.out.println("Test 3: Modificar Tipo de Cambio");
        
        if (tipoCambioIdTest == null) {
            tipoCambioIdTest = 1;
        }
        
        LocalDateTime fecha = LocalDateTime.now();
        BigDecimal nuevaTasa = new BigDecimal("3.80");
        Integer monedaOrigenId = 1;
        Integer monedaDestinoId = 2;
        
        Integer resultado = tiposCambioBO.modificar(
            tipoCambioIdTest, fecha, nuevaTasa, monedaOrigenId, monedaDestinoId
        );
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La modificación debe ser exitosa");
        
        // Verificar que se modificó
        TiposDeCambioDTO modificado = tiposCambioBO.obtenerPorId(tipoCambioIdTest);
        assertEquals(nuevaTasa, modificado.getTasa_de_cambio(),
                    "La tasa debe haberse actualizado");
        
        System.out.println("✓ Tipo de cambio modificado");
        System.out.println("  Nueva tasa: " + modificado.getTasa_de_cambio());
    }
    
    /**
     * Test 4: Validar que las tasas sean positivas
     */
    @Test
    @Order(4)
    @DisplayName("Debe validar que las tasas de cambio sean positivas")
    void testValidarTasasPositivas() {
        System.out.println("Test 4: Validar Tasas Positivas");
        
        if (tipoCambioIdTest != null) {
            TiposDeCambioDTO tipoCambio = tiposCambioBO.obtenerPorId(tipoCambioIdTest);
            
            if (tipoCambio != null && tipoCambio.getTasa_de_cambio() != null) {
                assertTrue(tipoCambio.getTasa_de_cambio().compareTo(BigDecimal.ZERO) > 0,
                          "La tasa debe ser positiva");
                
                System.out.println("✓ Tasa de cambio es positiva: " + 
                                 tipoCambio.getTasa_de_cambio());
            }
        } else {
            System.out.println("⚠ No hay tipos de cambio para validar");
        }
    }
}