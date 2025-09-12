package pe.edu.pucp.softpac.model;

public class UsuarioPaisAccesoDTO {
    private PaisesDTO pais;
    private Boolean acceso;

    public UsuarioPaisAccesoDTO(){
        setPais(null);
        setAcceso(false);
    }
    public UsuarioPaisAccesoDTO(PaisesDTO pais){
        this.setPais(pais);
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
}
