package pe.edu.pucp.softpac.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softpac.bo.util.PasswordUtil;
import pe.edu.pucp.softpac.dao.UsuarioPaisAccesoDAO;
import pe.edu.pucp.softpac.dao.UsuariosDAO;
import pe.edu.pucp.softpac.daoImpl.UsuarioPaisAccesoDAOImpl;
import pe.edu.pucp.softpac.daoImpl.UsuariosDAOImpl;
import pe.edu.pucp.softpac.model.PaisesDTO;
import pe.edu.pucp.softpac.model.UsuarioPaisAccesoDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;
import pe.edu.pucp.softpac.bo.util.EmailUtil;
import pe.edu.pucp.softpac.bo.util.TipoOperacionUsuario;
import pe.edu.pucp.softpac.dao.PaisesDAO;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;

public class UsuariosBO {

    private UsuariosDAO usuariosDAO;
    private UsuarioPaisAccesoDAO usuariosPaisAccesoDAO;

    public UsuariosBO() {
        this.usuariosDAO = new UsuariosDAOImpl();
        this.usuariosPaisAccesoDAO = new UsuarioPaisAccesoDAOImpl();
    }

    public UsuariosDTO autenticarUsuarioPorNombreUsuario(String nombreUsuario, String password) {
        UsuariosDTO usuario = this.usuariosDAO.obtenerPorNombreDeUsuario(nombreUsuario.trim());

        if (usuario == null) {
            return new UsuariosDTO(); //Usuario no encontrado
        }

        Boolean esValido = PasswordUtil.checkPassword(password.trim(), usuario.getPassword_hash());

        if (esValido && usuario.getActivo() == true) {
            //PARA SERIALIZACIÓN:
            for (UsuarioPaisAccesoDTO upa : usuario.getUsuario_pais()) {
                upa.setUsuario(null);
            }
            return usuario; //Autenticación exitosa
        }

        return new UsuariosDTO(); //Contraseña incorrecta
    }

    public UsuariosDTO autenticarUsuarioPorCorreo(String correo_usuario, String password) {
        UsuariosDTO usuario = this.usuariosDAO.obtenerPorCorreo(correo_usuario);

        if (usuario == null) {
            return null; //Usuario no encontrado
        }

        Boolean esValido = PasswordUtil.checkPassword(password.trim(), usuario.getPassword_hash());

        if (esValido) {
            //PARA SERIALIZACIÓN:
            for (UsuarioPaisAccesoDTO upa : usuario.getUsuario_pais()) {
                upa.setUsuario(null);
            }
            return usuario; //Autenticación exitosa
        }

        return null; //Contraseña incorrecta

    }

    public ArrayList<UsuariosDTO> listarTodos() {
        return (ArrayList<UsuariosDTO>) usuariosDAO.listarTodos();
    }


    public UsuariosDTO obtenerPorId(Integer usuario_id) {
        UsuariosDTO usuario = new UsuariosDTO();
        usuario.setUsuario_id(usuario_id);

        usuario = usuariosDAO.obtenerPorId(usuario_id);

        //PARA SERIALIZACIÓN:
        for (UsuarioPaisAccesoDTO upa : usuario.getUsuario_pais()) {
            upa.setUsuario(null);
        }

        return usuario;
    }

