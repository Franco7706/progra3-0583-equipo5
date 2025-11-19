package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import pe.edu.pucp.softpac.dao.PaisesDAO;
import pe.edu.pucp.softpac.model.PaisesDTO;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softpac.daoImpl.util.Columna;

public class PaisesDAOImpl extends DAOImplBase implements PaisesDAO {

    private PaisesDTO pais;
    private List<PaisesDTO> paises;

    public PaisesDAOImpl() {
        super("PA_PAISES");
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
    protected void incluirValorDeParametrosParaInsercion() throws SQLException{
        this.statement.setString(1, this.pais.getNombre());
        this.statement.setString(2, this.pais.getCodigo_iso());
        this.statement.setString(3, this.pais.getCodigo_telefonico());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException{
        this.statement.setString(1, this.pais.getNombre());
        this.statement.setString(2, this.pais.getCodigo_iso());
        this.statement.setString(3, this.pais.getCodigo_telefonico());
        this.statement.setInt(4, this.pais.getPais_id());

    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1, this.pais.getPais_id());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException{
        this.statement.setInt(1, this.pais.getPais_id());
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException{
        this.pais=null;
        if(this.resultSet.next()){
            this.pais = new PaisesDTO();
            this.pais.setPais_id(this.resultSet.getInt(1));
            this.pais.setNombre(this.resultSet.getString(2));
            this.pais.setCodigo_iso(this.resultSet.getString(3));
            this.pais.setCodigo_telefonico(this.resultSet.getString(4));
        }
    }

    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException{
        this.paises=new ArrayList<>();
        while(this.resultSet.next()){
            PaisesDTO p = new PaisesDTO();
            p.setPais_id(this.resultSet.getInt(1));
            p.setNombre(this.resultSet.getString(2));
            p.setCodigo_iso(this.resultSet.getString(3));
            p.setCodigo_telefonico(this.resultSet.getString(4));
            this.paises.add(p);
        }
    }

    @Override
    public Integer insertar(PaisesDTO pais) {
        this.pais = pais;
        return super.insertar();
    }

    @Override
    public PaisesDTO obtenerPorId(Integer paisId) {
        this.pais = new PaisesDTO();
        this.pais.setPais_id(paisId);
        super.obtenerPorId();
        return this.pais;
    }

    @Override
    public List<PaisesDTO> listarTodos() {
        super.listarTodosQuery();
        return this.paises;
    }


    @Override
    public Integer modificar(PaisesDTO pais) {
        this.pais = pais;
        return super.modificar();
    }

    @Override
    public Integer eliminar(PaisesDTO pais) {
        this.pais = pais;
        return super.eliminar();
    }
}
