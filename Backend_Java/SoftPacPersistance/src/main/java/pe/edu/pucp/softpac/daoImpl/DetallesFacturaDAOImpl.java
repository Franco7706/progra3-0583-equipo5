package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.DetallesFacturaDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.DetallesFacturaDTO;
import pe.edu.pucp.softpac.model.FacturasDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DetallesFacturaDAOImpl extends DAOImplBase implements DetallesFacturaDAO  {

    private DetallesFacturaDTO detalleFactura;

    public DetallesFacturaDAOImpl() {
        super("PA_DETALLES_FACTURA");
        this.retornarLlavePrimaria=true;
        this.esDetalle=true;
        this.seEliminaLogicamente=true;
        this.incluirColumnasDeEliminacionLogica();
    }

    public DetallesFacturaDAOImpl(Connection conexion) {
        super("PA_DETALLES_FACTURA");
        this.retornarLlavePrimaria=true;
        this.esDetalle=true;
        this.conexion=conexion;
        this.seEliminaLogicamente=true;
        this.incluirColumnasDeEliminacionLogica();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("DETALLE_FACTURA_ID",true,true));
        this.listaColumnas.add(new Columna("SUBTOTAL",false,false));
        this.listaColumnas.add(new Columna("DESCRIPCION",false,false));
        this.listaColumnas.add(new Columna("FACTURA_ID",false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException{
        this.statement.setBigDecimal(1,this.detalleFactura.getSubtotal());
        this.statement.setString(2,this.detalleFactura.getDescripcion());
        this.statement.setInt(3,this.detalleFactura.getFactura().getFactura_id());
    }
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException{
        this.statement.setBigDecimal(1,this.detalleFactura.getSubtotal());
        this.statement.setString(2,this.detalleFactura.getDescripcion());
        this.statement.setInt(3,this.detalleFactura.getFactura().getFactura_id());
        this.statement.setInt(4,this.detalleFactura.getDetalle_factura_id());
    }
    @Override
    protected String generarSQLParaEliminacion() {
        return "DELETE FROM PA_DETALLES_FACTURA WHERE FACTURA_ID=?";
    }
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1,this.detalleFactura.getFactura().getFactura_id());
    }
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException{
        this.statement.setInt(1,this.detalleFactura.getDetalle_factura_id());
    }
    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException{
        this.detalleFactura=null;
        FacturasDTO factura=null;
        if(this.resultSet.next()){
            this.detalleFactura=new DetallesFacturaDTO();
            this.detalleFactura.setDetalle_factura_id(this.resultSet.getInt(1));
            this.detalleFactura.setSubtotal(this.resultSet.getBigDecimal(2));
            this.detalleFactura.setDescripcion(this.resultSet.getString(3));
            factura=new FacturasDTO();
            this.detalleFactura.setFactura(factura);
            factura.setFactura_id(this.resultSet.getInt(4));
        }
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.detalleFactura.getFecha_eliminacion().getTime()));
        this.statement.setInt(2,this.detalleFactura.getUsuario_eliminacion().getUsuario_id());
        this.statement.setInt(3,this.detalleFactura.getDetalle_factura_id());
    }

    @Override
    public Integer insertar(DetallesFacturaDTO detalleFactura) {
        this.detalleFactura=detalleFactura;
        return super.insertar();
    }

    @Override
    public DetallesFacturaDTO obtenerPorId(Integer detalleFacturaId) {
        this.detalleFactura=new DetallesFacturaDTO();
        this.detalleFactura.setDetalle_factura_id(detalleFacturaId);
        super.obtenerPorId();
        return this.detalleFactura;
    }

    @Override
    public Integer modificar(DetallesFacturaDTO detalleFactura) {
        this.detalleFactura=detalleFactura;
        return super.modificar();
    }

    @Override
    public Integer eliminarPorFactura(Integer facturaId) {
        this.detalleFactura=new DetallesFacturaDTO();
        FacturasDTO factura=new FacturasDTO();
        factura.setFactura_id(facturaId);
        this.detalleFactura.setFactura(factura);
        return super.eliminar();
    }

    @Override
    public Integer eliminarLogico(DetallesFacturaDTO detalleFactura) {
        this.detalleFactura=detalleFactura;
        return super.eliminarLogico();
    }
}
