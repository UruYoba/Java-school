package HW16;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Game {

    private final static Scanner scanner = new Scanner(System.in);
    private static Cell[] cells;
    private final static Random rnd = new Random();

    private static int score = 0;
    private static int jumps;

    /**
     * Игра максимально хреново относится к игроку
     * хуже чем Dark Souls
     */
    public static void main(String[] args) {
        showHelp();
        initCells();
        jumps = cells.length / 2;

        for (Cell cell : cells) {
            play(cell);
        }
        System.out.println("Тебе просто повезло.\nОчки: " + score);
    }

    private static void showHelp(){
        System.out.println("Задача проста: выжить.");
        System.out.println("Перед тобой будут случайные клетки. Если перепрыгнешь - гарантированно спасешься. Просто нажми пробел.");
        System.out.println("Если попытаешься пройти - можешь и подохнуть. Просто нажми w.");
        System.out.println("Количество прыжков ограничено и восполняется, когда пытаешься пройти.");
        System.out.println("Попытаешься сделать что-то не так - сдохнешь.");
        System.out.println();
    }

    private static void play(Cell cell) {
        String action = readConsole(
                String.format(
                        "Количество доступных прыжков: %d. Твой ход, неудачник",
                        jumps
                )
        );
        if(action.equals(" ") & jumps == 0){
            System.out.println("Думал меня обмануть? Хрен там! Ты проиграл.\nОчки: " + (Math.min(score, 0)));
            System.exit(0);
        }
        boolean survived = cell.tryToSurvive(action);
        if (survived) {
            score += cell instanceof BombCell ? 2 : 1;
            System.out.println(cell instanceof BombCell ? "Перепрыгнул!" : "Прошел!");
            if (action.equals(" ")) {
                jumps--;
            } else {
                jumps++;
            }
        } else {
            System.out.println("Проиграл! Впрочем, ничего удивительного, неудачник.\nОчки: " + score);
            System.exit(0);
        }

    }

    private static void initCells() {
        int sizeOfArray;
        while (true) {
            try {
                sizeOfArray = parseInt(readConsole("Выбери длину своего пути"));
                if (sizeOfArray <= 0){
                    System.out.println("Совсем за дурака меня держишь? Минус балл!");
                    score--;
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Что, сложно число вписать? Отброс...");
            }
        }
        cells = new Cell[sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++) {
            if (rnd.nextBoolean()) {
                cells[i] = new BombCell();
                continue;
            }
            cells[i] = new EmptyCell();
        }
    }

    private static String readConsole(String message) {
        System.out.print(message + ": ");
        String answer = scanner.nextLine();
        if (answer.equals("exit")) {
            System.out.println("Loser!");
            System.exit(0);
        }
        return answer;
    }
}
