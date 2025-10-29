package pe.edu.pucp.softpac.daoImpl.exception;
import java.sql.SQLException;

public class DAODetalleException extends RuntimeException {

    public DAODetalleException(String message, SQLException cause) {
        super(message, cause);
    }

    public DAODetalleException(SQLException cause) {
        super(cause);
    }

    public SQLException getSQLException() {
        Throwable cause = getCause();
        if (cause instanceof SQLException) {
            return (SQLException) cause;
        }
        return null;
    }
}
