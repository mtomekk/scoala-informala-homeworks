package ro.sci.h10;

/**
 * Represents a festivals attendee, able to validate its ticket at a festival gate.
 * Created by Tomekk on 2/20/2017.
 */
public class FestivalAttendee{
    private TicketType ticketType;
    private FestivalGate gate;

    public FestivalAttendee(TicketType ticketType, FestivalGate gate) {
        this.ticketType = ticketType;
        this.gate = gate;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * Stores information about this attendee in a festival gates queue.
     * @throws InterruptedException if the {@code put()} method is interrupted for some reason.
     */
    public void validateTicket() throws InterruptedException {
        gate.put(this);
//        System.out.println(this.getTicketType() + " ticket validated." );
    }

}
