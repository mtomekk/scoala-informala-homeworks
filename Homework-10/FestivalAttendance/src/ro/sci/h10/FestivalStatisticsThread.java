package ro.sci.h10;

import java.util.Hashtable;

/**
 * Represents a thread that reads data from a festival gate and generates
 * statistics about the festivals attendance in every 5 seconds.
 * Created by Tomekk on 2/20/2017.
 */
public class FestivalStatisticsThread extends Thread {
    private FestivalGate gate;
    private Hashtable<TicketType,Integer> attendees;
    private int totalAttendeeCounter;

    /**
     * Creates a {@code {@link FestivalStatisticsThread}} instance, initializes
     * a map, where the headcount for all ticket type is stored, and sets the
     * initial counts to 0, even for the total attendee counter.
     * @param gate
     */
    public FestivalStatisticsThread(FestivalGate gate) {
        this.gate = gate;
        totalAttendeeCounter = 0;
        attendees = new Hashtable<>();
        for(TicketType key:TicketType.values()) {
            attendees.put(key,0);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for 5 seconds...\n");
                Thread.sleep(5000);
                while(!gate.isEmpty()) {
                    addToStatistics(gate.take());
                }
                System.out.println(totalAttendeeCounter + " people entered the festival.");
                System.out.println(attendees.get(TicketType.FREE_PASS) + " people have free passes.");
                System.out.println(attendees.get(TicketType.FULL) + " people have full passes.");
                System.out.println(attendees.get(TicketType.FULL_VIP) + " people have full VIP passes.");
                System.out.println(attendees.get(TicketType.ONE_DAY) + " people have one-day passes.");
                System.out.println(attendees.get(TicketType.ONE_DAY_VIP) + " people have one-day VIP passes.\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Increments both, the counter for a certain ticket
     * type and the total attendee counter.
     * @param attendee Its ticket type information will be added to the statistics.
     */
    private void addToStatistics(FestivalAttendee attendee) {
        TicketType ticketType = attendee.getTicketType();
        attendees.replace(ticketType, attendees.get(ticketType) + 1);
        totalAttendeeCounter++;
    }


}
