package HW6.MainApp;

import HW6.Vehicles.Aircraft;
import HW6.Vehicles.Airplane;
import HW6.Vehicles.SpaceCrafts.Shuttle;
import HW6.Vehicles.SpaceCrafts.SpaceCraft;
import HW6.Vehicles.SpaceCrafts.UFO;

import java.lang.constant.Constable;
import java.util.Arrays;

public class MainApp {

    // На этом моменте мне надоело писать на английском

    public static void main(String[] args) {
        Aircraft[] transport = {
                new Airplane(),
                new UFO(),
                new Shuttle()
        };
        getFastestAircraft(transport, 9000000, 5, false);
    }

    public static void getFastestAircraft(Aircraft[] aircrafts, int distance, int passengersCount, boolean inSpace) {
        int minimumTime = -1;
        String transportName = "";
        int iter = 0;
        for (Aircraft aircraft : aircrafts) {
            int time = calculateTransportTime(aircraft, distance, passengersCount, inSpace);
            if ((time > 0 & iter == 0) | (time < minimumTime & time > 0)) {
                minimumTime = time;
                iter++;
                transportName = aircraft.name;
            }
        }
        if (minimumTime < 0) {
            System.out.println("None of the aircrafts fit the requirements");
        } else {
            System.out.println(
                    String.format(
                            "Fastest aircraft is %s: %d",
                            transportName,
                            minimumTime
                    )
            );
        }
    }

    /**
     * Рассчитывает время перевозки всех пассажиров на заданное расстояние
     *
     * @param transport       - летательный аппарат (подходят UFO, Airplane и Shuttle)
     * @param distance        - расстоение, на которое нужно перевезти пассажиров
     * @param passengersCount - общее количество пассажиров
     * @param inSpace         - нужно ли летать в космос
     * @return
     */
    public static int calculateTransportTime(Aircraft transport, int distance, int passengersCount, boolean inSpace) {
        if (!(transport instanceof SpaceCraft) & inSpace) { //если нужно полететь в космос
            return -1; // Возвращает -1, если транспорт не летит в космос
        }
        int currentDistance = 0;
        int hours = 0;
        int iterationHours = 0;
        int passengerDeliveredCount = 0;
        while (passengerDeliveredCount < passengersCount) { //каждая итерация - один час. Каждый час увеличивается скорость транспортного средства
            iterationHours++;
            if (transport.isLanded) { // если транспорт на земле
                transport.takePassengers(transport.maxPassengersCount); //Забираем пассажира. Ограничение на пассажиров не ставил. Они все равно могут умереть)
                currentDistance += transport.rise(); //добавляем расстояние при взлете
                if (inSpace) {
                    dropOffDead(transport);
                }
                continue;
            }
            currentDistance += transport.increaseSpeed();
            if (currentDistance >= distance) { // когда прилетели до пункта назначения
                if (inSpace) {
                    // При посадке на землю люди тоже испытывают перегрузку
                    dropOffDead(transport);
                }
                iterationHours += transport.land(); // при приземлении сбрасываем скорость
                passengerDeliveredCount += transport.currentPassengersCount; // считаем пассажиров
                transport.dropOffPassengers(transport.currentPassengersCount); // высаживаем пассажиров
                if (passengerDeliveredCount >= passengersCount) { //если доставили всех пассажиров
                    hours += iterationHours; // обратную дорогу не считаем
                    break;
                }
                hours += iterationHours * 2; // добавляем время с учетом обратной дороги
                iterationHours = 0;
                currentDistance = 0;
            }
        }
        return hours;
    }

    /**
     * Расчет погибших пассажиров
     *
     * @param transport - транспортное средство
     */
    public static void dropOffDead(Aircraft transport) {
        /*
        Хотел использовать transport.dropOffDead, но его нет в Aircraft, но есть в SpaceCraft
        Благо в SpaceCraft определил статический метод, который считает количество погибших
        */
        transport.dropOffPassengers(
                SpaceCraft.getDeadPassengersCount(
                        transport.currentPassengersCount
                )
        );
    }
}
