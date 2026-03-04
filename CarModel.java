import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CarModel {
    private ArrayList<DrawableImage> images;
    private Workshop<Volvo240> volvo240Workshop;
    private Random random = new Random();
    private BufferedImage saabImage;
    private BufferedImage volvoImage;
    private BufferedImage scaniaImage;
    protected BufferedImage volvoWorkshopImage;

    public CarModel() {
        try {
            scaniaImage = (ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            volvoImage = (ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            saabImage = (ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            images = new ArrayList<>();
            volvo240Workshop = new Workshop<>(10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (DrawableImage img : images) {
            img.getCar().gas(gas);
        }
    }
    // calls the brake method for each car
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (DrawableImage img : images) {
            img.getCar().brake(brake);
        }
    }

    void setTurboOn() {
        for(DrawableImage img : images) {
            if(img.getCar() instanceof Saab95 saab) {
                saab.setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for(DrawableImage img : images) {
            if(img.getCar() instanceof Saab95 saab) {
                saab.setTurboOff();
            }
        }
    }

    public void lowerBed() {
        for(DrawableImage img : images) {
            if(img.getCar() instanceof  Scania scania) {
                scania.lowerBed();
            }
        }
    }

    public void raiseBed() {
        for(DrawableImage img : images) {
            if(img.getCar() instanceof Scania scania) {
                scania.raiseBed();
            }
        }
    }

    public void stopAllCars() {
        for(DrawableImage img : images) {
            img.getCar().stopEngine();
        }
    }

    public void startAllCars() {
        for(DrawableImage img : images) {
            if(img.getCar().currentSpeed == 0) {
                img.getCar().startEngine();
            }
        }
    }

    public ArrayList<DrawableImage> getImages() {
        return images;
    }

    public void addImage(DrawableImage img) {
        if(images.size() < 10) images.add(img);
    }

    public void addCar() {
        if(images.size() < 10) {
            int n = random.nextInt(0,3);
            switch(n) {
                    case 0 -> images.add(new DrawableImage(new Volvo240(), volvoImage));
                    case 1 -> images.add(new DrawableImage(new Saab95(), saabImage));
                    case 2 -> images.add(new DrawableImage(new Scania(), scaniaImage));
            }
        }
    }

    public void removeCar() {
        if(!images.isEmpty()) {
            int n = random.nextInt(0, images.size());
            images.remove(n);
        }
    }

    public Workshop<Volvo240> getVolvo240Workshop() {
        return volvo240Workshop;
    }

    public BufferedImage getVolvoWorkshopImage() {
        return volvoWorkshopImage;
    }
}
