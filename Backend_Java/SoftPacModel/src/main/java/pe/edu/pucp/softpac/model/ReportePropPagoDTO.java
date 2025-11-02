/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpac.model;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ReportePropPagoDTO {
    private int idPropuesta;
    private String estado;
    private Date fechaCreacion;
    private String usuarioCreador;
    private String correoUsuarioCreador;
    private String pais;
    private String bancoPropuesta;
    private int totalPagos;
    private ArrayList<ReportePagoDTO> pagos = new ArrayList<>();

    public ReportePropPagoDTO() {
        idPropuesta = 0;
        estado = null;
        fechaCreacion = null;
        usuarioCreador = null;
        correoUsuarioCreador = null;
        pais = null;
        bancoPropuesta = null;
        totalPagos = 0;
        pagos = new ArrayList<>();
    }

    // Getters y Setters
    public int getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(int idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getBancoPropuesta() {
        return bancoPropuesta;
    }

    public void setBancoPropuesta(String bancoPropuesta) {
        this.bancoPropuesta = bancoPropuesta;
    }

    public int getTotalPagos() {
        return totalPagos;
    }

    public void setTotalPagos(int totalPagos) {
        this.totalPagos = totalPagos;
    }

    public ArrayList<ReportePagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<ReportePagoDTO> pagos) {
        this.pagos = pagos;
    }

    public void addPago(ReportePagoDTO pago) {
        if(this.pagos==null) this.pagos=new ArrayList<>();
        this.pagos.add(pago);
        this.totalPagos = this.pagos.size();
    }

    /**
     * @return the correoUsuarioCreador
     */
    public String getCorreoUsuarioCreador() {
        return correoUsuarioCreador;
    }

    /**
     * @param correoUsuarioCreador the correoUsuarioCreador to set
     */
    public void setCorreoUsuarioCreador(String correoUsuarioCreador) {
        this.correoUsuarioCreador = correoUsuarioCreador;
    }
}