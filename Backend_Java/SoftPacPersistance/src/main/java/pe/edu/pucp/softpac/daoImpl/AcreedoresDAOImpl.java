package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softpac.dao.AcreedoresDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.AcreedoresDTO;
import pe.edu.pucp.softpac.model.PaisesDTO;

public class AcreedoresDAOImpl extends DAOImplBase implements AcreedoresDAO {

    private AcreedoresDTO acreedor;
    private List<AcreedoresDTO> acreedores;

    public AcreedoresDAOImpl() {
        super("PA_ACREEDORES");
        this.retornarLlavePrimaria=true;
        this.seEliminaLogicamente=true;
        super.incluirColumnasDeEliminacionLogica();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ACREEDOR_ID",true, true));
        this.listaColumnas.add(new Columna("RAZON_SOCIAL", false, false));
        this.listaColumnas.add(new Columna("RUC",  false, false));
        this.listaColumnas.add(new Columna("DIRECCION_FISCAL", false, false));
        this.listaColumnas.add(new Columna("CONDICION", false, false));
        this.listaColumnas.add(new Columna("PLAZO_DE_PAGO", false, false));
        this.listaColumnas.add(new Columna("ACTIVO", false, false));
        this.listaColumnas.add(new Columna("PAIS_ID", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1,this.acreedor.getRazon_social());
        this.statement.setString(2,this.acreedor.getRuc());
        this.statement.setString(3,this.acreedor.getDireccion_fiscal());
        this.statement.setString(4,this.acreedor.getCondicion());
        this.statement.setInt(5,this.acreedor.getPlazo_de_pago());
        this.statement.setString(6,this.acreedor.getActivo()?"S":"N");
        this.statement.setInt(7,this.acreedor.getPais().getPais_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1,this.acreedor.getRazon_social());
        this.statement.setString(2,this.acreedor.getRuc());
        this.statement.setString(3,this.acreedor.getDireccion_fiscal());
        this.statement.setString(4,this.acreedor.getCondicion());
        this.statement.setInt(5,this.acreedor.getPlazo_de_pago());
        this.statement.setString(6,this.acreedor.getActivo()?"S":"N");
        this.statement.setInt(7,this.acreedor.getPais().getPais_id());
        this.statement.setInt(8,this.acreedor.getAcreedor_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,this.acreedor.getAcreedor_id());
    }

    @Override
    protected String generarSQLParaObtenerPorId(){
        return "SELECT a.ACREEDOR_ID, a.RAZON_SOCIAL, a.RUC, a.DIRECCION_FISCAL, a.CONDICION, " +
                "a.PLAZO_DE_PAGO, a.ACTIVO, a.PAIS_ID, p.NOMBRE, p.CODIGO_ISO, p.CODIGO_TELEFONICO " +
                "FROM PA_ACREEDORES a JOIN PA_PAISES p ON a.PAIS_ID=p.PAIS_ID WHERE a.ACREEDOR_ID=?";
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,this.acreedor.getAcreedor_id());
    }

    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        PaisesDTO pais=null;
        this.acreedor=null;
        if(this.resultSet.next()){
            this.acreedor=new AcreedoresDTO();
            this.acreedor.setAcreedor_id(this.resultSet.getInt(1));
            this.acreedor.setRazon_social(this.resultSet.getString(2));
            this.acreedor.setRuc(this.resultSet.getString(3));
            this.acreedor.setDireccion_fiscal(this.resultSet.getString(4));
            this.acreedor.setCondicion(this.resultSet.getString(5));
            this.acreedor.setPlazo_de_pago(this.resultSet.getInt(6));
            this.acreedor.setActivo(this.resultSet.getString(7).equalsIgnoreCase("S"));
            pais=new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(8));
            pais.setNombre(this.resultSet.getString(9));
            pais.setCodigo_iso(this.resultSet.getString(10));
            pais.setCodigo_telefonico(this.resultSet.getString(11));
            this.acreedor.setPais(pais);
        }
    }

    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        PaisesDTO pais=null;
        AcreedoresDTO a=null;
        this.acreedores=new ArrayList<>();
        while(this.resultSet.next()){
            a=new AcreedoresDTO();
            a.setAcreedor_id(this.resultSet.getInt(1));
            a.setRazon_social(this.resultSet.getString(2));
            a.setRuc(this.resultSet.getString(3));
            a.setDireccion_fiscal(this.resultSet.getString(4));
            a.setCondicion(this.resultSet.getString(5));
            a.setPlazo_de_pago(this.resultSet.getInt(6));
            a.setActivo(this.resultSet.getString(7).equalsIgnoreCase("S"));
            pais=new PaisesDTO();
            pais.setPais_id(this.resultSet.getInt(8));
            a.setPais(pais);
            acreedores.add(a);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.acreedor.getFecha_eliminacion().getTime()));
        this.statement.setInt(2,this.acreedor.getUsuario_eliminacion().getUsuario_id());
        this.statement.setInt(3,this.acreedor.getAcreedor_id());
    }

    @Override
    public Integer insertar(AcreedoresDTO acreedor) {
        this.acreedor=acreedor;
        return super.insertar();
    }

    @Override
    public AcreedoresDTO obtenerPorId(Integer acreedorId) {
        this.acreedor=new AcreedoresDTO();
        this.acreedor.setAcreedor_id(acreedorId);
        super.obtenerPorId();
        return this.acreedor;
    }

    @Override
    public List<AcreedoresDTO> listarTodos() {
        super.listarTodosQuery();
        return this.acreedores;
    }

    @Override
    public Integer modificar(AcreedoresDTO acreedor) {
        this.acreedor=acreedor;
        return super.modificar();
    }

    @Override
    public Integer eliminar(AcreedoresDTO acreedor) {
        this.acreedor=acreedor;
        return super.eliminar();
    }

    @Override
    public Integer eliminarLogico(AcreedoresDTO acreedor) {
        this.acreedor=acreedor;
        return super.eliminarLogico();
    }
}
