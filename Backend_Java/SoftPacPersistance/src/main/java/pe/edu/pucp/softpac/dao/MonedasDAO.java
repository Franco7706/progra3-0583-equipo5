package pe.edu.pucp.softpac.dao;

import java.util.List;
import pe.edu.pucp.softpac.model.MonedasDTO;

public interface MonedasDAO {
    public Integer insertar(MonedasDTO moneda);
    
    public MonedasDTO obtenerPorId(Integer monedaId);
    
    public List<MonedasDTO> listarTodos();
    
    public Integer modificar(MonedasDTO moneda);
    
    public Integer eliminar(MonedasDTO moneda);

    public MonedasDTO obtenerPorDivisa(String divisaMoneda);

}
