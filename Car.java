import java.awt.*;

public abstract class Car implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    protected enum Direction {N, E, S, W} // Possible directions of the car: North, East, South, West
    protected Direction currentDirection; // Represents the car's current direction
    protected double x, y; // Represents the cars x and y coordinates

    protected Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentSpeed = 0;

        this.currentDirection = Direction.N;
        this.x = this.y = 0.0;
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    @Override
    public void move() {
        switch (currentDirection) {
            case N -> y += currentSpeed;
            case E -> x += currentSpeed;
            case S -> y -= currentSpeed;
            case W -> x -= currentSpeed;
        }
    }

    @Override
    public void turnLeft() {
        switch (currentDirection) {
            case N -> currentDirection = Direction.W;
            case E -> currentDirection = Direction.N;
            case S -> currentDirection = Direction.E;
            case W -> currentDirection = Direction.S;
        }
    }

    @Override
    public void turnRight() {
        switch (currentDirection) {
            case N -> currentDirection = Direction.E;
            case E -> currentDirection = Direction.S;
            case S -> currentDirection = Direction.W;
            case W -> currentDirection = Direction.N;
        }
    }

    public void gas(double amount){
        if(amount < 0 || amount > 1) throw new IllegalArgumentException("Input outside of allowed range [0,1]");
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if(amount < 0 || amount > 1) throw new IllegalArgumentException("Input outside of allowed range [0,1]");
        decrementSpeed(amount);
    }

    protected double speedFactor() {
        return enginePower * 0.01;
    }

    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    @Override
    public String toString() {
        return "{" +
                "modelName = '" + modelName + '\'' +
                ", color = " + color +
                '}';
    }

    // For testing
    public double getX() { return x; }
    public double getY() { return y; }
    public Direction getCurrentDirection() { return currentDirection; }
}
