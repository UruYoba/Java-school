package HW16.Minesweeper.core;

import HW16.Minesweeper.core.Cells.BombCell;
import HW16.Minesweeper.core.Cells.Cell;
import HW16.Minesweeper.core.Cells.EmptyCell;

import java.util.Random;

public class Game {

    private static final Random rnd = new Random();
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    Cell[][] cells;
    Mode mode;
    boolean isSafe;

    public static void main(String[] args) {
        System.out.println(1 + "|");
        System.out.println("\u2691|");
        System.out.println(1);
    }

    public Game(Mode mode, boolean isSafe) {
        this.mode = mode;
        this.isSafe = isSafe;
        this.cells = new Cell[this.mode.sizeX][this.mode.sizeY];
        generateCells();
    }

    public void print(boolean isGameOver){
        for(int i = 0; i < cells.length; i++){
            System.out.print(i + cells.length > 10 ? " |": "  |");
            String line = "";
            for(int j = 0; j < cells[i].length; j++){
                line += cells[i][j].toPrint(isGameOver);
            }
            line += " " + i;
            System.out.println();
        }
    }

    private void generateCells() {
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
