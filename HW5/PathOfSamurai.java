package HW5;

import java.util.Arrays;
import java.util.Random;

public class PathOfSamurai {

    private static Random rnd = new Random();

    public static void main(String[] args) {
        int[] array = generateIntArray(70000, 500);
        bubbleSort(array, true);
//        Arrays.sort(array);

        System.out.println("------");
        System.out.println(Arrays.toString(array));

        int searchTarget = rnd.nextInt(501);
        System.out.println(searchTarget);

        long beforeLinearSearchSorted = System.currentTimeMillis();
        System.out.println("Линейные поиск: " + linearSearch(array, searchTarget));
        long afterLinearSearchSorted = System.currentTimeMillis();
        long linearSearchTime = afterLinearSearchSorted - beforeLinearSearchSorted ;
        System.out.println("Время: " +  linearSearchTime);

        long beforeBinarySearchSorted = System.currentTimeMillis();
        System.out.println("Бинарный поиск: " + binarySearchDesc(array, searchTarget));
        long afterBinarySearchSorted = System.currentTimeMillis();
        long binarySearchTime = afterBinarySearchSorted - beforeBinarySearchSorted ;
        System.out.println("Время: " + binarySearchTime );

        System.out.println(
                String.format(
                        "%s поиск быстрее",
                        binarySearchTime < linearSearchTime ? "Бинарный " : "Линейный"
                )
        );

    }

    /**
     * генерирует int массив
     *
     * @param length - длина генерируемого массива
     * @param diap   - диапозон значений массива
     * @return возвращает массив с рандомными значениями типа int
     */
    public static int[] generateIntArray(int length, int diap) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(diap + 1);
        }
        return arr;
    }

    /**
     * пузырьковая сортировка массива
     *
     * @param arr        сортируемый массив
     * @param descending сортировка по убыванию
     * @return сортированный массив
     */
    public static int[] bubbleSort(int[] arr, Boolean descending) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
//                printSortIteration(arr, j, j + 1);
                if (descending) {
                    if (arr[j] < arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                } else {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }

            }

        }
        return arr;
    }

    /**
     * алгоритм линейного поиска
     *
     * @param arr    массив чисел
     * @param target искомое число
     * @return индекс массива
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * алгоритм бинарного поиска для массива, отсортированного по убыванию
     *
     * @param arr    массив чисел
     * @param target искомое число
     * @return индекс массива
     */
    public static int binarySearchDesc(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (right + left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (target < arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Наглядный вывод работы сортировочного массива, подсвечивает сравниваемые элементы
     *
     * @param arr массив
     * @param i   индекс сравниваемого элемента массива
     * @param j   индекс сравниваемого элемента массива
     */
    public static void printSortIteration(int[] arr, int i, int j) {
        System.out.print("[");
        // возможно есть варианты и получше, например с выводом подмассивов, но я не догадался)
        // для массива длиной в 50000 элементов это слишком жестоко
        for (int iter = 0; iter < arr.length; iter++) {
            System.out.print(
                    String.format(
                            iter == i ? "{%d}, " : iter == j ? "{%d}, " : iter == arr.length - 1 ? "%d" : "%d, ",
                            arr[iter]
                    )
            );
        }
        System.out.println("]");
    }

}
