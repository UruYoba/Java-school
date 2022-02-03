package HW16.Minesweeper.core;

public enum Cell {
    BOMB,
    NUMBER,
    EMPTY;

    Cell() {
        this.defaultValue = "■";
        this.flag = "";
        this.isFlagged = false;

    }
    public void explode(){
        this.flag = "a";
    }

    String defaultValue;
    String flag;
    String value;
    String getFlag;
    boolean isFlagged;
}
