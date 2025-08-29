/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

/**
 *
 * @author Usuario
 */
public class Cuenta_propia {
    private String cuenta_propia_id;
    private String tipo_cuenta;
    private String moneda_id;
    private double saldo_disponible;
    private String entidad_bancaria_id;
    private char activa; // A (Activa) | I (Inactiva)
    private String numero_cuenta;
    private String codigo_cuenta_interbancario;

    public Cuenta_propia() {
        cuenta_propia_id=null;
        tipo_cuenta=null;
        moneda_id=null;
        entidad_bancaria_id=null;
        numero_cuenta=null;
        codigo_cuenta_interbancario=null;
        activa='A';
        saldo_disponible=0.0;
    }

    public Cuenta_propia(String cuenta_propia_id, String tipo_cuenta, String moneda_id, double saldo_disponible, String entidad_bancaria_id, char activa, String numero_cuenta, String codigo_cuenta_interbancario) {
        this.cuenta_propia_id = cuenta_propia_id;
        this.tipo_cuenta = tipo_cuenta;
        this.moneda_id = moneda_id;
        this.saldo_disponible = saldo_disponible;
        this.entidad_bancaria_id = entidad_bancaria_id;
        this.activa = activa;
        this.numero_cuenta = numero_cuenta;
        this.codigo_cuenta_interbancario = codigo_cuenta_interbancario;
    }
    
    public Cuenta_propia(Cuenta_propia cuenta_propia){
        this.cuenta_propia_id = cuenta_propia.getCuenta_propia_id();
        this.tipo_cuenta = cuenta_propia.getTipo_cuenta();
        this.moneda_id = cuenta_propia.getMoneda_id();
        this.saldo_disponible = cuenta_propia.getSaldo_disponible();
        this.entidad_bancaria_id = cuenta_propia.getEntidad_bancaria_id();
        this.activa = cuenta_propia.getActiva();
        this.numero_cuenta = cuenta_propia.getNumero_cuenta();
        this.codigo_cuenta_interbancario = cuenta_propia.getCodigo_cuenta_interbancario();
    }

    /**
     * @return the cuenta_propia_id
     */
    public String getCuenta_propia_id() {
        return cuenta_propia_id;
    }

    /**
     * @param cuenta_propia_id the cuenta_propia_id to set
     */
    public void setCuenta_propia_id(String cuenta_propia_id) {
        this.cuenta_propia_id = cuenta_propia_id;
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
     * @return the saldo_disponible
     */
    public double getSaldo_disponible() {
        return saldo_disponible;
    }

    /**
     * @param saldo_disponible the saldo_disponible to set
     */
    public void setSaldo_disponible(double saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
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
     * @return the codigo_cuenta_interbancario
     */
    public String getCodigo_cuenta_interbancario() {
        return codigo_cuenta_interbancario;
    }

    /**
     * @param codigo_cuenta_interbancario the codigo_cuenta_interbancario to set
     */
    public void setCodigo_cuenta_interbancario(String codigo_cuenta_interbancario) {
        this.codigo_cuenta_interbancario = codigo_cuenta_interbancario;
    }
    
    
}
