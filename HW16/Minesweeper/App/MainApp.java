package HW16.Minesweeper.App;

import HW16.Minesweeper.core.Game;
import HW16.Minesweeper.core.Mode;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainApp {

    private static Scanner scanner = new Scanner(System.in);
    private static Game game;

    private static String column;
    private static int line;
    private static boolean flag;

    public static void main(String[] args) {
        game = new Game(Mode.EASY, false);
        while (!game.isGameOver){
            game.print();
            move();
        }

    }

    private static void move(){
        String input;
        while (true){
            input = readConsole("Ваш ход");
            if(validateInput(input)){
                if (game.validateUserInput(column, line)){
                    break;
                }
            }
            System.out.println("Неправильный ввод.");
        }
        if (flag){
            game.flag(column, line);
        } else {
            game.click(line, column);
        }
    }
    private static boolean validateInput(String input){
        Matcher m;
        m = Pattern.compile("([f|flag]?)\\s?([A-Za-z]{1})+(\\d+)").matcher(input);
        if(m.find()){
            column = m.group(2).toUpperCase();
            line = Integer.parseInt(m.group(3));
            flag = m.group(1) == null;
        }
        return m.matches();
    }

    private static String readConsole(String message) {
        System.out.print(message + ": ");
        String input = scanner.nextLine().toLowerCase().trim();
        if(input.equals("exit")){
            System.exit(0);
        }
        return input;
    }
}
