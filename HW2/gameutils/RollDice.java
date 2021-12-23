package HW2.gameutils;
import java.util.Random;

public class RollDice {

    private static Random rnd = new Random();

    /**
     * генерирует рандомное число в заданном диапозоне и выводит на печать
     * @param diap - максимальное значение броска
     * @return рандомное число int
     */
    private static int random(int diap) {
        // Решил вывести рандом в отдельный метод, чтобы не дублировать код
        int dice = rnd.nextInt(diap) + 1;
        System.out.println(dice);
        return dice;
    }

    /**
     * бросок кубика D20
     * @return возвращает результат броска с типом byte
     */
    public static byte rollD20() {
        byte dice = (byte) random(20); //Учись кодить) Замени на random
        if (dice == 1) {
            System.out.println("Critical failure!");
        }
        if (dice == 20) {
            System.out.println("Critical luck!");
        }
        return dice;
    }
    /**
     * бросок кубика D6
     * @return возвращает результат броска с типом byte
     */
    public static byte rollD6() {
        return (byte) random(6);
    }

    /**
     * бросок кубика D8
     * @return возвращает результат броска с типом byte
     */
    public static byte rollD8() {
        return (byte) random(8);
    }

    /**
     * бросок кубика D12
     * @return возвращает результат броска с типом byte
     */
    public static byte rollD12() {
        return (byte) random(12);
    }

    /**
     * бросок кубика D10
     * @return возвращает результат броска с типом byte
     */
    public static byte rollD10() {
        // Здесь пришлось прописывать реализацию рандома, чтобы входил 0
        int dice = rnd.nextInt(9);
        System.out.println(dice);
        return (byte) dice;
    }

        /**
     * бросок кубика D100
     * @return возвращает результат броска с типом byte
     */
    public static byte rollD100() {
        // Здесь пришлось прописывать реализацию рандома, чтобы входил 00 и тд
        int dice = rnd.nextInt(9);
        System.out.println(dice + "0");
        return (byte) dice;
    }
}