import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawableImage {
    private Car car;
    private BufferedImage img;

    public DrawableImage(Car car, BufferedImage img) {
        this.car = car;
        this.img = img;
    }

    public Car getCar() {
        return car;
    }

    public BufferedImage getImg() {
        return this.img;
    }
}
