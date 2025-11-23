package pe.edu.pucp.softpac.bo.util;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import pe.edu.pucp.softpac.email.model.*;

public class EmailClienteUtil {

    private static final String ARCHIVO_CONFIGURACION = "email.properties";

    private static String BASE_URL;

    private Client client;
    private WebTarget webTarget;

    public EmailClienteUtil() {
        cargarConfiguracion();   // LEER EL PROPERTIES
        inicializarCliente();    // CREAR EL CLIENTE
    }

    private void cargarConfiguracion() {
        if (BASE_URL != null) return; // Solo cargar una vez

        Properties properties = new Properties();
        try {
            String archivo = "/" + ARCHIVO_CONFIGURACION;
            properties.load(this.getClass().getResourceAsStream(archivo));

            BASE_URL = properties.getProperty("email.base.url");

            if (BASE_URL == null || BASE_URL.isBlank()) {
                throw new RuntimeException("email.base.url no encontrado en email.properties");
            }

        } catch (IOException ex) {
            throw new RuntimeException("Error al leer archivo email.properties: " + ex.getMessage(), ex);
        }
    }


    private void inicializarCliente() {
        this.client = ClientBuilder.newBuilder()
                .register(org.glassfish.jersey.jackson.JacksonFeature.class)
                .build();

        this.webTarget = client.target(BASE_URL);
    }

    // ------------------ MÉTODOS ASYNC ---------------------

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
            System.out.println("Resultado envío correo: " + respuesta);
        }).exceptionally(ex -> {
            System.err.println("Error crítico al enviar correo: " + ex.getMessage());
            return null;
        });
    }

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
            System.out.println("Resultado envío correo con CC: " + respuesta);
        }).exceptionally(ex -> {
            System.err.println("Error crítico al enviar correo con CC: " + ex.getMessage());
            return null;
        });
    }

    public void close() {
        if (client != null) {
            client.close();
        }
    }
}