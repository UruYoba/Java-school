package HW6.Vehicles.SpaceCrafts;

import HW6.Vehicles.Aircraft;

import java.util.Random;

public class SpaceCraft extends Aircraft {
    private static Random rnd = new Random();
    private static int chanceToDie = 10; // passengers are not astronauts, they're not that healthy

    public SpaceCraft(String name, int maxSpeed, int speedIncrease, int maxPassengersCount) {
        super(name, maxSpeed, speedIncrease, maxPassengersCount);
    }

    public void goToSpace(){
        dropOffDead();
        if (currentPassengersCount == 0) {
            this.land();
        }
    }

    private void dropOffDead(){
        // someone can die due to overload
        int deadPassengers = getDeadPassengersCount(this.currentPassengersCount);
        this.dropOffPassengers(deadPassengers); // a little incorrect, but it's fine
    }

    public void goToPlanet(){
        dropOffDead();
    }

    private static int getDeadPassengersCount(int currentPassengersCount){
        int deadPassengersCount = 0;
        for (int i = 0; i < currentPassengersCount; i++){
            int chanceToSurvive = rnd.nextInt(101);
            if (chanceToDie >= chanceToSurvive) {
                deadPassengersCount++;
            }
        }
        return deadPassengersCount;
    }
}
