package pe.edu.pucp.softpac.email.model;

public class EmailDTO {
    private String destinatario;
    private String asunto;
    private String contenido;
    private String contenidoHTML;
    
    public EmailDTO() {}
    
    public EmailDTO(String destinatario, String asunto, String contenido) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.contenido = contenido;
    }
    
    // Getters y Setters
    public String getDestinatario() {
        return destinatario;
    }
    
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
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