package ro.sci.booking.dao;

import ro.sci.booking.Accomodation;
import ro.sci.booking.db.BookingsDbException;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface contains all the necessary methods that will be needed
 * in order to connect the information from the 'accomodation' and 'room_fair' tables.
 * It also returns a list with all of the accomodations stored in the database
 * by using the JOIN command and all the information from the databases 3 tables.
 * Created by Tamas on 3/12/2017.
 */
public interface RelationalDAO {

    /**
     * Queries information from the database and returns a list of accomodations
     * created based on the retrieved information from the database.
     * Uses the JOIN command 2 times to obtain the necessary information
     * and to create accomodation objects. Than creates a list with
     * these accomodation objects and returns them.
     * @return a list containing all the accomodations from the database.
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    List<Accomodation> getAllAccomodations() throws BookingsDbException, SQLException;

    /**
     * Adds information about an accomodation to the afferent database table.
     * @param accomodation Information about this object will be stored in the database.
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    void add(Accomodation accomodation) throws BookingsDbException, SQLException;
}
