import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MAN extends Truck {

    protected final ArrayList<Car> truckBed;
    private final int maxCapacity;
    private boolean loadable;

    public MAN(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Negative capacity not allowed");
        super(2,200, Color.black, "MAN");
        this.truckBed = new ArrayList<>();
        loadable = false;
        this.maxCapacity = capacity;
    }

    public int getAmountOfLoadedCars() {
        return truckBed.size();
    }

    public Car getCar(int i) {
        return truckBed.get(i);
    }

    public void loadBed(Car car) {
        if(car instanceof Truck) return;
        if(truckBed.size() >= maxCapacity) return;
        if(Math.abs(this.getX() - car.getX()) <= 3 && Math.abs(this.getY() - car.getY()) <= 3 && loadable) truckBed.add(car);
    }

    public void unloadBed(int amount) {
        if(loadable && amount <= truckBed.size()) {
            for(int i = 0; i < amount; i++) {
                int lastIdx = truckBed.size() - 1;
                Car temp = truckBed.get(lastIdx);
                temp.y -= lastIdx + 1;
                truckBed.remove(lastIdx);
            }
        }
    }

    @Override
    public void lowerBed() {
        if(isStationary()) loadable = true;
    }

    @Override
    public void raiseBed() {
        if(isStationary()) loadable = false;
    }

    @Override
    public boolean isDrivable() {
        return !loadable;
    }

    @Override
    public void move() {
        super.move();
        for (Car car : truckBed) {
            car.x = this.x;
            car.y = this.y;
        }
    }

    // For testing
    protected List<Car> getTruckBed() { return truckBed; }
    protected boolean getLoadable() { return loadable; }

}
