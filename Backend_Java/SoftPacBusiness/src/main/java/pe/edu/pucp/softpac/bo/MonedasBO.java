
package pe.edu.pucp.softpac.bo;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.MonedasDAO;
import pe.edu.pucp.softpac.daoImpl.MonedasDAOImpl;
import pe.edu.pucp.softpac.model.MonedasDTO;

public class MonedasBO {
    
    private MonedasDAO monedasDAO;
    
    public MonedasBO(){
        monedasDAO = new MonedasDAOImpl();
    }
    
    public ArrayList<MonedasDTO> listarTodos(){
        return (ArrayList<MonedasDTO>) this.monedasDAO.listarTodos();
    }
    
    public MonedasDTO obtenerPorID(Integer moneda_id){
        MonedasDTO monedasDTO = new MonedasDTO();
        monedasDTO.setMoneda_id(moneda_id);
        return this.monedasDAO.obtenerPorId(moneda_id);
    }
    
    
}
