package HW3;

public class Rating {
    public static void main(String[] args) {
        int[] arr = {1,2,9, 10, 8, 9};
        System.out.println(getRating(arr));
    }

    public static float getRating(int[] ratings) {
        int avg = 0;
        for (int i = 0; i < ratings.length; i++){
            avg += ratings[i];
        }
        return (float) avg/ratings.length;
    }
}
