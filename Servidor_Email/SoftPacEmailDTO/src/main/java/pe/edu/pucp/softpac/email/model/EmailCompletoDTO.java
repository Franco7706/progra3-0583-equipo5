package pe.edu.pucp.softpac.email.model;

public class EmailCompletoDTO {
    private String destinatario;
    private String[] cc;
    private String[] bcc;
    private String asunto;
    private String contenidoHTML;
    
    public EmailCompletoDTO() {}
    
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
    
    public String getContenidoHTML() {
        return contenidoHTML;
    }
    
    public void setContenidoHTML(String contenidoHTML) {
        this.contenidoHTML = contenidoHTML;
    }
}