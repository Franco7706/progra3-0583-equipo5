/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;
/**
 *
 * @author Usuario
 */
public class Proveedor {
    private String proveedor_id;
    private String razon_social;
    private String ruc;
    private String direccion_fiscal;
    private String pais; // hacer clase y hacer clase moneda
    private String condicion;
    private int plazo_de_pago;
    private char activo;

    public Proveedor() {
        proveedor_id=null;
        razon_social=null;
        ruc=null;
        direccion_fiscal=null;
        pais=null;
        condicion=null;
    }

    public Proveedor(String proveedor_id, String razon_social, String ruc, String direccion_fiscal, String pais, String condicion, int plazo_de_pago, char activo) {
        this.proveedor_id = proveedor_id;
        this.razon_social = razon_social;
        this.ruc = ruc;
        this.direccion_fiscal = direccion_fiscal;
        this.pais = pais;
        this.condicion = condicion;
        this.plazo_de_pago = plazo_de_pago;
        this.activo = activo;
    }
    
    public Proveedor(Proveedor proveedor){
        this.proveedor_id = proveedor.getProveedor_id();
        this.razon_social = proveedor.getRazon_social();
        this.ruc = proveedor.getRuc();
        this.direccion_fiscal = proveedor.getDireccion_fiscal();
        this.pais = proveedor.getPais();
        this.condicion = proveedor.getCondicion();
        this.plazo_de_pago = proveedor.getPlazo_de_pago();
        this.activo = proveedor.getActivo();
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
     * @return the razon_social
     */
    public String getRazon_social() {
        return razon_social;
    }

    /**
     * @param razon_social the razon_social to set
     */
    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the direccion_fiscal
     */
    public String getDireccion_fiscal() {
        return direccion_fiscal;
    }

    /**
     * @param direccion_fiscal the direccion_fiscal to set
     */
    public void setDireccion_fiscal(String direccion_fiscal) {
        this.direccion_fiscal = direccion_fiscal;
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
     * @return the condicion
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * @param condicion the condicion to set
     */
    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    /**
     * @return the plazo_de_pago
     */
    public int getPlazo_de_pago() {
        return plazo_de_pago;
    }

    /**
     * @param plazo_de_pago the plazo_de_pago to set
     */
    public void setPlazo_de_pago(int plazo_de_pago) {
        this.plazo_de_pago = plazo_de_pago;
    }

    /**
     * @return the activo
     */
    public char getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(char activo) {
        this.activo = activo;
    }
}
