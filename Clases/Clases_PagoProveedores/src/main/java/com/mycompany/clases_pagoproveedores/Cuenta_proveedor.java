/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

/**
 *
 * @author Usuario
 */
public class Cuenta_proveedor {
    private String cuenta_proveedor_id;
    private String numero_cuenta;
    private String divisa;
    private String codigo_cuenta_interbancario;
    private String proveedor_id;
    private String entidad_bancaria_id;
    private char activa;

    public Cuenta_proveedor() {
        cuenta_proveedor_id=null;
        numero_cuenta=null;
        divisa=null;
        codigo_cuenta_interbancario=null;
        proveedor_id=null;
        entidad_bancaria_id=null;
    }

    public Cuenta_proveedor(String cuenta_proveedor_id, String numero_cuenta, String divisa, String codigo_cuenta_interbancario, String proveedor_id, String entidad_bancaria_id, char activa) {
        this.cuenta_proveedor_id = cuenta_proveedor_id;
        this.numero_cuenta = numero_cuenta;
        this.divisa = divisa;
        this.codigo_cuenta_interbancario = codigo_cuenta_interbancario;
        this.proveedor_id = proveedor_id;
        this.entidad_bancaria_id = entidad_bancaria_id;
        this.activa = activa;
    }
    
    public Cuenta_proveedor(Cuenta_proveedor cuenta_proveedor){
        this.cuenta_proveedor_id = cuenta_proveedor.getCuenta_proveedor_id();
        this.numero_cuenta = cuenta_proveedor.getNumero_cuenta();
        this.divisa = cuenta_proveedor.getDivisa();
        this.codigo_cuenta_interbancario = cuenta_proveedor.getCodigo_cuenta_interbancario();
        this.proveedor_id = cuenta_proveedor.getProveedor_id();
        this.entidad_bancaria_id = cuenta_proveedor.getEntidad_bancaria_id();
        this.activa = cuenta_proveedor.getActiva();
    }

    /**
     * @return the cuenta_proveedor_id
     */
    public String getCuenta_proveedor_id() {
        return cuenta_proveedor_id;
    }

    /**
     * @param cuenta_proveedor_id the cuenta_proveedor_id to set
     */
    public void setCuenta_proveedor_id(String cuenta_proveedor_id) {
        this.cuenta_proveedor_id = cuenta_proveedor_id;
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
     * @return the divisa
     */
    public String getDivisa() {
        return divisa;
    }

    /**
     * @param divisa the divisa to set
     */
    public void setDivisa(String divisa) {
        this.divisa = divisa;
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

    /**
     * @return the proveedor_id
     */
    public String getProveedor_id() {
        return proveedor_id;
    }

    /**
     * @param proveedor_id the proveedor_id to set
     */
    public void setProveedor_id(String proveedor_id) {
        this.proveedor_id = proveedor_id;
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
    
    
}
