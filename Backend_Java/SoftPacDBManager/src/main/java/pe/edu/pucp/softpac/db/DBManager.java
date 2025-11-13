package pe.edu.pucp.softpac.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import pe.edu.pucp.softpac.db.util.Cifrado;
import pe.edu.pucp.softpac.db.util.MotorDeBaseDeDatos;

public abstract class DBManager {

    protected static final String ARCHIVO_CONFIGURACION = "jdbc2.properties";

    protected String driver;
    protected String tipo_de_driver;
    protected String base_de_datos;
    protected String nombre_de_host;
    protected String puerto;
    protected String usuario;
    private String contraseña;

    private static DBManager dbManager = null;
    private static HikariDataSource dataSource = null;

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
            DBManager.dbManager.inicializarPool();
        }
    }

    private void inicializarPool() {
        if (dataSource == null) {
            try {
                HikariConfig config = new HikariConfig();

                config.setJdbcUrl(getURL());
                config.setUsername(this.usuario);
                config.setPassword(this.contraseña);
                config.setDriverClassName(this.driver);

                // Configuración optimizada
                config.setMaximumPoolSize(10);              
                config.setMinimumIdle(3);
                config.setConnectionTimeout(30000);
                config.setIdleTimeout(300000);              // 5 minutos
                config.setMaxLifetime(1800000);             // 30 minutos
                config.setLeakDetectionThreshold(30000);    
                config.setValidationTimeout(5000);

                // Optimizaciones
                config.setAutoCommit(true);
                config.addDataSourceProperty("cachePrepStmts", "true");
                config.addDataSourceProperty("prepStmtCacheSize", "250");
                config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
                config.addDataSourceProperty("useServerPrepStmts", "true");

                config.setPoolName("SoftPacHikariPool");

                dataSource = new HikariDataSource(config);
                System.out.println("Pool de conexiones HikariCP inicializado correctamente");
            } catch (Exception ex) {
                System.err.println("Error al inicializar el pool de conexiones - " + ex);
            }
        }
    }

    public Connection getConnection() {
        try {
            if (dataSource == null) {
                inicializarPool();
            }
            return dataSource.getConnection();
        } catch (SQLException ex) {
            System.err.println("Error al obtener conexión del pool - " + ex);
            return null;
        }
    }

    protected abstract String getURL();

    protected void leer_archivo_de_propiedades() {
        Properties properties = new Properties();
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(this.getClass().getResourceAsStream(nmArchivoConf));
            this.base_de_datos = properties.getProperty("base_de_datos");
            this.contraseña = Cifrado.descifrarMD5(properties.getProperty("contrasenha"));
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

    // Método para cerrar el pool al finalizar la aplicación
    public static void cerrarPool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println("Pool de conexiones cerrado correctamente");
        }
    }
}
