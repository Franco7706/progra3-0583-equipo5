package pe.edu.pucp.softpac.model;

import java.util.ArrayList;

public class UsuariosDTO extends EliminableDTOBase {
    private Integer usuario_id;
    private String correo_electronico;
    private Boolean activo;
    private String password_hash;
    private Boolean superusuario;
    private ArrayList<UsuarioPaisAccesoDTO> usuario_pais;

    public UsuariosDTO(){
        super();
        this.usuario_id = null;
        this.correo_electronico = null;
        this.activo = null;
        this.password_hash = null;
        this.superusuario = null;
        this.usuario_pais=new ArrayList<>();
    }
    public UsuariosDTO(Integer usuario_id, String correo_electronico, Boolean activo, String password_hash, Boolean superusuario,  ArrayList<UsuarioPaisAccesoDTO> usuario_pais){
        super();
        this.usuario_id = usuario_id;
        this.correo_electronico = correo_electronico;
        this.activo = activo;
        this.password_hash = password_hash;
        this.superusuario = superusuario;
        this.usuario_pais=usuario_pais;
    }
    public UsuariosDTO(Integer usuario_id, String correo_electronico, Boolean activo, String password_hash, Boolean superusuario) {
        super();
        this.usuario_id = usuario_id;
        this.correo_electronico = correo_electronico;
        this.activo = activo;
        this.password_hash = password_hash;
        this.superusuario = superusuario;
        this.usuario_pais=new ArrayList<>();
    }
    public UsuariosDTO(UsuariosDTO usuario){
        super(usuario);
        this.usuario_id = usuario.getUsuario_id();
        this.correo_electronico = usuario.getCorreo_electronico();
        this.activo = usuario.getActivo();
        this.password_hash = usuario.getPassword_hash();
        this.superusuario = usuario.getSuperusuario();
        this.usuario_pais=usuario.getUsuario_pais();
    }
    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Boolean getSuperusuario() {
        return superusuario;
    }

    public void setSuperusuario(Boolean superusuario) {
        this.superusuario = superusuario;
    }

    public UsuarioPaisAccesoDTO getUsuario_pais(Integer index) {
        return usuario_pais.get(index);
    }

    public void addUsuario_pais(UsuarioPaisAccesoDTO usuario_pais) {
        if(this.usuario_pais==null){
            this.usuario_pais=new ArrayList<>();
        }
        this.usuario_pais.add(usuario_pais);
    }

    public ArrayList<UsuarioPaisAccesoDTO> getUsuario_pais() {
        return usuario_pais;
    }

    public void setUsuario_pais(ArrayList<UsuarioPaisAccesoDTO> usuario_pais) {
        this.usuario_pais = usuario_pais;
    }
}
