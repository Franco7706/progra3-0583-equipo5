package pe.edu.pucp.softpac.model;

public class EntidadesBancariasDTO {
    private Integer entidad_bancaria_id;
    private String nombre;
    private String formato_aceptado;
    private String codigo_swift;
    private PaisesDTO pais;

    public EntidadesBancariasDTO() {
        entidad_bancaria_id=null;
        nombre=null;
        pais=null;
        formato_aceptado=null;
    }

    public EntidadesBancariasDTO(Integer entidad_bancaria_id, String nombre, PaisesDTO pais, String formato_aceptado) {
        this.entidad_bancaria_id = entidad_bancaria_id;
        this.nombre = nombre;
        this.pais = new PaisesDTO(pais);
        this.formato_aceptado = formato_aceptado;
    }
    
    public EntidadesBancariasDTO(EntidadesBancariasDTO entidad_bancaria){
        this.entidad_bancaria_id = entidad_bancaria.getEntidad_bancaria_id();
        this.nombre = entidad_bancaria.getNombre();
        this.pais = new PaisesDTO(entidad_bancaria.getPais());
        this.formato_aceptado = entidad_bancaria.getFormato_aceptado();
    }

    /**
     * @return the entidad_bancaria_id
     */
    public Integer getEntidad_bancaria_id() {
        return entidad_bancaria_id;
    }

    /**
     * @param entidad_bancaria_id the entidad_bancaria_id to set
     */
    public void setEntidad_bancaria_id(Integer entidad_bancaria_id) {
        this.entidad_bancaria_id = entidad_bancaria_id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the pais
     */
    public PaisesDTO getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(PaisesDTO pais) {
        this.pais = pais;
    }

    /**
     * @return the formato_aceptado
     */
    public String getFormato_aceptado() {
        return formato_aceptado;
    }

    /**
     * @param formato_aceptado the formato_aceptado to set
     */
    public void setFormato_aceptado(String formato_aceptado) {
        this.formato_aceptado = formato_aceptado;
    }


    public String getCodigo_swift() {
        return codigo_swift;
    }

    public void setCodigo_swift(String codigo_swift) {
        this.codigo_swift = codigo_swift;
    }
}
