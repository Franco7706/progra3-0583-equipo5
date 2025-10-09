package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para AcreedoresBO
 * RF001: El sistema permitirá registrar, consultar y modificar acreedores,
 * gestionando razón social, RUC, dirección, país, condición y plazo de pago
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AcreedoresBOTest {
    
    private static AcreedoresBO acreedoresBO;
    private static Integer acreedorIdTest;
    private static UsuariosDTO testUsuario;
    
    @BeforeAll
    static void init() {
        acreedoresBO = new AcreedoresBO();
        testUsuario = new UsuariosDTO();
        testUsuario.setUsuario_id(1);
        System.out.println("=== Iniciando Tests de AcreedoresBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de AcreedoresBO ===\n");
    }
    
    /**
     * Test 1: Insertar acreedor válido
     * Verifica RF001 - Registro de acreedores
     */
    @Test
    @Order(1)
    @DisplayName("Debe insertar un acreedor con datos válidos")
    void testInsertarAcreedorExitoso() {
        System.out.println("Test 1: Insertar Acreedor");
        
        String razonSocial = "Tech Solutions SAC";
        String ruc = "20123456789";
        String direccion = "Av. Javier Prado 123, Lima";
        String condicion = "ACTIVO";
        Integer plazoPago = 30;
        String activo = "S";
        Integer idPais = 1; // Perú
        
        Integer resultado = acreedoresBO.insertar(
            razonSocial, ruc, direccion, condicion, plazoPago, activo, idPais
        );
        
        assertNotNull(resultado, "El ID del acreedor no debe ser nulo");
        assertTrue(resultado > 0, "El ID debe ser mayor a 0");
        acreedorIdTest = resultado;
        
        System.out.println("✓ Acreedor insertado con ID: " + resultado);
    }
    
    /**
     * Test 2: Listar todos los acreedores
     * Verifica RF001 - Consulta de acreedores
     */
    @Test
    @Order(2)
    @DisplayName("Debe listar todos los acreedores")
    void testListarTodosLosAcreedores() {
        System.out.println("Test 2: Listar Todos los Acreedores");
        
        ArrayList<AcreedoresDTO> acreedores = acreedoresBO.listarTodos();
        
        assertNotNull(acreedores, "La lista de acreedores no debe ser nula");
        assertFalse(acreedores.isEmpty(), "Debe haber al menos un acreedor");
        
        System.out.println("✓ Total de acreedores: " + acreedores.size());
        
        for (AcreedoresDTO a : acreedores) {
            System.out.println("  - " + a.getRazon_social() + 
                             " | RUC: " + a.getRuc() +
                             " | Activo: " + a.getActivo());
        }
    }
    
    /**
     * Test 3: Obtener acreedor por ID
     * Verifica RF001 - Consulta específica
     */
    @Test
    @Order(3)
    @DisplayName("Debe obtener un acreedor por su ID")
    void testObtenerAcreedorPorId() {
        System.out.println("Test 3: Obtener Acreedor Por ID");
        
        if (acreedorIdTest == null) {
            ArrayList<AcreedoresDTO> acreedores = acreedoresBO.listarTodos();
            if (!acreedores.isEmpty()) {
                acreedorIdTest = acreedores.get(0).getAcreedor_id();
            }
        }
        
        assertNotNull(acreedorIdTest, "Debe haber un ID de acreedor para buscar");
        
        AcreedoresDTO acreedor = acreedoresBO.obtenerPorId(acreedorIdTest);
        
        assertNotNull(acreedor, "El acreedor no debe ser nulo");
        assertEquals(acreedorIdTest, acreedor.getAcreedor_id(), "El ID debe coincidir");
        assertNotNull(acreedor.getRazon_social(), "La razón social no debe ser nula");
        assertNotNull(acreedor.getRuc(), "El RUC no debe ser nulo");
        
        System.out.println("✓ Acreedor encontrado: " + acreedor.getRazon_social());
        System.out.println("  RUC: " + acreedor.getRuc());
        System.out.println("  Dirección: " + acreedor.getDireccion_fiscal());
        System.out.println("  Plazo de pago: " + acreedor.getPlazo_de_pago() + " días");
    }
    
    /**
     * Test 4: Modificar acreedor
     * Verifica RF001 - Modificación de acreedores
     */
    @Test
    @Order(4)
    @DisplayName("Debe modificar un acreedor existente")
    void testModificarAcreedor() {
        System.out.println("Test 4: Modificar Acreedor");
        
        if (acreedorIdTest == null) {
            ArrayList<AcreedoresDTO> acreedores = acreedoresBO.listarTodos();
            if (!acreedores.isEmpty()) {
                acreedorIdTest = acreedores.get(0).getAcreedor_id();
            }
        }
        
        assertNotNull(acreedorIdTest, "Debe haber un ID de acreedor para modificar");
        
        String nuevaRazonSocial = "Tech Solutions SAC - MODIFICADO";
        String nuevoRuc = "20123456789";
        String nuevaDireccion = "Av. Javier Prado 456, Lima";
        String nuevaCondicion = "ACTIVO";
        Integer nuevoPlazoPago = 45;
        String activo = "S";
        Integer idPais = 1;
        
        Integer resultado = acreedoresBO.modificar(
            acreedorIdTest, nuevaRazonSocial, nuevoRuc, nuevaDireccion,
            nuevaCondicion, nuevoPlazoPago, activo, idPais
        );
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La modificación debe ser exitosa");
        
        // Verificar que se modificó
        AcreedoresDTO acreedorModificado = acreedoresBO.obtenerPorId(acreedorIdTest);
        assertEquals(nuevaRazonSocial, acreedorModificado.getRazon_social(),
                    "La razón social debe haberse actualizado");
        assertEquals(nuevoPlazoPago, acreedorModificado.getPlazo_de_pago(),
                    "El plazo de pago debe haberse actualizado");
        
        System.out.println("✓ Acreedor modificado exitosamente");
        System.out.println("  Nueva razón social: " + acreedorModificado.getRazon_social());
        System.out.println("  Nuevo plazo: " + acreedorModificado.getPlazo_de_pago() + " días");
    }
    
    /**
     * Test 5: Filtrar acreedores activos
     * Verifica RF001 - Actualizar estado entre activo e inactivo
     */
    @Test
    @Order(5)
    @DisplayName("Debe filtrar acreedores por estado activo")
    void testFiltrarAcreedoresActivos() {
        System.out.println("Test 5: Filtrar Acreedores Activos");
        
        ArrayList<AcreedoresDTO> todos = acreedoresBO.listarTodos();
        int activos = 0;
        int inactivos = 0;
        
        for (AcreedoresDTO a : todos) {
            if (a.getActivo() != null && a.getActivo()) {
                activos++;
            } else {
                inactivos++;
            }
        }
        
        System.out.println("✓ Total acreedores: " + todos.size());
        System.out.println("  - Activos: " + activos);
        System.out.println("  - Inactivos: " + inactivos);
        
        assertFalse(todos.isEmpty(), "Debe haber acreedores en el sistema");
    }
    
    /**
     * Test 6: Filtrar por país
     * Verifica RF001 - Aplicar filtros por país
     */
    @Test
    @Order(6)
    @DisplayName("Debe filtrar acreedores por país")
    void testFiltrarPorPais() {
        System.out.println("Test 6: Filtrar Acreedores por País");
        
        ArrayList<AcreedoresDTO> todos = acreedoresBO.listarTodos();
        int acreedoresPeru = 0;
        int acreedoresColombia = 0;
        int acreedoresMexico = 0;
        
        for (AcreedoresDTO a : todos) {
            if (a.getPais() != null) {
                switch (a.getPais().getNombre()) {
                    case "Perú":
                        acreedoresPeru++;
                        break;
                    case "Colombia":
                        acreedoresColombia++;
                        break;
                    case "México":
                        acreedoresMexico++;
                        break;
                }
            }
        }
        
        System.out.println("✓ Distribución por país:");
        System.out.println("  - Perú: " + acreedoresPeru);
        System.out.println("  - Colombia: " + acreedoresColombia);
        System.out.println("  - México: " + acreedoresMexico);
    }
    
    /**
     * Test 7: Eliminar acreedor lógicamente
     * Verifica RF001 - Actualizar estado a inactivo
     */
    @Test
    @Order(7)
    @DisplayName("Debe realizar eliminación lógica de acreedor")
    void testEliminarAcreedorLogicamente() {
        System.out.println("Test 7: Eliminar Acreedor Lógicamente");
        
        if (acreedorIdTest == null) {
            ArrayList<AcreedoresDTO> acreedores = acreedoresBO.listarTodos();
            if (!acreedores.isEmpty()) {
                acreedorIdTest = acreedores.get(0).getAcreedor_id();
            }
        }
        
        assertNotNull(acreedorIdTest, "Debe haber un ID de acreedor para eliminar");
        
        AcreedoresDTO acreedor = acreedoresBO.obtenerPorId(acreedorIdTest);
        assertNotNull(acreedor, "El acreedor debe existir");
        
        Integer resultado = acreedoresBO.eliminarLogico(acreedor, testUsuario);
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La eliminación debe ser exitosa");
        
        System.out.println("✓ Acreedor eliminado lógicamente");
    }
    
    /**
     * Test 8: Validar datos obligatorios
     */
    @Test
    @Order(8)
    @DisplayName("Debe rechazar eliminación con datos nulos")
    void testValidarDatosNulos() {
        System.out.println("Test 8: Validar eliminación con acreedor nulo");
        
        assertThrows(IllegalArgumentException.class, () -> {
            acreedoresBO.eliminarLogico(null, null);
        });
        
        System.out.println("✓ Validación de datos nulos funcionando");
    }
}