import java.awt.*;

public class Scania extends Truck {
    private double bedAngle;

    public Scania() {
        super(2,200, Color.black, "Scania");
        stopEngine();
    }

    public double getAngle() {
        return bedAngle;
    }

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

    @Override
    public boolean isDrivable() {
        return getAngle() == 0;
    }
}
