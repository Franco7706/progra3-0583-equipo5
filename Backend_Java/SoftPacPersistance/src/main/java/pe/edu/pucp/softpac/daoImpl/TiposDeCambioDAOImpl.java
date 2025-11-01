package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.dao.TiposDeCambioDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.TiposDeCambioDTO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TiposDeCambioDAOImpl extends DAOImplBase implements TiposDeCambioDAO {

    private TiposDeCambioDTO tipoDeCambio;
    private List<TiposDeCambioDTO> tiposDeCambio;

    public TiposDeCambioDAOImpl() {
        super("PA_TIPOS_DE_CAMBIO");
        this.retornarLlavePrimaria=true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("TIPO_CAMBIO_ID",true,true));
        this.listaColumnas.add(new Columna("FECHA_HORA",false,false));
        this.listaColumnas.add(new Columna("TASA_DE_CAMBIO",false,false));
        this.listaColumnas.add(new Columna("MONEDA_ORIGEN_ID",false,false));
        this.listaColumnas.add(new Columna("MONEDA_DESTINO_ID",false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.tipoDeCambio.getFecha().getTime()));
        this.statement.setBigDecimal(2,this.tipoDeCambio.getTasa_de_cambio());
        this.statement.setInt(3,this.tipoDeCambio.getMoneda_origen().getMoneda_id());
        this.statement.setInt(4,this.tipoDeCambio.getMoneda_destino().getMoneda_id());
    }
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setTimestamp(1, new Timestamp(this.tipoDeCambio.getFecha().getTime()));
        this.statement.setBigDecimal(2,this.tipoDeCambio.getTasa_de_cambio());
        this.statement.setInt(3,this.tipoDeCambio.getMoneda_origen().getMoneda_id());
        this.statement.setInt(4,this.tipoDeCambio.getMoneda_destino().getMoneda_id());
        this.statement.setInt(5,this.tipoDeCambio.getTipo_cambio_id());
    }
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,this.tipoDeCambio.getTipo_cambio_id());
    }
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,this.tipoDeCambio.getTipo_cambio_id());
    }
    @Override
    protected String generarSQLParaObtenerPorId(){
        return "SELECT t.TIPO_CAMBIO_ID, t.FECHA_HORA, t.TASA_DE_CAMBIO, t.MONEDA_ORIGEN_ID, mo.NOMBRE, mo.CODIGO_ISO, mo.SIMBOLO, t.MONEDA_DESTINO_ID, md.NOMBRE, md.CODIGO_ISO, md.SIMBOLO" +
                " FROM PA_TIPOS_DE_CAMBIO t JOIN PA_MONEDAS mo ON t.MONEDA_ORIGEN_ID=mo.MONEDA_ID JOIN PA_MONEDAS md ON t.MONEDA_DESTINO_ID=md.MONEDA_ID" +
                " WHERE t.TIPO_CAMBIO_ID=?";
    }
    @Override
    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        this.tipoDeCambio = null;
        MonedasDTO moneda=null;
        if(resultSet.next()){
            this.tipoDeCambio = new TiposDeCambioDTO();
            this.tipoDeCambio.setTipo_cambio_id(resultSet.getInt(1));
            this.tipoDeCambio.setFecha(resultSet.getTime(2));
            this.tipoDeCambio.setTasa_de_cambio(resultSet.getBigDecimal(3));
            moneda = new MonedasDTO();
            moneda.setMoneda_id(resultSet.getInt(4));
            moneda.setNombre(resultSet.getString(5));
            moneda.setCodigo_iso(resultSet.getString(6));
            moneda.setSimbolo(resultSet.getString(7));
            this.tipoDeCambio.setMoneda_origen(moneda);
            moneda = new MonedasDTO();
            moneda.setMoneda_id(resultSet.getInt(8));
            moneda.setNombre(resultSet.getString(9));
            moneda.setCodigo_iso(resultSet.getString(10));
            moneda.setSimbolo(resultSet.getString(11));
            this.tipoDeCambio.setMoneda_destino(moneda);
        }
    }
    @Override
    protected void extraerResultSetParaListarTodos() throws SQLException {
        this.tiposDeCambio = new ArrayList<>();
        TiposDeCambioDTO tipoDeCambio = null;
        MonedasDTO moneda = null;
        while(resultSet.next()){
            tipoDeCambio = new TiposDeCambioDTO();
            tipoDeCambio.setTipo_cambio_id(resultSet.getInt(1));
            tipoDeCambio.setFecha(resultSet.getTime(2));
            tipoDeCambio.setTasa_de_cambio(resultSet.getBigDecimal(3));
            moneda = new MonedasDTO();
            moneda.setMoneda_id(resultSet.getInt(4));
            tipoDeCambio.setMoneda_origen(moneda);
            moneda = new MonedasDTO();
            moneda.setMoneda_id(resultSet.getInt(5));
            tipoDeCambio.setMoneda_destino(moneda);
            tiposDeCambio.add(tipoDeCambio);
        }
    }

    @Override
    public Integer insertar(TiposDeCambioDTO tipoDeCambio) {
        this.tipoDeCambio=tipoDeCambio;
        return super.insertar();
    }

    @Override
    public TiposDeCambioDTO obtenerPorId(Integer tipoDeCambioId) {
        this.tipoDeCambio=new TiposDeCambioDTO();
        this.tipoDeCambio.setTipo_cambio_id(tipoDeCambioId);
        super.obtenerPorId();
        return this.tipoDeCambio;
    }

    @Override
    public List<TiposDeCambioDTO> listarTodos() {
        super.listarTodosQuery();
        return this.tiposDeCambio;
    }

    @Override
    public Integer modificar(TiposDeCambioDTO tipoDeCambio) {
        this.tipoDeCambio=tipoDeCambio;
        return super.modificar();
    }

    @Override
    public Integer eliminar(TiposDeCambioDTO tipoDeCambio) {
        this.tipoDeCambio=tipoDeCambio;
        return super.eliminar();
    }
}
