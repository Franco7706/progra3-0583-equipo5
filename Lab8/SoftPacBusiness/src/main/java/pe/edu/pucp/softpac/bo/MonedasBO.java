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
    
    public Integer insertar(String nombre, String codigo_iso, String simbolo){
        MonedasDTO monedasDTO = new MonedasDTO();
        monedasDTO.setNombre(nombre);
        monedasDTO.setCodigo_iso(codigo_iso);
        monedasDTO.setSimbolo(simbolo);
        return this.monedasDAO.insertar(monedasDTO);
        
    }
    
    public Integer modificar(Integer moneda_id, String nombre, String codigo_iso, String simbolo){
        MonedasDTO monedasDTO = new MonedasDTO();
        monedasDTO.setMoneda_id(moneda_id);
        monedasDTO.setNombre(nombre);
        monedasDTO.setCodigo_iso(codigo_iso);
        monedasDTO.setSimbolo(simbolo);
        return this.monedasDAO.modificar(monedasDTO);
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
