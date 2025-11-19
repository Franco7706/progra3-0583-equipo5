package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.KardexCuentasPropiasDTO;

public interface KardexCuentasPropiasDAO {
    public ArrayList<KardexCuentasPropiasDTO>obtenerPorUsuario(int usuarioId);
    public ArrayList<KardexCuentasPropiasDTO>obtenerPorCuentaPropia(int cuentaPropiaId);
    public int insertar(KardexCuentasPropiasDTO kardexCuentasPropias);
}
