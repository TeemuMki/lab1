import java.awt.*;

public class Scania extends Truck {

    private int bedAngle;
    //private double bedAngle;

    public Scania() {
        super(2,200, Color.black, "Scania");
        bedAngle = 0;
    }

    public int getAngle()  { return bedAngle; }

    /*
    public double getAngle() {
        return bedAngle;
    }
    */

    @Override
    public void raiseBed() { raiseBed(1); }

    public void raiseBed(int amount) {
        if (!isStationary()) return;
        if(amount < 0) throw new IllegalArgumentException("Negative values not allowed");
        bedAngle = Math.min(70, bedAngle + amount);
    }

    @Override
    public void lowerBed() { lowerBed(1); }

    public void lowerBed(int amount) {
        if (!isStationary()) return;
        if (amount < 0) throw new IllegalArgumentException("Negative values not allowed");
        bedAngle = Math.max(0, bedAngle - amount);
    }

    /*

    public void lowerBed(double amount) {
        if(getCurrentSpeed() > 0) return;
        if(amount < 0) throw new IllegalArgumentException("Negative values not allowed");
        if(bedAngle - amount < 0) bedAngle = 0;
        else bedAngle -= 10;
    }

    public void raiseBed(double amount) {
        if(getCurrentSpeed() > 0) return;
        if(amount < 0) throw new IllegalArgumentException("Negative values not allowed");
        if(bedAngle + amount > 70) bedAngle = 70;
        else bedAngle += 10;
    }

    */

    @Override
    public boolean isDrivable() {
        return getAngle() == 0;
    }

}