    //                CAMBIOS
    public Integer insertarUsuario(UsuariosDTO nuevoUsuario, UsuariosDTO usuarioActual) {
        //1RO INSERTAR USUARIO
        if (nuevoUsuario.getPassword_hash() == null) {
            try {
                throw new Exception("La contraseña es obligatoria para nuevos usuarios.");
            } catch (Exception ex) {
                Logger.getLogger(UsuariosBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String guardaContrasenhaOriginal = nuevoUsuario.getPassword_hash();
        String hashedPassword = PasswordUtil.hashPassword(nuevoUsuario.getPassword_hash());
        nuevoUsuario.setPassword_hash(hashedPassword);
        PaisesDAO paisesDAO = new PaisesDAOImpl();
        List<PaisesDTO> paises = paisesDAO.listarTodos();
        for (UsuarioPaisAccesoDTO acceso : nuevoUsuario.getUsuario_pais()) {
            acceso.getPais().setNombre(obtenerNombrePais(acceso.getPais().getPais_id(), paises));
            acceso.setUsuario(nuevoUsuario);
        }
        Integer resultado = usuariosDAO.insertar(nuevoUsuario);
        //2DO: 
        ArrayList<UsuarioPaisAccesoDTO> accesosConcedidos = new ArrayList<>();
        for(UsuarioPaisAccesoDTO acceso : nuevoUsuario.getUsuario_pais()){
            if(acceso.getAcceso()==Boolean.TRUE) accesosConcedidos.add(acceso);
        }
        nuevoUsuario.setUsuario_pais(accesosConcedidos);
        if(resultado>0){
            EmailUtil eu = new EmailUtil();
            nuevoUsuario.setPassword_hash(guardaContrasenhaOriginal);
            eu.enviarCorreo(nuevoUsuario, usuarioActual, TipoOperacionUsuario.INSERTAR);
        }
        return resultado;

    }

        //                CAMBIOS
    public Integer modificarAccesoUsuario(int usuarioId, String nuevoNombreUsuario,
            Boolean activo, List<Integer> paisesIds, UsuariosDTO usuarioActual,
            String nuevaContrasenha) {
        UsuariosDTO usuario = usuariosDAO.obtenerPorId(usuarioId);
        if (usuario == null) {
            return 0;
        }
        
        // --- GUARDAR ESTADO ANTERIOR DE ACCESOS ---
        Map<Integer, Boolean> accesosAnteriores = new HashMap<>();
        if (usuario.getUsuario_pais() != null) {
            for (UsuarioPaisAccesoDTO upa : usuario.getUsuario_pais()) {
                if (upa.getPais() != null) {
                    accesosAnteriores.put(
                        upa.getPais().getPais_id(), 
                        upa.getAcceso() != null ? upa.getAcceso() : false
                    );
                }
            }
        }
        
        //           ACTUALIZACION
        usuario.setNombre_de_usuario(nuevoNombreUsuario);
        usuario.setActivo(activo);
        
        //Catalogo de Paises para los nombres
        PaisesDAO paisesDAO = new PaisesDAOImpl();
        List<PaisesDTO> catalogoPaises = paisesDAO.listarTodos();
        
        //Actualizamos la lista de países
        usuario.getUsuario_pais().clear();
        for (PaisesDTO pais : catalogoPaises) {
            UsuarioPaisAccesoDTO user = new UsuarioPaisAccesoDTO();
            String nombrePais = this.obtenerNombrePais(pais.getPais_id(), catalogoPaises);
            pais.setNombre(nombrePais);
            user.setUsuario(usuario);
            user.setPais(pais);
            user.setAcceso(paisesIds.contains(pais.getPais_id()));
            usuario.getUsuario_pais().add(user);
        }
        String guardaContrasenhaOriginal = null;
        //Actualizamos contraseña
        if(nuevaContrasenha!=null && !nuevaContrasenha.trim().isEmpty()){
            guardaContrasenhaOriginal = nuevaContrasenha;
            String hashedPassword = PasswordUtil.hashPassword(nuevaContrasenha);
            usuario.setPassword_hash(hashedPassword);
        }
                
        //        FIN ACTUALIZACION
        
        Integer resultado = usuariosDAO.modificar(usuario);
        
        if(resultado > 0) {
            // --- FILTRAR SOLO LOS CAMBIOS DE ACCESO ---
            List<UsuarioPaisAccesoDTO> soloAccesosCambiados = new ArrayList<>();
            
            for (UsuarioPaisAccesoDTO upa : usuario.getUsuario_pais()) {
                Integer paisId = upa.getPais().getPais_id();
                Boolean accesoAnterior = accesosAnteriores.getOrDefault(paisId, false);
                Boolean accesoNuevo = upa.getAcceso() != null ? upa.getAcceso() : false;
                
                // Solo incluir si hubo un CAMBIO en el acceso
                if (!accesoAnterior.equals(accesoNuevo)) {
                    soloAccesosCambiados.add(upa);
                }
            }
            
            // Reemplazar temporalmente la lista completa con solo los cambios
            usuario.setUsuario_pais((ArrayList<UsuarioPaisAccesoDTO>) soloAccesosCambiados);
            usuario.setPassword_hash(guardaContrasenhaOriginal);
            
            EmailUtil eu = new EmailUtil();
            eu.enviarCorreo(usuario, usuarioActual, TipoOperacionUsuario.MODIFICARACCESO);
            
        }
        return resultado;
    }


    public Integer modificarUsuario(UsuariosDTO usuario) {
        UsuariosDTO usuarioExistente = usuariosDAO.obtenerPorId(usuario.getUsuario_id());
        if (usuarioExistente == null) {
            return 0;
        }

        // Actualizar campos básicos
        usuarioExistente.setNombre_de_usuario(usuario.getNombre_de_usuario());
        usuarioExistente.setActivo(usuario.getActivo());

        // Actualizar accesos a países
        usuarioExistente.getUsuario_pais().clear();

        for (UsuarioPaisAccesoDTO acceso : usuario.getUsuario_pais()) {
            acceso.setUsuario(usuarioExistente);
            usuarioExistente.getUsuario_pais().add(acceso);
        }

        return usuariosDAO.modificar(usuarioExistente);
    }

        //                CAMBIOS
    public Integer eliminarUsuario(UsuariosDTO usuario, UsuariosDTO usuarioActual) {

        usuario.setFecha_eliminacion(new Date());
        usuario.setUsuario_eliminacion(usuarioActual);
        for (UsuarioPaisAccesoDTO acceso : usuario.getUsuario_pais()) {
            acceso.setUsuario(usuario);
        }
        Integer resultado = usuariosDAO.eliminarLogico(usuario);
        if(resultado>0){
            EmailUtil eu = new EmailUtil();
            eu.enviarCorreo(usuario, usuarioActual , TipoOperacionUsuario.ELIMINAR);
        }
        return resultado;

    }
    
        //                CAMBIOS
    private String obtenerNombrePais(Integer paisId, List<PaisesDTO> paises){
        String nombrePais="Pais Desconocido";
        for(PaisesDTO pais : paises){
            if(Objects.equals(pais.getPais_id(), paisId)){
                nombrePais=pais.getNombre();
            }
        }
        return nombrePais;
    }

}