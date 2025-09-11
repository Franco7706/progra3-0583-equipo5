/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clases_pagoproveedores;

/**
 *
 * @author Usuario
 */
public class Cuenta_proveedor extends Cuenta_bancaria {
    private Proveedor proveedor;
    public Cuenta_proveedor(){
        super();
        proveedor=null;
    }
    
    public Cuenta_proveedor(String cuenta_bancaria_id, String tipo_cuenta, String cci, char activa, String numero_cuenta, Moneda moneda, Entidad_bancaria entidad_bancaria, Proveedor proveedor){
        super(cuenta_bancaria_id,tipo_cuenta,cci,activa,numero_cuenta,moneda,entidad_bancaria);
        this.proveedor=new Proveedor(proveedor);
    }
    
    public Cuenta_proveedor(Cuenta_proveedor cuenta_proveedor){
        super(cuenta_proveedor);
        this.proveedor=new Proveedor(cuenta_proveedor.getProveedor());
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

    
}
