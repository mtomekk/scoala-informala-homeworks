import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Tomekk on 2/5/2017.
 */
public class BiathlonTimeTest {
    @Test
    public void parseValidValue() {
        BiathlonTime time = BiathlonTime.parse("20:25");
        Assert.assertEquals(20,time.getMinutes());
        Assert.assertEquals(25,time.getSeconds());
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseNull() {
        BiathlonTime time = BiathlonTime.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseEmptyMinutesAndSeconds() {
        BiathlonTime time = BiathlonTime.parse(":");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseNotIntegerValue() {
        BiathlonTime time = BiathlonTime.parse("2.0:30");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseNonNumericValue() {
        BiathlonTime time = BiathlonTime.parse("2B:30");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseInvalidSecond() {
        BiathlonTime time = BiathlonTime.parse("20:89");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseInvalidMinute() {
        BiathlonTime time = BiathlonTime.parse("89:30");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseTooLongValues() {
        BiathlonTime time = BiathlonTime.parse("23:135");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseNegativeValue() {
        BiathlonTime time = BiathlonTime.parse("-5:15");
    }

    @Test
    public void addValidValue() {
        BiathlonTime time = new BiathlonTime(20,15);
        BiathlonTime resultTime = time.add(20);
        Assert.assertEquals(20,resultTime.getMinutes());
        Assert.assertEquals(35,resultTime.getSeconds());
    }

    @Test
    public void addMoreThan_60_Seconds() {
        BiathlonTime time = new BiathlonTime(20,15);
        BiathlonTime resultTime = time.add(60);
        Assert.assertEquals(21,resultTime.getMinutes());
        Assert.assertEquals(15,resultTime.getSeconds());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNegativeValue() {
        BiathlonTime time = new BiathlonTime(20,15);
        time.add(-20);
    }

    @Test(expected = IllegalArgumentException.class)
    // The maximum amount of penalty seconds is 150.
    public void addMoreThanMaxPenaltySeconds() {
        BiathlonTime time = new BiathlonTime(20,15);
        time.add(152);
    }
}
