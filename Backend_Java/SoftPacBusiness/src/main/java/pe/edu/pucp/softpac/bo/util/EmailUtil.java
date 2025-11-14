package pe.edu.pucp.softpac.bo.util;

import java.util.stream.Collectors;
import pe.edu.pucp.softpac.email.model.EmailConCCDTO;
import pe.edu.pucp.softpac.email.model.EmailDTO;
import pe.edu.pucp.softpac.email.model.RespuestaDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

public class EmailUtil {

    private EmailClienteUtil emailCliente;

    public EmailUtil() {
        this.emailCliente = new EmailClienteUtil();
    }

    public String enviarCorreo(UsuariosDTO usuario, UsuariosDTO usuarioActual, TipoOperacionUsuario operacion) {

        EmailConCCDTO emailDTO = new EmailConCCDTO();
        emailDTO.setDestinatario(usuario.getCorreo_electronico());
        String cc[]={usuarioActual.getCorreo_electronico()};
        emailDTO.setCc(cc);
        //ASUNTO Y CONTENIDO
        String asunto = "";
        String contenido = "";
        switch(operacion){
            case TipoOperacionUsuario.INSERTAR:
                asunto=this.crearAsuntoInsertar();
                contenido=this.armarCorreoInsertar(usuario);
                break;
            case TipoOperacionUsuario.MODIFICARACCESO:
                asunto=this.crearAsuntoModificar();
                contenido=this.armarCorreoModificar(usuario);
                break;
            case TipoOperacionUsuario.ELIMINAR:
                asunto=this.crearAsuntoEliminar();
                contenido=this.armarCorreoEliminar(usuario);
                break;
                
        }
        emailDTO.setAsunto(asunto);        
        emailDTO.setContenido(contenido); 
        
        emailCliente.enviarCorreoConCCAsync(emailDTO); 
        return "SUCCESS";
        //Envio coreo
//        RespuestaDTO respuesta = emailCliente.enviarCorreoConCC(emailDTO); 
//        
//        if (respuesta.isSuccess()) {
//            return "SUCCESS: " + respuesta.getMensaje();
//        } else {
//            return "ERROR: " + respuesta.getMensaje();
//        }
    }

    // ===============================================
    // MÉTODOS DE CONTENIDO Y ASUNTO
    // ===============================================

    /**
     * Genera el asunto formal del correo de notificación de cuenta.
     */
    private String crearAsuntoInsertar() {
        return "Acceso Creado: Credenciales de Acceso al Sistema de Pago a Acreedores";
    }

    /**
     * Genera el contenido del correo en formato HTML, incluyendo las credenciales.
     * @param usuario El DTO del nuevo usuario, con la contraseña original.
     * @return String con el contenido HTML.
     */
    
    private String armarCorreoInsertar(UsuariosDTO usuario) {

        // --- 1. SANITIZACIÓN DE ARGUMENTOS ---

        // Asegurar que nombre y apellidos no sean null antes de concatenar
        String nombre = (usuario.getNombre() != null) ? usuario.getNombre() : "";
        String apellidos = (usuario.getApellidos() != null) ? usuario.getApellidos() : "";
        String nombreCompleto = (nombre + " " + apellidos).trim();
        if (nombreCompleto.isEmpty()) {
            nombreCompleto = "Usuario";
        }

        // Asegurar que las credenciales no sean null
        String nombreUsuario = (usuario.getNombre_de_usuario() != null) ? usuario.getNombre_de_usuario() : "N/A";
        String passwordOriginal = (usuario.getPassword_hash() != null) ? usuario.getPassword_hash() : "SE MANTIENE"; 

        // Nueva sanitización: Estado de Superusuario
        String estadoSuperusuario = (usuario.getSuperusuario() != null && usuario.getSuperusuario()) ? "Sí (Administrador)" : "No (Usuario Estándar)";

        // Lógica de países (Versión segura)
        String listaPaises = "Ningún país asignado."; 
        if (usuario.getUsuario_pais() != null && !usuario.getUsuario_pais().isEmpty()) {
            listaPaises = usuario.getUsuario_pais().stream()
                .map(upa -> {
                    // Verificación profunda: si el país es null o el nombre es null
                    if (upa.getPais() == null || upa.getPais().getNombre() == null) {
                        return "País Desconocido";
                    }
                    return upa.getPais().getNombre();
                })
                .collect(Collectors.joining(", "));
        }

        // --- 2. GENERACIÓN DEL CONTENIDO DE TEXTO PLANO ---

        // Usamos String.format para construir el cuerpo del correo en texto plano
        String contenidoPlano = String.format(
            "Estimado(a) %s,\n\n" + // %s: nombreCompleto
            "Su cuenta de usuario para el Sistema de Pago a Acreedores ha sido creada satisfactoriamente.\n\n" +
            "A continuación, encontrará sus credenciales de acceso inicial:\n\n" +
            "-------------------------------------------------------------\n" +
            "Usuario: %s\n" + // %s: nombreUsuario
            "Contraseña: %s\n" + // %s: passwordOriginal
            "Permisos de Administrador: %s\n" + // NUEVA LÍNEA: estadoSuperusuario
            "-------------------------------------------------------------\n\n" +
            "Sus permisos de acceso están asignados a los siguientes países:\n" +
            "%s\n\n" + // %s: listaPaises
            "Por favor, no comparta su contraseña con nadie.\n\n" +
            "Atentamente,\n" +
            "El equipo de SoftPac.\n\n" +
            "-------------------------------------------------------------\n" +
            "Este es un correo automático. Por favor, no responda a este mensaje.",

            nombreCompleto, 
            nombreUsuario, 
            passwordOriginal, 
            estadoSuperusuario, // ¡NUEVO ARGUMENTO!
            listaPaises
        );

        return contenidoPlano;
    }
    
