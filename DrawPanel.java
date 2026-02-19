import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    void addImage(BufferedImage image) {
        images.add(image);
    }

    void addPoint(Point point) {
        carPoints.add(point);
    }

    // Just a single image, TODO: Generalize
    ArrayList<BufferedImage> images = new ArrayList<>();
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    // To keep track of a single car's position
    ArrayList<Point> carPoints = new ArrayList<>();
    Point volvoPoint = new Point(0,0);
    Point saabPoint = new Point(0,100);
    Point scaniaPoint = new Point(0,200);

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    // TODO: Make this general for all cars
    void moveit(int index, int x, int y){
        carPoints.get(index).x = x;
        carPoints.get(index).y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // carImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            scaniaImage = (ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            volvoImage = (ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            saabImage = (ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            addImage(volvoImage);
            addImage(saabImage);
            addImage(scaniaImage);
            addPoint(volvoPoint);
            addPoint(saabPoint);
            addPoint(scaniaPoint);
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < carPoints.size(); i++) {
            g.drawImage(images.get(i), carPoints.get(i).x, carPoints.get(i).y, null);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
