import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CarMain {
    static void main(String[] args) {
        CarModel m = new CarModel(10);
        CarView v = new CarView(m);
        CarController c = new CarController(m, v);

        JFrame frame = new JFrame("CarSim 2.0");
        frame.add(v);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
