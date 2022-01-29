package HW6.Vehicles;

public class Airplane extends Aircraft{
    public Airplane() {
        super("Airplane", 1000, 500, 660);
    }

    public int goSupersonic(){
        int supersonicSpeed = 2100; //km/h
        this.currentSpeed = supersonicSpeed;
        return currentSpeed;
    }

    @Override
    protected int accelerate(){
        return goSupersonic();
    }
}
