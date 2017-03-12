package ro.sci.booking.dao.sql;

import ro.sci.booking.Accomodation;
import ro.sci.booking.dao.RoomFairDAO;
import ro.sci.booking.db.BookingsDb;
import ro.sci.booking.db.BookingsDbException;

import java.sql.*;

/**
 * An implementation of the {@code {@link RoomFairDAO} interface.
 * @see ro.sci.booking.dao.RoomFairDAO
 * Created by Tamas on 3/11/2017.
 */
public class SQLRoomFairDAO implements RoomFairDAO {
    BookingsDb db;

    public SQLRoomFairDAO(BookingsDb db) {
        this.db = db;
    }

    /**
     * @see RoomFairDAO#add(Accomodation)
     * @param accomodation Information about this object will be stored in the database.
     * @throws BookingsDbException
     * @throws SQLException
     */
    @Override
    public void add(Accomodation accomodation) throws BookingsDbException, SQLException {
        try(Connection connection = db.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO room_fair(value, season) values(?,?);");

            preparedStatement.setDouble(1,accomodation.getValue());
            preparedStatement.setString(2,accomodation.getSeason());
            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('room_fair_ids');");
            resultSet.next();
            accomodation.setRoomFairId(resultSet.getInt(1));
        }
    }
}
