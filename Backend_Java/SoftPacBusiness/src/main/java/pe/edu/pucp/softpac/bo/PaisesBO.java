
package pe.edu.pucp.softpac.bo;

import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.PaisesDAO;
import pe.edu.pucp.softpac.daoImpl.PaisesDAOImpl;
import pe.edu.pucp.softpac.model.PaisesDTO;

public class PaisesBO {
    
    private PaisesDAO paisDAO;
    
    public PaisesBO(){
        paisDAO = new PaisesDAOImpl();
    }
    
    public ArrayList<PaisesDTO> listarTodos(){
        return (ArrayList<PaisesDTO>) this.paisDAO.listarTodos();
    }
    
    public PaisesDTO obtenerPorId(Integer pais_id){
        return this.paisDAO.obtenerPorId(pais_id);
    }
    
}
