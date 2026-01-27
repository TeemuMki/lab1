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

    // Move

    // Turn Left

    // Turn Right

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