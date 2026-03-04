import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    private CarModel model;
    private CarView view;

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 20;
    // The timer is started with a listener (see below) that executes the statements
    private  Timer timer;
    // indexes to remove
    private ArrayList<Integer> indexes;

    // Konstruktor
    public CarController(CarModel model, CarView view) {
        this.model = model;
        this.view = view;
        timer = new Timer(delay, new TimerListener());
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
        timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Iterator<DrawableImage> iterator = model.getImages().iterator();
            Rectangle workshopRect =
                    new Rectangle(300, 300, model.volvoWorkshopImage.getWidth(), model.volvoWorkshopImage.getHeight());
            int drawPanelWidth = view.getDrawPanel().getWidth();
            int drawPanelHeight = view.getDrawPanel().getHeight();
            while(iterator.hasNext()) {
                    DrawableImage img = iterator.next();
                    img.getCar().move();
                    int x = img.getCar().getX();
                    int y = img.getCar().getY();
                    int width = img.getImg().getWidth();
                    int height = img.getImg().getHeight();
                    Rectangle carRect = new Rectangle(x, y, width, height);
                    if (carRect.intersects(workshopRect) && img.getCar() instanceof Volvo240 volvo240) {
                        model.getVolvo240Workshop().add(volvo240);
                        iterator.remove();
                    }
                    if (y < 0 || y > drawPanelHeight - img.getImg().getHeight()) {
                        img.getCar().turnRight();
                        img.getCar().turnRight();
                    }
                    if (x < 0 || x > drawPanelWidth - img.getImg().getWidth()) {
                        img.getCar().turnRight();
                        img.getCar().turnRight();
                    }
                }
            // repaint() calls the paintComponent method of the panel
            view.repaint();
        }
    }
}
