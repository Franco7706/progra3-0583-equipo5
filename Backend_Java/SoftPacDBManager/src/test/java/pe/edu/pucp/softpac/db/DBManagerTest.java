package pe.edu.pucp.softpac.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DBManagerTest {

    public DBManagerTest() {
    }

    @org.junit.jupiter.api.Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DBManager dBManager = DBManager.getInstance();
        assertNotNull(dBManager);
    }

    @org.junit.jupiter.api.Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DBManager dBManager = DBManager.getInstance();
        Connection conexion = dBManager.getConnection();
        assertNotNull(conexion);
    }

    @Test
    public void testMultiplesConexiones() {
        System.out.println("testMultiplesConexiones");
        DBManager dBManager = DBManager.getInstance();
        List<Connection> conexiones = new ArrayList<>();

        try {
            // Obtener 5 conexiones del pool
            for (int i = 0; i < 5; i++) {
                Connection conn = dBManager.getConnection();
                assertNotNull(conn);
                conexiones.add(conn);
            }

            System.out.println("Pool funcionando: " + conexiones.size() + " conexiones activas");

        } catch (Exception ex) {
            fail("Error: " + ex.getMessage());
        } finally {
            // Devolver al pool
            for (Connection conn : conexiones) {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                }
            }
        }
    }

}
