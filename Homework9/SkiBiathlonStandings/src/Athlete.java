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
    private List<String> shootings;
    private int missedShots;

    public Athlete(int athleteNumber, String athleteName, String countryCode, BiathlonTime time, List<String> shootings) {
        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.initialTime = time;
        this.shootings = shootings;
        this.missedShots = 0;
        for(String shot:shootings) {
            if("o".equalsIgnoreCase(shot)) {
                missedShots++;
            }
        }
        this.finalTime = correctTime(missedShots);
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

    public int getMissedShots() {
        return missedShots;
    }

    private List<String> getShootings() {
        return shootings;
    }

    /**
     * Calculates this athletes final time based on its initial
     * time and on its missed shots in the three shooting events.
     * @param missedShots A list representing this athletes shooting results.     *
     * @return This athletes final time.
     */
    private BiathlonTime correctTime(int missedShots) {
        return initialTime.add(missedShots * 10);
    }

    @Override
    public String toString() {
        return  athleteName +
                " " + finalTime  +
                "(" + initialTime + " + " +
                missedShots * 10 + ")";
    }

    @Override
    public int compareTo(Athlete other) {
        return this.finalTime.compareTo(other.finalTime);
    }
}

