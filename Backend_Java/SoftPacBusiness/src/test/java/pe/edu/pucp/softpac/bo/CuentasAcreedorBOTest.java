package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.CuentasAcreedorDTO;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para CuentasAcreedorBO
 * RF002: El sistema permitirá registrar, modificar y consultar 
 * las cuentas bancarias de un acreedor
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CuentasAcreedorBOTest {
    
    private static CuentasAcreedorBO cuentasAcreedorBO;
    private static Integer cuentaIdTest;
    private static final Integer acreedorIdTest = 1;
    private static UsuariosDTO testUsuario;
    
    @BeforeAll
    static void init() {
        cuentasAcreedorBO = new CuentasAcreedorBO();
        testUsuario = new UsuariosDTO();
        testUsuario.setUsuario_id(1);
        System.out.println("=== Iniciando Tests de CuentasAcreedorBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de CuentasAcreedorBO ===\n");
    }
    
    /**
     * Test 1: Insertar cuenta de acreedor
     * Verifica RF002 - Registro de cuentas
     */
    @Test
    @Order(1)
    @DisplayName("Debe insertar cuenta de acreedor")
    void testInsertarCuentaAcreedor() {
        System.out.println("Test 1: Insertar Cuenta de Acreedor");
        
        CuentasAcreedorDTO cuenta = new CuentasAcreedorDTO();
        cuenta.setTipo_cuenta("Corriente");
        cuenta.setNumero_cuenta("0011-9876-5432109876");
        cuenta.setCci("00219876543210987654");
        
        // Acreedor
        AcreedoresDTO acreedor = new AcreedoresDTO();
        acreedor.setAcreedor_id(acreedorIdTest);
        cuenta.setAcreedor(acreedor);
        
        // Moneda
        MonedasDTO moneda = new MonedasDTO();
        moneda.setMoneda_id(1); // PEN
        cuenta.setMoneda(moneda);
        
        // Entidad Bancaria
        EntidadesBancariasDTO banco = new EntidadesBancariasDTO();
        banco.setEntidad_bancaria_id(1);
        cuenta.setEntidad_bancaria(banco);
        
        // Código SWIFT
        cuenta.getEntidad_bancaria().setCodigo_swift("BCPLPEPL");
        
        Integer resultado = cuentasAcreedorBO.insertar(cuenta);
        
        assertNotNull(resultado, "El ID de la cuenta no debe ser nulo");
        assertTrue(resultado > 0, "El ID debe ser mayor a 0");
        cuentaIdTest = resultado;
        
        System.out.println("✓ Cuenta de acreedor insertada con ID: " + resultado);
    }
    
    /**
     * Test 2: Listar todas las cuentas de acreedor
     * Verifica RF002 - Consulta de cuentas
     */
    @Test
    @Order(2)
    @DisplayName("Debe listar todas las cuentas de acreedor")
    void testListarTodasLasCuentas() {
        System.out.println("Test 2: Listar Todas las Cuentas");
        
        ArrayList<CuentasAcreedorDTO> cuentas = cuentasAcreedorBO.listarTodos();
        
        assertNotNull(cuentas, "La lista de cuentas no debe ser nula");
        assertFalse(cuentas.isEmpty(), "Debe haber al menos una cuenta");
        
        System.out.println("✓ Total de cuentas de acreedores: " + cuentas.size());
        
        for (CuentasAcreedorDTO c : cuentas) {
            System.out.println("  - Cuenta: " + c.getNumero_cuenta() +
                             " | Acreedor: " + c.getAcreedor().getRazon_social() +
                             " | Tipo: " + c.getTipo_cuenta());
        }
    }
    
    /**
     * Test 3: Obtener cuentas por acreedor
     * Verifica RF002 - Filtrado por acreedor
     */
    @Test
    @Order(3)
    @DisplayName("Debe obtener cuentas por acreedor")
    void testObtenerCuentasPorAcreedor() {
        System.out.println("Test 3: Obtener Cuentas Por Acreedor");
        
        ArrayList<CuentasAcreedorDTO> cuentas = 
            cuentasAcreedorBO.obtenerPorAcreedor(acreedorIdTest);
        
        assertNotNull(cuentas, "La lista no debe ser nula");
        
        // Verificar que todas pertenezcan al acreedor
        for (CuentasAcreedorDTO c : cuentas) {
            assertEquals(acreedorIdTest, c.getAcreedor().getAcreedor_id(),
                        "Todas las cuentas deben ser del mismo acreedor");
        }
        
        System.out.println("✓ Cuentas del acreedor ID " + acreedorIdTest + ": " + 
                          cuentas.size());
        
        for (CuentasAcreedorDTO c : cuentas) {
            System.out.println("  - " + c.getNumero_cuenta() +
                             " | " + c.getMoneda().getCodigo_iso() +
                             " | " + c.getTipo_cuenta());
        }
    }
    
    /**
     * Test 4: Obtener cuenta por ID
     * Verifica RF002 - Consulta específica
     */
    @Test
    @Order(4)
    @DisplayName("Debe obtener cuenta por ID")
    void testObtenerCuentaPorId() {
        System.out.println("Test 4: Obtener Cuenta Por ID");
        
        if (cuentaIdTest == null) {
            ArrayList<CuentasAcreedorDTO> cuentas = cuentasAcreedorBO.listarTodos();
            if (!cuentas.isEmpty()) {
                cuentaIdTest = cuentas.get(0).getCuenta_bancaria_id();
            }
        }
        
        assertNotNull(cuentaIdTest, "Debe haber un ID de cuenta para buscar");
        
        CuentasAcreedorDTO cuenta = cuentasAcreedorBO.obtenerPorId(cuentaIdTest);
        
        assertNotNull(cuenta, "La cuenta no debe ser nula");
        assertEquals(cuentaIdTest, cuenta.getCuenta_bancaria_id(), "El ID debe coincidir");
        assertNotNull(cuenta.getNumero_cuenta(), "Debe tener número de cuenta");
        assertNotNull(cuenta.getAcreedor(), "Debe tener acreedor");
        
        System.out.println("✓ Cuenta encontrada: " + cuenta.getNumero_cuenta());
        System.out.println("  Acreedor: " + cuenta.getAcreedor().getRazon_social());
        System.out.println("  Tipo: " + cuenta.getTipo_cuenta());
        System.out.println("  CCI: " + cuenta.getCci());
        System.out.println("  SWIFT: " + cuenta.getEntidad_bancaria().getCodigo_swift());
    }
    
    /**
     * Test 5: Modificar cuenta de acreedor
     * Verifica RF002 - Modificación de cuentas
     */
    @Test
    @Order(5)
    @DisplayName("Debe modificar cuenta de acreedor")
    void testModificarCuentaAcreedor() {
        System.out.println("Test 5: Modificar Cuenta de Acreedor");
        
        if (cuentaIdTest == null) {
            ArrayList<CuentasAcreedorDTO> cuentas = cuentasAcreedorBO.listarTodos();
            if (!cuentas.isEmpty()) {
                cuentaIdTest = cuentas.get(0).getCuenta_bancaria_id();
            }
        }
        
        assertNotNull(cuentaIdTest, "Debe haber un ID de cuenta para modificar");
        
        CuentasAcreedorDTO cuenta = cuentasAcreedorBO.obtenerPorId(cuentaIdTest);
        assertNotNull(cuenta, "La cuenta debe existir");
        
        // Modificar algunos campos
        cuenta.setTipo_cuenta("Ahorro");
        cuenta.getEntidad_bancaria().setCodigo_swift("BCPLPEPL-MOD");
        
        Integer resultado = cuentasAcreedorBO.modificar(cuenta);
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La modificación debe ser exitosa");
        
        // Verificar que se modificó
        CuentasAcreedorDTO cuentaModificada = cuentasAcreedorBO.obtenerPorId(cuentaIdTest);
        assertEquals("Ahorro", cuentaModificada.getTipo_cuenta(),
                    "El tipo de cuenta debe haberse actualizado");
        
        System.out.println("✓ Cuenta modificada exitosamente");
        System.out.println("  Nuevo tipo: " + cuentaModificada.getTipo_cuenta());
    }
    
    /**
     * Test 6: Validar datos de cuenta
     * Verifica RF002 - Especificación de campos
     */
    @Test
    @Order(6)
    @DisplayName("Debe validar datos obligatorios de cuenta")
    void testValidarDatosCuenta() {
        System.out.println("Test 6: Validar Datos de Cuenta");
        
        ArrayList<CuentasAcreedorDTO> cuentas = cuentasAcreedorBO.listarTodos();
        
        for (CuentasAcreedorDTO c : cuentas) {
            // Validar campos obligatorios según RF002
            assertNotNull(c.getTipo_cuenta(), "El tipo de cuenta es obligatorio");
            assertNotNull(c.getNumero_cuenta(), "El número de cuenta es obligatorio");
            assertNotNull(c.getMoneda(), "La moneda es obligatoria");
            assertNotNull(c.getEntidad_bancaria().getCodigo_swift(), "El código SWIFT es obligatorio");
            assertNotNull(c.getEntidad_bancaria(), "La entidad bancaria es obligatoria");
        }
        
        System.out.println("✓ Todas las cuentas tienen datos válidos");
        System.out.println("  Total validadas: " + cuentas.size());
    }
    
    /**
     * Test 7: Agrupar por moneda
     * Verifica diversidad de monedas en cuentas
     */
    @Test
    @Order(7)
    @DisplayName("Debe agrupar cuentas por moneda")
    void testAgruparPorMoneda() {
        System.out.println("Test 7: Agrupar Cuentas Por Moneda");
        
        ArrayList<CuentasAcreedorDTO> cuentas = cuentasAcreedorBO.listarTodos();
        
        int pen = 0, usd = 0, cop = 0, mxn = 0;
        
        for (CuentasAcreedorDTO c : cuentas) {
            if (c.getMoneda() != null && c.getMoneda().getCodigo_iso() != null) {
                switch (c.getMoneda().getCodigo_iso()) {
                    case "PEN":
                        pen++;
                        break;
                    case "USD":
                        usd++;
                        break;
                    case "COP":
                        cop++;
                        break;
                    case "MXN":
                        mxn++;
                        break;
                }
            }
        }
        
        System.out.println("✓ Distribución por moneda:");
        System.out.println("  - PEN (Soles): " + pen);
        System.out.println("  - USD (Dólares): " + usd);
        System.out.println("  - COP (Pesos colombianos): " + cop);
        System.out.println("  - MXN (Pesos mexicanos): " + mxn);
        
    }
    
    /**
     * Test 8: Eliminar cuenta lógicamente
     * Verifica gestión de cuentas inactivas
     */
    @Test
    @Order(8)
    @DisplayName("Debe eliminar cuenta lógicamente")
    void testEliminarCuentaLogicamente() {
        System.out.println("Test 8: Eliminar Cuenta Lógicamente");
        
        if (cuentaIdTest == null) {
            ArrayList<CuentasAcreedorDTO> cuentas = cuentasAcreedorBO.listarTodos();
            if (!cuentas.isEmpty()) {
                cuentaIdTest = cuentas.get(0).getCuenta_bancaria_id();
            }
        }
        
        assertNotNull(cuentaIdTest, "Debe haber un ID de cuenta para eliminar");
        
        CuentasAcreedorDTO cuenta = cuentasAcreedorBO.obtenerPorId(cuentaIdTest);
        assertNotNull(cuenta, "La cuenta debe existir");
        
        Integer resultado = cuentasAcreedorBO.eliminarLogico(cuenta, testUsuario);
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La eliminación debe ser exitosa");
        
        System.out.println("✓ Cuenta eliminada lógicamente");
    }
}