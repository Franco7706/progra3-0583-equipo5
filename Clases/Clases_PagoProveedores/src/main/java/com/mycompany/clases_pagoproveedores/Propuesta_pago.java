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
public class Propuesta_pago {
    private String propuesta_id;
    private Date fecha_hora_creacion;
    private double monto_total;
    private Moneda moneda;
    private Entidad_bancaria entidad_bancaria;

    public Propuesta_pago() {
        propuesta_id=null;
        fecha_hora_creacion=null;
        monto_total=0.0;
        entidad_bancaria=new Entidad_bancaria();
        moneda=new Moneda();
        
    }

    public Propuesta_pago(String propuesta_id, Date fecha_hora_creacion, Double monto_total, Entidad_bancaria entidad_bancaria, Moneda moneda) {
        this.propuesta_id = propuesta_id;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.monto_total = monto_total;
        this.entidad_bancaria = new Entidad_bancaria(entidad_bancaria);
        this.moneda = moneda;
    }
    
    public Propuesta_pago(Propuesta_pago propuesta_pago){
        this.propuesta_id = propuesta_pago.getPropuesta_id();
        this.fecha_hora_creacion = propuesta_pago.getFecha_hora_creacion();
        this.monto_total = propuesta_pago.getMonto_total();
        this.entidad_bancaria = new Entidad_bancaria(propuesta_pago.getEntidad_bancaria());
        this.moneda = propuesta_pago.getMoneda();
    }
    /**
     * @return the propuesta_id
     */
    public String getPropuesta_id() {
        return propuesta_id;
    }

    /**
     * @param propuesta_id the propuesta_id to set
     */
    public void setPropuesta_id(String propuesta_id) {
        this.propuesta_id = propuesta_id;
    }

    /**
     * @return the fecha_hora_creacion
     */
    public Date getFecha_hora_creacion() {
        return fecha_hora_creacion;
    }

    /**
     * @param fecha_hora_creacion the fecha_hora_creacion to set
     */
    public void setFecha_hora_creacion(Date fecha_hora_creacion) {
        this.fecha_hora_creacion = fecha_hora_creacion;
    }

    /**
     * @return the monto_total
     */
    public Double getMonto_total() {
        return monto_total;
    }

    /**
     * @param monto_total the monto_total to set
     */
    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }

    /**
     * @return the entidad_bancaria_id
     */
    public Entidad_bancaria getEntidad_bancaria() {
        return entidad_bancaria;
    }

    /**
     * @param entidad_bancaria the entidad_bancaria_id to set
     */
    public void setEntidad_bancaria(Entidad_bancaria entidad_bancaria) {
        this.entidad_bancaria = entidad_bancaria;
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
    
    
}
