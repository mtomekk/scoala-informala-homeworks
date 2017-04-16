package ro.sci.h10;

import java.util.Random;

/**
 * Represents a festival
 * Created by Tomekk on 2/20/2017.
 */
public class FestivalMain {
    private static Random random = new Random();

    /**
     * Entry point to the application, generates at least 100 attendees and
     * starts the statsThread thread, which will generate statistics about the attendance.
     * @param args this application isn't using any arguments.
     */
    public static void main(String[] args) {
        FestivalGate gate = new FestivalGate();
        FestivalStatisticsThread statsThread = new FestivalStatisticsThread(gate);

        //when the main thread will finish generating the attendees
        //the whole process will terminate, so this thread will be terminated as well
        statsThread.setDaemon(true);
        statsThread.start();

        generateAttendees(gate);
    }

    /**
     * Simulates at least 100 ticket validations at a festival gate.
     * @param gate A {@code FestivalGate} where the attendees validate their tickets.
     */
    private static void generateAttendees(FestivalGate gate) {
        int limit = 100 + random.nextInt(100);
        for (int i = 0; i < limit; i++) {
            try {
                Thread.sleep(100);
                new FestivalAttendee(TicketType.getRandomTicket(), gate).validateTicket();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
