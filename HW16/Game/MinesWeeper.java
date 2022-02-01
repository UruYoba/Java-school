package HW16.Game;

import HW16.Game.Cells.BombCell;
import HW16.Game.Cells.Cell;
import HW16.Game.Cells.EmptyCell;

import java.util.Random;

public class MinesWeeper {

    private static Random rnd = new Random();

    Cell[][] cells;
    public int size;
    int bombCount;
    private boolean isSafe;
    int deminedCount;
    int clickCount;

    public MinesWeeper(boolean isSafe, int size) {
        this.size = size;
        this.isSafe = isSafe;
        this.cells = new Cell[size][size];
        this.bombCount = size * size / 10;
        this.deminedCount = 0;
        generateCells(1, 1);
        print(false);
    }

    public MinesWeeper click(int x, int y) {
        Cell cell = cells[x][y];
        clickCount++;
        if (clickCount == 1) {
            generateCells(x, y);
        }
        if (cell instanceof BombCell) {
            cell.click();
            print(true);
            exitGame("You failed");
        } else {
            EmptyCell.countValue(cells, cell).click();
        }
        print(false);
        return this;
    }

    public MinesWeeper demine(int x, int y) {
        Cell cell = cells[x][y];
        cell.demine();
        return this;
    }

    private void exitGame(String message) {
        System.out.println(message);
        System.exit(0);
    }

    public void print(boolean isGameOver) {
        int line = 1;
        System.out.print("X\u2192");
        for (int i = 1; i <= size; i++) {
            System.out.print(" " + i);
        }
        System.out.println(" Y\u2193");
        for (Cell[] arr : cells) {
            System.out.print("  |");
            String pr = "";
            for (Cell cell : arr) {
                pr += cell.print(isGameOver);
            }
            System.out.println(
                    pr + (pr.lastIndexOf("|") == pr.length() - 1 ? "  " : "| ") + line++
            );
        }
    }

    private void generateCells(int x, int y) {
        int bombIter = 0;
        while (bombIter < this.bombCount) {
            int i = rnd.nextInt(size);
            int j = rnd.nextInt(size);
            if ((i == x & j == y | i == x - 1 | j == y - 1) & isSafe) {
                continue;
            } else {
                cells[i][j] = new BombCell(i, j);
                bombIter++;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] instanceof BombCell) {
                    continue;
                }
                cells[i][j] = new EmptyCell(i, j);
            }
        }
    }
}
