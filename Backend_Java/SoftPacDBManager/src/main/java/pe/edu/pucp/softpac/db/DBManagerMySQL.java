
package pe.edu.pucp.softpac.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBManagerMySQL extends DBManager {
    
    protected DBManagerMySQL(){
        
    }
    
    
    @Override
    protected String getURL() {
        String url = this.tipo_de_driver.concat("://");
        url = url.concat(this.nombre_de_host);
        url = url.concat(":");
        url = url.concat(this.puerto);
        url = url.concat("/");
        url = url.concat(this.base_de_datos);
        url = url.concat("?useSSL=false&serverTimezone=UTC-5");
        return url;
    }


    @Override
    protected void leer_archivo_de_propiedades() {
        super.leer_archivo_de_propiedades(); 

        Properties properties = new Properties();
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(this.getClass().getResourceAsStream(nmArchivoConf));

            this.driver = properties.getProperty("driver_mysql");
            this.tipo_de_driver = properties.getProperty("tipo_de_driver_mysql");
            this.nombre_de_host = properties.getProperty("nombre_de_host_mysql");
            this.puerto = properties.getProperty("puerto_mysql");
            this.usuario = properties.getProperty("usuario_mysql");

        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer archivo de propiedades MySQL - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer archivo de propiedades MySQL - " + ex);
        }
    }
}
