package pe.edu.pucp.softpac.model;

public class UsuarioPaisAccesoDTO {
    private UsuariosDTO usuario;
    private PaisesDTO pais;
    private Boolean acceso;

    public UsuarioPaisAccesoDTO(){
        setUsuario(null);
        setPais(null);
        setAcceso(false);
    }
    public UsuarioPaisAccesoDTO(UsuariosDTO usuario, PaisesDTO pais){
        setUsuario(usuario);
        setPais(pais);
        setAcceso(true);
    }

    public PaisesDTO getPais() {
        return pais;
    }

    public void setPais(PaisesDTO pais) {
        this.pais = pais;
    }

    public Boolean getAcceso() {
        return acceso;
    }

    public void setAcceso(Boolean acceso) {
        this.acceso = acceso;
    }

    public UsuariosDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosDTO usuario) {
        this.usuario = usuario;
    }
}
