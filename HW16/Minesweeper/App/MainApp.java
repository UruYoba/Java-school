package HW16.Minesweeper.App;

import HW16.Minesweeper.core.Game;
import HW16.Minesweeper.core.Mode;

public class MainApp {
    public static void main(String[] args) {
        Game game = new Game(Mode.EASY,  false);
        game.print(false);
    }
}
