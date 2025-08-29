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
    private String moneda_id;
    private double subtotal;
    private String descripcion;
    private double monto_descuentos;
    private String factura_id;

    public Detalle_factura() {
        detalle_factura_id=null;
        moneda_id=null;
        subtotal=0.0;
        descripcion=null;
        monto_descuentos=0.0;
        factura_id=null;
    }

    public Detalle_factura(String detalle_factura_id, String moneda_id, double subtotal, String descripcion, double monto_descuentos, String factura_id) {
        this.detalle_factura_id = detalle_factura_id;
        this.moneda_id = moneda_id;
        this.subtotal = subtotal;
        this.descripcion = descripcion;
        this.monto_descuentos = monto_descuentos;
        this.factura_id = factura_id;
    }
    
    public Detalle_factura(Detalle_factura detalle_factura){
        this.detalle_factura_id = detalle_factura.getDetalle_factura_id();
        this.moneda_id = detalle_factura.getMoneda_id();
        this.subtotal = detalle_factura.getSubtotal();
        this.descripcion = detalle_factura.getDescripcion();
        this.monto_descuentos = detalle_factura.getMonto_descuentos();
        this.factura_id = detalle_factura.getFactura_id();
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
     * @return the divisa
     */
    public String getMoneda_id() {
        return moneda_id;
    }

    /**
     * @param divisa the divisa to set
     */
    public void setMoneda_id(String divisa) {
        this.moneda_id = divisa;
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
