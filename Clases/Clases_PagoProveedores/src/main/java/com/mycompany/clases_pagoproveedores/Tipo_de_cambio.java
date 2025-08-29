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
public class Tipo_de_cambio {
    private Date fecha;
    private String moneda_origen;
    private String moneda_destino;
    private double tasa;
    
    public Tipo_de_cambio(){
        fecha=null;
        moneda_origen=null;
        moneda_destino=null;
        tasa=0.0;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the moneda_origen
     */
    public String getMoneda_origen() {
        return moneda_origen;
    }

    /**
     * @param moneda_origen the moneda_origen to set
     */
    public void setMoneda_origen(String moneda_origen) {
        this.moneda_origen = moneda_origen;
    }

    /**
     * @return the moneda_destino
     */
    public String getMoneda_destino() {
        return moneda_destino;
    }

    /**
     * @param moneda_destino the moneda_destino to set
     */
    public void setMoneda_destino(String moneda_destino) {
        this.moneda_destino = moneda_destino;
    }

    /**
     * @return the tasa
     */
    public double getTasa() {
        return tasa;
    }

    /**
     * @param tasa the tasa to set
     */
    public void setTasa(double tasa) {
        this.tasa = tasa;
    }
}
