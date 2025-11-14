package pe.edu.pucp.softpac.bo.util;

public enum TipoOperacionUsuario {
    
    /**
     * Indica la creación de un nuevo usuario.
     */
    INSERTAR,
    
    /**
     * Indica la modificación de los accesos (países, estado activo) de un usuario existente.
     */
    MODIFICARACCESO,
    
    /**
     * Indica la eliminación lógica de un usuario.
     */
    ELIMINAR
}