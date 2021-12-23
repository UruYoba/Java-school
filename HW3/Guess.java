package HW3;

import java.util.Random;
import java.util.Scanner;

public class Guess {
    public static void main(String[] args) {
        int colorId = new Random().nextInt(8);
        String[] colors = new String[]{
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "indigo",
            "violet"
        };
        System.out.println("Guess the collor:");
        for (int i=0; i < colors.length; i++){
            System.out.println(colors[i]);
        }
        System.out.println("-----");

        Scanner scan = new Scanner(System.in);

        while(true){
            String answ = scan.nextLine().trim().toLowerCase();
            Boolean bool = colors[colorId].equals(answ);
            System.out.println(
                String.format(
                    "%s: %b",
                    answ,
                    bool
                )
            );
            if (bool){
                break;
            }
        }
        scan.close();
    }
}
