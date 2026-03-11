import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CarModel {
    private final ArrayList<Car> cars;
    private final Workshop<Volvo240> volvo240Workshop;
    private final Random random = new Random();
    MotorVehicleFactory factory = new MotorVehicleFactory();

    public CarModel(int cap) {
            volvo240Workshop = new Workshop<>(cap);
            cars = new ArrayList<>();

    }

    private final List<ModelObserver> observers = new ArrayList<>();

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    private void notifyObserver() {
        for(ModelObserver obs : observers) {
            obs.actOnModelUpdate();
        }
    }

    Point getCarDim() {
        if(!observers.isEmpty()) return observers.getFirst().getCarDimension();
        throw new RuntimeException();
    }

    Point getWorkshopDim() {
        if(!observers.isEmpty()) return observers.getFirst().getWorkshopDimension();
        return null;
    }

    Point getDrawPanelDim() {
        if(!observers.isEmpty()) return observers.getFirst().getDrawpanelDimension();
        return null;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }
    // calls the brake method for each car
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void setTurboOn() {
        for(Car car : cars) {
            if(car instanceof Saab95 saab) {
                saab.setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for(Car car : cars) {
            if(car instanceof Saab95 saab) {
                saab.setTurboOff();
            }
        }
    }

    public void lowerBed() {
        for(Car car : cars) {
            if(car instanceof  Scania scania) {
                scania.lowerBed();
            }
        }
    }

    public void raiseBed() {
        for(Car car: cars) {
            if(car instanceof Scania scania) {
                scania.raiseBed();
            }
        }
    }

    public void stopAllCars() {
        for(Car car : cars) {
            car.stopEngine();
        }
    }

    public void startAllCars() {
        for(Car car : cars) {
            if(car.currentSpeed == 0) {
                car.startEngine();
            }
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCar() {
        if(cars.size() < 10) {
            int n = random.nextInt(0,3);
            switch(n) {
                    case 0 -> cars.add(factory.buildVolvo());
                    case 1 -> cars.add(factory.buildSaab());
                    case 2 -> cars.add(factory.buildScania());
            }
            notifyObserver();
        }
    }

    public void removeCar() {
        if(!cars.isEmpty()) {
            int n = random.nextInt(0, cars.size());
            cars.remove(n);
            notifyObserver();
        }
    }

    public Workshop<Volvo240> getVolvo240Workshop() {
        return volvo240Workshop;
    }


    public void updateCars() {
        Iterator<Car> it = cars.iterator();
        while(it.hasNext()) {
            Car car = it.next();
            car.move();
            Point carDim = getCarDim();
            Point workDim = getWorkshopDim();
            Point drawPanelDim = getDrawPanelDim();
            if(car.getX() < 0 || car.getX() > drawPanelDim.getX() - carDim.getX()) {
                car.turnRight(); car.turnRight();
            }
            if(car.getY() < 0 || car.getY() > drawPanelDim.getY() - carDim.getY()) {
                car.turnRight(); car.turnRight();
            }
            Rectangle workRect = new Rectangle(300, 300,(int) workDim.getX(),(int) workDim.getY());
            Rectangle carRect = new Rectangle(car.getX(), car.getY(),(int) carDim.getX(), (int) carDim.getY());
            if(carRect.intersects(workRect) && car instanceof  Volvo240 volvo) {
                volvo240Workshop.add(volvo);
                it.remove();
            }
        }
        notifyObserver();
    }
}
