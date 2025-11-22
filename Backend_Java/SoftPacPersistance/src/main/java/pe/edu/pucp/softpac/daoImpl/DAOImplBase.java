package pe.edu.pucp.softpac.daoImpl;

import pe.edu.pucp.softpac.daoImpl.exception.DAODetalleException;
import pe.edu.pucp.softpac.daoImpl.util.Columna;
import pe.edu.pucp.softpac.daoImpl.util.Tipo_Operacion;
import pe.edu.pucp.softpac.daoImpl.util.Tipo_Query;
import pe.edu.pucp.softpac.db.DBManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class DAOImplBase {

    protected String nombre_tabla;
    protected ArrayList<Columna> listaColumnas;
    protected Boolean retornarLlavePrimaria;
    protected Connection conexion;
    protected CallableStatement statement;
    protected ResultSet resultSet;
    protected Boolean ejecutaOperacionesEnCascada;
    protected Boolean esDetalle;
    protected Boolean listarEliminados;
    protected ArrayList<Columna> columnasEliminacion;
    protected Boolean seEliminaLogicamente;

    public DAOImplBase(String nombre_tabla) {
        this.nombre_tabla = nombre_tabla;
        this.retornarLlavePrimaria = false;
        this.ejecutaOperacionesEnCascada = false;
        this.esDetalle = false;
        this.listarEliminados = false;
        this.seEliminaLogicamente = false;
        this.incluirListaDeColumnas();
    }

    protected void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    protected Connection getConexion() {
        return this.conexion;
    }

    private void incluirListaDeColumnas() {
        this.listaColumnas = new ArrayList<>();
        this.configurarListaDeColumnas();
    }

    protected void incluirColumnasDeEliminacionLogica() {
        this.columnasEliminacion = new ArrayList<>();
        this.columnasEliminacion.add(new Columna("FECHA_ELIMINACION", false, false));
        this.columnasEliminacion.add(new Columna("USUARIO_ELIMINACION", false, false));
    }

    protected abstract void configurarListaDeColumnas();

    protected Integer insertar() {
        if (this.esDetalle == true) {
            try {
                return this.ejecutaDetalle_DML(Tipo_Operacion.INSERTAR);
            } catch (DAODetalleException e) {
                throw e;
            }
        }
        return this.ejecuta_DML(Tipo_Operacion.INSERTAR);
    }

    protected Integer modificar() {
        if (this.esDetalle == true) {
            try {
                return this.ejecutaDetalle_DML(Tipo_Operacion.MODIFICAR);
            } catch (DAODetalleException e) {
                throw e;
            }
        }
        return this.ejecuta_DML(Tipo_Operacion.MODIFICAR);
    }

    protected Integer eliminar() {
        if (this.esDetalle == true) {
            try {
                return this.ejecutaDetalle_DML(Tipo_Operacion.ELIMINAR);
            } catch (DAODetalleException e) {
                throw e;
            }
        }
        return this.ejecuta_DML(Tipo_Operacion.ELIMINAR);
    }

    protected Integer eliminarLogico() {
        if (this.esDetalle == true) {
            try {
                return this.ejecutaDetalle_DML(Tipo_Operacion.ELIMINAR_LOGICO);
            } catch (DAODetalleException e) {
                throw e;
            }
        }
        return this.ejecuta_DML(Tipo_Operacion.ELIMINAR_LOGICO);
    }

    protected void obtenerPorId() {
        this.ejecuta_Query(Tipo_Query.OBTENER_POR_ID);
    }

    protected void listarTodosQuery() {
        this.ejecuta_Query(Tipo_Query.LISTAR_TODOS);
    }

    protected void queryCustom1() {
        this.ejecuta_Query(Tipo_Query.CUSTOM1);
    }

    protected void queryCustom2() {
        this.ejecuta_Query(Tipo_Query.CUSTOM2);
    }

    protected void queryCustom3() {
        this.ejecuta_Query(Tipo_Query.CUSTOM3);
    }

    private Integer ejecutarDMLenBD() throws SQLException {
        return this.statement.executeUpdate();
    }

    protected void colocarSQLEnStatement(String sql) throws SQLException {
        this.statement = this.conexion.prepareCall(sql);
    }

    protected void rollbackTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.rollback();
        }
    }

    protected void commitTransaccion() throws SQLException {
        this.conexion.commit();
    }

    protected void iniciarTransaccion() throws SQLException {
        this.abrirConexion();
        this.conexion.setAutoCommit(false);

    }

    protected void cerrarConexion() throws SQLException {
        try {
            if (this.resultSet != null && !this.resultSet.isClosed()) {
                this.resultSet.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar ResultSet - " + ex);
        }

        try {
            if (this.statement != null && !this.statement.isClosed()) {
                this.statement.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar Statement - " + ex);
        }

        try {
            if (this.conexion != null && !this.conexion.isClosed()) {
                this.conexion.close();
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar Conexión - " + ex);
        }
    }

    private String generarSQLparaDML(Tipo_Operacion tipo_operacion) {
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
            case Tipo_Operacion.ELIMINAR_LOGICO:
                sql = this.generarSQLParaEliminacionLogica();
        }
        return sql;
    }

    private void incluirValorDeParametrosParaDML(Tipo_Operacion tipo_operacion) throws SQLException {
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
            case Tipo_Operacion.ELIMINAR_LOGICO:
                this.incluirValorDeParametrosParaEliminacionLogica();
                break;
        }
    }

    protected void ejecutarEliminacionEnCascada(Tipo_Operacion tipo_operacion) throws SQLException {
        if (this.ejecutaOperacionesEnCascada == false) {
            return;
        }
        if (tipo_operacion == Tipo_Operacion.ELIMINAR) {
            this.ejecutarCascadaParaEliminacion();
        }
    }

    private void ejecutarOperacionesEnCascada(Tipo_Operacion tipo_operacion, Integer resultado) throws SQLException {
        if (this.ejecutaOperacionesEnCascada == false) {
            return;
        }
        try {
            switch (tipo_operacion) {
                case Tipo_Operacion.INSERTAR:
                    this.recuperarAutoGeneradoParaInsercionDeDetalle(resultado);
                    this.ejecutarCascadaParaInsercion();
                    break;
                case Tipo_Operacion.MODIFICAR:
                    this.ejecutarCascadaParaModificacion();
                    break;
            }
        } catch (DAODetalleException e) {
            SQLException sqlEx = e.getSQLException();
            if (sqlEx != null) {
                throw sqlEx;
            } else {
                throw new SQLException("Error en operación en cascada", e);
            }
        }
    }

    protected void recuperarAutoGeneradoParaInsercionDeDetalle(Integer resultado) {
    }

    protected void ejecutarCascadaParaModificacion() {
    }

    protected void ejecutarCascadaParaInsercion() {
    }

    protected void ejecutarCascadaParaEliminacion() {
    }

    private Integer ejecutaDetalle_DML(Tipo_Operacion tipo_operacion) {
        Integer resultado = 0;
        try {
            String sql = this.generarSQLparaDML(tipo_operacion);
            this.colocarSQLEnStatement(sql);
            this.incluirValorDeParametrosParaDML(tipo_operacion);
            resultado = this.ejecutarDMLenBD();
            if (this.retornarLlavePrimaria && tipo_operacion == Tipo_Operacion.INSERTAR) {
                resultado = this.retornarUltimoAutoGenerado();
            }
            return resultado;
        } catch (SQLException ex) {
            System.err.println("Error al intentar ejecutar DML en Detalle - " + ex);
            throw new DAODetalleException(" Error al ejecutar operación de detalle: " + tipo_operacion, ex);
        } finally {
            try {
                if (this.statement != null) {
                    this.statement.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar Statement en detalle - " + ex);
            }
        }
    }

    private Integer ejecuta_DML(Tipo_Operacion tipo_operacion) {
        Integer resultado = 0;
        try {
            this.iniciarTransaccion();
            String sql = this.generarSQLparaDML(tipo_operacion);
            this.colocarSQLEnStatement(sql);
            this.incluirValorDeParametrosParaDML(tipo_operacion);
            this.ejecutarEliminacionEnCascada(tipo_operacion);
            resultado = this.ejecutarDMLenBD();
            if (this.retornarLlavePrimaria && tipo_operacion == Tipo_Operacion.INSERTAR) {
                resultado = this.retornarUltimoAutoGenerado();
            }
            this.ejecutarOperacionesEnCascada(tipo_operacion, resultado);
            this.commitTransaccion();
        } catch (SQLException ex) {
            System.err.println("Error al intentar ejecutar DML - " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

    private String generarSQLParaQuerys(Tipo_Query tipo_query) throws SQLException {
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
        return sql;
    }

    private void incluirValorDeParametrosParaQuerys(Tipo_Query tipo_query) throws SQLException {
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
    }

    private ResultSet ejecutarQueryEnBD() throws SQLException {
        this.resultSet = this.statement.executeQuery();
        return resultSet;
    }

    private void extraerResultSetParaQuerys(Tipo_Query tipo_query) throws SQLException {
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
    }

    private void ejecuta_Query(Tipo_Query tipo_query) {
        try {
            abrirConexion();
            String sql = this.generarSQLParaQuerys(tipo_query);
            this.colocarSQLEnStatement(sql);
            this.incluirValorDeParametrosParaQuerys(tipo_query);
            this.ejecutarQueryEnBD();
            this.extraerResultSetParaQuerys(tipo_query);
        } catch (SQLException ex) {
            System.err.println("Error al intentar query - " + ex);
        } finally {
            try {
                cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
    }

    public void ejecutarProcedimientoAlmacenado(String sql,
            Boolean conTransaccion) {
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        this.ejecutarProcedimientoAlmacenado(sql, incluirValorDeParametros, parametros, conTransaccion);
    }

    public void ejecutarProcedimientoAlmacenado(String sql,
            Consumer incluirValorDeParametros,
            Object parametros,
            Boolean conTransaccion) {
        try {
            if (conTransaccion) {
                this.iniciarTransaccion();
            } else {
                this.abrirConexion();
            }
            this.colocarSQLEnStatement(sql);
            if (incluirValorDeParametros != null) {
                incluirValorDeParametros.accept(parametros);
            }
            this.ejecutarDMLenBD();
            if (conTransaccion) {
                this.commitTransaccion();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar ejecutar procedimiento almacenado: " + ex);
            try {
                if (conTransaccion) {
                    this.rollbackTransaccion();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex);
            }
        } finally {
            try {
                this.cerrarConexion();
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
                    sql_predicado = sql_predicado.concat(" AND ");
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
                    sql_predicado = sql_predicado.concat(" AND ");
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

    protected String generarSQLParaEliminacionLogica() {
        //sentencia SQL a generar es similar a
        //UPDATE INV_ALMACENES SET FECHA_ELIMINACION=?, USUARIO_ELIMINACION=? WHERE ALMACEN_ID=?
        String sql = "UPDATE ";
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat(" SET ");
        String sql_columnas = "";
        String sql_predicado = "";
        for (Columna columna : this.listaColumnas) {
            if (columna.getEsLlavePrimaria()) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(" AND ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat("=?");
            }
        }
        for (Columna columna : this.columnasEliminacion) {
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat(columna.getNombre());
            sql_columnas = sql_columnas.concat("=?");
        }
        sql = sql.concat(sql_columnas);
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
        String sql_predicado = "";
        if (this.seEliminaLogicamente && !this.listarEliminados) {
            for (Columna columna : this.columnasEliminacion) {
                if (!sql_predicado.isBlank()) {
                    sql_predicado = sql_predicado.concat(" AND ");
                }
                sql_predicado = sql_predicado.concat(columna.getNombre());
                sql_predicado = sql_predicado.concat(" IS NULL");
            }
            sql = sql.concat(" WHERE ");
            sql = sql.concat(sql_predicado);
        }
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

    protected void incluirValorDeParametrosParaEliminacionLogica() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected String generarSQLCustom1() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected String generarSQLCustom2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected String generarSQLCustom3() {
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
        CallableStatement stmtTemp = null;
        ResultSet rsTemp = null;
        try {
            String sql = DBManager.retornarSQLParaUltimoAutoGenerado();
            stmtTemp = this.conexion.prepareCall(sql);
            rsTemp = stmtTemp.executeQuery();
            if (rsTemp.next()) {
                resultado = rsTemp.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar retornarUltimoAutoGenerado - " + ex);
        } finally {
            try {
                if (rsTemp != null) {
                    rsTemp.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar ResultSet temp - " + ex);
            }
            try {
                if (stmtTemp != null) {
                    stmtTemp.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar Statement temp - " + ex);
            }
        }
        return resultado;
    }

    protected void abrirConexion() {
        this.conexion = DBManager.getInstance().getConnection();
    }

    public List listarTodos() {
        String sql = null;
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return this.listarTodos(sql, incluirValorDeParametros, parametros);
    }

    public List listarTodos(String sql,
            Consumer incluirValorDeParametros,
            Object parametros) {
        List lista = new ArrayList<>();
        try {
            this.abrirConexion();
            if (sql == null) {
                sql = this.generarSQLParaListarTodos();
            }
            this.colocarSQLEnStatement(sql);
            if (incluirValorDeParametros != null) {
                incluirValorDeParametros.accept(parametros);
            }
            this.ejecutarSelectEnDB();
            while (this.resultSet.next()) {
                agregarObjetoALaLista(lista);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return lista;
    }

    protected void ejecutarSelectEnDB() throws SQLException {
        this.resultSet = this.statement.executeQuery();
    }

    protected void agregarObjetoALaLista(List lista) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected void instanciarObjetoDelResultSet() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
