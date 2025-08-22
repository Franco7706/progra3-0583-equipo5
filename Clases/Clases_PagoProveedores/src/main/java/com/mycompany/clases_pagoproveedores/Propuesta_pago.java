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
    private Double monto_total;
    private String entidad_bancaria_id;

    public Propuesta_pago() {
        propuesta_id=null;
        fecha_hora_creacion=null;
        monto_total=null;
        entidad_bancaria_id=null;
    }

    public Propuesta_pago(String propuesta_id, Date fecha_hora_creacion, Double monto_total, String entidad_bancaria_id) {
        this.propuesta_id = propuesta_id;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.monto_total = monto_total;
        this.entidad_bancaria_id = entidad_bancaria_id;
    }
    
    public Propuesta_pago(Propuesta_pago propuesta_pago){
        this.propuesta_id = propuesta_pago.getPropuesta_id();
        this.fecha_hora_creacion = propuesta_pago.getFecha_hora_creacion();
        this.monto_total = propuesta_pago.getMonto_total();
        this.entidad_bancaria_id = propuesta_pago.getEntidad_bancaria_id();
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
    public String getEntidad_bancaria_id() {
        return entidad_bancaria_id;
    }

    /**
     * @param entidad_bancaria_id the entidad_bancaria_id to set
     */
    public void setEntidad_bancaria_id(String entidad_bancaria_id) {
        this.entidad_bancaria_id = entidad_bancaria_id;
    }
    
    
}
