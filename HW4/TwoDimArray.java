package HW4;

import java.util.Random;


public class TwoDimArray {

    private static Random rnd = new Random();

    public static void main(String[] args) {
        int[][] arr1 = genTwoDimArray(5);
        int[][] arr2 = genTwoDimArray(5);
        compareArrays(arr1, arr2);
    }

    public static void compareArrays(int[][] arr1, int[][] arr2){
        int l = arr1.length;
        for (int i = 0; i < l; i++){
            for (int j = 0; j < l; j++){
                System.out.println(
                    String.format(
                        "Элемент по индексу [%d][%d] из 1го массива: '%d', из 2го массива: '%d', результат сравнения: '%s'",
                        i,
                        j,
                        arr1[i][j],
                        arr2[i][j],
                        arr1[i][j] == arr2[i][j] ? "равны" : "не равны"
                    )
                );
            }
        }
    }

    private static int[][] genTwoDimArray(int length) {
        int[][] arr = new int[length][length];
        for (int i =0; i < length; i++){
            for (int j = 0; j < length; j++){
                arr[i][j] = rnd.nextInt();
            }
        }
        return arr;
    }
}