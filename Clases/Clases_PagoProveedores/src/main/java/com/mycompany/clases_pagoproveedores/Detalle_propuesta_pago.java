/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

/**
 *
 * @author Usuario
 */
public class Detalle_propuesta_pago {
    private String detalle_propuesta_pago_id;
    private double monto_pago;
    private String moneda_id;
    private char estado_pago;
    private char forma_pago;
    private String cuenta_proveedor_id;
    private String propuesta_pago_id;
    private String cuenta_propia_id;
    private String factura_id;

    public Detalle_propuesta_pago() {
        detalle_propuesta_pago_id=null;
        moneda_id=null;
        cuenta_proveedor_id=null;
        propuesta_pago_id=null;
        cuenta_propia_id=null;
        factura_id=null;
    }

    public Detalle_propuesta_pago(String detalle_propuesta_pago_id, double monto_pago, String moneda_id, char estado_pago, char forma_pago, String cuenta_proveedor_id, String propuesta_pago_id, String cuenta_propia_id, String factura_id) {
        this.detalle_propuesta_pago_id = detalle_propuesta_pago_id;
        this.monto_pago = monto_pago;
        this.moneda_id = moneda_id;
        this.estado_pago = estado_pago;
        this.forma_pago = forma_pago;
        this.cuenta_proveedor_id = cuenta_proveedor_id;
        this.propuesta_pago_id = propuesta_pago_id;
        this.cuenta_propia_id = cuenta_propia_id;
        this.factura_id = factura_id;
    }

    public Detalle_propuesta_pago(Detalle_propuesta_pago detalle_propuesta_pago){
        this.detalle_propuesta_pago_id = detalle_propuesta_pago.getDetalle_propuesta_pago_id();
        this.monto_pago = detalle_propuesta_pago.getMonto_pago();
        this.moneda_id = detalle_propuesta_pago.getMoneda_id();
        this.estado_pago = detalle_propuesta_pago.getEstado_pago();
        this.forma_pago = detalle_propuesta_pago.getForma_pago();
        this.cuenta_proveedor_id = detalle_propuesta_pago.getCuenta_proveedor_id();
        this.propuesta_pago_id = detalle_propuesta_pago.getPropuesta_pago_id();
        this.cuenta_propia_id = detalle_propuesta_pago.getCuenta_propia_id();
        this.factura_id = detalle_propuesta_pago.getFactura_id();
    }
    /**
     * @return the detalle_propuesta_pago_id
     */
    public String getDetalle_propuesta_pago_id() {
        return detalle_propuesta_pago_id;
    }

    /**
     * @param detalle_propuesta_pago_id the detalle_propuesta_pago_id to set
     */
    public void setDetalle_propuesta_pago_id(String detalle_propuesta_pago_id) {
        this.detalle_propuesta_pago_id = detalle_propuesta_pago_id;
    }

    /**
     * @return the monto_pago
     */
    public double getMonto_pago() {
        return monto_pago;
    }

    /**
     * @param monto_pago the monto_pago to set
     */
    public void setMonto_pago(double monto_pago) {
        this.monto_pago = monto_pago;
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
     * @return the estado_pago
     */
    public char getEstado_pago() {
        return estado_pago;
    }

    /**
     * @param estado_pago the estado_pago to set
     */
    public void setEstado_pago(char estado_pago) {
        this.estado_pago = estado_pago;
    }

    /**
     * @return the forma_pago
     */
    public char getForma_pago() {
        return forma_pago;
    }

    /**
     * @param forma_pago the forma_pago to set
     */
    public void setForma_pago(char forma_pago) {
        this.forma_pago = forma_pago;
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
     * @return the propuesta_pago_id
     */
    public String getPropuesta_pago_id() {
        return propuesta_pago_id;
    }

    /**
     * @param propuesta_pago_id the propuesta_pago_id to set
     */
    public void setPropuesta_pago_id(String propuesta_pago_id) {
        this.propuesta_pago_id = propuesta_pago_id;
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
     * @return the factura_id
     */
    public String getFactura_id() {
        return factura_id;
    }

    /**
     * @param factura_id the factura_id to set
     */
    public void setFactura_id(String factura_id) {
        this.factura_id = factura_id;
    }
    
    
}
