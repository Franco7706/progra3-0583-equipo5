package pe.edu.pucp.softpac.email.model;

public class EmailConCCDTO {
    private String destinatario;
    private String[] cc;
    private String asunto;
    private String contenido;
    
    public EmailConCCDTO() {}
    
    // Getters y Setters
    public String getDestinatario() {
        return destinatario;
    }
    
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    
    public String[] getCc() {
        return cc;
    }
    
    public void setCc(String[] cc) {
        this.cc = cc;
    }
    
    public String getAsunto() {
        return asunto;
    }
    
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public String getContenido() {
        return contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}