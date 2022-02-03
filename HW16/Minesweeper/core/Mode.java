package HW16.Minesweeper.core;

public enum Mode {
    EASY(8, 8, 10),
    NORMAL(16, 16, 40),
    HARD(30, 16, 99);

    int sizeX;
    int sizeY;
    int bombCount;

    Mode(int sizeX, int sizeY, int bombCount) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.bombCount = bombCount;
    }
}
