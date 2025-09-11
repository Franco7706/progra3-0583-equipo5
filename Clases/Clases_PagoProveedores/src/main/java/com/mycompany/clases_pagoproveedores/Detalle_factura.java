/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

/**
 *
 * @author Usuario
 */
public class Detalle_factura {
    private String detalle_factura_id;
    private Moneda moneda;
    private double subtotal;
    private String descripcion;
    private double monto_descuentos;
    private Factura factura;

    public Detalle_factura() {
        detalle_factura_id=null;
        moneda=null;
        subtotal=0.0;
        descripcion=null;
        monto_descuentos=0.0;
        factura=null;
    }

    public Detalle_factura(String detalle_factura_id, Moneda moneda, double subtotal, String descripcion, double monto_descuentos, Factura factura) {
        this.detalle_factura_id = detalle_factura_id;
        this.moneda = moneda;
        this.subtotal = subtotal;
        this.descripcion = descripcion;
        this.monto_descuentos = monto_descuentos;
        this.factura = factura;
    }
    
    public Detalle_factura(Detalle_factura detalle_factura){
        this.detalle_factura_id = detalle_factura.getDetalle_factura_id();
        this.moneda = detalle_factura.getMoneda();
        this.subtotal = detalle_factura.getSubtotal();
        this.descripcion = detalle_factura.getDescripcion();
        this.monto_descuentos = detalle_factura.getMonto_descuentos();
        this.factura = detalle_factura.getFactura();
    }

    /**
     * @return the detalle_factura_id
     */
    public String getDetalle_factura_id() {
        return detalle_factura_id;
    }

    /**
     * @param detalle_factura_id the detalle_factura_id to set
     */
    public void setDetalle_factura_id(String detalle_factura_id) {
        this.detalle_factura_id = detalle_factura_id;
    }

    /**
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the monto_descuentos
     */
    public double getMonto_descuentos() {
        return monto_descuentos;
    }

    /**
     * @param monto_descuentos the monto_descuentos to set
     */
    public void setMonto_descuentos(double monto_descuentos) {
        this.monto_descuentos = monto_descuentos;
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
