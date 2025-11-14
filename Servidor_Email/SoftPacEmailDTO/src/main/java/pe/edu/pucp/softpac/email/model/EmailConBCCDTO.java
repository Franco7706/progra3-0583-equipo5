package pe.edu.pucp.softpac.email.model;

public class EmailConBCCDTO {
    private String destinatario;
    private String[] bcc;
    private String asunto;
    private String contenido;
    
    public EmailConBCCDTO() {}
    
    // Getters y Setters
    public String getDestinatario() {
        return destinatario;
    }
    
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    
    public String[] getBcc() {
        return bcc;
    }
    
    public void setBcc(String[] bcc) {
        this.bcc = bcc;
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