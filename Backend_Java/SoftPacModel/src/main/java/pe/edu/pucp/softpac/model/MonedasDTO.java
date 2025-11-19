package pe.edu.pucp.softpac.model;

public class MonedasDTO {
    private Integer moneda_id;
    private String nombre;
    private String codigo_iso;
    private String simbolo;
    
    public MonedasDTO(){
        moneda_id=null;
        nombre=null;
        codigo_iso=null;
        simbolo=null;
    }
    
    public MonedasDTO(Integer moneda_id, String nombre, String codigo_iso, String simbolo){
        this.moneda_id=moneda_id;
        this.nombre=nombre;
        this.codigo_iso=codigo_iso;
        this.simbolo=simbolo;
    }
    
    public MonedasDTO(MonedasDTO moneda){
        this.codigo_iso = moneda.codigo_iso;
        this.moneda_id = moneda.moneda_id;
        this.nombre = moneda.nombre;
        this.simbolo = moneda.simbolo;
    }
    
    // Getters y Setters
    public Integer getMoneda_id() {
        return moneda_id;
    }
    
    public void setMoneda_id(Integer moneda_id) {
        this.moneda_id = moneda_id;
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
    
    public String getSimbolo() {
        return simbolo;
    }
    
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
}