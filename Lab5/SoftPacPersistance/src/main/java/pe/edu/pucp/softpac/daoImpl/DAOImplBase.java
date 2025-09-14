package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.daoImpl.util.Tipo_Operacion;
import pe.edu.pucp.softpac.daoImpl.util.Tipo_Query;
import pe.edu.pucp.softpac.db.DBManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAOImplBase {

    protected String nombre_tabla;
    protected ArrayList<Columna> listaColumnas;
    protected Boolean retornarLlavePrimaria;
    protected Connection conexion;
    protected CallableStatement statement;
    protected ResultSet resultSet;

    public DAOImplBase(String nombre_tabla) {
        this.nombre_tabla = nombre_tabla;
        this.retornarLlavePrimaria = false;
        this.incluirListaDeColumnas();
    }

    private void incluirListaDeColumnas() {
        this.listaColumnas = new ArrayList<>();
        this.configurarListaDeColumnas();
    }

    protected abstract void configurarListaDeColumnas();
    
    protected Integer insertar(){
        return this.ejecuta_DML(Tipo_Operacion.INSERTAR);
    }
    
    protected Integer modificar(){
        return this.ejecuta_DML(Tipo_Operacion.MODIFICAR);
    }
    
    protected Integer eliminar(){
        return this.ejecuta_DML(Tipo_Operacion.ELIMINAR);
    }

    protected void obtenerPorId() { this.ejecuta_Query(Tipo_Query.OBTENER_POR_ID);}

    protected void listarTodosQuery() { this.ejecuta_Query(Tipo_Query.LISTAR_TODOS);}

    protected void queryCustom1() { this.ejecuta_Query(Tipo_Query.CUSTOM1);}

    protected void queryCustom2() { this.ejecuta_Query(Tipo_Query.CUSTOM3);}

    protected void queryCustom3() { this.ejecuta_Query(Tipo_Query.CUSTOM3);}

    private Integer ejecuta_DML(Tipo_Operacion tipo_operacion) {
        Integer resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = null;
            switch (tipo_operacion) {
                case Tipo_Operacion.INSERTAR:
                    sql = this.generarSQLParaInsercion();
                    break;
                case Tipo_Operacion.MODIFICAR:
                    sql = this.generarSQLParaModificacion();
                    break;
                case Tipo_Operacion.ELIMINAR:
                    sql = this.generarSQLParaEliminacion();
                    break;
            }

            this.statement = this.conexion.prepareCall(sql);
            switch (tipo_operacion) {
                case Tipo_Operacion.INSERTAR:
                    this.incluirValorDeParametrosParaInsercion();
                    break;
                case Tipo_Operacion.MODIFICAR:
                    this.incluirValorDeParametrosParaModificacion();
                    break;
                case Tipo_Operacion.ELIMINAR:
                    this.incluirValorDeParametrosParaEliminacion();
                    break;
            }

            resultado = this.statement.executeUpdate();
            if (this.retornarLlavePrimaria && tipo_operacion == Tipo_Operacion.INSERTAR) {
                resultado = this.retornarUltimoAutoGenerado();
            }
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar ejecutar DML - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

    private void ejecuta_Query(Tipo_Query tipo_query) {
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = null;
            switch (tipo_query) {
                case Tipo_Query.LISTAR_TODOS:
                    sql = this.generarSQLParaListarTodos();
                    break;
                case Tipo_Query.OBTENER_POR_ID:
                    sql = this.generarSQLParaObtenerPorId();
                    break;
                case Tipo_Query.CUSTOM1:
                    sql = this.generarSQLCustom1();
                    break;
                case Tipo_Query.CUSTOM2:
                    sql = this.generarSQLCustom2();
                    break;
                case Tipo_Query.CUSTOM3:
                    sql = this.generarSQLCustom3();
                    break;
            }
            this.statement = this.conexion.prepareCall(sql);
            switch (tipo_query) {
                case Tipo_Query.LISTAR_TODOS:
                    break;
                case Tipo_Query.OBTENER_POR_ID:
                    this.incluirValorDeParametrosParaObtenerPorId();
                    break;
                case Tipo_Query.CUSTOM1:
                    this.incluirValorDeParametrosCustom1();
                    break;
                case Tipo_Query.CUSTOM2:
                    this.incluirValorDeParametrosCustom2();
                    break;
                case Tipo_Query.CUSTOM3:
                    this.incluirValorDeParametrosCustom3();
                    break;
            }
            this.resultSet = this.statement.executeQuery();
            switch (tipo_query) {
                case Tipo_Query.LISTAR_TODOS:
                    this.extraerResultSetParaListarTodos();
                    break;
                case Tipo_Query.OBTENER_POR_ID:
                    this.extraerResultSetParaObtenerPorId();
                    break;
                case Tipo_Query.CUSTOM1:
                    this.extraerResultSetCustom1();
                    break;
                case Tipo_Query.CUSTOM2:
                    this.extraerResultSetCustom2();
                    break;
                case Tipo_Query.CUSTOM3:
                    this.extraerResultSetCustom3();
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar query - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
    }

    protected String generarSQLParaInsercion() {
        //La sentencia que se generará es similiar a
        //INSERT INTO INV_ALMACENES (NOMBRE, ALMACEN_CENTRAL) VALUES (?,?)
        String sql = "INSERT INTO ";
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat("(");
        String sql_columnas = "";
        String sql_parametros = "";
        for (Columna columna : this.listaColumnas) {
            if (!columna.getEsAutoGenerado()) {
                if (!sql_columnas.isBlank()) {
                    sql_columnas = sql_columnas.concat(", ");
                    sql_parametros = sql_parametros.concat(", ");
                }
                sql_columnas = sql_columnas.concat(columna.getNombre());
                sql_parametros = sql_parametros.concat("?");
            }
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(") VALUES (");
        sql = sql.concat(sql_parametros);
        sql = sql.concat(")");
        return sql;
    }

    protected String generarSQLParaModificacion() {
        //sentencia SQL a generar es similar a 
        //UPDATE INV_ALMACENES SET NOMBRE=?, ALMACEN_CENTRAL=? WHERE ALMACEN_ID=?
        String sql = "UPDATE ";
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat(" SET ");
        String sql_columnas = "";
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsLlavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(", ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            } else {
                if (!sql_columnas.isBlank()) {
                    sql_columnas = sql_columnas.concat(", ");
                }
                sql_columnas = sql_columnas.concat(columna.getNombre());
                sql_columnas = sql_columnas.concat("=?");
            }
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" WHERE ");
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaEliminacion() {
        //sentencia SQL a generar es similar a 
        //DELETE FROM INV_ALMACENES WHERE ALMACEN_ID=?
        String sql = "DELETE FROM ";
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat(" WHERE ");
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsLlavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(", ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            }
        }
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaObtenerPorId() {
        //sentencia SQL a generar es similar a 
        //SELECT ALMACEN_ID, NOMBRE, ALMACEN_CENTRAL FROM INV_ALMACENES WHERE ALMACEN_ID = ?
        String sql = "SELECT ";
        String sql_columnas = "";
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsLlavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(", ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            }
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat(columna.getNombre());
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" FROM ");
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat(" WHERE ");
        sql = sql.concat(sql_predicado);
        return sql;
    }

    protected String generarSQLParaListarTodos() {
        //sentencia SQL a generar es similar a 
        //SELECT ALMACEN_ID, NOMBRE, ALMACEN_CENTRAL FROM INV_ALMACENES
        String sql = "SELECT ";
        String sql_columnas = "";
        for (Columna columna : this.listaColumnas) {
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat(columna.getNombre());
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" FROM ");
        sql = sql.concat(this.nombre_tabla);
        return sql;
    }

    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected String generarSQLCustom1() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected String generarSQLCustom2() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected String generarSQLCustom3() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void incluirValorDeParametrosCustom3() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void incluirValorDeParametrosCustom2() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void incluirValorDeParametrosCustom1() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    protected void extraerResultSetCustom3() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void extraerResultSetCustom2() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void extraerResultSetCustom1() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void extraerResultSetParaObtenerPorId() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void extraerResultSetParaListarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer retornarUltimoAutoGenerado() {
        Integer resultado = null;
        try {
            String sql = "select @@last_insert_id as id";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                resultado = this.resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar retornarUltimoAutoGenerado - " + ex);
        }
        return resultado;
    }

}
