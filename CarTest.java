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
        assertFalse(saab.getTurbo());
    }

    @Test
    void saabTurboShouldEqualTrue() {
        var saab = new Saab95();
        saab.setTurboOn();
        assertTrue(saab.getTurbo());
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

    @Test
    void scaniaBedAngleShouldEqualSeventy() {
        var scania = new Scania();
        scania.raiseBed(10);
        assertEquals(10, scania.getAngle());
    }

    @Test
    void scaniaBedAngleShouldEqualZero() {
        var scania = new Scania();
        scania.lowerBed(10);
        assertEquals(0, scania.getAngle());
    }

    @Test
    void scaniaBedAngleShouldEqualFifty() {
        var scania = new Scania();
        scania.raiseBed(10);
        assertEquals(10, scania.getAngle());
    }

    @Test
    void raisingBedWhileMovingShouldDoNothing() {
        var scania = new Scania();
        scania.startEngine();
        scania.raiseBed(10);
        assertEquals(0, scania.getAngle());
    }

    @Test
    void startEngineWhileBedRaisedShouldDoNothing() {
        var scania = new Scania();
        scania.raiseBed(10);
        scania.startEngine();
        assertEquals(0, scania.getCurrentSpeed());
    }

    @Test
    void MoveWithRaisedBedShouldDoNothing() {
        var scania = new Scania();
        scania.raiseBed(10);
        scania.startEngine();
        scania.move();
        assertEquals(0, scania.getY());
    }

    @Test
    void scaniaMoveShouldFunctionNormally() {
        var scania = new Scania();
        scania.startEngine();
        scania.move();
        assertEquals(0.1, scania.getY());
    }

    @Test
    void loadNUnloadBed() {
        var man = new MAN(6);
        var car1 = new Saab95();
        var car2 = new Saab95();
        var car3 = new Volvo240();
        var car4 = new Volvo240();
        var car5 = new Volvo240();
        man.lowerRamp();
        man.loadBed(car1);
        man.loadBed(car2);
        man.loadBed(car3);
        man.loadBed(car4);
        man.loadBed(car5);
        assertEquals(5, man.getAmountOfCars());
    }

    @Test
    void shouldNotLoadCarThatIsFarAway() {
        var man = new MAN(4);
        var car = new Saab95();
        car.startEngine();
        car.setTurboOn();
        car.gas(1);
        car.gas(1);
        car.move();
        man.lowerRamp();
        man.loadBed(car);
        assertEquals(0, man.getAmountOfCars());
    }

    @Test
    void unloadAll() {
        var man = new MAN(4);
        var car1 = new Saab95();
        var car2 = new Saab95();
        var car3 = new Volvo240();
        var car4 = new Volvo240();
        man.lowerRamp();
        man.loadBed(car1);
        man.loadBed(car2);
        man.loadBed(car3);
        man.loadBed(car4);
        man.unloadBed(4);
        assertEquals(0, man.getAmountOfCars());
    }

    @Test
    void positionShouldUpdateForAllCars() {
        var man = new MAN(4);
        var car1 = new Volvo240();
        man.lowerRamp();
        man.loadBed(car1);
        man.raiseRamp();
        man.startEngine();
        man.gas(1);
        man.gas(1);
        man.move();
        man.move();
        man.unloadBed(1);
        assertEquals(car1.getY(), man.getY());
    }

    @Test
    void lowerRampWhileDrivingShouldNotWork() {
        var man = new MAN(3);
        man.lowerRamp();
        assertFalse(man.isDrivable());
    }

    @Test
    void unloadedCarsShouldLineupInRow() {
        var man = new MAN(4);
        var car1 = new Saab95();
        var car2 = new Volvo240();
        var car3 = new Saab95();
        var car4 = new Volvo240();
        man.lowerRamp();
        man.loadBed(car1);
        man.loadBed(car2);
        man.loadBed(car3);
        man.loadBed(car4);
        man.unloadBed(4);
        assertEquals(-1, car1.getY());
        assertEquals(-2, car2.getY());
        assertEquals(-3, car3.getY());
        assertEquals(-4, car4.getY());
    }

    @Test
    void loweredRampShouldNotBeDrivable() {
        var man = new MAN(4);
        man.lowerRamp();
        man.startEngine();
        assertEquals(0, man.getCurrentSpeed());
    }

    @Test
    void getCarShouldEqualSameCar() {
        var man = new MAN(4);
        var car1 = new Saab95();
        man.lowerRamp();
        man.loadBed(car1);
        assertEquals(car1, man.getCar(0));
    }

    @Test
    void workshopTesting() {
        Workshop<Car> workshop = new Workshop<>(4);
        var car1 = new Saab95();
        var car2 = new Volvo240();
        var car3 = new Scania();
        var car4 = new MAN(4);
        workshop.add(car1);
        workshop.add(car2);
        workshop.add(car3);
        workshop.add(car4);
        workshop.remove(car4);
        assertEquals("This car is not in storage", workshop.lookup(car4));
    }
}