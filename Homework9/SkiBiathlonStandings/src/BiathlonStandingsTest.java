import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Tomekk on 2/6/2017.
 */
public class BiathlonStandingsTest {

    @Test(expected = IllegalArgumentException.class)
    public void tooManyArguments() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5,Kecske Attila,UK,30:27,xoxxx,xoxox,oooxo,44");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notEnoughArguments() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5,Kecske Attila,UK,30:27,xoxxx,xoxox");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInput() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyInput() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidAthleteNumber() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5c,Kecske Attila,UK,30:27,xoxxx,xoxox,oooxo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidAthleteName() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5c,KecskeAttila,UK,30:27,xoxxx,xoxox,oooxo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidCountryCode() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5c,Kecske Attila,Spain,30:27,xoxxx,xoxox,oooxo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidTime() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5c,Kecske Attila,UK,30:78,xoxxx,xoxox,oooxo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalShootingSign() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5,Kecske Attila,UK,30:25,xBBBx,xoxox,oooxo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lessShootingSign() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5,Kecske Attila,UK,30:20,xx,xoxox,oooxo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void moreShootingSign() {
        BiathlonStandings bs = new BiathlonStandings();
        bs.parseValues("5,Kecske Attila,UK,30:18,xxooooo,xoxox,oooxo");
    }

    @Test
    public void validAthleteNumber() {
        BiathlonStandings bs = new BiathlonStandings();
        Athlete athlete = bs.parseValues("51,Kecske Attila,UK,30:20,xxooo,xoxox,oooxo");
        Assert.assertEquals(51,athlete.getAthleteNumber());
    }

    @Test
    public void validName() {
        BiathlonStandings bs = new BiathlonStandings();
        Athlete athlete = bs.parseValues("51,George Cook,UK,30:20,xxooo,xoxox,oooxo");
        Assert.assertEquals("George Cook",athlete.getAthleteName());
    }

    @Test
    public void validCountryCode() {
        BiathlonStandings bs = new BiathlonStandings();
        Athlete athlete = bs.parseValues("51,George Cook,UK,30:20,xxooo,xoxox,oooxo");
        Assert.assertEquals("UK",athlete.getCountryCode());
    }

    @Test
    public void validTime() {
        BiathlonStandings bs = new BiathlonStandings();
        Athlete athlete = bs.parseValues("51,George Cook,UK,30:20,xxooo,xoxox,oooxo");
        Assert.assertEquals(30,athlete.getInitialTime().getMinutes());
        Assert.assertEquals(20,athlete.getInitialTime().getSeconds());
    }

    @Test
    public void validShootResult() {
        BiathlonStandings bs = new BiathlonStandings();
        Athlete athlete = bs.parseValues("51,George Cook,UK,30:20,xxooo,xoxox,oooxo");
        Assert.assertEquals(30,athlete.getInitialTime().getMinutes());
        Assert.assertEquals(20,athlete.getInitialTime().getSeconds());
    }

    @Test
    public void calculateFinalTime() {
        BiathlonStandings bs = new BiathlonStandings();
        Athlete athlete = bs.parseValues("51,George Cook,UK,30:20,xxxxx,xxxxx,ooooo");
        Assert.assertEquals(31,athlete.getFinalTime().getMinutes());
        Assert.assertEquals(10,athlete.getFinalTime().getSeconds());
    }

//    @Test
//    public void fileDoesNotExists() {
//        BiathlonStandings bs = new BiathlonStandings();
//        Assert.assertEquals(-1,bs.evaluateResults("noSuchFile.csv"));
//    }
//
//    @Test
//    public void fileWithIllegalArguments() {
//        BiathlonStandings bs = new BiathlonStandings();
//        Assert.assertEquals(-2,bs.evaluateResults("InvalidArguments.csv"));
//    }
//
//    @Test
//    public void legalFile() {
//        BiathlonStandings bs = new BiathlonStandings();
//        Assert.assertEquals(0,bs.evaluateResults("Results.csv"));
//    }

}
