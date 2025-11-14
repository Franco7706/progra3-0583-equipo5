package pe.edu.pucp.softpac.bo.util;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import pe.edu.pucp.softpac.email.model.*;

public class EmailClienteUtil {

    // URL base del servicio REST (ajustar según tu configuración)
    //private static final String BASE_URL = "http://localhost:8080/SoftPacEmailWS/resources/Email";
    private static final String BASE_URL = "http://localhost:8080/SoftPacEmailWS/resources/Email";
    private Client client;
    private WebTarget webTarget;
    
    public EmailClienteUtil() {
        // Crear el cliente Jersey con soporte JSON
        // Crear el cliente Jersey con soporte JSON
        this.client = ClientBuilder.newBuilder()
                .register(org.glassfish.jersey.jackson.JacksonFeature.class)  // ← AGREGAR ESTO
                .build();
        this.webTarget = client.target(BASE_URL);
    }
    
    /**
     * Envía un correo simple de forma asincrónica (NO BLOQUEANTE)
     * @param emailDTO datos del correo
     */
    public void enviarCorreoAsync(EmailDTO emailDTO) {
        CompletableFuture.supplyAsync(() -> {
            try {
                Response response = webTarget
                        .path("enviar")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(emailDTO, MediaType.APPLICATION_JSON));

                if (response.getStatus() == 200) {
                    return response.readEntity(RespuestaDTO.class);
                } else {
                    return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
                }
            } catch (Exception e) {
                return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
            }
        }).thenAccept(respuesta -> {
            // Callback - solo logging del resultado
            System.out.println("Resultado envío correo: " + respuesta);
        }).exceptionally(ex -> {
            System.err.println("Error crítico al enviar correo: " + ex.getMessage());
            return null;
        });
    }
    
    
    /**
     * Envía un correo simple
     * @param emailDTO datos del correo
     * @return RespuestaDTO con el resultado
     */
    public RespuestaDTO enviarCorreo(EmailDTO emailDTO) {
        try {
            Response response = webTarget
                    .path("enviar")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(emailDTO, MediaType.APPLICATION_JSON));
            
            if (response.getStatus() == 200) {
                return response.readEntity(RespuestaDTO.class);
            } else {
                return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
            }
        } catch (Exception e) {
            return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
        }
    }
    
    /**
     * Envía un correo HTML
     * @param emailDTO datos del correo con contenidoHTML
     * @return RespuestaDTO con el resultado
     */
    public RespuestaDTO enviarCorreoHTML(EmailDTO emailDTO) {
        try {
            Response response = webTarget
                    .path("enviar-html")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(emailDTO, MediaType.APPLICATION_JSON));
            
            if (response.getStatus() == 200) {
                return response.readEntity(RespuestaDTO.class);
            } else {
                return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
            }
        } catch (Exception e) {
            return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
        }
    }
    
    /**
     * Envía correo a múltiples destinatarios
     * @param emailMultipleDTO datos con array de destinatarios
     * @return RespuestaDTO con el resultado
     */
    public RespuestaDTO enviarCorreoMultiple(EmailMultipleDTO emailMultipleDTO) {
        try {
            Response response = webTarget
                    .path("enviar-multiple")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(emailMultipleDTO, MediaType.APPLICATION_JSON));
            
            if (response.getStatus() == 200) {
                return response.readEntity(RespuestaDTO.class);
            } else {
                return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
            }
        } catch (Exception e) {
            return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
        }
    }
    
    /**
     * Envía correo HTML a múltiples destinatarios
     * @param emailMultipleDTO datos con array de destinatarios y HTML
     * @return RespuestaDTO con el resultado
     */
    public RespuestaDTO enviarCorreoMultipleHTML(EmailMultipleDTO emailMultipleDTO) {
        try {
            Response response = webTarget
                    .path("enviar-multiple-html")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(emailMultipleDTO, MediaType.APPLICATION_JSON));
            
            if (response.getStatus() == 200) {
                return response.readEntity(RespuestaDTO.class);
            } else {
                return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
            }
        } catch (Exception e) {
            return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
        }
    }
    
    /**
     * Envía correo con copia (CC) de forma Async (NO BLOQUEANTE)
     * @param emailConCCDTO datos con CC
     */
    public void enviarCorreoConCCAsync(EmailConCCDTO emailConCCDTO) {
        CompletableFuture.supplyAsync(() -> {
            try {
                Response response = webTarget
                        .path("enviar-con-cc")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(emailConCCDTO, MediaType.APPLICATION_JSON));

                if (response.getStatus() == 200) {
                    return response.readEntity(RespuestaDTO.class);
                } else {
                    return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
                }
            } catch (Exception e) {
                return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
            }
        }).thenAccept(respuesta -> {
            // Callback - solo logging del resultado
            System.out.println("Resultado envío correo con CC: " + respuesta);
        }).exceptionally(ex -> {
            System.err.println("Error crítico al enviar correo con CC: " + ex.getMessage());
            return null;
        });
    }
    
    /**
     * Envía correo con copia (CC)
     * @param emailConCCDTO datos con CC
     * @return RespuestaDTO con el resultado
     */
    public RespuestaDTO enviarCorreoConCC(EmailConCCDTO emailConCCDTO) {
        try {
            Response response = webTarget
                    .path("enviar-con-cc")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(emailConCCDTO, MediaType.APPLICATION_JSON));
            
            if (response.getStatus() == 200) {
                return response.readEntity(RespuestaDTO.class);
            } else {
                return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
            }
        } catch (Exception e) {
            return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
        }
    }
    
    /**
     * Envía correo con copia oculta (BCC)
     * @param emailConBCCDTO datos con BCC
     * @return RespuestaDTO con el resultado
     */
    public RespuestaDTO enviarCorreoConBCC(EmailConBCCDTO emailConBCCDTO) {
        try {
            Response response = webTarget
                    .path("enviar-con-bcc")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(emailConBCCDTO, MediaType.APPLICATION_JSON));
            
            if (response.getStatus() == 200) {
                return response.readEntity(RespuestaDTO.class);
            } else {
                return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
            }
        } catch (Exception e) {
            return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
        }
    }
    
    /**
     * Envía correo completo con CC, BCC y HTML
     * @param emailCompletoDTO datos completos
     * @return RespuestaDTO con el resultado
     */
    public RespuestaDTO enviarCorreoCompleto(EmailCompletoDTO emailCompletoDTO) {
        try {
            Response response = webTarget
                    .path("enviar-completo")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(emailCompletoDTO, MediaType.APPLICATION_JSON));
            
            if (response.getStatus() == 200) {
                return response.readEntity(RespuestaDTO.class);
            } else {
                return new RespuestaDTO(false, "Error HTTP: " + response.getStatus());
            }
        } catch (Exception e) {
            return new RespuestaDTO(false, "Error de conexión: " + e.getMessage());
        }
    }
    
    /**
     * Cierra la conexión del cliente
     */
    public void close() {
        if (client != null) {
            client.close();
        }
    }
    
}