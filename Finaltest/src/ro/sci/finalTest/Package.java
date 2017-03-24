package ro.sci.finalTest;

import java.time.LocalDate;

/**
 * Created by Tamas on 3/24/2017.
 */
public class Package {
    private String targetLocation;
    private int distance;
    private double value;
    private LocalDate deliveryDate;

    public Package(String targetLocation, int distance, double value, LocalDate deliveryDate) {
        this.targetLocation = targetLocation;
        this.distance = distance;
        this.value = value;
        this.deliveryDate = deliveryDate;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(String targetLocation) {
        this.targetLocation = targetLocation;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
