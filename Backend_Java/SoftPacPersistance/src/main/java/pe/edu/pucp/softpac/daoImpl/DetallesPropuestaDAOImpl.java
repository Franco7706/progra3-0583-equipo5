package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.DetallesPropuestaDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DetallesPropuestaDAOImpl extends DAOImplBase implements DetallesPropuestaDAO {

    private DetallesPropuestaDTO detallePropuesta;

    public DetallesPropuestaDAOImpl() {
        super("PA_DETALLES_PROPUESTA");
        this.retornarLlavePrimaria=true;
        this.esDetalle=true;
        this.seEliminaLogicamente=true;
        this.incluirColumnasDeEliminacionLogica();
    }

    public DetallesPropuestaDAOImpl(Connection conexion) {
        super("PA_DETALLES_PROPUESTA");
        this.retornarLlavePrimaria=true;
        this.esDetalle=true;
        this.conexion=conexion;
        this.seEliminaLogicamente=true;
        this.incluirColumnasDeEliminacionLogica();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("DETALLE_PROPUESTA_ID",true,true));
        this.listaColumnas.add(new Columna("MONTO_PAGO",false,false));
        this.listaColumnas.add(new Columna("FORMA_PAGO",false,false));
        this.listaColumnas.add(new Columna("PROPUESTA_DE_PAGO_ID",false,false));
        this.listaColumnas.add(new Columna("FACTURA_ID",false,false));
        this.listaColumnas.add(new Columna("CUENTA_ACREEDOR_ID",false,false));
        this.listaColumnas.add(new Columna("CUENTA_PROPIA_ID",false,false));
        this.listaColumnas.add(new Columna("ESTADO",false,true));
    }
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException{
        this.statement.setBigDecimal(1,this.detallePropuesta.getMonto_pago());
        this.statement.setString(2,String.valueOf(this.detallePropuesta.getForma_pago()));
        this.statement.setInt(3,this.detallePropuesta.getPropuesta_pago().getPropuesta_id());
        this.statement.setInt(4,this.detallePropuesta.getFactura().getFactura_id());
        this.statement.setInt(5,this.detallePropuesta.getCuenta_acreedor().getCuenta_bancaria_id());
        this.statement.setInt(6,this.detallePropuesta.getCuenta_propia().getCuenta_bancaria_id());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException{
        this.statement.setBigDecimal(1,this.detallePropuesta.getMonto_pago());
        this.statement.setString(2,String.valueOf(this.detallePropuesta.getForma_pago()));
        this.statement.setInt(3,this.detallePropuesta.getPropuesta_pago().getPropuesta_id());
        this.statement.setInt(4,this.detallePropuesta.getFactura().getFactura_id());
        this.statement.setInt(5,this.detallePropuesta.getCuenta_acreedor().getCuenta_bancaria_id());
        this.statement.setInt(6,this.detallePropuesta.getCuenta_propia().getCuenta_bancaria_id());
        this.statement.setString(7,String.valueOf(this.detallePropuesta.getEstado()));
        this.statement.setInt(8,this.detallePropuesta.getDetalle_propuesta_id());
    }
    @Override
    protected String generarSQLParaEliminacion(){
        return "DELETE FROM PA_DETALLES_PROPUESTA WHERE PROPUESTA_DE_PAGO_ID=?";
    }
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1,this.detallePropuesta.getPropuesta_pago().getPropuesta_id());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException{
        this.statement.setTimestamp(1, new Timestamp(this.detallePropuesta.getFecha_eliminacion().getTime()));
        this.statement.setInt(2,this.detallePropuesta.getUsuario_eliminacion().getUsuario_id());
        this.statement.setInt(3,this.detallePropuesta.getDetalle_propuesta_id());
    }

    @Override
    public Integer insertar(DetallesPropuestaDTO detallePropuesta) {
        this.detallePropuesta=detallePropuesta;
        return super.insertar();
    }

    @Override
    public Integer modificar(DetallesPropuestaDTO detallePropuesta) {
        this.detallePropuesta=detallePropuesta;
        return super.modificar();
    }

    @Override
    public Integer eliminarPorPropuesta(Integer propuestaId) {
        PropuestasPagoDTO propuesta=new PropuestasPagoDTO();
        propuesta.setPropuesta_id(propuestaId);
        this.detallePropuesta=new DetallesPropuestaDTO();
        this.detallePropuesta.setPropuesta_pago(propuesta);
        return super.eliminar();
    }

    @Override
    public Integer eliminarLogico(DetallesPropuestaDTO detallePropuesta) {
        this.detallePropuesta=detallePropuesta;
        return super.eliminarLogico();
    }
}
