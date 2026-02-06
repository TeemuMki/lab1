import java.awt.*;

public class Scania extends Truck {

    private int bedAngle;

    public Scania() {
        super(2,200, Color.black, "Scania");
        bedAngle = 0;
    }

    protected int getAngle()  { return bedAngle; }

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
        if (getAngle() <= 0 || !isStationary()) return;
        if (amount < 0) throw new IllegalArgumentException("Negative values not allowed");
        bedAngle = Math.max(0, bedAngle - amount);
    }

    @Override
    public boolean isDrivable() {
        return getAngle() == 0;
    }

    // For testing
    protected void setAngle(int angle) { bedAngle = angle ; }

}
