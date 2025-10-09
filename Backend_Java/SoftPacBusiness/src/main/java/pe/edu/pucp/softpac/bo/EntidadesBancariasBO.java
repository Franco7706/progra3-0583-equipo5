package pe.edu.pucp.softpac.bo;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.EntidadesBancariasDAO;
import pe.edu.pucp.softpac.daoImpl.EntidadesBancariasDAOImpl;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;

public class EntidadesBancariasBO {
    private EntidadesBancariasDAO entidadesBancariasDAO;
    
    public EntidadesBancariasBO(){
        entidadesBancariasDAO = new EntidadesBancariasDAOImpl();
    }
    
    public ArrayList<EntidadesBancariasDTO> listarTodos(){
        return (ArrayList<EntidadesBancariasDTO>) this.entidadesBancariasDAO.listarTodos();
    }
    
    public EntidadesBancariasDTO obtenerPorId(Integer entidades_bancarias_id){
        EntidadesBancariasDTO entidadesBancariasDTO = new EntidadesBancariasDTO();
        entidadesBancariasDTO.setEntidad_bancaria_id(entidades_bancarias_id);
        return this.entidadesBancariasDAO.obtenerPorId(entidades_bancarias_id);
    }
    
}