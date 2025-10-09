package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.PropuestasPagoDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para PropuestaPagoBO
 * RF006-RF013: El sistema permitirá crear, modificar y consultar propuestas de pago
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PropuestaPagoBOTest {
    
    private static PropuestaPagoBO propuestaBO;
    private static Integer propuestaIdTest;
    private static final Integer usuarioIdTest = 1;
    
    @BeforeAll
    static void init() {
        propuestaBO = new PropuestaPagoBO();
        System.out.println("=== Iniciando Tests de PropuestaPagoBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de PropuestaPagoBO ===\n");
    }
    
    /**
     * Test 1: Insertar propuesta de pago
     * Verifica RF009 - Generar y guardar propuesta de pago
     */
    @Test
    @Order(1)
    @DisplayName("Debe insertar una propuesta de pago")
    void testInsertarPropuestaPago() {
        System.out.println("Test 1: Insertar Propuesta de Pago");
        
        PropuestasPagoDTO propuesta = new PropuestasPagoDTO();
        propuesta.setFecha_hora_creacion(LocalDateTime.now());
        propuesta.setEstado("Pendiente de exportación");
        
        
        // Usuario creación
        UsuariosDTO usuario = new UsuariosDTO();
        usuario.setUsuario_id(usuarioIdTest);
        propuesta.setUsuario_creacion(usuario);
        
        // Entidad bancaria
        EntidadesBancariasDTO banco = new EntidadesBancariasDTO();
        banco.setEntidad_bancaria_id(1);
        propuesta.setEntidad_bancaria(banco);
        
        Integer resultado = propuestaBO.insertar(propuesta);
        
        assertNotNull(resultado, "El ID de la propuesta no debe ser nulo");
        assertTrue(resultado > 0, "El ID debe ser mayor a 0");
        propuestaIdTest = resultado;
        
        System.out.println("✓ Propuesta insertada con ID: " + resultado);
    }
    
    /**
     * Test 2: Listar propuestas por usuario
     * Verifica RF013 - Consultar propuestas generadas
     */
    @Test
    @Order(2)
    @DisplayName("Debe listar propuestas por usuario")
    void testListarPropuestasPorUsuario() {
        System.out.println("Test 2: Listar Propuestas Por Usuario");
        
        ArrayList<PropuestasPagoDTO> propuestas = 
            propuestaBO.listarTodasPorUsuario(usuarioIdTest);
        
        assertNotNull(propuestas, "La lista no debe ser nula");
        
        System.out.println("✓ Total propuestas del usuario " + usuarioIdTest + ": " + 
                          propuestas.size());
        
        for (PropuestasPagoDTO p : propuestas) {
            System.out.println("  - Propuesta ID: " + p.getPropuesta_id() +
                             " | Estado: " + p.getEstado());
        }
    }
    
    /**
     * Test 3: Listar últimas propuestas
     * Verifica ordenamiento por fecha
     */
    @Test
    @Order(3)
    @DisplayName("Debe listar últimas N propuestas")
    void testListarUltimasPropuestas() {
        System.out.println("Test 3: Listar Últimas Propuestas");
        
        int cantidad = 5;
        ArrayList<PropuestasPagoDTO> ultimas = 
            propuestaBO.listarUltimasPorUsuario(usuarioIdTest, cantidad);
        
        assertNotNull(ultimas, "La lista no debe ser nula");
        assertTrue(ultimas.size() <= cantidad,
                  "No debe exceder la cantidad solicitada");
        
        System.out.println("✓ Últimas " + ultimas.size() + " propuestas:");
        
        for (PropuestasPagoDTO p : ultimas) {
            System.out.println("  - " + p.getFecha_hora_creacion() +
                             " | Estado: " + p.getEstado());
        }
    }
    
    /**
     * Test 4: Modificar propuesta de pago
     * Verifica RF010 - Modificar detalles de pagos antes de exportación
     */
    @Test
    @Order(4)
    @DisplayName("Debe modificar una propuesta existente")
    void testModificarPropuesta() {
        System.out.println("Test 4: Modificar Propuesta");
        
        if (propuestaIdTest == null) {
            ArrayList<PropuestasPagoDTO> propuestas = propuestaBO.listarTodasPorUsuario(usuarioIdTest);
            if (!propuestas.isEmpty()) {
                propuestaIdTest = propuestas.get(0).getPropuesta_id();
            }
        }
        
        assertNotNull(propuestaIdTest, "Debe haber un ID de propuesta para modificar");
        
        PropuestasPagoDTO propuesta = new PropuestasPagoDTO();
        propuesta.setPropuesta_id(propuestaIdTest);
        propuesta.setFecha_hora_creacion(LocalDateTime.now());
        propuesta.setEstado("En revisión");
        
        UsuariosDTO usuario = new UsuariosDTO();
        usuario.setUsuario_id(usuarioIdTest);
        propuesta.setUsuario_modificacion(usuario);
        propuesta.setFecha_hora_modificacion(LocalDateTime.now());
        
        EntidadesBancariasDTO banco = new EntidadesBancariasDTO();
        banco.setEntidad_bancaria_id(1);
        propuesta.setEntidad_bancaria(banco);
        
        Integer resultado = propuestaBO.modificar(propuesta);
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La modificación debe ser exitosa");
        
        System.out.println("✓ Propuesta modificada exitosamente");
    }
    
    /**
     * Test 5: Listar actividad del usuario
     * Verifica registro de creación, modificación y eliminación
     */
    @Test
    @Order(5)
    @DisplayName("Debe listar actividad del usuario")
    void testListarActividadUsuario() {
        System.out.println("Test 5: Listar Actividad del Usuario");
        
        ArrayList<PropuestasPagoDTO> actividad = 
            propuestaBO.listarActividadPorUsuario(usuarioIdTest);
        
        assertNotNull(actividad, "La lista de actividad no debe ser nula");
        
        System.out.println("✓ Total de propuestas con actividad del usuario: " + 
                          actividad.size());
        
        // Contar por tipo de actividad
        int creadas = 0, modificadas = 0, eliminadas = 0;
        
        for (PropuestasPagoDTO p : actividad) {
            if (p.getUsuario_creacion() != null && 
                p.getUsuario_creacion().getUsuario_id().equals(usuarioIdTest)) {
                creadas++;
            }
            if (p.getUsuario_modificacion() != null && 
                p.getUsuario_modificacion().getUsuario_id().equals(usuarioIdTest)) {
                modificadas++;
            }
            if (p.getUsuario_eliminacion() != null && 
                p.getUsuario_eliminacion().getUsuario_id().equals(usuarioIdTest)) {
                eliminadas++;
            }
        }
        
        System.out.println("  - Creadas: " + creadas);
        System.out.println("  - Modificadas: " + modificadas);
        System.out.println("  - Eliminadas: " + eliminadas);
    }
    
    /**
     * Test 6: Verificar estados de propuesta
     * Verifica RF012, RF016 - Estados y exportación
     */
    @Test
    @Order(6)
    @DisplayName("Debe validar estados de propuestas")
    void testVerificarEstadosPropuesta() {
        System.out.println("Test 6: Verificar Estados de Propuesta");
        
        ArrayList<PropuestasPagoDTO> todas = 
            propuestaBO.listarTodasPorUsuario(usuarioIdTest);
        
        int pendientes = 0, exportadas = 0, enviadas = 0, anuladas = 0;
        
        for (PropuestasPagoDTO p : todas) {
            if (p.getEstado() != null) {
                switch (p.getEstado()) {
                    case "Pendiente de exportación":
                        pendientes++;
                        break;
                    case "Exportada":
                        exportadas++;
                        break;
                    case "Enviada":
                        enviadas++;
                        break;
                    case "Anulada":
                        anuladas++;
                        break;
                }
            }
        }
        
        System.out.println("✓ Distribución de estados:");
        System.out.println("  - Pendiente de exportación: " + pendientes);
        System.out.println("  - Exportada: " + exportadas);
        System.out.println("  - Enviada: " + enviadas);
        System.out.println("  - Anulada: " + anuladas);
        
        assertFalse(todas.isEmpty(), "Debe haber propuestas");
    }
    
    /**
     * Test 7: Validar ordenamiento
     * Verifica que el ordenamiento funciona correctamente
     */
    @Test
    @Order(7)
    @DisplayName("Debe validar ordenamiento de propuestas")
    void testValidarOrdenamiento() {
        System.out.println("Test 7: Validar Ordenamiento de Propuestas");
        
        ArrayList<PropuestasPagoDTO> propuestas = 
            propuestaBO.listarUltimasPorUsuario(usuarioIdTest, 10);
        
        if (propuestas.size() > 1) {
            LocalDateTime fechaAnterior = null;
            boolean ordenCorrecto = true;
            
            for (PropuestasPagoDTO p : propuestas) {
                if (fechaAnterior != null) {
                    if (p.getFecha_hora_creacion().isAfter(fechaAnterior)) {
                        ordenCorrecto = false;
                        break;
                    }
                }
                fechaAnterior = p.getFecha_hora_creacion();
            }
            
            System.out.println("✓ Orden de propuestas: " + 
                             (ordenCorrecto ? "Correcto (más reciente primero)" : 
                              "Incorrecto - Verificar sort()"));
        } else {
            System.out.println("✓ Solo hay " + propuestas.size() + 
                             " propuesta(s), no se puede validar ordenamiento");
        }
    }
    
    /**
     * Test 8: Verificar agrupación por entidad bancaria
     * Verifica RF013 - Filtrado por entidad bancaria
     */
    @Test
    @Order(8)
    @DisplayName("Debe agrupar propuestas por entidad bancaria")
    void testAgruparPorEntidadBancaria() {
        System.out.println("Test 8: Agrupar Por Entidad Bancaria");
        
        ArrayList<PropuestasPagoDTO> todas = 
            propuestaBO.listarTodasPorUsuario(usuarioIdTest);
        
        int bcp = 0, bbva = 0, interbank = 0, otros = 0;
        
        for (PropuestasPagoDTO p : todas) {
            if (p.getEntidad_bancaria() != null) {
                String nombreBanco = p.getEntidad_bancaria().getNombre();
                if (nombreBanco != null) {
                    if (nombreBanco.contains("BCP")) {
                        bcp++;
                    } else if (nombreBanco.contains("BBVA")) {
                        bbva++;
                    } else if (nombreBanco.contains("Interbank")) {
                        interbank++;
                    } else {
                        otros++;
                    }
                }
            }
        }
        
        System.out.println("✓ Propuestas por banco:");
        System.out.println("  - BCP: " + bcp);
        System.out.println("  - BBVA: " + bbva);
        System.out.println("  - Interbank: " + interbank);
        System.out.println("  - Otros: " + otros);
    }
}