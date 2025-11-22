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
     * Cierra la conexión del cliente
     */
    public void close() {
        if (client != null) {
            client.close();
        }
    }
    
}