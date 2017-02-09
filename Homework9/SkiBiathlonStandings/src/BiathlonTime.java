import java.text.NumberFormat;
import java.util.Locale;

/**
 * Represents a biathlon athletes time (in minutes and seconds).
 * Because the use of Javas {@code LocalTime} class is cumbersome when measuring only minutes
 * and seconds, there was a need for a new implementation for measuring minutes and seconds.
 * Created by Tomekk on 2/5/2017.
 */
public class BiathlonTime implements Comparable<BiathlonTime>{
    //locale and numberFormat objects will be used for formatting when printing out BiathlonTimes
    private static Locale locale = new Locale("minutes/seconds");
    private static NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

    private int minutes;
    private int seconds;

    public BiathlonTime(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    /**
     * Converts a string into a valid {@code BiathlonTime}.
     * @param text A string representing an athletes time and containing characters
     *             that can be converted to numeric values, separated by the ":" sign.
     * @throws IllegalArgumentException if the input string cannot be converted to a valid time.
     * @return {@code BiathlonTime} object, representing an athletes time.
     */
    public static BiathlonTime parse (String text) {
        String[] elements;
        try{
            elements = text.split(":");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if(elements.length != 2) {
            throw new IllegalArgumentException("Invalid value for the athletes time.");
        }

        int minute, second;

        try {
            minute = Integer.parseInt(elements[0]);
            second = Integer.parseInt(elements[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The athletes time isn't an integral value.");
        }

        if(minute >= 0 && minute < 60 && second >= 0 && second < 60) {
            return new BiathlonTime(minute, second);
        } else {
            throw new IllegalArgumentException("Invalid values for minutes and seconds.");
        }
    }

    /**
     * Adds seconds to this {@code BiathlonTime}.
     * @param secondsToAdd The additional time expressed in seconds.
     * @throws  IllegalArgumentException if the passed value is less than
     *          zero or greater than 150 (the maximum time penalty).
     * @return {@code BiathlonTime} object, the result of the addition.
     */
    public BiathlonTime add(int secondsToAdd) {
        if(secondsToAdd > 150 || secondsToAdd < 0) {
            throw new IllegalArgumentException("Cannot add more than 150 seconds");
        }
        int additionalMinutes = (seconds + secondsToAdd) / 60;
        int newSeconds = (seconds + secondsToAdd) % 60;

        return new BiathlonTime((minutes + additionalMinutes), newSeconds);
    }

    @Override
    public String toString() {
        numberFormat.setMinimumIntegerDigits(2);
        return  numberFormat.format(minutes) + ":" + numberFormat.format(seconds);
    }

    @Override
    public int compareTo(BiathlonTime that) {
        int result = Integer.compare(this.getMinutes(),that.getMinutes());

        if(result == 0) {
            result = Integer.compare(this.getSeconds(),that.getSeconds());
        }
        return result;
    }
}
