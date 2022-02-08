package HW16.Minesweeper.core;

import HW16.Minesweeper.core.Cells.BombCell;
import HW16.Minesweeper.core.Cells.Cell;
import HW16.Minesweeper.core.Cells.EmptyCell;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    private static final Random rnd = new Random();
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    Cell[][] cells;
    Mode mode;
    boolean isSafe;
    public boolean isGameOver;

    public static void main(String[] args) {
        Matcher m = Pattern.compile("([f\\|flag]?)\\s?([a-zA-Z]{1})(\\d+)").matcher("f a11");
        m.find();
        System.out.println(m.group(1));
        System.out.println(m.group(2));
        System.out.println(m.group(3));
    }

    public Game(Mode mode, boolean isSafe) {
        this.mode = mode;
        this.isSafe = isSafe;
        this.cells = new Cell[this.mode.sizeX][this.mode.sizeY];
        this.isGameOver = false;
        generateCells();
    }

    public void print() {
        System.out.print("|");
        for (int i = 0; i < mode.sizeY; i++) {
            System.out.print(alphabet.charAt(i) + "|");
        }
        System.out.println();
        for (int i = 0; i < cells.length; i++) {
            System.out.print("|");
            String line = "";
            for (int j = 0; j < cells[i].length; j++) {
                line += cells[i][j].toPrint(this.isGameOver);
            }
            line += i;
            System.out.println(line);
        }
    }

    public boolean validateUserInput(String column, int line){
        System.out.println(alphabet.indexOf(column) < mode.sizeY);
        return alphabet.indexOf(column) < mode.sizeY & line < mode.sizeX & line >= 0;
    }

    public void flag(String column, int x){
        System.out.println("s");
        int y = alphabet.indexOf(column);
        cells[x][y].flag();
    }

    public void click(int x, String column) {
        int y = alphabet.indexOf(column);
        Cell cell = cells[x][y];
        System.out.println(cell);
        cell.click();
        if (cell instanceof BombCell & cell.isOpened) {
            this.isGameOver = true;
            this.print();
            System.out.println("You failed.");
        } else {

        }
    }

    private void openEmptyCells(Cell cell) {
        if (cell.isOpened) {
            return;
        }
        int x = cell.x;
        int y = cell.y;
        Cell[] cellsToOpen = new Cell[8];
        int iter = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y; j++) {
                if (x == i & y == j){
                    continue;
                }
                cells[i][j].click();
                if(cell.currentValue.equals(" ")){
                    cellsToOpen[iter++] = cells[i][j];
                }
            }
        }
        for(Cell emptyCell: cellsToOpen){
            openEmptyCells(emptyCell);
        }
    }

    private Cell[] generateBombs() {
        Cell[] bombCells = new Cell[mode.bombCount];
        int bombCount = 0;
        while (bombCount < mode.bombCount) {
            int x = rnd.nextInt(mode.sizeX);
            int y = rnd.nextInt(mode.sizeY);
            if (cells[x][y] != null) {
                continue;
            } else {
                Cell bomb = new BombCell(x, y);
                bombCells[bombCount++] = bomb;
                this.cells[x][y] = bomb;
            }
        }
        return bombCells;
    }

    private void generateCells() {
        Cell[] bombCells = generateBombs();

        for (int i = 0; i < mode.sizeX; i++) {
            for (int j = 0; j < mode.sizeY; j++) {
                if (cells[i][j] != null) {
                    continue;
                }
                this.cells[i][j] = new EmptyCell(i, j, countBombs(bombCells, i, j));
            }
        }
    }

    private static int countBombs(Cell[] bombCells, int x, int y) {
        int bombCount = 0;
        for (Cell cell : bombCells) {
            if (bombCount == 8) {
                break;
            }
            if (x - 1 == cell.x | x == cell.x | x + 1 == cell.x) {
                if (y - 1 == cell.y | y == cell.y | y + 1 == cell.y) {
                    bombCount++;
                }
            }
        }
        return bombCount;
    }
}
