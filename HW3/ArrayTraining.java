package HW3;

import java.util.Random;
import java.util.Arrays;

public class ArrayTraining {
    public static void main(String[] args) {
        Random rnd = new Random();
        int[] firstArr = new int[rnd.nextInt(11) + 5];
        System.out.println(Arrays.toString(firstArr));

        float[] secondArr = new float[4];

        for (int i = 0; i < secondArr.length; i++){
            secondArr[i] = rnd.nextFloat();
        }
        System.out.println(secondArr[2]);

    }
}
