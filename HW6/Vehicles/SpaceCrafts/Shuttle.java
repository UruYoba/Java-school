package HW6.Vehicles.SpaceCrafts;

public class Shuttle extends SpaceCraft {
    public Shuttle() {
        super("Shuttle", 27000, 450, 20);
    }

    public int goSecondSpaceSpeed() {
        int secondSpaceSpeed = 40320;
        this.currentSpeed = secondSpaceSpeed;
        return this.currentSpeed;
    }

    @Override
    protected int accelerate(){
        return goSecondSpaceSpeed();
    }
}