    // ===============================================
// MÉTODOS DE MODIFICACIÓN DE ACCESO (MODIFICARACCESO)
// ===============================================

/**
 * Genera el asunto formal del correo de notificación de modificación de acceso.
 */
private String crearAsuntoModificar() {
    return "Notificación de Seguridad: Su Acceso al Sistema de Pago a Acreedores ha sido Actualizado";
}

/**
 * Genera el contenido del correo de texto plano para la modificación de acceso.
 *
 * @param usuario El DTO del usuario cuyos accesos fueron modificados.
 * @return String con el contenido de texto plano.
 */
private String armarCorreoModificar(UsuariosDTO usuario) {
    // --- 1. SANITIZACIÓN DE ARGUMENTOS ---
    // Asegurar que nombre y apellidos no sean null antes de concatenar
    String nombre = (usuario.getNombre() != null) ? usuario.getNombre() : "";
    String apellidos = (usuario.getApellidos() != null) ? usuario.getApellidos() : "";
    String nombreCompleto = (nombre + " " + apellidos).trim();
    if (nombreCompleto.isEmpty()) {
        nombreCompleto = "Usuario";
    }
    
    //Contraseña
    String passwordOriginal = (usuario.getPassword_hash() != null && !usuario.getPassword_hash().trim().isEmpty()) 
    ? usuario.getPassword_hash() 
    : "NO MODIFICADA";
    // Estado activo
    String estadoActivo = (usuario.getActivo() != null && usuario.getActivo()) ? "ACTIVO" : "INACTIVO";
    String estadoMensaje = (usuario.getActivo() != null && usuario.getActivo()) 
                            ? "Su cuenta está actualmente activa." 
                            : "Su cuenta ha sido **DESACTIVADA**. Si cree que es un error, contacte a su superusuario.";
    
    // --- LÓGICA DE PAÍSES: SEPARAR POR ESTADO DE ACCESO ---
    String paisesAsignados = "";
    String paisesDesasignados = "";
    
    if (usuario.getUsuario_pais() != null && !usuario.getUsuario_pais().isEmpty()) {
        // Filtrar países con acceso = true
        String asignados = usuario.getUsuario_pais().stream()
            .filter(upa -> upa.getAcceso() != null && upa.getAcceso())
            .map(upa -> {
                if (upa.getPais() == null || upa.getPais().getNombre() == null) {
                    return "País Desconocido";
                }
                return upa.getPais().getNombre();
            })
            .collect(Collectors.joining(", "));
        
        paisesAsignados = asignados.isEmpty() ? "Ninguno" : asignados;
        
        // Filtrar países con acceso = false
        String desasignados = usuario.getUsuario_pais().stream()
            .filter(upa -> upa.getAcceso() == null || !upa.getAcceso())
            .map(upa -> {
                if (upa.getPais() == null || upa.getPais().getNombre() == null) {
                    return "País Desconocido";
                }
                return upa.getPais().getNombre();
            })
            .collect(Collectors.joining(", "));
        
        paisesDesasignados = desasignados.isEmpty() ? "Ninguno" : desasignados;
    } else {
        paisesAsignados = "Ninguno";
        paisesDesasignados = "Ninguno";
    }
    
    // --- 2. GENERACIÓN DEL CONTENIDO DE TEXTO PLANO ---
    String contenidoPlano = String.format(
        "Estimado(a) %s,\n\n" +
        "Le informamos que su perfil de usuario en el Sistema de Pago a Acreedores ha sido modificado.\n\n" +
        "-------------------------------------------------------------\n" +
        "RESUMEN DE CAMBIOS EN SU ACCESO:\n" +
        "-------------------------------------------------------------\n" +
        "Nombre de Usuario: %s\n" +
        "Contraseña: %s\n" +
        "Estado de la Cuenta: %s\n\n" +
        "%s\n\n" +
        "Países Asignados:\n" +
        "%s\n\n" +
        "Países Desasignados:\n" +
        "%s\n" +
        "-------------------------------------------------------------\n\n" +
        "Si usted no solicitó esta modificación, contacte inmediatamente al equipo de soporte.\n\n" +
        "Atentamente,\n" +
        "El equipo de SoftPac.\n\n" +
        "Este es un correo automático. Por favor, no responda a este mensaje.",
        nombreCompleto,
        usuario.getNombre_de_usuario() != null ? usuario.getNombre_de_usuario() : "N/A",
        passwordOriginal,
        estadoActivo,
        estadoMensaje,
        paisesAsignados,
        paisesDesasignados
    );
    return contenidoPlano;
}

// ===============================================
// MÉTODOS DE ELIMINACIÓN LÓGICA (ELIMINAR)
// ===============================================

/**
 * Genera el asunto formal del correo de notificación de eliminación de cuenta.
 */
private String crearAsuntoEliminar() {
    return "AVISO IMPORTANTE: Su Cuenta de Acceso ha sido Eliminada (Desactivada)";
}

/**
 * Genera el contenido del correo de texto plano para la eliminación lógica.
 *
 * @param usuario El DTO del usuario eliminado (lógicamente).
 * @return String con el contenido de texto plano.
 */
private String armarCorreoEliminar(UsuariosDTO usuario) {

    // --- 1. SANITIZACIÓN DE ARGUMENTOS ---

    String nombre = (usuario.getNombre() != null) ? usuario.getNombre() : "";
    String apellidos = (usuario.getApellidos() != null) ? usuario.getApellidos() : "";
    String nombreCompleto = (nombre + " " + apellidos).trim();
    if (nombreCompleto.isEmpty()) {
        nombreCompleto = "Usuario";
    }

    // --- 2. GENERACIÓN DEL CONTENIDO DE TEXTO PLANO ---

    String contenidoPlano = String.format(
        "Estimado(a) %s,\n\n" +
        "Le notificamos que su cuenta de usuario con el nombre '%s' en el Sistema de Pago a Acreedores ha sido **eliminada** del sistema por un superusuario.\n\n" +
        "Esto significa que ya no podrá iniciar sesión en la aplicación.\n\n" +
        "-------------------------------------------------------------\n" +
        "Detalles de la Cuenta:\n" +
        "Nombre de Usuario: %s\n" +
        "Correo Electrónico: %s\n" +
        "Estado: Eliminado (Inactivo)\n" +
        "-------------------------------------------------------------\n\n" +
        "Si tiene alguna pregunta o considera que esto es un error, por favor, póngase en contacto con el administrador de su empresa.\n\n" +
        "Atentamente,\n" +
        "El equipo de SoftPac.\n\n" +
        "Este es un correo automático. Por favor, no responda a este mensaje.",

        nombreCompleto,
        usuario.getNombre_de_usuario() != null ? usuario.getNombre_de_usuario() : "N/A",
        usuario.getNombre_de_usuario() != null ? usuario.getNombre_de_usuario() : "N/A",
        usuario.getCorreo_electronico() != null ? usuario.getCorreo_electronico() : "N/A"
    );

    return contenidoPlano;
}
    
}