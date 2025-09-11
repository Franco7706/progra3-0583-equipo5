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
    private Moneda moneda_origen;
    private Moneda moneda_destino;
    private double tasa;
    
    public Tipo_de_cambio(){
        fecha=null;
        moneda_origen=null;
        moneda_destino=null;
        tasa=0.0;
    }
    
    public Tipo_de_cambio(Date fecha, Moneda moneda_origen, Moneda moneda_destino, double tasa){
        this.fecha = fecha;
        this.moneda_destino = new Moneda(moneda_destino);
        this.moneda_origen = new Moneda(moneda_origen);
        this.tasa = tasa;
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
    public Moneda getMoneda_origen() {
        return moneda_origen;
    }

    /**
     * @param moneda_origen the moneda_origen to set
     */
    public void setMoneda_origen(Moneda moneda_origen) {
        this.moneda_origen = moneda_origen;
    }

    /**
     * @return the moneda_destino
     */
    public Moneda getMoneda_destino() {
        return moneda_destino;
    }

    /**
     * @param moneda_destino the moneda_destino to set
     */
    public void setMoneda_destino(Moneda moneda_destino) {
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
