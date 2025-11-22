
package pe.edu.pucp.softpac.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBManagerMSSQL extends DBManager{
    
    protected DBManagerMSSQL(){
        
    }
    
    @Override
    protected String getURL() {
        String url = this.tipo_de_driver.concat("://");
        url = url.concat(this.nombre_de_host);
        url = url.concat(":");
        url = url.concat(this.puerto);
        url = url.concat(";");
        url = url.concat("databaseName="+this.base_de_datos);
        url = url.concat(";encrypt=false");
        return url;
    }

    @Override
    protected void leer_archivo_de_propiedades() {
        super.leer_archivo_de_propiedades(); 

        Properties properties = new Properties();
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(this.getClass().getResourceAsStream(nmArchivoConf));

            this.driver = properties.getProperty("driver_mssql");
            this.tipo_de_driver = properties.getProperty("tipo_de_driver_mssql");
            this.nombre_de_host = properties.getProperty("nombre_de_host_mssql");
            this.puerto = properties.getProperty("puerto_mssql");
            this.usuario = properties.getProperty("usuario_mssql");

        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer archivo de propiedades MSSQL - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer archivo de propiedades MSSQL - " + ex);
        }
    }
}
