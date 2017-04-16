package ro.sci.h10;

import java.util.Random;

/**
 * Represents the available ticket types for a festival, it is also
 * capable to generate random tickets for the festival attendees.
 * Created by Tomekk on 2/20/2017.
 */
public enum TicketType {
    FULL, FULL_VIP, FREE_PASS, ONE_DAY, ONE_DAY_VIP;

    private static final TicketType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    /**
     * Generates a random ticket type from the available ticket types.
     * @return a randomly generated {@code {@link TicketType}}.
     */
    public static TicketType getRandomTicket() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
