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
    private Moneda moneda;
    private Date fecha_emision;
    private Date fecha_limite_pago;
    private Date fecha_recepcion;
    private String motivo;
    private Proveedor proveedor;
    private double monto_restante;
    private double igv;

    public Factura() {
        factura_id=null;
        moneda = new Moneda();
        fecha_emision=null;
        fecha_limite_pago=null;
        fecha_recepcion=null;
        motivo=null;
        proveedor=new Proveedor();
        igv=0.0;
    }

    public Factura(String factura_id, double monto_total, Moneda moneda, Date fecha_emision, Date fecha_limite_pago, Date fecha_recepcion, String motivo, Proveedor proveedor, double monto_restante, double igv) {
        this.factura_id = factura_id;
        this.monto_total = monto_total;
        this.moneda = new Moneda(moneda);
        this.fecha_emision = fecha_emision;
        this.fecha_limite_pago = fecha_limite_pago;
        this.fecha_recepcion = fecha_recepcion;
        this.motivo = motivo;
        this.proveedor = proveedor;
        this.monto_restante = monto_restante;
        this.igv = igv;
    }
    
    public Factura(Factura factura){
        this.factura_id = factura.getFactura_id();
        this.monto_total = factura.getMonto_total();
        this.moneda = new Moneda(factura.getMoneda());
        this.fecha_emision = factura.getFecha_emision();
        this.fecha_limite_pago = factura.getFecha_limite_pago();
        this.fecha_recepcion = factura.getFecha_recepcion();
        this.motivo = factura.getMotivo();
        this.proveedor = new Proveedor(factura.getProveedor());
        this.monto_restante = factura.getMonto_restante();
        this.igv = factura.getIgv();
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
     * @return moneda
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
     * @return the proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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

    /**
     * @return the igv
     */
    public double getIgv() {
        return igv;
    }

    /**
     * @param igv the igv to set
     */
    public void setIgv(double igv) {
        this.igv = igv;
    }
    
    
}
