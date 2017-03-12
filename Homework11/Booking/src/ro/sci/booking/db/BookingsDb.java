package ro.sci.booking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for establishing a connection to the 'booking' database.
 * Created by andrei on 3/3/17.
 */
public class BookingsDb {

    /**
     * Connects to the 'booking' database by creating a Connection object.
     * @return A {@code Conneciton} object, that can be used to communicate with the database.
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    public Connection connect() throws BookingsDbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("booking")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("password").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingsDbException("Could not load DB driver.", e);
        }
    }
}
