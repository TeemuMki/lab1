import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JPanel {

    // The controller member
    private  CarModel model;
    private JPanel drawPanel;
    private JPanel controlPanel;
    private JPanel botPanel;
    JPanel startStopPanel;

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");
    JButton addCarButton = new JButton("Add car");
    JButton removeCarButton = new JButton("Remove car");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarView(CarModel model){
        this.model = model;
        setLayout(new BorderLayout());

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(DrawableImage img : model.getImages()) {
                    g.drawImage(img.getImg(), img.getCar().getX(), img.getCar().getY(), null);
                }
                g.drawImage(model.getVolvoWorkshopImage(), model.getVolvo240Workshop().x, model.getVolvo240Workshop().y, null);
            }
        };
        drawPanel.setDoubleBuffered(true);
//        drawPanel.setPreferredSize(new Dimension(800, 800));
        drawPanel.setBackground(Color.green);

        // kontrollpanel
        controlPanel = new JPanel();
        controlPanel.add(gasButton, 0);
        controlPanel.add(brakeButton, 1);
        controlPanel.add(turboOnButton, 2);
        controlPanel.add(turboOffButton, 3);
        controlPanel.add(liftBedButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addCarButton, 6);
        controlPanel.add(removeCarButton, 7);
//        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        controlPanel.setBackground(Color.cyan);

        // Spinner
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        // Stop / start knappar
        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
//        startButton.setPreferredSize(new Dimension(X/5-15,200));

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
//        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        startStopPanel = new JPanel();
        startStopPanel.setBackground(Color.cyan);
        startStopPanel.add(startButton, 0);
        startStopPanel.add(stopButton, 1);

        // En Bottom panel som håller alla knappar osv
        botPanel = new JPanel();
        botPanel.setLayout(new BorderLayout());
;
        // lägg till panelerna
        add(drawPanel, BorderLayout.CENTER);
        botPanel.add(controlPanel, BorderLayout.CENTER);
        botPanel.add(gasPanel, BorderLayout.WEST);
        botPanel.add(startStopPanel, BorderLayout.EAST);
        add(botPanel, BorderLayout.SOUTH);
    }

    public JPanel getDrawPanel() {
        return drawPanel;
    }
}