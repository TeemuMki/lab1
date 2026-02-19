import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    // Volvo workshop
    Workshop<Volvo240> volvo240Workshop = new Workshop<>(5);
    // List of cars to be removed
    private ArrayList<Integer> indexes = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());
        cc.cars.get(0).y = 345; // Volvo
        cc.cars.get(1).y = 200; // Saab
        cc.cars.get(2).y = 100; // Scania

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                int i = cars.indexOf(car);
                if(Math.abs(x-300) <= 50  && Math.abs(y-300) <= 50 && car instanceof Volvo240 volvo240) {
                    volvo240Workshop.add(volvo240);
                    indexes.add(i);
                }
                if(y < 0 || y > frame.drawPanel.getHeight() - frame.drawPanel.images.get(i).getHeight()) {
                    car.turnRight();
                    car.turnRight();
                }
                if(x < 0 || x > frame.drawPanel.getWidth() - frame.drawPanel.images.get(i).getWidth()) {
                    car.turnRight();
                    car.turnRight();
                }
                frame.drawPanel.moveit(i, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
            if(!indexes.isEmpty()) {
                for (int i : indexes) {
                    cars.remove(i);
                    frame.drawPanel.images.remove(i);
                    frame.drawPanel.carPoints.remove(i);
                }
                indexes.clear();
            }
        }
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
        for(Car car : cars) {
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
            car.startEngine();
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}
