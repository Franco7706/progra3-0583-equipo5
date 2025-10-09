package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.FacturasDTO;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para FacturasBO
 * RF005: El sistema permitirá registrar facturas con acreedor, monto, moneda, 
 * fechas y motivo; modificarlas y eliminarlas; y consultarlas con filtros
 * RF008: Visualizar facturas pendientes de pago
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FacturasBOTest {
    
    private static FacturasBO facturasBO;
    private static Integer facturaIdTest;
    private static UsuariosDTO testUsuario;
    
    @BeforeAll
    static void init() {
        facturasBO = new FacturasBO();
        testUsuario = new UsuariosDTO();
        testUsuario.setUsuario_id(1);
        System.out.println("=== Iniciando Tests de FacturasBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de FacturasBO ===\n");
    }
    
    /**
     * Test 1: Insertar factura válida
     * Verifica RF005 - Registro de facturas
     */
    @Test
    @Order(1)
    @DisplayName("Debe insertar una factura con datos válidos")
    void testInsertarFacturaExitosa() {
        System.out.println("Test 1: Insertar Factura");
        
        FacturasDTO factura = new FacturasDTO();
        factura.setNumero_factura("F001-00001234");
        factura.setMonto_total(new BigDecimal("5500.00"));
        factura.setFecha_emision(LocalDateTime.now());
        factura.setFecha_limite_pago(LocalDateTime.now().plusDays(30));
        
        factura.setEstado("Pendiente");
        
        // Configurar acreedor
        AcreedoresDTO acreedor = new AcreedoresDTO();
        acreedor.setAcreedor_id(1);
        factura.setAcreedor(acreedor);
        
        // Configurar moneda
        MonedasDTO moneda = new MonedasDTO();
        moneda.setMoneda_id(1); // PEN
        factura.setMoneda(moneda);
        
        Integer resultado = facturasBO.insertar(factura);
        
        assertNotNull(resultado, "El ID de la factura no debe ser nulo");
        assertTrue(resultado > 0, "El ID debe ser mayor a 0");
        facturaIdTest = resultado;
        
        System.out.println("✓ Factura insertada con ID: " + resultado);
    }
    
    /**
     * Test 2: Listar todas las facturas
     * Verifica RF005 - Consulta de facturas
     */
    @Test
    @Order(2)
    @DisplayName("Debe listar todas las facturas")
    void testListarTodasLasFacturas() {
        System.out.println("Test 2: Listar Todas las Facturas");
        
        ArrayList<FacturasDTO> facturas = facturasBO.listarTodos();
        
        assertNotNull(facturas, "La lista de facturas no debe ser nula");
        assertFalse(facturas.isEmpty(), "Debe haber al menos una factura");
        
        System.out.println("✓ Total de facturas: " + facturas.size());
        
        for (FacturasDTO f : facturas) {
            System.out.println("  - Factura: " + f.getNumero_factura() + 
                             " | Monto: " + f.getMonto_total() +
                             " | Estado: " + f.getEstado());
        }
    }
    
    /**
     * Test 3: Listar facturas pendientes
     * Verifica RF008 - Facturas pendientes de pago
     */
    @Test
    @Order(3)
    @DisplayName("Debe listar solo facturas pendientes")
    void testListarFacturasPendientes() {
        System.out.println("Test 3: Listar Facturas Pendientes");
        
        ArrayList<FacturasDTO> pendientes = facturasBO.listarPendientes();
        
        assertNotNull(pendientes, "La lista de facturas pendientes no debe ser nula");
        
        // Verificar que todas estén en estado "Pendiente"
        for (FacturasDTO f : pendientes) {
            assertEquals("Pendiente", f.getEstado(),
                        "Todas las facturas deben estar en estado Pendiente");
        }
        
        System.out.println("✓ Total de facturas pendientes: " + pendientes.size());
    }
    
    /**
     * Test 4: Modificar factura
     * Verifica RF005 - Modificación de facturas
     */
    @Test
    @Order(4)
    @DisplayName("Debe modificar una factura existente")
    void testModificarFactura() {
        System.out.println("Test 4: Modificar Factura");
        
        if (facturaIdTest == null) {
            ArrayList<FacturasDTO> facturas = facturasBO.listarTodos();
            if (!facturas.isEmpty()) {
                facturaIdTest = facturas.get(0).getFactura_id();
            }
        }
        
        assertNotNull(facturaIdTest, "Debe haber un ID de factura para modificar");
        
        FacturasDTO factura = new FacturasDTO();
        factura.setFactura_id(facturaIdTest);
        factura.setNumero_factura("F001-00001234-MOD");
        factura.setMonto_total(new BigDecimal("6000.00"));
        factura.setFecha_emision(LocalDateTime.now());
        factura.setFecha_limite_pago(LocalDateTime.now().plusDays(30));
        
        factura.setEstado("Pendiente");
        
        AcreedoresDTO acreedor = new AcreedoresDTO();
        acreedor.setAcreedor_id(1);
        factura.setAcreedor(acreedor);
        
        MonedasDTO moneda = new MonedasDTO();
        moneda.setMoneda_id(1);
        factura.setMoneda(moneda);
        
        Integer resultado = facturasBO.modificar(factura);
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La modificación debe ser exitosa");
        
        System.out.println("✓ Factura modificada exitosamente");
    }
    
    /**
     * Test 5: Eliminar factura lógicamente
     * Verifica RF005 - Eliminación de facturas
     */
    @Test
    @Order(5)
    @DisplayName("Debe realizar eliminación lógica de factura")
    void testEliminarFacturaLogicamente() {
        System.out.println("Test 5: Eliminar Factura Lógicamente");
        
        if (facturaIdTest == null) {
            ArrayList<FacturasDTO> facturas = facturasBO.listarTodos();
            if (!facturas.isEmpty()) {
                facturaIdTest = facturas.get(0).getFactura_id();
            }
        }
        
        assertNotNull(facturaIdTest, "Debe haber un ID de factura para eliminar");
        
        FacturasDTO factura = new FacturasDTO();
        factura.setFactura_id(facturaIdTest);
        
        Integer resultado = facturasBO.eliminarLogico(factura, testUsuario);
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La eliminación debe ser exitosa");
        
        System.out.println("✓ Factura eliminada lógicamente");
    }
    
    /**
     * Test 6: Verificar filtrado por estado
     * Verifica RF005 - Consulta con filtros
     */
    @Test
    @Order(6)
    @DisplayName("Debe filtrar facturas correctamente por estado")
    void testVerificarFiltradoPorEstado() {
        System.out.println("Test 6: Verificar Filtrado por Estado");
        
        ArrayList<FacturasDTO> todas = facturasBO.listarTodos();
        ArrayList<FacturasDTO> pendientes = facturasBO.listarPendientes();
        
        assertFalse(todas.isEmpty(), "Debe haber facturas en total");
        
        // Las pendientes deben ser menor o igual al total
        assertTrue(pendientes.size() <= todas.size(),
                  "Las pendientes deben ser <= al total");
        
        System.out.println("✓ Total facturas: " + todas.size());
        System.out.println("✓ Facturas pendientes: " + pendientes.size());
        System.out.println("✓ Otras facturas: " + (todas.size() - pendientes.size()));
    }
    
    /**
     * Test 7: Verificar facturas por fecha de vencimiento
     * Verifica RF008 - Filtrado por plazo de vencimiento
     */
    @Test
    @Order(7)
    @DisplayName("Debe filtrar facturas por fecha de vencimiento")
    void testVerificarFacturasPorVencimiento() {
        System.out.println("Test 7: Verificar Facturas por Vencimiento");
        
        ArrayList<FacturasDTO> pendientes = facturasBO.listarPendientes();
        LocalDateTime fechaLimite = LocalDateTime.now().plusDays(30);
        
        int facturasProximas = 0;
        for (FacturasDTO f : pendientes) {
            if (f.getFecha_limite_pago() != null && 
                f.getFecha_limite_pago().isBefore(fechaLimite)) {
                facturasProximas++;
                System.out.println("  - Factura " + f.getNumero_factura() + 
                                 " vence el " + f.getFecha_limite_pago());
            }
        }
        
        System.out.println("✓ Facturas que vencen en 30 días: " + facturasProximas);
    }
}