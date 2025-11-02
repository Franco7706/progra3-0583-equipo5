package pe.edu.pucp.softpac.email.model;

public class RespuestaDTO {
    private boolean success;
    private String mensaje;
    
    public RespuestaDTO() {}
    
    public RespuestaDTO(boolean success, String mensaje) {
        this.success = success;
        this.mensaje = mensaje;
    }
    
    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}