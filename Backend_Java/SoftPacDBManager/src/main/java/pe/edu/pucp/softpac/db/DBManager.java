package pe.edu.pucp.softpac.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import pe.edu.pucp.softpac.db.util.Cifrado;
import pe.edu.pucp.softpac.db.util.MotorDeBaseDeDatos;

public abstract class DBManager {

    protected static final String ARCHIVO_CONFIGURACION = "jdbc2.properties";   //CAMBIO

    private Connection conexion;
    protected String driver;
    protected String tipo_de_driver;
    protected String base_de_datos;
    protected String nombre_de_host;
    protected String puerto;
    protected String usuario;
    private String contraseña;

    private static DBManager dbManager = null;

    protected DBManager() {
        // No hace nada el constructor
    }

    public static DBManager getInstance() {
        if (DBManager.dbManager == null) {
            DBManager.createInstance();
        }
        return DBManager.dbManager;
    }

    private static void createInstance() {
        if (DBManager.dbManager == null) {
            if (DBManager.obtenerMotorDeBaseDeDatos() == MotorDeBaseDeDatos.MYSQL) {
                DBManager.dbManager = new DBManagerMySQL();
            } else {
                DBManager.dbManager = new DBManagerMSSQL();
            }
            DBManager.dbManager.leer_archivo_de_propiedades();
        }
    }

    public Connection getConnection() {
        try {
            Class.forName(this.driver);
            this.conexion = DriverManager.getConnection(
                    getURL(),
                    this.usuario,
                    this.contraseña
            );
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error al generar la conexión - " + ex);
        }
        return conexion;
    }

    protected abstract String getURL();

    
    protected void leer_archivo_de_propiedades() {
        Properties properties = new Properties();
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(this.getClass().getResourceAsStream(nmArchivoConf));
            this.base_de_datos = properties.getProperty("base_de_datos");
            this.contraseña = Cifrado.descifrarMD5(properties.getProperty("contrasenha"));
//            this.contraseña = properties.getProperty("contrasenha");
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        }
    }

    private static MotorDeBaseDeDatos obtenerMotorDeBaseDeDatos() {
        Properties properties = new Properties();
        
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(DBManager.class.getResourceAsStream(nmArchivoConf));
            String seleccion = properties.getProperty("db.seleccion"); 
            
            if (seleccion.equals("MySQL")) {
                return MotorDeBaseDeDatos.MYSQL;
            } else {
                return MotorDeBaseDeDatos.MSSQL;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        }
        return null;
    }
    
    public static String retornarSQLParaUltimoAutoGenerado() {
    if (dbManager instanceof DBManagerMySQL) {
        return "select @@last_insert_id as id";
    } else if (dbManager instanceof DBManagerMSSQL) {
        return "select @@IDENTITY as id";
    } else {
        throw new UnsupportedOperationException("Motor de base de datos no soportado");
    }
}
}
