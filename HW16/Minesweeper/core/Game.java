package HW16.Minesweeper.core;

public class Game {
    Cell[] cells;
    Mode mode;
    boolean isSafe;

    public static void main(String[] args) {
        System.out.println("⬛");
        System.out.println(12);
    }

    public Game(Mode mode, boolean isSafe) {
        this.mode = mode;
        this.isSafe = isSafe;
    }
}
