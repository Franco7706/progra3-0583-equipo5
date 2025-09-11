package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;

public interface EntidadesBancariasDAO {
    public Integer insertar(EntidadesBancariasDTO almacen);
    
    public EntidadesBancariasDTO obtenerPorId(Integer almacenId);
    
    public ArrayList<EntidadesBancariasDTO> listarTodos();
    
    public Integer modificar(EntidadesBancariasDTO almacen);
    
    public Integer eliminar(EntidadesBancariasDTO almacen);
}
