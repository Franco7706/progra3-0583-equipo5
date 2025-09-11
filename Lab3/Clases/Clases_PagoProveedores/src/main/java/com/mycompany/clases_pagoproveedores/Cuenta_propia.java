/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

/**
 *
 * @author Usuario
 */
public class Cuenta_propia extends Cuenta_bancaria{
    private double saldo_disponible;
    public Cuenta_propia(){
        super();
        saldo_disponible=0.0;
    }
    
    public Cuenta_propia(String cuenta_bancaria_id, String tipo_cuenta, String cci, char activa, String numero_cuenta, Moneda moneda, Entidad_bancaria entidad_bancaria, double saldo_disponible){
        super(cuenta_bancaria_id,tipo_cuenta,cci,activa,numero_cuenta,moneda,entidad_bancaria);
        this.saldo_disponible=saldo_disponible;
    }

    /**
     * @return the saldo_disponible
     */
    public double getSaldo_disponible() {
        return saldo_disponible;
    }

    /**
     * @param saldo_disponible the saldo_disponible to set
     */
    public void setSaldo_disponible(double saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }
}
