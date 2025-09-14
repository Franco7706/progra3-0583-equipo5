package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import pe.edu.pucp.softpac.dao.PaisesDAO;
import pe.edu.pucp.softpac.model.PaisesDTO;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.db.DBManager;

public class PaisesDAOImpl extends DAOImplBase implements PaisesDAO {

    private PaisesDTO pais;

    public PaisesDAOImpl() {
        super("PA_PAISES");
        this.pais = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("PAIS_ID",true,true));
        this.listaColumnas.add(new Columna("NOMBRE",false,false));
        this.listaColumnas.add(new Columna("CODIGO_ISO",false,false));
        this.listaColumnas.add(new Columna("CODIGO_TELEFONICO",false,false));
    }

    @Override
    public Integer insertar(PaisesDTO pais) {
        this.pais = pais;
        return super.insertar();
    }

    @Override
    public PaisesDTO obtenerPorId(Integer paisId) {
        PaisesDTO pais = null;
        try {
            
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT PAIS_ID, NOMBRE, CODIGO_ISO, CODIGO_TELEFONICO FROM PA_PAISES WHERE PAIS_ID = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, paisId);
            this.resultSet = this.statement.executeQuery();
            if(this.resultSet.next()){
                pais = new PaisesDTO();
                pais.setPais_id(this.resultSet.getInt("PAIS_ID"));
                pais.setNombre(this.resultSet.getString("NOMBRE"));
                pais.setCodigo_telefonico(this.resultSet.getString("CODIGO_TELEFONICO"));
                pais.setCodigo_iso(this.resultSet.getString("CODIGO_ISO"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaisesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{         
                try {
                    if(this.conexion != null){
                        this.conexion.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PaisesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        } 
        return pais;
    }

    @Override
    public ArrayList<PaisesDTO> listarTodos() {
        ArrayList<PaisesDTO> listaPaises = new ArrayList<>();
        try {
            
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT PAIS_ID, NOMBRE, CODIGO_ISO, CODIGO_TELEFONICO FROM PA_PAISES";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while(this.resultSet.next()){
                PaisesDTO pais = new PaisesDTO();
                pais.setPais_id(this.resultSet.getInt("PAIS_ID"));
                pais.setNombre(this.resultSet.getString("NOMBRE"));
                pais.setCodigo_iso(this.resultSet.getString("CODIGO_ISO"));
                pais.setCodigo_telefonico(this.resultSet.getString("CODIGO_TELEFONICO"));
                listaPaises.add(pais);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(PaisesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(this.conexion != null){
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PaisesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaPaises;
    }
    
    //Por evaluar 
    @Override
    public Integer modificar(PaisesDTO pais) {
//        this.pais = pais;
//        return super.modificar();
        return 0;
    }

    @Override
    public Integer eliminar(PaisesDTO pais) {
//        this.pais = pais;
//        return super.eliminar();
        return 0;
    }
}
