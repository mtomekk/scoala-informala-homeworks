package ro.sci.booking.dao.sql;

import ro.sci.booking.Accomodation;
import ro.sci.booking.dao.RelationalDAO;
import ro.sci.booking.db.BookingsDb;
import ro.sci.booking.db.BookingsDbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@code {@link RelationalDAO}} interface.
 * Created by Tamas on 3/12/2017.
 */
public class SQLRelationalDAO implements RelationalDAO {
    BookingsDb db;

    public SQLRelationalDAO(BookingsDb db) {
        this.db = db;
    }

    /**
     * @see RelationalDAO#getAllAccomodations()
     * @return
     * @throws BookingsDbException
     * @throws SQLException
     */
    @Override
    public List<Accomodation> getAllAccomodations() throws BookingsDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM accomodation JOIN accomodation_fair_relation ON " +
                    "accomodation.id = accomodation_fair_relation.id_accomodation JOIN room_fair ON room_fair.id = accomodation_fair_relation.id_room_fair;");
            ArrayList<Accomodation> accomodations = new ArrayList<>();
            while (resultSet.next()) {
                Accomodation accomodation = new Accomodation();
                accomodation.setRoomFairId(resultSet.getInt("id_room_fair"));
                accomodation.setAccomodationId(resultSet.getInt("id_accomodation"));
                accomodation.setBedType(resultSet.getString("bed_type"));
                accomodation.setMaxGuests(resultSet.getInt("max_guests"));
                accomodation.setType(resultSet.getString("type"));
                accomodation.setDescription(resultSet.getString("description"));
                accomodation.setValue(resultSet.getDouble("value"));
                accomodation.setSeason(resultSet.getString("season"));
                accomodations.add(accomodation);
            }
            return accomodations;
        }
    }

    /**
     * @see RelationalDAO#add(Accomodation)
     * @param accomodation Information about this object will be stored in the database.
     * @throws BookingsDbException
     * @throws SQLException
     */
    @Override
    public void add(Accomodation accomodation) throws BookingsDbException, SQLException {
        try(Connection connection = db.connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accomodation_fair_relation(id_accomodation, id_room_fair) values (?,?);");
            preparedStatement.setInt(1,accomodation.getAccomodationId());
            preparedStatement.setInt(2,accomodation.getRoomFairId());

            preparedStatement.executeUpdate();
        }
    }
}
