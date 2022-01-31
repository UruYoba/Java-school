package HW12.Game;

import HW12.Game.Cards.Card;

import java.util.Arrays;

public class Player {
    public Card[] cards;
    int number;

    public Player(Card[] cards, int number) {
        this.cards = cards;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Игрок " + number + " " +
                "Карты: " + Arrays.toString(cards);
    }
}
