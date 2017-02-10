/**
 * Serves testing purposes for the SkiBiathlonStandings application.
 * Created by Tomekk on 2/5/2017.
 */
public class Main {

    /**
     * Entry point for the application.
     * @param args This application is not using any arguments.
     */
    public static void main(String[] args) {

        BiathlonStandings bs = new BiathlonStandings();
        bs.evaluateResults("Results.csv");
    }
}
