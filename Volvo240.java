import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;

    public Volvo240(){
        super(4, 100, Color.black, "Volvo240");
    }

    /*
    public Volvo240(){
        color = Color.black;
        nrDoors = 4;
        enginePower = 100;
        modelName = "Volvo240";
        stopEngine();
    }
    */

    @Override
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}
