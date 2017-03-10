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
 * Represents the core of the SkiBiathlonStandings application,
 * it is responsible for reading the input file, for parsing the values
 * from the input file, for creating athletes instances and
 * for printing out the first three athletes based on their final time.
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
    private int parseAthleteNumber(String input) {
        int athleteNumber;
        try {
            athleteNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for athlete number");
        }
        if(athleteNumber <= 0) {
            throw new IllegalArgumentException("Invalid value for athlete number.");
        }
        return athleteNumber;
    }

    private String parseAthleteName(String input) {
        if(input.split(" ").length < 2) {
            throw new IllegalArgumentException("Must contain first and last name.");
        }
        return input;
    }

    private String parseCountryCode(String input) {
        if(input.length() > 3) {
            throw new IllegalArgumentException("The maximum number of characters for country code is 3");
        }
        return input.toUpperCase();
    }

    private List<Character> parseShootings(String[] shootings) {
        List<Character> results = new ArrayList<>();
        for(String round:shootings) {
            if(round.length() != 5) {
                throw new IllegalArgumentException("Too many or too less shots in of the rounds");
            }
            for(Character result : round.toCharArray()) {
                if('x' == result || 'o' == result || 'X' == result || 'O' == result) {
                    results.add(result);
                } else {
                    throw new IllegalArgumentException("Illegal value for shooting result");
                }
            }
        }
        return results;
    }

    public void calculateAthletesFinalTime(Athlete athlete) {
        int missedShots = athlete.getMissedShots();
        BiathlonTime finalTime = athlete.getInitialTime().add(missedShots * 10);
        athlete.setFinalTime(finalTime);
    }

    /**
     * Evaluates the result of a biathlon event stored in a file and
     * prints out the first three athletes having the best result.
     * It also returns an integer value, permitting unit testing on itself.
     * @param fileName The name of a file located in this applications base
     *                 directory containing results from a ski biathlon event.
     * @throws FileNotFoundException if the passed is file is not found.
     * @throws IOException if an error occurs in the reading process.
     */
    public void evaluateResults(String fileName){
        List<String> inputLines = new ArrayList<>();
        int i = 0; // it's used for tracking the lines in the input file
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for(String line = br.readLine(); line != null; line = br.readLine()) {
                inputLines.add(line);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE,"The specified file was not found.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred in the reading process.");
        }
        for(String line:inputLines) {
           try {
               i++;
               Athlete currentAthlete = parseValues(line);
               calculateAthletesFinalTime(currentAthlete);
               athletes.add(currentAthlete);
           } catch (IllegalArgumentException e) {
               logger.log(Level.SEVERE,e.getMessage() + " on line " + i);
           }
        }

        logger.log(Level.INFO, "Results from " + fileName + " were successfully processed.");
        Collections.sort(athletes);
        try {
            logger.log(Level.INFO, "Winner - " + athletes.get(0).toString());
            logger.log(Level.INFO, "Runner-up - " + athletes.get(1).toString());
            logger.log(Level.INFO, "Third place - " + athletes.get(2).toString());
        } catch (IndexOutOfBoundsException e) {
            // if there are less than 3 athletes this can happen
        }
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
            throw new IllegalArgumentException("Missing element, or too many elements");
        }

        int athleteNumber = parseAthleteNumber(elements[0]);
        String athleteName = parseAthleteName(elements[1]);
        String countryCode = parseCountryCode(elements[2]);
        BiathlonTime time = BiathlonTime.parse(elements[3]);
        List<Character> shootings = parseShootings(Arrays.copyOfRange(elements,4,elements.length));

        return new Athlete(athleteNumber,athleteName,countryCode,time,shootings);
    }
}
