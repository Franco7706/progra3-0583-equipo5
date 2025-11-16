/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpac.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class KardexCuentasPropiasDTO {
    private CuentasPropiasDTO cuenta_propia;
    private Date fecha_modificacion;
    private BigDecimal saldo_modificacion;
    private BigDecimal saldo_resultante;
    private UsuariosDTO usuario_modificacion;

    public KardexCuentasPropiasDTO() {
    }

    public KardexCuentasPropiasDTO(CuentasPropiasDTO cuenta_propia, Date fecha_modificacion, BigDecimal saldo_modificacion, BigDecimal saldo_resultante, UsuariosDTO usuario_modificacion) {
        this.cuenta_propia = cuenta_propia;
        this.fecha_modificacion = fecha_modificacion;
        this.saldo_modificacion = saldo_modificacion;
        this.saldo_resultante = saldo_resultante;
        this.usuario_modificacion = usuario_modificacion;
    }
    
    public KardexCuentasPropiasDTO(KardexCuentasPropiasDTO kardexCuentasPropias){
        this.cuenta_propia = new CuentasPropiasDTO(kardexCuentasPropias.getCuenta_propia());
        this.fecha_modificacion = kardexCuentasPropias.getFecha_modificacion();
        this.saldo_modificacion = kardexCuentasPropias.getSaldo_modificacion();
        this.saldo_resultante = kardexCuentasPropias.getSaldo_resultante();
        this.usuario_modificacion = new UsuariosDTO(kardexCuentasPropias.getUsuario_modificacion());
    }
    
    /**
     * @return the cuenta_propia
     */
    public CuentasPropiasDTO getCuenta_propia() {
        return cuenta_propia;
    }

    /**
     * @param cuenta_propia the cuenta_propia to set
     */
    public void setCuenta_propia(CuentasPropiasDTO cuenta_propia) {
        this.cuenta_propia = cuenta_propia;
    }

    /**
     * @return the fecha_modificacion
     */
    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    /**
     * @param fecha_modificacion the fecha_modificacion to set
     */
    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    /**
     * @return the saldo_modificacion
     */
    public BigDecimal getSaldo_modificacion() {
        return saldo_modificacion;
    }

    /**
     * @param saldo_modificacion the saldo_modificacion to set
     */
    public void setSaldo_modificacion(BigDecimal saldo_modificacion) {
        this.saldo_modificacion = saldo_modificacion;
    }

    /**
     * @return the usuario_modificacion
     */
    public UsuariosDTO getUsuario_modificacion() {
        return usuario_modificacion;
    }

    /**
     * @param usuario_modificacion the usuario_modificacion to set
     */
    public void setUsuario_modificacion(UsuariosDTO usuario_modificacion) {
        this.usuario_modificacion = usuario_modificacion;
    }

    /**
     * @return the saldo_resultante
     */
    public BigDecimal getSaldo_resultante() {
        return saldo_resultante;
    }

    /**
     * @param saldo_resultante the saldo_resultante to set
     */
    public void setSaldo_resultante(BigDecimal saldo_resultante) {
        this.saldo_resultante = saldo_resultante;
    }
    
}
