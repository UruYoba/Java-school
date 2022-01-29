package HW6.Vehicles;

import java.util.SortedMap;

public class Aircraft {
    protected int maxSpeed;
    protected int currentSpeed;
    protected int speedIncrease;
    protected int maxPassengersCount;
    protected int currentPassengersCount;
    protected boolean isLanded;
    protected String name;

    /**
     * @param name               - name of an Aircraft
     * @param maxSpeed           - km/h. Default speed = 0, because aircraft is landed by default;
     * @param speedIncrease      - km/h^2. Can be used as braking;
     * @param maxPassengersCount - max count of passengers. Current passengers count is 0;
     */
    public Aircraft(
            String name,
            int maxSpeed,
            int speedIncrease,
            int maxPassengersCount
    ) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.speedIncrease = speedIncrease;
        this.maxPassengersCount = maxPassengersCount;
        this.currentSpeed = 0;
        this.isLanded = true;
        this.currentPassengersCount = 0;
    }

    /**
     * lands this aircraft; returns time to slow down and to land
     *
     * @return time to land an aircraft
     */
    public int land() {
        this.isLanded = true;
        int timeToLand = 0;
        while (currentSpeed > 0) {
            timeToLand++;
            slowDown();
        }
        return timeToLand;
    }

    /**
     * braking (currentSpeed - speedIncrease)
     * @return currentSpeed
     */
    public int slowDown() {
        if (currentSpeed - speedIncrease <= 0 & !isLanded) {
            System.out.println(
                    String.format(
                            "%s can't stop if it's not landed",
                            name
                    )
            );
            return currentSpeed;
        } else if (currentSpeed - speedIncrease <= 0) {
            currentSpeed = 0;
            return currentSpeed;
        }
        currentSpeed -= speedIncrease;
        return currentSpeed;
    }

    /**
     * rises an aircraft and increases speed
     * @return currentSpeed
     */
    public int rise() {
        this.isLanded = false;
        return increaseSpeed();
    }

    /**
     * increases Speed
     * @return currentSpeed
     */
    public int increaseSpeed() {
        if (isLanded) {
            System.out.println(
                    String.format(
                            "%s can't increase speed if it's landed",
                            name
                    )
            );
            return currentSpeed;
        }
        currentSpeed += speedIncrease;
        if (currentSpeed > maxSpeed) {
            System.out.println(
                    String.format(
                            "%s is on its max speed",
                            name
                    )
            );
            currentSpeed = maxSpeed;
        }
        return currentSpeed;
    }

    /**
     * increases currentPassengersCount
     * @param passengersCount - passengers to take
     * @return currentPassengersCount
     */
    public int takePassengers(int passengersCount) {
        currentPassengersCount += passengersCount;
        if (currentPassengersCount > maxPassengersCount){
            currentPassengersCount = maxPassengersCount;
        }
        return currentPassengersCount;
    }

    /**
     * decreases passengersCount
     * @param passengersCount - passengers to drop off
     * @return currentPassengersCount
     */
    public int dropOffPassengers(int passengersCount){
        currentPassengersCount -= passengersCount;
        if (currentPassengersCount < 0) {
            currentPassengersCount = 0;
        }
        return currentPassengersCount;
    }
}
