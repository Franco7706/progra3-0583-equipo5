/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Factura {
    private String factura_id;
    private double monto_total;
    private String moneda_id;
    private Date fecha_emision;
    private Date fecha_limite_pago;
    private Date fecha_recepcion;
    private String motivo;
    private String proveedor_id;
    private double monto_restante;

    public Factura() {
        factura_id=null;
        moneda_id=null;
        fecha_emision=null;
        fecha_limite_pago=null;
        fecha_recepcion=null;
        motivo=null;
        proveedor_id=null;
    }

    public Factura(String factura_id, double monto_total, String moneda_id, Date fecha_emision, Date fecha_limite_pago, Date fecha_recepcion, String motivo, String proveedor_id, double monto_restante) {
        this.factura_id = factura_id;
        this.monto_total = monto_total;
        this.moneda_id = moneda_id;
        this.fecha_emision = fecha_emision;
        this.fecha_limite_pago = fecha_limite_pago;
        this.fecha_recepcion = fecha_recepcion;
        this.motivo = motivo;
        this.proveedor_id = proveedor_id;
        this.monto_restante = monto_restante;
    }
    
    public Factura(Factura factura){
        this.factura_id = factura.getFactura_id();
        this.monto_total = factura.getMonto_total();
        this.moneda_id = factura.getMoneda_id();
        this.fecha_emision = factura.getFecha_emision();
        this.fecha_limite_pago = factura.getFecha_limite_pago();
        this.fecha_recepcion = factura.getFecha_recepcion();
        this.motivo = factura.getMotivo();
        this.proveedor_id = factura.getProveedor_id();
        this.monto_restante = factura.getMonto_restante();
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

    /**
     * @return the monto_total
     */
    public double getMonto_total() {
        return monto_total;
    }

    /**
     * @param monto_total the monto_total to set
     */
    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
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
     * @return the fecha_emision
     */
    public Date getFecha_emision() {
        return fecha_emision;
    }

    /**
     * @param fecha_emision the fecha_emision to set
     */
    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    /**
     * @return the fecha_limite_pago
     */
    public Date getFecha_limite_pago() {
        return fecha_limite_pago;
    }

    /**
     * @param fecha_limite_pago the fecha_limite_pago to set
     */
    public void setFecha_limite_pago(Date fecha_limite_pago) {
        this.fecha_limite_pago = fecha_limite_pago;
    }

    /**
     * @return the fecha_recepcion
     */
    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    /**
     * @param fecha_recepcion the fecha_recepcion to set
     */
    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the provedor_id
     */
    public String getProveedor_id() {
        return proveedor_id;
    }

    /**
     * @param provedor_id the provedor_id to set
     */
    public void setProveedor_id(String provedor_id) {
        this.proveedor_id = provedor_id;
    }

    /**
     * @return the monto_restante
     */
    public double getMonto_restante() {
        return monto_restante;
    }

    /**
     * @param monto_restante the monto_restante to set
     */
    public void setMonto_restante(double monto_restante) {
        this.monto_restante = monto_restante;
    }
    
    
}
