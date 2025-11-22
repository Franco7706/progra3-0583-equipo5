package pe.edu.pucp.softpac.email.emailserver;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Servicio para envío de correos electrónicos.
 * Lee la configuración automáticamente desde el archivo email.properties
 */
public class ServicioEmail {
    
    private Properties emailConfig;
    private Session session;
    private String remitente;

    /**
     * Constructor que carga automáticamente la configuración desde email.properties
     */
    public ServicioEmail() {
        cargarConfiguracion();
        this.remitente = emailConfig.getProperty("email.smtp.remitente");
        this.session = crearSesion();
    }

    /**
     * Constructor con ruta personalizada del archivo properties
     */
    public ServicioEmail(String rutaArchivo) {
        cargarConfiguracion(rutaArchivo);
        this.remitente = emailConfig.getProperty("email.smtp.remitente");
        this.session = crearSesion();
    }

    private void cargarConfiguracion() {
        emailConfig = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("email.properties");
            
            if (input == null) {
                input = new FileInputStream("email.properties");
            }
            
            if (input == null) {
                throw new RuntimeException("No se pudo encontrar el archivo email.properties");
            }

            emailConfig.load(input);
            
        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar el archivo de configuración email.properties", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void cargarConfiguracion(String rutaArchivo) {
        emailConfig = new Properties();
        
        try (InputStream input = new FileInputStream(rutaArchivo)) {
            emailConfig.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar el archivo de configuración: " + rutaArchivo, ex);
        }
    }

    private Session crearSesion() {
        Properties props = new Properties();

        // Configuración básica SMTP
        props.put("mail.smtp.host", emailConfig.getProperty("email.smtp.host"));
        props.put("mail.smtp.port", emailConfig.getProperty("email.smtp.port"));
        props.put("mail.smtp.auth", emailConfig.getProperty("email.smtp.auth", "true"));

        // Configuración TLS/SSL mejorada para GlassFish 7
        props.put("mail.smtp.starttls.enable", emailConfig.getProperty("email.smtp.starttls.enable", "true"));
        props.put("mail.smtp.starttls.required", emailConfig.getProperty("email.smtp.starttls.required", "true"));
        props.put("mail.smtp.ssl.enable", emailConfig.getProperty("email.smtp.ssl.enable", "false"));

        // Protocolos SSL permitidos
        props.put("mail.smtp.ssl.protocols", emailConfig.getProperty("email.smtp.ssl.protocols", "TLSv1.2 TLSv1.3"));

        // Confiar en el servidor SMTP
        props.put("mail.smtp.ssl.trust", emailConfig.getProperty("email.smtp.ssl.trust", "*"));

        // Timeouts
        props.put("mail.smtp.connectiontimeout", emailConfig.getProperty("email.smtp.connectiontimeout", "10000"));
        props.put("mail.smtp.timeout", emailConfig.getProperty("email.smtp.timeout", "10000"));
        props.put("mail.smtp.writetimeout", emailConfig.getProperty("email.smtp.writetimeout", "10000"));

        props.put("mail.debug", "true");

        final String usuario = emailConfig.getProperty("email.smtp.usuario");
        final String password = emailConfig.getProperty("email.smtp.password");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });
    }

    public void enviarCorreo(String destinatario, String asunto, String contenido) 
            throws MessagingException {
        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(remitente));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        mensaje.setSubject(asunto);
        mensaje.setText(contenido);
        
        Transport.send(mensaje);
    }

    public void enviarCorreoConCC(String destinatario, String[] cc, 
                                  String asunto, String contenido) 
            throws MessagingException {
        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(remitente));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        
        if (cc != null && cc.length > 0) {
            InternetAddress[] direccionesCC = new InternetAddress[cc.length];
            for (int i = 0; i < cc.length; i++) {
                direccionesCC[i] = new InternetAddress(cc[i]);
            }
            mensaje.setRecipients(Message.RecipientType.CC, direccionesCC);
        }
        
        mensaje.setSubject(asunto);
        mensaje.setText(contenido);
        
        Transport.send(mensaje);
    }

    public String getRemitente() {
        return remitente;
    }

    public Properties getConfiguracion() {
        return emailConfig;
    }
}