package HW6.Vehicles;

public class Aircraft {
    public int maxSpeed;
    public int currentSpeed;
    public int speedIncrease;
    public int maxPassengersCount;
    public int currentPassengersCount;
    public boolean isLanded;
    public String name;

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
        if (currentSpeed > maxSpeed) {
            currentSpeed = maxSpeed;
        }
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
        isLanded = false;
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
        if(currentSpeed == maxSpeed){
            return accelerate();
        }
        if (currentSpeed > maxSpeed){
            currentSpeed = maxSpeed / 2;
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
     * Acceleration for 1 move.
     * Has to be overrided.
     */
    protected int accelerate(){
        return 0;
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
