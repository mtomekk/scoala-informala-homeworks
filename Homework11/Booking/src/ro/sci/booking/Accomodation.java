package ro.sci.booking;

/**
 * Represents an accomodation with all of its parameters,
 * including some auxiliary ids, used for storing the accomodations
 * properties in the afferent tables of the database 'booking'.
 * Created by Tomekk on 3/10/2017.
 */
public class Accomodation {
    private int accomodationId;
    private int roomFairId;
    private String type;
    private String bedType;
    private int maxGuests;
    private String description;
    private double value;
    private String season;

    public int getAccomodationId() {
        return accomodationId;
    }

    public void setAccomodationId(int accomodationId) {
        this.accomodationId = accomodationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRoomFairId() {
        return roomFairId;
    }

    public void setRoomFairId(int roomFairId) {
        this.roomFairId = roomFairId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Id: " + accomodationId +
                ", type: " + type +
                ", bedType: " + bedType +
                ", maxGuests: " + maxGuests +
                ", description: " + description +
                ", value: " + value +
                ", season: " + season + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accomodation that = (Accomodation) o;

        if (accomodationId != that.accomodationId) return false;
        if (roomFairId != that.roomFairId) return false;
        if (maxGuests != that.maxGuests) return false;
        if (Double.compare(that.value, value) != 0) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (bedType != null ? !bedType.equals(that.bedType) : that.bedType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return season != null ? season.equals(that.season) : that.season == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = accomodationId;
        result = 31 * result + roomFairId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (bedType != null ? bedType.hashCode() : 0);
        result = 31 * result + maxGuests;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (season != null ? season.hashCode() : 0);
        return result;
    }
}
