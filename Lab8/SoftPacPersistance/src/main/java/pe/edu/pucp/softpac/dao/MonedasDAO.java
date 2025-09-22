package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.MonedasDTO;

public interface MonedasDAO {
    public Integer insertar(MonedasDTO moneda);
    
    public MonedasDTO obtenerPorId(Integer monedaId);
    
    public ArrayList<MonedasDTO> listarTodos();
    
    public Integer modificar(MonedasDTO moneda);
    
    public Integer eliminar(MonedasDTO moneda);
}
