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

    // isStationary

    @Test
    void isStationaryShouldEqualTrue() {
        var scania = new Scania();

        assertTrue(scania.isStationary());
    }

    @Test
    void isStationaryShouldEqualFalse() {
        var scania = new Scania();
        scania.startEngine();

        assertFalse(scania.isStationary());
    }

    // Truck

    @Test
    void truckStartEngineShouldWorkIfIsDrivable() {
        var scania = new Scania();
        assertTrue(scania.isDrivable());
        scania.startEngine();

        assertEquals(0.1, scania.getCurrentSpeed());
    }

    @Test
    void truckGasShouldWorkIfIsDrivable() {
        var scania = new Scania();
        assertTrue(scania.isDrivable());
        scania.gas(1.0);

        assertTrue(scania.getCurrentSpeed() > 0.0);
    }

    @Test
    void truckStartEngineShouldDoNothingIfNotDrivable() {
        var scania = new Scania();
        scania.raiseBed();
        assertFalse(scania.isDrivable());
        scania.startEngine();

        assertEquals(0.0, scania.getCurrentSpeed());
    }

    @Test
    void truckGasShouldDoNothingIfNotDrivable() {
        var scania = new Scania();
        scania.raiseBed();
        scania.gas(1.0);

        assertFalse(scania.isDrivable());
        assertEquals(0.0, scania.getCurrentSpeed());
    }

    // Scania

    @Test
    void scaniaRaiseBedNoArgShouldIncreaseAngleByOne() {
        var scania = new Scania();
        scania.raiseBed();

        assertEquals(1, scania.getAngle());
    }

    @Test
    void scaniaRaiseBedWithArg() {
        var scania = new Scania();
        scania.raiseBed(10);

        assertEquals(10, scania.getAngle());
    }

    @Test
    void scaniaLowerBedNoArgShouldLowerAngleByOne() {
        var scania = new Scania();
        scania.raiseBed();
        scania.lowerBed();

        assertEquals(0, scania.getAngle());
    }

    @Test
    void scaniaLowerBedWithArg() {
        var scania = new Scania();
        scania.raiseBed(10);
        scania.lowerBed(10);

        assertEquals(0, scania.getAngle());
    }

    @Test
    void scaniaRaiseBedWhileNotStationaryShouldDoNothing() {
        var scania = new Scania();
        scania.startEngine();
        assertFalse(scania.isStationary());
        scania.raiseBed(10);

        assertEquals(0, scania.getAngle());
    }

    @Test
    void scaniaLowerBedWhileBedAngleIsZeroShouldDoNothing() {
        var scania = new Scania();
        scania.lowerBed();

        assertEquals(0, scania.getAngle());
    }

    @Test
    void scaniaLowerBedWhileMovingShouldDoNothing() {
        var scania = new Scania();
        scania.startEngine();
        scania.setAngle(10);
        scania.lowerBed(10);

        assertEquals(10, scania.getAngle());
    }

    @Test
    void scaniaRaiseBedShouldMakeScaniaNotDrivable() {
        var scania = new Scania();
        scania.raiseBed(10);

        assertFalse(scania.isDrivable());
    }

    @Test
    void scaniaRaiseBedNegativeAmountShouldThrowException() {
        var scania = new Scania();

        assertThrowsExactly(IllegalArgumentException.class, () -> scania.raiseBed(-1));
    }

    @Test
    void scaniaLowerBedNegativeAmountShouldThrowException() {
        var scania = new Scania();
        scania.raiseBed();

        assertThrowsExactly(IllegalArgumentException.class, () -> scania.lowerBed(-1));
    }

    // MAN

    @Test
    void manLowerBedWhileIsStationaryShouldMakeCarLoadable() {
        var man = new MAN(10);
        assertFalse(man.getLoadable());
        man.lowerBed();

        assertTrue(man.getLoadable());
    }

    @Test
    void manRaiseBedWhileIsStationaryShouldMakeCarNotLoadable() {
        var man = new MAN(10);
        man.lowerBed();
        assertTrue(man.getLoadable());
        man.raiseBed();

        assertFalse(man.getLoadable());
    }

    @Test
    void manLowerBedWhileNotStationaryShouldDoNothing() {
        var man = new MAN(10);
        man.startEngine();
        assertFalse(man.getLoadable());
        man.lowerBed();

        assertFalse(man.getLoadable());
    }

    @Test
    void loadBed() {
        var man = new MAN(6);
        var car1 = new Saab95();
        var car2 = new Saab95();
        var car3 = new Volvo240();
        var car4 = new Volvo240();
        var car5 = new Volvo240();
        man.lowerBed();
        man.loadBed(car1);
        man.loadBed(car2);
        man.loadBed(car3);
        man.loadBed(car4);
        man.loadBed(car5);
        assertEquals(5, man.getAmountOfLoadedCars());
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
        man.lowerBed();
        man.loadBed(car);
        assertEquals(0, man.getAmountOfLoadedCars());
    }

    @Test
    void unloadAll() {
        var man = new MAN(4);
        var car1 = new Saab95();
        var car2 = new Saab95();
        var car3 = new Volvo240();
        var car4 = new Volvo240();
        man.lowerBed();
        man.loadBed(car1);
        man.loadBed(car2);
        man.loadBed(car3);
        man.loadBed(car4);
        man.unloadBed(4);
        assertEquals(0, man.getAmountOfLoadedCars());
    }

    @Test
    void positionShouldUpdateForAllCars() {
        var man = new MAN(4);
        var car1 = new Volvo240();
        man.lowerBed();
        man.loadBed(car1);
        man.raiseBed();
        man.startEngine();
        man.gas(1);
        man.gas(1);
        man.move();
        man.move();
        man.unloadBed(1);
        assertEquals(car1.getY(), man.getY());
    }

    @Test
    void unloadedCarsShouldLineupInRow() {
        var man = new MAN(4);
        var car1 = new Saab95();
        var car2 = new Volvo240();
        var car3 = new Saab95();
        var car4 = new Volvo240();
        man.lowerBed();
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
    void lastInFirstOut() {
        var man = new MAN(4);
        var car1 = new Saab95();
        var car2 = new Volvo240();
        var car3 = new Saab95();
        var car4 = new Volvo240();

        man.lowerBed();
        man.loadBed(car1);
        man.loadBed(car2);
        man.loadBed(car3);
        man.loadBed(car4);

        man.unloadBed(1);
        assertFalse(man.getTruckBed().contains(car4));
        man.unloadBed(1);
        assertFalse(man.getTruckBed().contains(car3));
        man.unloadBed(1);
        assertFalse(man.getTruckBed().contains(car2));
        man.unloadBed(1);
        assertFalse(man.getTruckBed().contains(car1));
        assertEquals(0, man.getTruckBed().size());
    }

    @Test
    void loweredRampShouldNotBeDrivable() {
        var man = new MAN(4);
        man.lowerBed();
        man.startEngine();
        assertEquals(0, man.getCurrentSpeed());
    }

    @Test
    void getCarShouldEqualSameCar() {
        var man = new MAN(4);
        var car1 = new Saab95();
        man.lowerBed();
        man.loadBed(car1);
        assertEquals(car1, man.getCar(0));
    }

    @Test
    void loadBedWithInstanceOfTruckShouldDoNothing() {
        var man = new MAN(2);
        var truck = new MAN(1);
        man.loadBed(truck);

        assertFalse(man.getTruckBed().contains(truck));
    }

    @Test
    void loadBedAfterMaxCapacityIsReachedShouldDoNothing() {
        var man = new MAN(1);
        var car = new Saab95();
        var car2 = new Saab95();
        man.lowerBed();
        man.loadBed(car);
        assertEquals(1, man.getTruckBed().size());
        man.loadBed(car2);

        assertEquals(1, man.getTruckBed().size());
    }

    // Workshop

    @Test
    void negativeWorkshopCapacityShouldThrowException() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Workshop<Car>(-1));
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

    @Test
    void getAmountOfCarsInWorkshop() {
        Workshop<Car> workshop = new Workshop<>(10);
        var volvo240 = new Volvo240();
        var saab95 = new Saab95();
        workshop.add(volvo240);
        workshop.add(saab95);

        assertEquals(2, workshop.getAmountOfCarsInWorkshop());
    }

}