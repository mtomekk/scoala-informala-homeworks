package ro.sci.h10;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents a festivals entrance gate, able to store the
 * festival attendees ticket information.
 * Created by Tomekk on 2/20/2017.
 */
public class FestivalGate {
    private final BlockingQueue<FestivalAttendee> queue = new LinkedBlockingQueue<>();

    /**
     * Just a delegation for the {@link LinkedBlockingQueue#put(Object)} method.
     * @param festivalAttendee the element to add.
     * @throws InterruptedException
     */
    public void put(FestivalAttendee festivalAttendee) throws InterruptedException {
        queue.put(festivalAttendee);
    }

    /**
     * Just a delegation for the {@link LinkedBlockingQueue#take()} method.
     * @return the head of the queue
     * @throws InterruptedException
     */
    public FestivalAttendee take() throws InterruptedException {
        return queue.take();
    }

    /**
     * Just a delegation for the {@link LinkedBlockingQueue#isEmpty()} method.
     * @return True if the queue is empty, false if it has at least one non-null element.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
