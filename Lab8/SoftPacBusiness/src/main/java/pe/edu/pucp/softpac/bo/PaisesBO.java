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
    
    public Integer insertar(String nombre, String codigo_iso, String codigo_telefonico){
        PaisesDTO pais = new PaisesDTO();
        pais.setNombre(nombre);
        pais.setCodigo_iso(codigo_iso);
        pais.setCodigo_telefonico(codigo_telefonico);
        return this.paisDAO.insertar(pais);
        
    }
    
    public Integer modificar(Integer pais_id, String nombre, String codigo_iso, String codigo_telefonico ){
        PaisesDTO pais = new PaisesDTO();
        pais.setPais_id(pais_id);
        pais.setNombre(nombre);
        pais.setCodigo_iso(codigo_iso);
        pais.setCodigo_telefonico(codigo_telefonico);
        return this.paisDAO.modificar(pais);
    }
    
    public ArrayList<PaisesDTO> listarTodos(){
        return (ArrayList<PaisesDTO>) this.paisDAO.listarTodos();
    }
    
    public PaisesDTO obtenerPorId(Integer pais_id){
        PaisesDTO pais = new PaisesDTO();
        pais.setPais_id(pais_id);
        return this.paisDAO.obtenerPorId(pais_id);
    }
   
}
