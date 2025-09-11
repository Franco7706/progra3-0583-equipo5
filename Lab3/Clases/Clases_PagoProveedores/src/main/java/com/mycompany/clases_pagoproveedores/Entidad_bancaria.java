/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

/**
 *
 * @author Usuario
 */
public class Entidad_bancaria {
    private String entidad_bancaria_id;
    private String nombre;
    private String pais;
    private String formato_aceptado;

    public Entidad_bancaria() {
        entidad_bancaria_id=null;
        nombre=null;
        pais=null;
        formato_aceptado=null;
    }

    public Entidad_bancaria(String entidad_bancaria_id, String nombre, String pais, String formato_aceptado) {
        this.entidad_bancaria_id = entidad_bancaria_id;
        this.nombre = nombre;
        this.pais = pais;
        this.formato_aceptado = formato_aceptado;
    }
    
    public Entidad_bancaria(Entidad_bancaria entidad_bancaria){
        this.entidad_bancaria_id = entidad_bancaria.getEntidad_bancaria_id();
        this.nombre = entidad_bancaria.getNombre();
        this.pais = entidad_bancaria.getPais();
        this.formato_aceptado = entidad_bancaria.getFormato_aceptado();
    }

    /**
     * @return the entidad_bancaria_id
     */
    public String getEntidad_bancaria_id() {
        return entidad_bancaria_id;
    }

    /**
     * @param entidad_bancaria_id the entidad_bancaria_id to set
     */
    public void setEntidad_bancaria_id(String entidad_bancaria_id) {
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
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
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
    
    
}
