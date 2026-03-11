public class MotorVehicleFactory {
    public Volvo240 buildVolvo() { return new Volvo240(); }

    public Saab95 buildSaab() { return new Saab95(); }

    public Scania buildScania() { return new Scania(); }

    public MAN buildMAN(int cap) { return new MAN(cap); }
}
