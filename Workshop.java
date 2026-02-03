import java.util.ArrayList;

public class Workshop <X extends  Car>{
    private final ArrayList<X> workshop;
    private final int capacity;

    public Workshop(int capacity) {
        workshop = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public void add(X car) {
        if(workshop.size() < capacity) workshop.add(car);
    }

    public void remove(X car) {
        workshop.remove(car);
    }

    public String lookup(X car) {
        for(X x : workshop) {
            if(car == x) return "The car: " + x;
        }
        return "This car is not in storage";
    }
}
