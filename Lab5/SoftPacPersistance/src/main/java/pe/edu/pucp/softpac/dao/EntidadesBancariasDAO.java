package pe.edu.pucp.softpac.dao;

import java.util.ArrayList;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;

public interface EntidadesBancariasDAO {
    public Integer insertar(EntidadesBancariasDTO entidadBancaria);
    
    public EntidadesBancariasDTO obtenerPorId(Integer entidadBancariaId);
    
    public ArrayList<EntidadesBancariasDTO> listarTodos();
    
    public Integer modificar(EntidadesBancariasDTO entidadBancaria);
    
    public Integer eliminar(EntidadesBancariasDTO entidadBancaria);
}
