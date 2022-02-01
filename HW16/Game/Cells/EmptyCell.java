package HW16.Game.Cells;

import java.util.Arrays;

public class EmptyCell extends Cell {
    public EmptyCell(int x, int y) {
        super(x, y, "");
    }

    public static Cell countValue(Cell[][] cells, Cell currentCell) {
        if(!currentCell.value.equals("")){
            return currentCell;
        }
        int bombCount = 0;
        Cell[] cellsToOpen = new Cell[8];
        int iter = 0;
        int x = currentCell.x;
        int y = currentCell.y;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i == x & j == y)) {
                    continue;
                }
                if(i >= 0 & i < cells[0].length & j>= 0 & j < cells.length ){
                    Cell cell = cells[i][j];
                    if(cell instanceof BombCell){
                        bombCount++;
                        continue;
                    }
                    if (cell.value.equals("")) {
                        cellsToOpen[iter++] = cell;
                    }
                }
            }
        }
        currentCell.value = bombCount == 0 ? "  " : "" + bombCount;
        cellsToOpen = Arrays.copyOf(cellsToOpen, iter);
        for (Cell cellToOpen: cellsToOpen){
            countValue(cells, cellToOpen).click();
        }
//        if(currentCell.value.equals("")){
//            currentCell.click();
//        }
        return currentCell;
    }
}
