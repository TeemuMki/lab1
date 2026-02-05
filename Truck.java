import java.awt.*;

public abstract class Truck extends Car{

    protected Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    abstract protected boolean isDrivable();

    protected boolean isStationary() { return currentSpeed == 0; }

    public abstract void raiseBed();
    public abstract void lowerBed();

    @Override
    public void startEngine() {
        if(isDrivable()) super.startEngine();
    }

    @Override
    public void gas(double amount) {
        if(isDrivable()) super.gas(amount);
    }

}
