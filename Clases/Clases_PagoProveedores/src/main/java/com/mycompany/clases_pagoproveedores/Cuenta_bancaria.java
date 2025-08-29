package com.mycompany.clases_pagoproveedores;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public abstract class Cuenta_bancaria {
    private String cuenta_bancaria_id;
    private String tipo_cuenta;
    private String cci;
    private char activa;
    private String numero_cuenta;
    private Moneda moneda;
    private Entidad_bancaria entidad_bancaria;
    
    public Cuenta_bancaria(){
        cuenta_bancaria_id=null;
        tipo_cuenta=null;
        cci=null;
        activa='N';
        numero_cuenta=null;
        moneda=null;
        entidad_bancaria=null;
    }
    
    public Cuenta_bancaria(String cuenta_bancaria_id, String tipo_cuenta, String cci, char activa, String numero_cuenta, Moneda moneda, Entidad_bancaria entidad_bancaria){
        this.cuenta_bancaria_id=cuenta_bancaria_id;
        this.tipo_cuenta=tipo_cuenta;
        this.cci=cci;
        this.activa=activa;
        this.numero_cuenta=numero_cuenta;
        this.moneda=new Moneda(moneda);
        this.entidad_bancaria=new Entidad_bancaria(entidad_bancaria);
    }
    
    public Cuenta_bancaria(Cuenta_bancaria cuenta_bancaria){
        this.cuenta_bancaria_id=cuenta_bancaria.getCuenta_bancaria_id();
        this.tipo_cuenta=cuenta_bancaria.getTipo_cuenta();
        this.cci=cuenta_bancaria.getCci();
        this.activa=cuenta_bancaria.getActiva();
        this.numero_cuenta=cuenta_bancaria.getNumero_cuenta();
        this.moneda=new Moneda(cuenta_bancaria.getMoneda());
        this.entidad_bancaria=new Entidad_bancaria(cuenta_bancaria.getEntidad_bancaria());
    }
    
    /**
     * @return the cuenta_bancaria_id
     */
    public String getCuenta_bancaria_id() {
        return cuenta_bancaria_id;
    }

    /**
     * @param cuenta_bancaria_id the cuenta_bancaria_id to set
     */
    public void setCuenta_bancaria_id(String cuenta_bancaria_id) {
        this.cuenta_bancaria_id = cuenta_bancaria_id;
    }

    /**
     * @return the tipo_cuenta
     */
    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    /**
     * @param tipo_cuenta the tipo_cuenta to set
     */
    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    /**
     * @return the cci
     */
    public String getCci() {
        return cci;
    }

    /**
     * @param cci the cci to set
     */
    public void setCci(String cci) {
        this.cci = cci;
    }

    /**
     * @return the activa
     */
    public char getActiva() {
        return activa;
    }

    /**
     * @param activa the activa to set
     */
    public void setActiva(char activa) {
        this.activa = activa;
    }

    /**
     * @return the numero_cuenta
     */
    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    /**
     * @param numero_cuenta the numero_cuenta to set
     */
    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the entidad_bancaria
     */
    public Entidad_bancaria getEntidad_bancaria() {
        return entidad_bancaria;
    }

    /**
     * @param entidad_bancaria the entidad_bancaria to set
     */
    public void setEntidad_bancaria(Entidad_bancaria entidad_bancaria) {
        this.entidad_bancaria = entidad_bancaria;
    }
    
    
}
