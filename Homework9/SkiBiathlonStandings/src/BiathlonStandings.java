import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Created by Tomekk on 2/5/2017.
 */
public class BiathlonStandings {
    private static Logger logger = Logger.getLogger("BiathlonStandings");

    private List<Athlete> athletes;

    public BiathlonStandings() {
        athletes = new ArrayList<>();
    }

    /**
     * Checks if the athlete number is valid or not.
     * @param input A string representing the athletes number.
     * @throws IllegalArgumentException if the input cannot be
     *          converted to a legal athlete number.
     * @return An integer value representing the athletes number.
     */
    private int athleteNumberCheck(String input) {
        int athleteNumber;
        try {
            athleteNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for athlete number.");
        }
        if(athleteNumber <= 0) {
            throw new IllegalArgumentException("Invalid value for athlete number.");
        }
        return athleteNumber;
    }

    private String athleteNameCheck(String input) {
        if(input.split(" ").length != 2) {
            throw new IllegalArgumentException("Must contain first and last name.");
        }
        return input;
    }

    private String countryCodeCheck(String input) {
        if(input.length() > 3) {
            throw new IllegalArgumentException("The maximum number of characters for country code is 3");
        }
        return input.toUpperCase();
    }

    private List<String> shootingCheck(String[] shootings) {
        List<String> results = new ArrayList<>();
        for(String round:shootings) {
            if(round.length() != 5) {
                throw new IllegalArgumentException("Too many or too less shots in of the rounds");
            }
            for(String result:round.split("")) {
                if("x".equalsIgnoreCase(result) || "o".equalsIgnoreCase(result)) {
                    results.add(result);
                } else {
                    throw new IllegalArgumentException("Illegal value for shooting result");
                }
            }
        }
        return results;
    }

    /**
     * Evaluates the result of a biathlon event stored in a file and
     * prints out the first three athletes having the best result.
     * @param fileName The name of a file located in this applications base
     *                 directory containing results from a ski biathlon event.
     * @throws FileNotFoundException if the passed is file is not found.
     * @throws IOException if an error occurs in the reading process.
     */
    public void evaluateResults(String fileName){
        String line;
        int i = 1; // it's used for tracking the lines in the input file
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine()) != null) {
                addAthlete(line);
                i++;
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE,"The specified file was not found.");
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "In line " + i + ": " + e.getMessage());
            return;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred in the reading process.");
        }
        logger.log(Level.INFO, "Results from " + fileName + " were successfully processed.");
        Collections.sort(athletes);
        logger.log(Level.INFO, "Winner - " + athletes.get(0).toString());
        logger.log(Level.INFO, "Runner-up - " + athletes.get(1).toString());
        logger.log(Level.INFO, "Third place - " + athletes.get(2).toString());
    }

    /**
     * Evaluates ana converts a string to a valid {@code Athlete} instance.
     * @param input One line of input r
     * @throws IllegalArgumentException if from the input string cannot be
     *          created a valid {@code Athlete} instance.
     * @return {@code Athlete} object created from valid input.
     */
    public Athlete parseValues(String input) {
        String[] elements;
        try{
            elements = input.split(",");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if(elements.length != 7) {
            throw new IllegalArgumentException("Missing element, or too many elements.");
        }

        int athleteNumber = athleteNumberCheck(elements[0]);
        String athleteName = athleteNameCheck(elements[1]);
        String countryCode = countryCodeCheck(elements[2]);
        BiathlonTime time = BiathlonTime.parse(elements[3]);
        List<String> shootings = shootingCheck(Arrays.copyOfRange(elements,4,elements.length));

        return new Athlete(athleteNumber,athleteName,countryCode,time,shootings);
    }

    private boolean addAthlete(String input) {
        Athlete athlete = parseValues(input);
        return athletes.add(athlete);
    }

}
