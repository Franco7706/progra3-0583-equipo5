/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpac.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import pe.edu.pucp.softpac.dao.KardexCuentasPropiasDAO;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.model.CuentasPropiasDTO;
import pe.edu.pucp.softpac.model.EntidadesBancariasDTO;
import pe.edu.pucp.softpac.model.KardexCuentasPropiasDTO;
import pe.edu.pucp.softpac.model.MonedasDTO;
import pe.edu.pucp.softpac.model.UsuariosDTO;

/**
 *
 * @author Usuario
 */
public class KardexCuentasPropiasDAOImpl extends DAOImplBase implements KardexCuentasPropiasDAO{

    private KardexCuentasPropiasDTO kardexCuentasPropias;
    private ArrayList<KardexCuentasPropiasDTO> kardexCuentasPropiasDTOs;
    private int usuarioId;
    private int cuentaPropiaId;
    
    public KardexCuentasPropiasDAOImpl() {
        super("PA_ESTADO_CUENTAS_PROPIAS");
        this.retornarLlavePrimaria = false;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("CUENTA_PROPIA_ID",false,false));
        this.listaColumnas.add(new Columna("FECHA_MODIFICACION",false,false));
        this.listaColumnas.add(new Columna("SALDO_MODIFICACION",false,false));
        this.listaColumnas.add(new Columna("SALDO_RESULTANTE",false,false));
        this.listaColumnas.add(new Columna("USUARIO_MODIFICACION",false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException{
        this.statement.setInt(1, this.kardexCuentasPropias.getCuenta_propia().getCuenta_bancaria_id());
        this.statement.setTimestamp(2, new Timestamp(this.kardexCuentasPropias.getFecha_modificacion().getTime()));
        this.statement.setBigDecimal(3, this.kardexCuentasPropias.getSaldo_modificacion());
        this.statement.setBigDecimal(4, this.kardexCuentasPropias.getSaldo_resultante());
        this.statement.setInt(5, this.kardexCuentasPropias.getUsuario_modificacion().getUsuario_id());
    }
    
    private String generarSQLSinWhere(){
        return """
               SELECT
                  -- PA_ESTADO_CUENTAS_PROPIAS (k)
                  k.CUENTA_PROPIA_ID,
                  k.FECHA_MODIFICACION,
                  k.SALDO_MODIFICACION,
                  k.SALDO_RESULTANTE,
                  k.USUARIO_MODIFICACION,

                  -- PA_USUARIOS (u)
                  u.CORREO_ELECTRONICO AS U_CORREO_ELECTRONICO,
                  u.NOMBRE_DE_USUARIO AS U_NOMBRE_DE_USUARIO,
                  u.NOMBRE AS U_NOMBRE,
                  u.APELLIDOS AS U_APELLIDOS,
                  u.ACTIVO AS U_ACTIVO,
                  u.SUPERUSUARIO AS U_SUPERUSUARIO,

                  -- PA_CUENTAS_PROPIAS (c)
                  c.SALDO_DISPONIBLE AS C_SALDO_DISPONIBLE,
                  c.TIPO_CUENTA AS C_TIPO_CUENTA,
                  c.NUMERO_CUENTA AS C_NUMERO_CUENTA,
                  c.CCI AS C_CCI,
                  c.ACTIVO AS C_ACTIVO,
                  c.ENTIDAD_BANCARIA_ID AS C_ENTIDAD_BANCARIA_ID,
                  c.MONEDA_ID AS C_MONEDA_ID,

                  -- PA_MONEDAS (m)
                  m.NOMBRE AS M_NOMBRE,
                  m.CODIGO_ISO AS M_CODIGO_ISO,
                  m.SIMBOLO AS M_SIMBOLO,

                  -- PA_ENTIDADES_BANCARIAS (eb)
                  eb.NOMBRE AS EB_NOMBRE

               FROM PA_ESTADO_CUENTAS_PROPIAS k
               INNER JOIN PA_USUARIOS u
                  ON u.USUARIO_ID = k.USUARIO_MODIFICACION
               INNER JOIN PA_CUENTAS_PROPIAS c
                  ON c.CUENTA_PROPIA_ID = k.CUENTA_PROPIA_ID
               INNER JOIN PA_MONEDAS m
                  ON m.MONEDA_ID = c.MONEDA_ID
               INNER JOIN PA_ENTIDADES_BANCARIAS eb
                  ON eb.ENTIDAD_BANCARIA_ID = c.ENTIDAD_BANCARIA_ID
            """;
    }
    
    @Override
    protected String generarSQLCustom1(){
        return generarSQLSinWhere() + " WHERE k.USUARIO_MODIFICACION = ?;";
    }
    
    @Override
    protected String generarSQLCustom2(){
        return generarSQLSinWhere() + " WHERE k.CUENTA_PROPIA_ID = ?;";
    }
    
    @Override
    protected void incluirValorDeParametrosCustom1() throws SQLException{
        this.statement.setInt(1, usuarioId);
    }
    
    @Override
    protected void incluirValorDeParametrosCustom2() throws SQLException{
        this.statement.setInt(1, cuentaPropiaId);
    }
    
    @Override
    protected void extraerResultSetCustom1() throws SQLException{
        kardexCuentasPropiasDTOs = new ArrayList<>();
        while(this.resultSet.next()){
            KardexCuentasPropiasDTO kardex = new KardexCuentasPropiasDTO();
            CuentasPropiasDTO cuentaPropia = new CuentasPropiasDTO();
            EntidadesBancariasDTO entidadBancaria = new EntidadesBancariasDTO();
            MonedasDTO moneda = new MonedasDTO();
            UsuariosDTO usuarioModificacion = new UsuariosDTO();
            cuentaPropia.setCuenta_bancaria_id(this.resultSet.getInt(1));
            kardex.setFecha_modificacion(this.resultSet.getTimestamp(2));
            kardex.setSaldo_modificacion(this.resultSet.getBigDecimal(3));
            kardex.setSaldo_resultante(this.resultSet.getBigDecimal(4));
            usuarioModificacion.setUsuario_id(this.resultSet.getInt(5));
            usuarioModificacion.setCorreo_electronico(this.resultSet.getString(6));
            usuarioModificacion.setNombre_de_usuario(this.resultSet.getString(7));
            usuarioModificacion.setNombre(this.resultSet.getString(8));
            usuarioModificacion.setApellidos(this.resultSet.getString(9));
            usuarioModificacion.setActivo(this.resultSet.getString(10).equals("S"));
            usuarioModificacion.setSuperusuario(this.resultSet.getString(11).equals("S"));
            cuentaPropia.setSaldo_disponible(this.resultSet.getBigDecimal(12));
            cuentaPropia.setTipo_cuenta(this.resultSet.getString(13));
            cuentaPropia.setNumero_cuenta(this.resultSet.getString(14));
            cuentaPropia.setCci(this.resultSet.getString(15));
            cuentaPropia.setActiva(this.resultSet.getString(16).equals("S"));
            entidadBancaria.setEntidad_bancaria_id(this.resultSet.getInt(17));
            moneda.setMoneda_id(this.resultSet.getInt(18));
            moneda.setNombre(this.resultSet.getString(19));
            moneda.setCodigo_iso(this.resultSet.getString(20));
            moneda.setSimbolo(this.resultSet.getString(21));
            entidadBancaria.setNombre(this.resultSet.getString(22));
            cuentaPropia.setEntidad_bancaria(entidadBancaria);
            cuentaPropia.setMoneda(moneda);
            kardex.setCuenta_propia(cuentaPropia);
            kardex.setUsuario_modificacion(usuarioModificacion);
            kardexCuentasPropiasDTOs.add(kardex);
        }
    }
    
    @Override
    protected void extraerResultSetCustom2() throws SQLException{
        extraerResultSetCustom1();
    }
    
    @Override
    public ArrayList<KardexCuentasPropiasDTO> obtenerPorUsuario(int usuarioId) {
        this.usuarioId=usuarioId;
        super.queryCustom1();
        return this.kardexCuentasPropiasDTOs;
    }

    @Override
    public ArrayList<KardexCuentasPropiasDTO> obtenerPorCuentaPropia(int cuentaPropiaId) {
        this.cuentaPropiaId=cuentaPropiaId;
        super.queryCustom2();
        return this.kardexCuentasPropiasDTOs;
    }

    @Override
    public int insertar(KardexCuentasPropiasDTO kardexCuentasPropias) {
        this.kardexCuentasPropias=kardexCuentasPropias;
        return super.insertar();
    }
    
    
}
