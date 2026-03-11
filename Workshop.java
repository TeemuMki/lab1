import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Workshop <X extends  Car> {
    private final ArrayList<X> workshop;
    private final int capacity;
    protected int x, y;

    public Workshop(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Negative capacity not allowed");
        workshop = new ArrayList<>(capacity);
        this.capacity = capacity;
        this.x = 300;
        this.y = 300;
    }

    public void add(X car) {
        if(workshop.size() < capacity) workshop.add(car);
    }

    public void remove(X car) {
        workshop.remove(car);
    }

    public int getAmountOfCarsInWorkshop() { return workshop.size(); }

    public String lookup(X car) {
        for(X x : workshop) {
            if(car == x) return "The car: " + x;
        }
        return "This car is not in storage";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
