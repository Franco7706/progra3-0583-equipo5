package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para CuentasPropiasBO
 * RF004: El sistema permitirá al administrador el registro, modificación y consulta 
 * de las cuentas bancarias propias de la empresa
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CuentasPropiasBOTest {
    
    private static CuentasPropiasBO cuentasPropiasBO;
    private static Integer cuentaIdTest;
    private static UsuariosDTO testUsuario;
    
    @BeforeAll
    static void init() {
        cuentasPropiasBO = new CuentasPropiasBO();
        
        // Usuario para pruebas de eliminación
        testUsuario = new UsuariosDTO();
        testUsuario.setUsuario_id(1);
        
        System.out.println("=== Iniciando Tests de CuentasPropiasBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de CuentasPropiasBO ===\n");
    }
    
    @BeforeEach
    void setUp() {
        System.out.println("Preparando test...");
    }
    
    @AfterEach
    void tearDown() {
        System.out.println("Test completado.\n");
    }
    
    /**
     * Test 1: Insertar cuenta propia válida
     * Verifica RF004 - Registro de cuentas propias
     */
    @Test
    @Order(1)
    @DisplayName("Debe insertar una cuenta propia con datos válidos")
    void testInsertarCuentaPropiaExitoso() {
        System.out.println("Test 1: Insertar Cuenta Propia");
        
        BigDecimal saldo = new BigDecimal("50000.00");
        String tipoCuenta = "Corriente";
        String numeroCuenta = "0011-0123-4567890123";
        String cci = "00211012345678901234";
        String activa = "S";
        Integer entidadBancariaId = 1; // BCP
        Integer monedaId = 1; // PEN
        
        Integer resultado = cuentasPropiasBO.insertar(
            saldo, tipoCuenta, numeroCuenta, cci, activa, entidadBancariaId, monedaId
        );
        
        assertNotNull(resultado, "El ID de la cuenta insertada no debe ser nulo");
        assertTrue(resultado > 0, "El ID debe ser mayor a 0");
        cuentaIdTest = resultado;
        
        System.out.println("✓ Cuenta propia insertada con ID: " + resultado);
    }
    
    /**
     * Test 2: Validar inserción con saldo negativo
     * Verifica validaciones de negocio
     */
    @Test
    @Order(2)
    @DisplayName("Debe rechazar inserción con saldo negativo")
    void testInsertarConSaldoNegativo() {
        System.out.println("Test 2: Intentar insertar con saldo negativo");
        
        BigDecimal saldoNegativo = new BigDecimal("-1000.00");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cuentasPropiasBO.insertar(
                saldoNegativo, "Corriente", "001-123", "CCI123", "S", 1, 1
            );
        });
        
        assertTrue(exception.getMessage().contains("saldo"),
                  "El mensaje debe mencionar el saldo");
        
        System.out.println("✓ Validación de saldo negativo funcionando correctamente");
    }
    
    /**
     * Test 3: Validar inserción con datos nulos
     */
    @Test
    @Order(3)
    @DisplayName("Debe rechazar inserción con datos nulos")
    void testInsertarConDatosNulos() {
        System.out.println("Test 3: Intentar insertar con datos nulos");
        
        assertThrows(IllegalArgumentException.class, () -> {
            cuentasPropiasBO.insertar(null, null, null, null, "S", null, null);
        });
        
        System.out.println("✓ Validación de datos nulos funcionando correctamente");
    }
    
    /**
     * Test 4: Listar todas las cuentas propias
     * Verifica RF004 - Consulta de cuentas propias
     */
    @Test
    @Order(4)
    @DisplayName("Debe listar todas las cuentas propias")
    void testListarTodasLasCuentas() {
        System.out.println("Test 4: Listar Todas las Cuentas");
        
        ArrayList<CuentasPropiasDTO> cuentas = cuentasPropiasBO.listarTodos();
        
        assertNotNull(cuentas, "La lista de cuentas no debe ser nula");
        assertFalse(cuentas.isEmpty(), "Debe haber al menos una cuenta");
        
        System.out.println("✓ Total de cuentas encontradas: " + cuentas.size());
        
        // Mostrar detalles de cada cuenta
        for (CuentasPropiasDTO cuenta : cuentas) {
            System.out.println("  - Cuenta: " + cuenta.getNumero_cuenta() + 
                             " | Saldo: " + cuenta.getSaldo_disponible() +
                             " | Activa: " + cuenta.getActiva());
        }
    }
    
    /**
     * Test 5: Listar solo cuentas activas
     * Verifica RF004 - Filtrado por estado
     */
    @Test
    @Order(5)
    @DisplayName("Debe listar solo cuentas activas")
    void testListarCuentasActivas() {
        System.out.println("Test 5: Listar Solo Cuentas Activas");
        
        ArrayList<CuentasPropiasDTO> cuentasActivas = cuentasPropiasBO.listarActivas();
        
        assertNotNull(cuentasActivas, "La lista de cuentas activas no debe ser nula");
        
        // Verificar que todas estén activas
        for (CuentasPropiasDTO cuenta : cuentasActivas) {
            assertTrue(cuenta.getActiva(), "Todas las cuentas deben estar activas");
        }
        
        System.out.println("✓ Total de cuentas activas: " + cuentasActivas.size());
    }
    
    /**
     * Test 6: Obtener cuenta por ID
     * Verifica RF004 - Consulta específica
     */
    @Test
    @Order(6)
    @DisplayName("Debe obtener una cuenta por su ID")
    void testObtenerCuentaPorId() {
        System.out.println("Test 6: Obtener Cuenta Por ID");
        
        if (cuentaIdTest == null) {
            // Usar una cuenta existente
            ArrayList<CuentasPropiasDTO> cuentas = cuentasPropiasBO.listarTodos();
            if (!cuentas.isEmpty()) {
                cuentaIdTest = cuentas.get(0).getCuenta_bancaria_id();
            }
        }
        
        assertNotNull(cuentaIdTest, "Debe haber un ID de cuenta para probar");
        
        CuentasPropiasDTO cuenta = cuentasPropiasBO.obtenerPorId(cuentaIdTest);
        
        assertNotNull(cuenta, "La cuenta no debe ser nula");
        assertEquals(cuentaIdTest, cuenta.getCuenta_bancaria_id(), "El ID debe coincidir");
        assertNotNull(cuenta.getNumero_cuenta(), "El número de cuenta no debe ser nulo");
        
        System.out.println("✓ Cuenta encontrada: " + cuenta.getNumero_cuenta());
        System.out.println("  Tipo: " + cuenta.getTipo_cuenta());
        System.out.println("  Saldo: " + cuenta.getSaldo_disponible());
        System.out.println("  Moneda: " + cuenta.getMoneda().getCodigo_iso());
    }
    
    /**
     * Test 7: Modificar cuenta propia
     * Verifica RF004 - Modificación de cuentas propias
     */
    @Test
    @Order(7)
    @DisplayName("Debe modificar una cuenta propia existente")
    void testModificarCuentaPropia() {
        System.out.println("Test 7: Modificar Cuenta Propia");
        
        if (cuentaIdTest == null) {
            ArrayList<CuentasPropiasDTO> cuentas = cuentasPropiasBO.listarTodos();
            if (!cuentas.isEmpty()) {
                cuentaIdTest = cuentas.get(0).getCuenta_bancaria_id();
            }
        }
        
        assertNotNull(cuentaIdTest, "Debe haber un ID de cuenta para modificar");
        
        BigDecimal nuevoSaldo = new BigDecimal("75000.50");
        String nuevoTipo = "Ahorro";
        
        Integer resultado = cuentasPropiasBO.modificar(
            cuentaIdTest, nuevoSaldo, nuevoTipo, "0011-0123-4567890123",
            "00211012345678901234", "S", 1, 1
        );
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La modificación debe ser exitosa");
        
        // Verificar que se modificó
        CuentasPropiasDTO cuentaModificada = cuentasPropiasBO.obtenerPorId(cuentaIdTest);
        assertEquals(nuevoSaldo, cuentaModificada.getSaldo_disponible(), 
                    "El saldo debe haberse actualizado");
        
        System.out.println("✓ Cuenta modificada exitosamente");
        System.out.println("  Nuevo saldo: " + cuentaModificada.getSaldo_disponible());
    }
    
    /**
     * Test 8: Verificar saldo suficiente
     * Verifica RF011 - Validación de saldo
     */
    @Test
    @Order(8)
    @DisplayName("Debe validar saldo suficiente correctamente")
    void testVerificarSaldoSuficiente() {
        System.out.println("Test 8: Verificar Saldo Suficiente");
        
        if (cuentaIdTest == null) {
            ArrayList<CuentasPropiasDTO> cuentas = cuentasPropiasBO.listarTodos();
            if (!cuentas.isEmpty()) {
                cuentaIdTest = cuentas.get(0).getCuenta_bancaria_id();
            }
        }
        
        assertNotNull(cuentaIdTest, "Debe haber un ID de cuenta para validar");
        
        CuentasPropiasDTO cuenta = cuentasPropiasBO.obtenerPorId(cuentaIdTest);
        BigDecimal saldoActual = cuenta.getSaldo_disponible();
        
        // Monto menor al saldo
        BigDecimal montoMenor = saldoActual.subtract(new BigDecimal("1000"));
        boolean tieneSaldo1 = cuentasPropiasBO.tieneSaldoSuficiente(cuentaIdTest, montoMenor);
        assertTrue(tieneSaldo1, "Debe tener saldo suficiente");
        
        // Monto mayor al saldo
        BigDecimal montoMayor = saldoActual.add(new BigDecimal("1000"));
        boolean tieneSaldo2 = cuentasPropiasBO.tieneSaldoSuficiente(cuentaIdTest, montoMayor);
        assertFalse(tieneSaldo2, "No debe tener saldo suficiente");
        
        System.out.println("✓ Validación de saldo funcionando correctamente");
        System.out.println("  Saldo actual: " + saldoActual);
        System.out.println("  Validación con monto menor: " + tieneSaldo1);
        System.out.println("  Validación con monto mayor: " + tieneSaldo2);
    }
    
    /**
     * Test 9: Eliminar cuenta lógicamente
     * Verifica RF004 - Estado inactivo
     */
    @Test
    @Order(9)
    @DisplayName("Debe realizar eliminación lógica de cuenta")
    void testEliminarCuentaLogicamente() {
        System.out.println("Test 9: Eliminar Cuenta Lógicamente");
        
        if (cuentaIdTest == null) {
            ArrayList<CuentasPropiasDTO> cuentas = cuentasPropiasBO.listarTodos();
            if (!cuentas.isEmpty()) {
                cuentaIdTest = cuentas.get(0).getCuenta_bancaria_id();
            }
        }
        
        assertNotNull(cuentaIdTest, "Debe haber un ID de cuenta para eliminar");
        
        CuentasPropiasDTO cuenta = cuentasPropiasBO.obtenerPorId(cuentaIdTest);
        
        Integer resultado = cuentasPropiasBO.eliminarLogico(cuenta, testUsuario);
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La eliminación debe ser exitosa");
        
        System.out.println("✓ Cuenta eliminada lógicamente");
    }
    
    /**
     * Test 10: Validar eliminación con datos nulos
     */
    @Test
    @Order(10)
    @DisplayName("Debe rechazar eliminación con datos nulos")
    void testEliminarConDatosNulos() {
        System.out.println("Test 10: Intentar eliminar con datos nulos");
        
        assertThrows(IllegalArgumentException.class, () -> {
            cuentasPropiasBO.eliminarLogico(null, null);
        });
        
        System.out.println("✓ Validación de eliminación con datos nulos funcionando");
    }
}