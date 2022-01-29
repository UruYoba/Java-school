package HW6.Vehicles.SpaceCrafts;

public class UFO extends SpaceCraft{
    public UFO() {
        super("UFO", 8000, 100, 10);
    }

    private int goToHyperspace(){
        int speedOfLight = 300000; // km/s;
        this.currentSpeed = speedOfLight * 60 * 60;
        return this.currentSpeed;
    }

    @Override
    protected int accelerate(){
        return goToHyperspace();
    }
}
