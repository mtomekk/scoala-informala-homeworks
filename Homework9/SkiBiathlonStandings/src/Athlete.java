import java.util.Collections;
import java.util.List;

/**
 * Represents a biathlon athlete for the SkiBiathlonStandings application.
 * Created by Tomekk on 2/5/2017.
 */
public class Athlete implements Comparable<Athlete>{
    private int athleteNumber;
    private String athleteName;
    private String countryCode;
    private BiathlonTime initialTime;
    private BiathlonTime finalTime;
    private List<Character> shootings;
    private int missedShots;

    /**
     * Creates an instance of a biathlon athlete based on certain input values.
     * Assigns the passed values to the newly created objects fields, but also
     * determines how many shots an athlete missed and calculates the athletes
     * final time based on its initial time and the number of missed shots.
     * @param athleteNumber Integer value, representing an athletes race number.
     * @param athleteName String representing an athletes full name.
     * @param countryCode String representing an abbreviation of an athletes country of origin.
     * @param time BiathlonTime object representing an athletes time in a biathlon event.
     * @param shootings A list of Strings representing an athletes result in the shooting range.
     */
    public Athlete(int athleteNumber, String athleteName, String countryCode, BiathlonTime time, List<Character> shootings) {
        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.initialTime = time;
        this.shootings = shootings;
        this.missedShots = Collections.frequency(shootings, 'o');
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public BiathlonTime getInitialTime() {
        return initialTime;
    }

    public BiathlonTime getFinalTime() {
        return finalTime;
    }

    public List<Character> getShootings() {
        return shootings;
    }

    public int getMissedShots() {
        return missedShots;
    }

    @Override
    public String toString() {
        return  athleteName +
                " " + finalTime  +
                "(" + initialTime + " + " +
                missedShots * 10 + ")";
    }

    public void setFinalTime(BiathlonTime finalTime) {
        this.finalTime = finalTime;
    }

    /**
     * Compares athletes based on their final time.
     * @param other This athletes final time will be compared to its final time.
     * @return  A positive number if the final time of this is greater than the
     *          other athletes final time, a negative number if the final time of
     *          this is less than the others, and 0 if there final time is equal.
     */
    @Override
    public int compareTo(Athlete other) {
        return this.finalTime.compareTo(other.finalTime);
    }
}

