/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

/**
 *
 * @author Usuario
 */
public class Moneda {
    private String moneda_id;
    private String nombre;
    private String codigo_iso;
    private String simbolo;

    public Moneda(){
        moneda_id=null;
        nombre=null;
        codigo_iso=null;
        simbolo=null;
    }
    
    public Moneda(Moneda moneda){
        this.codigo_iso = moneda.codigo_iso;
        this.moneda_id = moneda.moneda_id;
        this.nombre = moneda.nombre;
        this.simbolo = moneda.simbolo;
    }
    
    /**
     * @return the moneda_id
     */
    public String getMoneda_id() {
        return moneda_id;
    }

    /**
     * @param moneda_id the moneda_id to set
     */
    public void setMoneda_id(String moneda_id) {
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
}
