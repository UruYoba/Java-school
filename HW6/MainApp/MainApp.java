package HW6.MainApp;

import HW6.Vehicles.Aircraft;
import HW6.Vehicles.SpaceCrafts.SpaceCraft;

public class MainApp {
    public static void main(String[] args) {
        SpaceCraft aaa = new SpaceCraft("kek", 2, 1, 0);
        int a = aaa.rise();
//        aaa.increaseSpeed();
        aaa.slowDown();
        aaa.land();
    }
}
