import java.awt.*;
import java.util.ArrayList;

public class MAN extends Truck {
    private final ArrayList<Car> truckBed;
    private final int maxCapacity;
    private boolean loadable;

    public MAN(int capacity) {
        super(2,200, Color.black, "MAN");
        this.truckBed = new ArrayList<>();
        loadable = false;
        this.maxCapacity = capacity;
        stopEngine();
    }

    public int getAmountOfCars() {
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
        Car temp;
        if(loadable && amount <= truckBed.size()) {
            for(int i = amount - 1; i >= 0; i--) {
                temp = truckBed.get(i);
                temp.y -= i+1;
                truckBed.remove(i);
            }
        }
    }

    public void lowerRamp() {
        if(getCurrentSpeed() == 0) loadable = true;
    }

    public void raiseRamp() {
        loadable = false;
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
}
