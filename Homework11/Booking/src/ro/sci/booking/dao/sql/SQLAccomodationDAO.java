package ro.sci.booking.dao.sql;

import ro.sci.booking.Accomodation;
import ro.sci.booking.dao.AccomodationDAO;
import ro.sci.booking.db.BookingsDb;
import ro.sci.booking.db.BookingsDbException;

import java.sql.*;

/**
 * An implementation of the {@code {@link AccomodationDAO}} interface.
 * @see ro.sci.booking.dao.AccomodationDAO
 * Created by Tamas on 3/11/2017.
 */
public class SQLAccomodationDAO implements AccomodationDAO {
    private BookingsDb db;

    public SQLAccomodationDAO(BookingsDb db) {
        this.db = db;
    }

    /**
     * @see AccomodationDAO#add(Accomodation)
     * @param accomodation Information about this object will be stored in the database.
     * @throws BookingsDbException
     * @throws SQLException
     */
    @Override
    public void add(Accomodation accomodation) throws BookingsDbException, SQLException {
        try(Connection connection = db.connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accomodation(type, bed_type, max_guests, description) values (?,?,?,?)");
            preparedStatement.setString(1, accomodation.getType());
            preparedStatement.setString(2,accomodation.getBedType());
            preparedStatement.setInt(3,accomodation.getMaxGuests());
            preparedStatement.setString(4,accomodation.getDescription());

            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('accomodation_ids');");
            resultSet.next();
            accomodation.setAccomodationId(resultSet.getInt(1));
        }
    }
}
