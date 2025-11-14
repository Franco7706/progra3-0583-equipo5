package pe.edu.pucp.softpac.email.model;

public class EmailMultipleDTO {
    private String[] destinatarios;
    private String asunto;
    private String contenido;
    private String contenidoHTML;
    
    public EmailMultipleDTO() {}
    
    // Getters y Setters
    public String[] getDestinatarios() {
        return destinatarios;
    }
    
    public void setDestinatarios(String[] destinatarios) {
        this.destinatarios = destinatarios;
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
    
    public String getContenidoHTML() {
        return contenidoHTML;
    }
    
    public void setContenidoHTML(String contenidoHTML) {
        this.contenidoHTML = contenidoHTML;
    }
}