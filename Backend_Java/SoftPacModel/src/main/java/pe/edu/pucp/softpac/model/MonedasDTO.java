package pe.edu.pucp.softpac.model;

import java.util.ArrayList;

public class MonedasDTO {
    private Integer moneda_id;
    private String nombre;
    private String codigo_iso;
    private String simbolo;
    private ArrayList<TiposDeCambioDTO> tipos_cambio;

    public MonedasDTO(){
        moneda_id=null;
        nombre=null;
        codigo_iso=null;
        simbolo=null;
        tipos_cambio=new ArrayList<>();
    }

    public MonedasDTO(Integer moneda_id, String nombre, String codigo_iso, String simbolo){
        this.moneda_id=moneda_id;
        this.nombre=nombre;
        this.codigo_iso=codigo_iso;
        this.simbolo=simbolo;
    }

    public MonedasDTO(Integer moneda_id, String nombre, String codigo_iso, String simbolo, ArrayList<TiposDeCambioDTO> tipos_cambio){
        this.moneda_id=moneda_id;
        this.nombre=nombre;
        this.codigo_iso=codigo_iso;
        this.simbolo=simbolo;
        this.tipos_cambio=tipos_cambio;
    }

    public MonedasDTO(MonedasDTO moneda){
        this.codigo_iso = moneda.codigo_iso;
        this.moneda_id = moneda.moneda_id;
        this.nombre = moneda.nombre;
        this.simbolo = moneda.simbolo;
        this.tipos_cambio=moneda.tipos_cambio;
    }
    
    /**
     * @return the moneda_id
     */
    public Integer getMoneda_id() {
        return moneda_id;
    }

    /**
     * @param moneda_id the moneda_id to set
     */
    public void setMoneda_id(Integer moneda_id) {
        this.moneda_id = moneda_id;
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
     * @return the codigo_iso
     */
    public String getCodigo_iso() {
        return codigo_iso;
    }

    /**
     * @param codigo_iso the codigo_iso to set
     */
    public void setCodigo_iso(String codigo_iso) {
        this.codigo_iso = codigo_iso;
    }

    /**
     * @return the simbolo
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * @param simbolo the simbolo to set
     */
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public ArrayList<TiposDeCambioDTO> getTipos_cambio() {
        return tipos_cambio;
    }

    public void setTipos_cambio(ArrayList<TiposDeCambioDTO> tipos_cambio) {
        this.tipos_cambio = tipos_cambio;
    }

    public TiposDeCambioDTO getTipo_de_cambio(Integer index) {
        return tipos_cambio.get(index);
    }

    public void addTipo_de_cambio(TiposDeCambioDTO tipo){
        if(tipos_cambio==null){
            tipos_cambio=new ArrayList<>();
        }
        this.tipos_cambio.add(tipo);
    }
}
