/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clases_pagoproveedores;

import java.util.Date;

public class Clases_PagoProveedores {

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DEL SISTEMA DE PAGO A PROVEEDORES ===");

        // 1. Crear una Moneda
        Moneda monedaSoles = new Moneda();
        monedaSoles.setMoneda_id("M1");
        monedaSoles.setNombre("Sol Peruano");
        monedaSoles.setCodigo_iso("PEN");
        monedaSoles.setSimbolo("S/.");
        System.out.println("Moneda creada: " + monedaSoles.getNombre() + " (" + monedaSoles.getCodigo_iso() + ")");

        // 2. Crear una Entidad Bancaria
        Entidad_bancaria banco = new Entidad_bancaria("B1", "Banco de la Nación", "Perú", "Formato01");
        System.out.println("Entidad Bancaria: " + banco.getNombre() + " - País: " + banco.getPais());

        // 3. Crear un Proveedor
        Proveedor proveedor = new Proveedor("P1", "Proveedor SAC", "20123456789", "Av. Lima 123", "Perú", "Habido", 30, 'S');
        System.out.println("Proveedor: " + proveedor.getRazon_social() + " con RUC: " + proveedor.getRuc());

        // 4. Crear una Cuenta Propia
        Cuenta_propia cuentaPropia = new Cuenta_propia("C1", "Ahorros", "CCI123", 'S', "1234567890", monedaSoles, banco, 50000.0);
        System.out.println("Cuenta Propia creada con saldo: " + cuentaPropia.getSaldo_disponible());

        // 5. Crear una Cuenta Proveedor
        Cuenta_proveedor cuentaProveedor = new Cuenta_proveedor("C2", "Corriente", "CCI987", 'S', "0987654321", monedaSoles, banco, proveedor);
        System.out.println("Cuenta del Proveedor: " + cuentaProveedor.getProveedor().getRazon_social());

        // 6. Crear una Factura
        Factura factura = new Factura("F1", 1000.0, monedaSoles, new Date(), new Date(), new Date(), "Compra de insumos", proveedor, 1000.0, 180.0);
        System.out.println("Factura creada con total: " + factura.getMonto_total() + " " + factura.getMoneda().getSimbolo());

        // 7. Crear un Detalle Factura
        Detalle_factura detalleFactura = new Detalle_factura("DF1", monedaSoles, 800.0, "Insumos de oficina", 50.0, factura);
        System.out.println("Detalle de Factura: " + detalleFactura.getDescripcion() + " con subtotal " + detalleFactura.getSubtotal());

        // 8. Crear una Propuesta de Pago
        Propuesta_pago propuesta = new Propuesta_pago("PR1", new Date(), 1000.0, banco, monedaSoles);
        System.out.println("Propuesta de Pago creada con total: " + propuesta.getMonto_total());

        // 9. Crear un Detalle de Propuesta de Pago
        Detalle_propuesta_pago detallePago = new Detalle_propuesta_pago("DP1", 1000.0, monedaSoles, 'P', 'T', cuentaProveedor, propuesta, cuentaPropia, factura);
        System.out.println("Detalle de Propuesta Pago -> Monto: " + detallePago.getMonto_pago() +
                " Moneda: " + detallePago.getMoneda().getSimbolo() +
                " Proveedor: " + detallePago.getCuenta_proveedor().getProveedor().getRazon_social());

        // 10. Crear un Tipo de Cambio
        Tipo_de_cambio tipoCambio = new Tipo_de_cambio(new Date(), monedaSoles, new Moneda(monedaSoles), 1.0);
        System.out.println("Tipo de Cambio registrado con tasa: " + tipoCambio.getTasa());

        System.out.println("=== FIN DE PRUEBAS ===");
    }
}
