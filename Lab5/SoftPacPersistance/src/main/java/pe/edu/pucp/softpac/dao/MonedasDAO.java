package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.MonedasDTO;

public interface MonedasDAO {
    public Integer insertar(MonedasDTO almacen);
    
    public MonedasDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<MonedasDTO> listarTodos();
    
    public Integer modificar(MonedasDTO almacen);
    
    public Integer eliminar(MonedasDTO almacen);
}
