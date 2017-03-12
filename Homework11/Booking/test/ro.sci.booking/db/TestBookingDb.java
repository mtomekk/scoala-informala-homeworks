package ro.sci.booking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Represents a class, that contains all the necessary methods
 * used for setting up the tests for the Booking application.
 * Created by Tamas on 3/4/17.
 */
public class TestBookingDb extends BookingsDb {

    // Creates a connection to the PostgreSQL without selecting a database
    private Connection connectToPostgreSQL() throws SQLException, BookingsDbException {
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
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("password").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingsDbException("Could not load DB driver.", e);
        }
    }

    /**
     * @see BookingsDb#connect()
     * @return
     * @throws BookingsDbException
     * @throws SQLException
     */
    @Override
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

    /**
     * Sets up the initial conditions for the tests, by creating
     * the database and the necessary tables
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    public static void setUpTestDB() throws BookingsDbException, SQLException {
        TestBookingDb tdb = new TestBookingDb();
        try(Connection connection = tdb.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE booking;");
        }

        // connect to newly created tests database and create tables.
        try(Connection connection = tdb.connect()) {
            StringBuilder builder = new StringBuilder();
            builder.append("CREATE SEQUENCE accomodation_ids;");
            builder.append("CREATE TABLE accomodation(id INT PRIMARY KEY DEFAULT NEXTVAL('accomodation_ids'), type VARCHAR(32), bed_type VARCHAR(32), max_guests INT, description VARCHAR(512));");
            builder.append("CREATE SEQUENCE room_fair_ids;");
            builder.append("CREATE TABLE room_fair(id INT PRIMARY KEY DEFAULT NEXTVAL('room_fair_ids'), value NUMERIC, season VARCHAR(32));");
            builder.append("CREATE SEQUENCE accomodation_fair_relation_ids;");
            builder.append("CREATE TABLE accomodation_fair_relation(id INT PRIMARY KEY DEFAULT NEXTVAL('accomodation_fair_relation_ids'), id_accomodation INT REFERENCES accomodation(id), id_room_fair INT REFERENCES room_fair(id));");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    /**
     * Deletes the 'booking' database, will be called
     * only after all the tests terminate.
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    public static void dropTestDB() throws BookingsDbException, SQLException {
        TestBookingDb tdb = new TestBookingDb();
        try(Connection connection = tdb.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE booking;");
        }
    }

    /**
     * Deletes all the tables from 'booking' database,
     * it will be called after each test terminates.
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    public void dropDataFromTables() throws BookingsDbException, SQLException {
        try(Connection connection = connect()){
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE FROM accomodation_fair_relation;");
            builder.append("DELETE FROM accomodation;");
            builder.append("DELETE FROM room_fair;");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }
}
