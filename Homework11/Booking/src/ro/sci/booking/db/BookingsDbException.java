package ro.sci.booking.db;

/**
 * Created by andrei on 3/3/17.
 */
public class BookingsDbException extends Throwable {
    public BookingsDbException(String s, Exception e) {
        super(s, e);
    }

    public BookingsDbException(String s) {
        super(s);
    }
}
