package HW16.Minesweeper.core.Cells;

public class Cell {
    String defaultValue;
    String currentValue;
    String flag;
    String value;
    boolean isFlagged;
    String getFlag;
    boolean isOpened;
    public int x;
    public int y;


    Cell(int x, int y, String value, String getFlag) {
        this.defaultValue = "■";
        this.currentValue = defaultValue;
        this.flag = "?";
        this.isFlagged = false;
        this.value = value;
        this.getFlag = getFlag;
        this.isOpened = false;
    }

    public Cell click() {
        if (!this.isOpened & !this.isFlagged) {
            this.currentValue = this.value;
        }
        return this;
    }

    public Cell flag(){
        if(this.isFlagged & !this.isOpened){
            this.isFlagged = false;
            this.currentValue = this.flag;
        } else if (this.isFlagged){
            this.isFlagged = true;
            this.currentValue = this.defaultValue;
        }
        return this;
    }

    public String toPrint(boolean isGameOver){
        if(isGameOver){
            if(this.isFlagged){
                return getFlag;
            } else if(this instanceof BombCell){
                this.click();
            }
        }
        return toString();
    }

    @Override
    public String toString() {
        return currentValue.equals(" ") ? "  " : currentValue + "|";
    }
}
