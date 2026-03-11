import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JPanel implements ModelObserver{
    private BufferedImage volvoWorkshopImage;
    private BufferedImage volvoImage;
    private BufferedImage saabImage;
    private BufferedImage scaniaImage;
    HashMap<Class<? extends Car>, BufferedImage> images;
    private ArrayList<DrawableImage> img;
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

    final JButton gasButton = new JButton("Gas");
    final JButton brakeButton = new JButton("Brake");
    final JButton turboOnButton = new JButton("Saab Turbo on");
    final JButton turboOffButton = new JButton("Saab Turbo off");
    final JButton liftBedButton = new JButton("Scania Lift Bed");
    final JButton lowerBedButton = new JButton("Lower Lift Bed");
    final JButton addCarButton = new JButton("Add car");
    final JButton removeCarButton = new JButton("Remove car");
    final JButton startButton = new JButton("Start all cars");
    final JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarView(CarModel model){
            this.model = model;
            images = new HashMap<>();
            setLayout(new BorderLayout());
            try {
                scaniaImage = (ImageIO.read(new File(("pics/Scania.jpg"))));
                volvoImage = (ImageIO.read(new File(("pics/Volvo240.jpg"))));
                saabImage = (ImageIO.read(new File(("pics/Saab95.jpg"))));
                images.put(Volvo240.class, volvoImage);
                images.put(Saab95.class, saabImage);
                images.put(Scania.class, scaniaImage);

                volvoWorkshopImage = ImageIO.read(new File(("pics/VolvoBrand.jpg")));

                Timer timer = new Timer(20, new TimerListener());
                timer.start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        drawPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                        Iterator<Car> iterator = model.getCars().iterator();
                        while(iterator.hasNext()) {
                            Car car = iterator.next();
                            BufferedImage img = images.get(car.getClass());
                            g.drawImage(img, car.getX(), car.getY(), null);
                        }
                        g.drawImage(volvoWorkshopImage, model.getVolvo240Workshop().getX(), model.getVolvo240Workshop().getY(), null);
                }
            };
            drawPanel.setDoubleBuffered(true);
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

            stopButton.setBackground(Color.red);
            stopButton.setForeground(Color.black);
            startStopPanel = new JPanel();
            startStopPanel.setBackground(Color.cyan);
            startStopPanel.add(startButton, 0);
            startStopPanel.add(stopButton, 1);

            // En Bottom panel som håller alla knappar osv
            botPanel = new JPanel();
            botPanel.setLayout(new BorderLayout());

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

    @Override
    public void actOnModelUpdate() {
        repaint();
    }

    @Override
    public Point getCarDimension() {
        return new Point(scaniaImage.getWidth(), scaniaImage.getHeight());
    }

    @Override
    public Point getWorkshopDimension() {
        return new Point(volvoWorkshopImage.getWidth(), volvoWorkshopImage.getHeight());
    }

    @Override
    public Point getDrawpanelDimension() {
        return new Point(drawPanel.getWidth(), drawPanel.getHeight());
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.updateCars();
        }
    }
}