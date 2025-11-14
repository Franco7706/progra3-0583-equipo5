package pe.edu.pucp.softpac.email.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pe.edu.pucp.softpac.email.emailserver.ServicioEmail;
import pe.edu.pucp.softpac.email.model.EmailDTO;
import pe.edu.pucp.softpac.email.model.EmailMultipleDTO;
import pe.edu.pucp.softpac.email.model.EmailCompletoDTO;
import pe.edu.pucp.softpac.email.model.EmailConCCDTO;
import pe.edu.pucp.softpac.email.model.EmailConBCCDTO;
import pe.edu.pucp.softpac.email.model.RespuestaDTO;

@Path("Email")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Email {
    private ServicioEmail servicioEmail;
    
    public Email(){
        this.servicioEmail = new ServicioEmail();
    }
    
    @POST
    @Path("enviar")
    public Response enviar(EmailDTO emailDTO){
        try {
            this.servicioEmail.enviarCorreo(
                emailDTO.getDestinatario(),
                emailDTO.getAsunto(),
                emailDTO.getContenido()
            );
            return Response.status(Response.Status.OK)
                    .entity(new RespuestaDTO(true, "Correo enviado exitosamente"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespuestaDTO(false, "Error al enviar correo: " + e.getMessage()))
                    .build();
        }
    }
    
    @POST
    @Path("enviar-html")
    public Response enviarHTML(EmailDTO emailDTO){
        try {
            this.servicioEmail.enviarCorreoHTML(
                emailDTO.getDestinatario(),
                emailDTO.getAsunto(),
                emailDTO.getContenidoHTML()
            );
            return Response.status(Response.Status.OK)
                    .entity(new RespuestaDTO(true, "Correo HTML enviado exitosamente"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespuestaDTO(false, "Error al enviar correo HTML: " + e.getMessage()))
                    .build();
        }
    }
    
    @POST
    @Path("enviar-multiple")
    public Response enviarMultiple(EmailMultipleDTO emailMultipleDTO){
        try {
            this.servicioEmail.enviarCorreo(
                emailMultipleDTO.getDestinatarios(),
                emailMultipleDTO.getAsunto(),
                emailMultipleDTO.getContenido()
            );
            return Response.status(Response.Status.OK)
                    .entity(new RespuestaDTO(true, "Correos enviados exitosamente"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespuestaDTO(false, "Error al enviar correos: " + e.getMessage()))
                    .build();
        }
    }
    
    @POST
    @Path("enviar-multiple-html")
    public Response enviarMultipleHTML(EmailMultipleDTO emailMultipleDTO){
        try {
            this.servicioEmail.enviarCorreoHTML(
                emailMultipleDTO.getDestinatarios(),
                emailMultipleDTO.getAsunto(),
                emailMultipleDTO.getContenidoHTML()
            );
            return Response.status(Response.Status.OK)
                    .entity(new RespuestaDTO(true, "Correos HTML enviados exitosamente"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespuestaDTO(false, "Error al enviar correos HTML: " + e.getMessage()))
                    .build();
        }
    }
    
    @POST
    @Path("enviar-con-cc")
    public Response enviarConCC(EmailConCCDTO emailConCCDTO){
        try {
            this.servicioEmail.enviarCorreoConCC(
                emailConCCDTO.getDestinatario(),
                emailConCCDTO.getCc(),
                emailConCCDTO.getAsunto(),
                emailConCCDTO.getContenido()
            );
            return Response.status(Response.Status.OK)
                    .entity(new RespuestaDTO(true, "Correo con CC enviado exitosamente"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespuestaDTO(false, "Error al enviar correo con CC: " + e.getMessage()))
                    .build();
        }
    }
    
    @POST
    @Path("enviar-con-bcc")
    public Response enviarConBCC(EmailConBCCDTO emailConBCCDTO){
        try {
            this.servicioEmail.enviarCorreoConBCC(
                emailConBCCDTO.getDestinatario(),
                emailConBCCDTO.getBcc(),
                emailConBCCDTO.getAsunto(),
                emailConBCCDTO.getContenido()
            );
            return Response.status(Response.Status.OK)
                    .entity(new RespuestaDTO(true, "Correo con BCC enviado exitosamente"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespuestaDTO(false, "Error al enviar correo con BCC: " + e.getMessage()))
                    .build();
        }
    }
    
    @POST
    @Path("enviar-completo")
    public Response enviarCompleto(EmailCompletoDTO emailCompletoDTO){
        try {
            this.servicioEmail.enviarCorreoHTMLCompleto(
                emailCompletoDTO.getDestinatario(),
                emailCompletoDTO.getCc(),
                emailCompletoDTO.getBcc(),
                emailCompletoDTO.getAsunto(),
                emailCompletoDTO.getContenidoHTML()
            );
            return Response.status(Response.Status.OK)
                    .entity(new RespuestaDTO(true, "Correo completo enviado exitosamente"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new RespuestaDTO(false, "Error al enviar correo completo: " + e.getMessage()))
                    .build();
        }
    }
}