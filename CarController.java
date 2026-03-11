import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarController {
    private CarModel model;
    private CarView view;

    // Konstruktor
    public CarController(CarModel model, CarView view) {
        this.model = model;
        this.view = view;
        view.gasButton.addActionListener(e -> model.gas(view.gasAmount));
        view.liftBedButton.addActionListener(e -> model.raiseBed());
        view.lowerBedButton.addActionListener(e -> model.lowerBed());
        view.brakeButton.addActionListener(e -> model.brake(view.gasAmount));
        view.turboOffButton.addActionListener(e -> model.setTurboOff());
        view.turboOnButton.addActionListener(e -> model.setTurboOn());
        view.startButton.addActionListener(e -> model.startAllCars());
        view.stopButton.addActionListener(e -> model.stopAllCars());
        view.addCarButton.addActionListener(e -> model.addCar());
        view.removeCarButton.addActionListener(e -> model.removeCar());
        model.addObserver(view);
    }
}
