package pe.edu.pucp.softpac.model;

public class PaisesDTO {
    private Integer pais_id;
    private String nombre;
    private String codigo_iso;
    private String codigo_telefonico;

    public PaisesDTO() {
        setPais_id(null);
        setNombre(null);
        setCodigo_iso(null);
        setCodigo_telefonico(null);
    }

    public PaisesDTO(Integer pais_id, String nombre, String codigo_iso, String codigo_telefonico) {
        this.pais_id = pais_id;
        this.nombre = nombre;
        this.codigo_iso = codigo_iso;
        this.codigo_telefonico = codigo_telefonico;
    }

    public PaisesDTO(PaisesDTO pais){
        this.pais_id = pais.getPais_id();
        this.nombre = pais.getNombre();
        this.codigo_iso = pais.getCodigo_iso();
        this.codigo_telefonico = pais.getCodigo_telefonico();
    }

    public Integer getPais_id() {
        return pais_id;
    }

    public void setPais_id(Integer pais_id) {
        this.pais_id = pais_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo_iso() {
        return codigo_iso;
    }

    public void setCodigo_iso(String codigo_iso) {
        this.codigo_iso = codigo_iso;
    }

    public String getCodigo_telefonico() {
        return codigo_telefonico;
    }

    public void setCodigo_telefonico(String codigo_telefonico) {
        this.codigo_telefonico = codigo_telefonico;
    }
}
