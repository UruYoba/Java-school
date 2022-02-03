package HW16.Minesweeper.core.Cells;

public class EmptyCell extends Cell {
    public EmptyCell(int x, int y, int value) {
        super(x, y, value == 0 ? " " : Integer.toString(value), "X");
    }
}
