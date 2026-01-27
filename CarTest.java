import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    // Saab95

    @Test
    void saabDoorsShouldEqualTwo() {
        var saab = new Saab95();
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    void saabTurboShouldEqualFalse() {
        var saab = new Saab95();
        saab.setTurboOff();
        assertFalse(saab.turboOn);
    }

    @Test
    void saabTurboShouldEqualTrue() {
        var saab = new Saab95();
        saab.setTurboOn();
        assertTrue(saab.turboOn);
    }

    @Test
    void saabSpeedFactorNoTurboShouldEqualOnePointTwoFive() {
        var saab = new Saab95();
        assertEquals(1.25, saab.speedFactor());
    }

    @Test
    void saabSpeedFactorWithTurboShouldEqualOnePointSixTwoFive() {
        var saab = new Saab95();
        saab.setTurboOn();
        assertEquals(1.625, saab.speedFactor());
    }

    @Test
    void saabIncrementSpeedShouldEqualOnePointTwoFive() {
        var saab = new Saab95();
        saab.incrementSpeed(1);
        assertEquals(1.25, saab.currentSpeed);
    }
    @Test
    void saabDecrementSpeedShouldEqualZero() {
        var saab = new Saab95();
        saab.decrementSpeed(1);
        assertEquals(0, saab.currentSpeed);
    }

    // Volvo240

    @Test
    void volvoEnginePowerShouldEqualOneHundred() {
        var volvo = new Volvo240();
        assertEquals(100, volvo.getEnginePower());
    }

    @Test
    void volvoColorShouldEqualBlack() {
        var volvo = new Volvo240();
        assertEquals(Color.BLACK, volvo.getColor());
    }

    @Test
    void volvoSpeedFactorShouldEqualOnePointTwoFive() {
        var volvo = new Volvo240();
        assertEquals(1.25, volvo.speedFactor());
    }

    @Test
    void volvoIncrementSpeedShouldEqualZeroPointSixTwoFive() {
        var volvo = new Volvo240();
        volvo.incrementSpeed(0.5);
        assertEquals(0.625, volvo.currentSpeed);
    }

    @Test
    void volvoDecrementSpeedShouldEqualZero() {
        var volvo = new Volvo240();
        volvo.decrementSpeed(1);
        assertEquals(0, volvo.currentSpeed);
    }

    // Car

    @Test
    void carCurrentSpeedShouldEqualZeroPointOne() {
        var car = new Saab95();
        car.startEngine();
        assertEquals(0.1, car.currentSpeed);
    }

    @Test
    void carColorShouldEqualBlue() {
        var car = new Saab95();
        car.setColor(Color.blue);
        assertEquals(Color.blue, car.getColor());
    }

    @Test
    void carStopEngineShouldEqualZero() {
        var car = new Saab95();
        car.startEngine();
        car.gas(1);
        car.gas(1);
        car.stopEngine();
        assertEquals(0, car.currentSpeed);
    }

    // Move

    @Test
    void moveNIncreasesY() {
        Car car = new Volvo240();
        car.startEngine();
        car.move();

        assertTrue(car.getY() > 0.0);
    }

    @Test
    void moveEIncreasesX() {
        Car car = new Volvo240();
        car.turnRight();
        car.startEngine();
        car.move();

        assertTrue(car.getX() > 0.0);
    }

    @Test
    void moveSDecreasesY() {
        Car car = new Volvo240();
        car.turnRight();
        car.turnRight();
        car.startEngine();
        car.move();

        assertTrue(car.getY() < 0.0);
    }

    @Test
    void moveWDecreasesX() {
        Car car = new Volvo240();
        car.turnLeft();
        car.startEngine();
        car.move();

        assertTrue(car.getX() < 0.0);
    }

    // Turn Left

    @Test
    void turnLeftFromNToW() {
        Car car = new Volvo240();
        car.turnLeft();

        assertSame(Car.Direction.W, car.getCurrentDirection());
    }

    @Test
    void turnLeftFromWToS() {
        Car car = new Volvo240();
        car.turnLeft();
        car.turnLeft();

        assertSame(Car.Direction.S, car.getCurrentDirection());
    }

    @Test
    void turnLeftFromSToE() {
        Car car = new Volvo240();
        car.turnLeft();
        car.turnLeft();
        car.turnLeft();

        assertSame(Car.Direction.E, car.getCurrentDirection());
    }

    @Test
    void turnLeftFromEToN() {
        Car car = new Volvo240();
        car.turnRight();
        car.turnLeft();

        assertSame(Car.Direction.N, car.getCurrentDirection());
    }

    // Turn Right

    @Test
    void turnRightFromNToE() {
        Car car = new Volvo240();
        car.turnRight();

        assertSame(Car.Direction.E, car.getCurrentDirection());
    }

    @Test
    void turnRightFromEToS() {
        Car car = new Volvo240();
        car.turnRight();
        car.turnRight();

        assertSame(Car.Direction.S, car.getCurrentDirection());
    }

    @Test
    void turnRightFromSToW() {
        Car car = new Volvo240();
        car.turnRight();
        car.turnRight();
        car.turnRight();

        assertSame(Car.Direction.W, car.getCurrentDirection());
    }

    @Test
    void turnRightFromWToN() {
        Car car = new Volvo240();
        car.turnLeft();
        car.turnRight();

        assertSame(Car.Direction.N, car.getCurrentDirection());
    }

    // Gas & Brake

    @Test
    void gasShouldThrowException() {
        var car = new Saab95();
        assertThrowsExactly(IllegalArgumentException.class, () -> car.gas(2.3));
    }

    @Test
    void gasShouldEqualOnePointThreeFive() {
        var car = new Volvo240();
        car.startEngine();
        car.gas(1);
        assertEquals(1.35, car.getCurrentSpeed());
    }

    @Test
    void brakeShouldThrowException() {
        var car = new Saab95();
        assertThrowsExactly(IllegalArgumentException.class, () -> car.brake(-2.3));
    }

    @Test
    void brakeShouldEqualOnePointThreeFive() {
        var car = new Saab95();
        car.startEngine();
        car.gas(1);
        car.gas(1);
        car.brake(1);
        assertEquals(1.35, car.getCurrentSpeed());
    }

}