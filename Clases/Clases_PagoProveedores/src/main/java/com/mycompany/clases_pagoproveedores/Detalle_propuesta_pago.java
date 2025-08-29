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
    private Moneda moneda;
    private char estado_pago;
    private char forma_pago;
    private Cuenta_proveedor cuenta_proveedor;
    private Propuesta_pago propuesta_pago;
    private Cuenta_propia cuenta_propia;
    private Factura factura;

    public Detalle_propuesta_pago() {
        detalle_propuesta_pago_id=null;
        moneda=null;
        cuenta_proveedor=null;
        propuesta_pago=null;
        cuenta_propia=null;
        factura=null;
        monto_pago=0.0;
        estado_pago='P';
        forma_pago='T';
    }

    public Detalle_propuesta_pago(String detalle_propuesta_pago_id, double monto_pago, Moneda moneda, char estado_pago, char forma_pago, Cuenta_proveedor cuenta_proveedor, Propuesta_pago propuesta_pago, Cuenta_propia cuenta_propia, Factura factura) {
        this.detalle_propuesta_pago_id = detalle_propuesta_pago_id;
        this.monto_pago = monto_pago;
        this.moneda = moneda;
        this.estado_pago = estado_pago;
        this.forma_pago = forma_pago;
        this.cuenta_proveedor = cuenta_proveedor;
        this.propuesta_pago = propuesta_pago;
        this.cuenta_propia = cuenta_propia;
        this.factura = factura;
    }

    public Detalle_propuesta_pago(Detalle_propuesta_pago detalle_propuesta_pago){
        this.detalle_propuesta_pago_id = detalle_propuesta_pago.getDetalle_propuesta_pago_id();
        this.monto_pago = detalle_propuesta_pago.getMonto_pago();
        this.moneda = detalle_propuesta_pago.getMoneda();
        this.estado_pago = detalle_propuesta_pago.getEstado_pago();
        this.forma_pago = detalle_propuesta_pago.getForma_pago();
        this.cuenta_proveedor = detalle_propuesta_pago.getCuenta_proveedor();
        this.propuesta_pago = detalle_propuesta_pago.getPropuesta_pago();
        this.cuenta_propia = detalle_propuesta_pago.getCuenta_propia();
        this.factura = detalle_propuesta_pago.getFactura();
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
     * @return the cuenta_proveedor
     */
    public Cuenta_proveedor getCuenta_proveedor() {
        return cuenta_proveedor;
    }

    /**
     * @param cuenta_proveedor the cuenta_proveedor to set
     */
    public void setCuenta_proveedor(Cuenta_proveedor cuenta_proveedor) {
        this.cuenta_proveedor = cuenta_proveedor;
    }

    /**
     * @return the propuesta_pago
     */
    public Propuesta_pago getPropuesta_pago() {
        return propuesta_pago;
    }

    /**
     * @param propuesta_pago the propuesta_pago to set
     */
    public void setPropuesta_pago(Propuesta_pago propuesta_pago) {
        this.propuesta_pago = propuesta_pago;
    }

    /**
     * @return the cuenta_propia
     */
    public Cuenta_propia getCuenta_propia() {
        return cuenta_propia;
    }

    /**
     * @param cuenta_propia the cuenta_propia to set
     */
    public void setCuenta_propia(Cuenta_propia cuenta_propia) {
        this.cuenta_propia = cuenta_propia;
    }

    /**
     * @return the factura
     */
    public Factura getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    
}
