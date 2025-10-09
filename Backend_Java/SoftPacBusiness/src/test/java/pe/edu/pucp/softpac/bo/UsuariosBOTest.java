package pe.edu.pucp.softpac.bo;

import org.junit.jupiter.api.*;
import pe.edu.pucp.softpac.model.UsuariosDTO;
import pe.edu.pucp.softpac.db.util.Cifrado;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para UsuariosBO
 * RF017: El sistema permitirá a los usuarios iniciar sesión
 * RF019-RF021: Gestión de usuarios y permisos
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuariosBOTest {
    
    private static UsuariosBO usuariosBO;
    private static Integer usuarioIdTest;
    
    @BeforeAll
    static void init() {
        usuariosBO = new UsuariosBO();
        System.out.println("=== Iniciando Tests de UsuariosBO ===");
    }
    
    @AfterAll
    static void cleanup() {
        System.out.println("=== Finalizando Tests de UsuariosBO ===\n");
    }
    
    /**
     * Test 1: Autenticar usuario válido
     * Verifica RF017 - Iniciar sesión
     */
    @Test
    @Order(1)
    @DisplayName("Debe autenticar usuario con credenciales válidas")
    void testAutenticarUsuarioExitoso() {
        System.out.println("Test 1: Autenticar Usuario");
        
        String correo = "admin@softpac.com";
        String password = "admin123"; // Contraseña en texto plano
        
        UsuariosDTO usuario = usuariosBO.autenticarUsuario(correo, password);
        
        assertNotNull(usuario, "El usuario autenticado no debe ser nulo");
        assertEquals(correo, usuario.getCorreo_electronico(), "El correo debe coincidir");
        assertNotNull(usuario.getUsuario_id(), "Debe tener un ID");
        
        System.out.println("✓ Usuario autenticado: " + usuario.getNombre_de_usuario());
        System.out.println("  ID: " + usuario.getUsuario_id());
        System.out.println("  ¿Es superusuario?: " + (usuario.getSuperusuario()? "SI": "NO"));
    }
    
    /**
     * Test 2: Autenticar con contraseña incorrecta
     * Verifica RF017 - Validación de credenciales
     */
    @Test
    @Order(2)
    @DisplayName("Debe rechazar autenticación con contraseña incorrecta")
    void testAutenticarConPasswordIncorrecta() {
        System.out.println("Test 2: Autenticar con Contraseña Incorrecta");
        
        String correo = "admin@softpac.com";
        String passwordIncorrecta = "password_incorrecta";
        
        UsuariosDTO usuario = usuariosBO.autenticarUsuario(correo, passwordIncorrecta);
        
        assertNull(usuario, "El usuario debe ser nulo con contraseña incorrecta");
        
        System.out.println("✓ Autenticación rechazada correctamente");
    }
    
    /**
     * Test 3: Autenticar con usuario inexistente
     * Verifica RF017 - Validación de usuario
     */
    @Test
    @Order(3)
    @DisplayName("Debe rechazar usuario inexistente")
    void testAutenticarUsuarioInexistente() {
        System.out.println("Test 3: Autenticar Usuario Inexistente");
        
        String correoInexistente = "usuario_falso@softpac.com";
        String password = "cualquier_password";
        
        UsuariosDTO usuario = usuariosBO.autenticarUsuario(correoInexistente, password);
        
        assertNull(usuario, "El usuario debe ser nulo si no existe");
        
        System.out.println("✓ Usuario inexistente manejado correctamente");
    }
    
    /**
     * Test 4: Listar todos los usuarios
     * Verifica RF019 - Gestión de usuarios
     */
    @Test
    @Order(4)
    @DisplayName("Debe listar todos los usuarios")
    void testListarTodosLosUsuarios() {
        System.out.println("Test 4: Listar Todos los Usuarios");
        
        ArrayList<UsuariosDTO> usuarios = usuariosBO.listarTodos();
        
        assertNotNull(usuarios, "La lista de usuarios no debe ser nula");
        assertFalse(usuarios.isEmpty(), "Debe haber al menos un usuario");
        
        System.out.println("✓ Total de usuarios: " + usuarios.size());
        
        for (UsuariosDTO u : usuarios) {
            System.out.println("  - " + u.getNombre_de_usuario() + 
                             " | " + u.getCorreo_electronico() +
                             " | Rol: " + (u.getSuperusuario()? "SI":"NO")+
                             " | Activo: " + u.getActivo());
        }
    }
    
    /**
     * Test 5: Obtener usuario por ID
     * Verifica RF019 - Consulta de usuario
     */
    @Test
    @Order(5)
    @DisplayName("Debe obtener usuario por ID")
    void testObtenerUsuarioPorId() {
        System.out.println("Test 5: Obtener Usuario Por ID");
        
        Integer usuarioId = 1; // Superusuario
        
        UsuariosDTO usuario = usuariosBO.obtenerPorId(usuarioId);
        
        assertNotNull(usuario, "El usuario no debe ser nulo");
        assertEquals(usuarioId, usuario.getUsuario_id(), "El ID debe coincidir");
        assertNotNull(usuario.getNombre_de_usuario(), "Debe tener nombre de usuario");
        
        System.out.println("✓ Usuario encontrado: " + usuario.getNombre_de_usuario());
        System.out.println("  Correo: " + usuario.getCorreo_electronico());
        System.out.println("  ¿Es superusuario?: " + (usuario.getSuperusuario()? "SI": "NO"));
        System.out.println("  Activo: " + usuario.getActivo());
    }
    
    /**
     * Test 6: Insertar nuevo usuario
     * Verifica RF020 - Registro de usuarios
     */
    @Test
    @Order(6)
    @DisplayName("Debe insertar nuevo usuario")
    void testInsertarNuevoUsuario() {
        System.out.println("Test 6: Insertar Nuevo Usuario");
        
        UsuariosDTO nuevoUsuario = new UsuariosDTO();
        nuevoUsuario.setNombre_de_usuario("usuario_test");
        nuevoUsuario.setCorreo_electronico("test@softpac.com");
        nuevoUsuario.setPassword_hash(Cifrado.cifrarMD5("test123"));
        nuevoUsuario.setSuperusuario(false);
        nuevoUsuario.setActivo(true);
        
        Integer resultado = usuariosBO.insertarUsuario(nuevoUsuario);
        
        assertNotNull(resultado, "El ID del usuario insertado no debe ser nulo");
        assertTrue(resultado > 0, "El ID debe ser mayor a 0");
        usuarioIdTest = resultado;
        
        System.out.println("✓ Usuario insertado con ID: " + resultado);
    }
    
    /**
     * Test 7: Modificar acceso de usuario
     * Verifica RF020 - Asignar y modificar permisos
     */
    @Test
    @Order(7)
    @DisplayName("Debe modificar acceso de usuario")
    void testModificarAccesoUsuario() {
        System.out.println("Test 7: Modificar Acceso de Usuario");
        
        if (usuarioIdTest == null) {
            ArrayList<UsuariosDTO> usuarios = usuariosBO.listarTodos();
            if (usuarios.size() > 1) {
                usuarioIdTest = usuarios.get(1).getUsuario_id();
            }
        }
        
        assertNotNull(usuarioIdTest, "Debe haber un ID de usuario para modificar");
        
        String nuevoNombre = "usuario_modificado";
        Boolean activo = true;
        ArrayList<Integer> paisesIds = new ArrayList<>(Arrays.asList(1, 2)); // Perú, Colombia
        
        Integer resultado = usuariosBO.modificarAccesoUsuario(
            usuarioIdTest, nuevoNombre, activo, paisesIds
        );
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La modificación debe ser exitosa");
        
        System.out.println("✓ Acceso de usuario modificado");
        System.out.println("  Nuevo nombre: " + nuevoNombre);
        System.out.println("  Países asignados: " + paisesIds.size());
    }
    
    /**
     * Test 8: Eliminar usuario lógicamente
     * Verifica RF020 - Revocar permisos
     */
    @Test
    @Order(8)
    @DisplayName("Debe eliminar usuario lógicamente")
    void testEliminarUsuarioLogicamente() {
        System.out.println("Test 8: Eliminar Usuario Lógicamente");
        
        if (usuarioIdTest == null) {
            ArrayList<UsuariosDTO> usuarios = usuariosBO.listarTodos();
            if (usuarios.size() > 1) {
                usuarioIdTest = usuarios.get(1).getUsuario_id();
            }
        }
        
        assertNotNull(usuarioIdTest, "Debe haber un ID de usuario para eliminar");
        
        UsuariosDTO usuario = usuariosBO.obtenerPorId(usuarioIdTest);
        assertNotNull(usuario, "El usuario debe existir");
        
        UsuariosDTO usuarioActual = new UsuariosDTO();
        usuarioActual.setUsuario_id(1); // Superusuario
        
        Integer resultado = usuariosBO.eliminarUsuario(usuario, usuarioActual);
        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertTrue(resultado > 0, "La eliminación debe ser exitosa");
        
        System.out.println("✓ Usuario eliminado lógicamente");
    }
    
    /**
     * Test 9: Validar roles de usuario
     * Verifica RF019 - Roles específicos
     */
    @Test
    @Order(9)
    @DisplayName("Debe validar distribución de roles")
    void testValidarRolesUsuario() {
        System.out.println("Test 9: Validar Roles de Usuario");
        
        ArrayList<UsuariosDTO> usuarios = usuariosBO.listarTodos();
        
        int superusuarios = 0;
        int operadores = 0;
                
        for (UsuariosDTO u : usuarios) {
            if (u.getSuperusuario() != null) {
                if(u.getSuperusuario()){
                    superusuarios++;
                }else{
                    operadores++;
                }
            }
        }
        
        System.out.println("✓ Distribución de roles:");
        System.out.println("  - Superusuarios: " + superusuarios);
        System.out.println("  - Operadores: " + operadores);
        
        assertTrue(superusuarios > 0, "Debe haber al menos un superusuario");
    }
    
    /**
     * Test 10: Validar encriptación de contraseñas
     * Verifica RF021 - Encriptación MD5
     */
    @Test
    @Order(10)
    @DisplayName("Debe validar encriptación de contraseñas")
    void testValidarEncriptacionPassword() {
        System.out.println("Test 10: Validar Encriptación de Contraseñas");
        
        ArrayList<UsuariosDTO> usuarios = usuariosBO.listarTodos();
        
        for (UsuariosDTO u : usuarios) {
            assertNotNull(u.getPassword_hash(), "El password_hash no debe ser nulo");
            assertNotEquals("admin123", u.getPassword_hash(),
                          "El password no debe estar en texto plano");
            assertNotEquals("password", u.getPassword_hash(),
                          "El password no debe estar en texto plano");
            
            // El hash MD5 tiene 32 caracteres
            if (u.getPassword_hash() != null) {
                assertTrue(u.getPassword_hash().length() >= 32,
                          "El hash MD5 debe tener longitud adecuada");
            }
        }
        
        System.out.println("✓ Todas las contraseñas están encriptadas");
        System.out.println("  Total validadas: " + usuarios.size());
    }
}