package ro.sci.booking.dao;

import ro.sci.booking.Accomodation;
import ro.sci.booking.db.BookingsDbException;

import java.sql.SQLException;

/**
 * This interface contains all the necessary methods that will be needed
 * in order to add information to the 'booking' database's 'accomodation' table.
 * Created by Tamas on 3/12/2017.
 */
public interface AccomodationDAO {

    /**
     * Adds information about an accomodation to the afferent database table.
     * @param accomodation Information about this object will be stored in the database.
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    void add(Accomodation accomodation) throws BookingsDbException, SQLException;

}
