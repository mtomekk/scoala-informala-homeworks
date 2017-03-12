package ro.sci.booking.repos;

import ro.sci.booking.Accomodation;
import ro.sci.booking.dao.AccomodationDAO;
import ro.sci.booking.dao.RelationalDAO;
import ro.sci.booking.dao.RoomFairDAO;
import ro.sci.booking.db.BookingsDbException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Tamas on 3/11/2017.
 */
public class AccomodationRepository {
    private AccomodationDAO accomodationDAO;
    private RoomFairDAO roomFairDAO;
    private RelationalDAO relationalDAO;

    public AccomodationRepository(AccomodationDAO accomodationDAO, RoomFairDAO roomFairDAO, RelationalDAO relationalDAO) {
        this.accomodationDAO = accomodationDAO;
        this.roomFairDAO = roomFairDAO;
        this.relationalDAO = relationalDAO;
    }

    public void add(Accomodation accomodation) throws BookingsDbException, SQLException {
        accomodationDAO.add(accomodation);
        roomFairDAO.add(accomodation);
        relationalDAO.add(accomodation);
    }

    public void printAllRooms() throws BookingsDbException, SQLException {
        for(Accomodation acc: relationalDAO.getAllAccomodations()) {
            System.out.println(acc);
        }
    }

    public List<Accomodation> getAllAccomodations() throws BookingsDbException, SQLException {
        return relationalDAO.getAllAccomodations();
    }

}
