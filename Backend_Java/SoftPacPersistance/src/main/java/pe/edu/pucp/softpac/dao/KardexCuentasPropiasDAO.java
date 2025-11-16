/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.KardexCuentasPropiasDTO;

/**
 *
 * @author Usuario
 */
public interface KardexCuentasPropiasDAO {
    public ArrayList<KardexCuentasPropiasDTO>obtenerPorUsuario(int usuarioId);
    public ArrayList<KardexCuentasPropiasDTO>obtenerPorCuentaPropia(int cuentaPropiaId);
    public int insertar(KardexCuentasPropiasDTO kardexCuentasPropias);
}
