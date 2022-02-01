package HW16.App;

import HW16.Game.Cells.BombCell;
import HW16.Game.MinesWeeper;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;

public class MainApp {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        MinesWeeper m = new MinesWeeper(false, 10);
        m.demine(1,1);
        m.print(false);
//        System.out.println("\uD83D\uDEA9");

    }

    public static String readConsole(String message) {
        System.out.print(message + ": ");
        String answer = scan.nextLine().trim().toLowerCase();
        if(answer.equals("exit")){
            System.exit(0);
        }
        return answer;
    }
}
