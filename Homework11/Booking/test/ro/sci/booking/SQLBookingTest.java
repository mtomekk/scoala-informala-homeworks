package ro.sci.booking;


import org.junit.*;
import ro.sci.booking.dao.sql.SQLAccomodationDAO;
import ro.sci.booking.dao.sql.SQLRelationalDAO;
import ro.sci.booking.dao.sql.SQLRoomFairDAO;
import ro.sci.booking.db.BookingsDbException;
import ro.sci.booking.db.TestBookingDb;
import ro.sci.booking.repos.AccomodationRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * This class serves testing purposes for the Booking application.
 * Created by Tamas on 3/12/2017.
 */
public class SQLBookingTest {

    private TestBookingDb db;
    private SQLAccomodationDAO accomodationDAO;
    private SQLRoomFairDAO roomFairDAO;
    private SQLRelationalDAO relationalDAO;
    private AccomodationRepository accomodationRepository;

    @BeforeClass
    public static void initTests() throws BookingsDbException, SQLException {
        TestBookingDb.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingsDbException, SQLException {
        TestBookingDb.dropTestDB();
    }

    @Before
    public void setUp(){
        db = new TestBookingDb();
        accomodationDAO = new SQLAccomodationDAO(db);
        roomFairDAO = new SQLRoomFairDAO(db);
        relationalDAO = new SQLRelationalDAO(db);
        accomodationRepository = new AccomodationRepository(accomodationDAO, roomFairDAO, relationalDAO);
    }

    @After
    public void tearDown() throws BookingsDbException, SQLException {
        db.dropDataFromTables();
    }

    /**
     * Checks if the information retrieved from the database is identical
     * with the information that has been used at the data insertion.
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    @Test
    public void whenNewAccomodationInsertedIntoDB_getReturnsThem() throws BookingsDbException, SQLException {
        Accomodation accomodation1 = new Accomodation();
        accomodation1.setType("VIP");
        accomodation1.setBedType("King-size");
        accomodation1.setMaxGuests(4);
        accomodation1.setDescription("Lovely room for Bill Gates.");
        accomodation1.setValue(500);
        accomodation1.setSeason("summer");

        Accomodation accomodation2 = new Accomodation();
        accomodation2.setType("Normal");
        accomodation2.setBedType("Double");
        accomodation2.setMaxGuests(2);
        accomodation2.setDescription("Just your average hotel room");
        accomodation2.setValue(200);
        accomodation2.setSeason("summer");

        accomodationRepository.add(accomodation1);
        accomodationRepository.add(accomodation2);

        List<Accomodation> all = accomodationRepository.getAllAccomodations();

        Assert.assertArrayEquals(new Accomodation[]{accomodation1,accomodation2}, all.toArray());

    }

    /**
     * Prints out the price and some information about the accomodations
     * stored in the database, and checks if this information is equal
     * to the inserted data.
     * @throws BookingsDbException if the database driver cannot be loaded.
     * @throws SQLException if an error occurs when accessing the database.
     */
    @Test
    public void printsPricesAndComparesListWithDataRetrievedFromDatabase() throws BookingsDbException, SQLException {
        Accomodation accomodation1 = new Accomodation();
        accomodation1.setType("VIP");
        accomodation1.setBedType("King-size");
        accomodation1.setMaxGuests(4);
        accomodation1.setDescription("Lovely room for Bill Gates.");
        accomodation1.setValue(500);
        accomodation1.setSeason("summer");

        Accomodation accomodation2 = new Accomodation();
        accomodation2.setType("Normal");
        accomodation2.setBedType("Double");
        accomodation2.setMaxGuests(2);
        accomodation2.setDescription("Just your average hotel room");
        accomodation2.setValue(200);
        accomodation2.setSeason("summer");

        Accomodation accomodation3 = new Accomodation();
        accomodation3.setType("Small");
        accomodation3.setBedType("Single");
        accomodation3.setMaxGuests(1);
        accomodation3.setDescription("Standard hotel room for one guest");
        accomodation3.setValue(150);
        accomodation3.setSeason("summer");

        accomodationRepository.add(accomodation1);
        accomodationRepository.add(accomodation2);
        accomodationRepository.add(accomodation3);

        List<Accomodation> all = accomodationRepository.getAllAccomodations();

        Assert.assertArrayEquals(new Accomodation[]{accomodation1,accomodation2,accomodation3}, all.toArray());
        for(Accomodation acc: all) {
            System.out.println(acc);
        }
    }
}
