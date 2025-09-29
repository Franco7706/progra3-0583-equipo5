package pe.edu.pucp.softpac.bo;

import pe.edu.pucp.softpac.dao.AcreedoresDAO;
import pe.edu.pucp.softpac.daoImpl.AcreedoresDAOImpl;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;


public class AcreedoresBO {
    
   private AcreedoresDAO acreedorDAO;
   
   public AcreedoresBO(){
       this.acreedorDAO = new AcreedoresDAOImpl();
   }
   
   public Integer insertar(String razon_social, String ruc, String direccion_fiscal,
            String condicion, Integer plazo_de_pago, String activo, Integer id_pais){
       AcreedoresDTO acreedoresDTO = new AcreedoresDTO();
       acreedoresDTO.setRazon_social(razon_social);
       acreedoresDTO.setRuc(ruc);
       acreedoresDTO.setPlazo_de_pago(plazo_de_pago);
       acreedoresDTO.setCondicion(condicion);
       acreedoresDTO.setDireccion_fiscal(direccion_fiscal);
       acreedoresDTO.setActivo(activo.equals("S") ? Boolean.TRUE : Boolean.FALSE);
       PaisesDTO pais = new PaisesDTO();
       pais.setPais_id(id_pais);
       acreedoresDTO.setPais(pais);
            
       return this.acreedorDAO.insertar(acreedoresDTO);
   }
   
   public Integer modificar(Integer id_acreedor, String razon_social, String ruc,
           String direccion_fiscal, String condicion, Integer plazo_de_pago, String activo,
           Integer id_pais){
       AcreedoresDTO acreedoresDTO = new AcreedoresDTO();
       acreedoresDTO.setAcreedor_id(id_acreedor);
       acreedoresDTO.setRazon_social(razon_social);
       acreedoresDTO.setRuc(ruc);
       acreedoresDTO.setDireccion_fiscal(direccion_fiscal);
       acreedoresDTO.setCondicion(condicion);
       acreedoresDTO.setPlazo_de_pago(plazo_de_pago);
       acreedoresDTO.setActivo(activo.equals("S") ? Boolean.TRUE : Boolean.FALSE);
       acreedoresDTO.setPlazo_de_pago(plazo_de_pago);
       PaisesDTO pais = new PaisesDTO();
       pais.setPais_id(id_pais);
       acreedoresDTO.setPais(pais);
       return this.acreedorDAO.modificar(acreedoresDTO);
   }
   
//   public Integer eliminarLogico(){
//       
//   }
   
   
    public AcreedoresDTO obtenerPorId(Integer acreedor_id){
       AcreedoresDTO acreedores = new AcreedoresDTO();
       acreedores.setAcreedor_id(acreedor_id);
       return this.acreedorDAO.obtenerPorId(acreedor_id);

    }
    
    
}
