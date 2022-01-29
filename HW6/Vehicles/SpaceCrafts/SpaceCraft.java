package HW6.Vehicles.SpaceCrafts;

import HW6.Vehicles.Aircraft;

import java.util.Random;

public class SpaceCraft extends Aircraft {
    private static Random rnd = new Random();
    private static int chanceToDie = 10; // passengers are not astronauts, they're not that healthy

    public SpaceCraft(String name, int maxSpeed, int speedIncrease, int maxPassengersCount) {
        super(name, maxSpeed, speedIncrease, maxPassengersCount);
    }

    /**
     * Someone can die due to overload)
     * If every passengers dies, spacecraft has to land again
     */
    public void goToSpace(){
        dropOffDead();
        if (currentPassengersCount == 0) {
            this.land();
        }
    }

    /**
     * reduces passengers count if someone died
     */
    private void dropOffDead(){
        // someone can die due to overload
        int deadPassengers = getDeadPassengersCount(this.currentPassengersCount);
        this.dropOffPassengers(deadPassengers); // a little incorrect, but it's fine
    }

    /**
     * someone can die by landing on planet too
     */
    public void goToPlanet(){
        dropOffDead();
    }

    /**
     * calculation of survivors
     * @param currentPassengersCount
     * @return count of dead passengers
     */
    public static int getDeadPassengersCount(int currentPassengersCount){
        int deadPassengersCount = 0;
        for (int i = 0; i < currentPassengersCount; i++){
            int chanceToSurvive = rnd.nextInt(101);
            if (chanceToDie >= chanceToSurvive) {
                deadPassengersCount++;
            }
        }
//        if (deadPassengersCount > 0){
//            System.out.println("Умерло: " + deadPassengersCount);
//        }
        return deadPassengersCount;
    }
}
