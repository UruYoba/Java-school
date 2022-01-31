package HW12.App;

import HW12.Game.Cards.Card;
import HW12.Game.Cards.CardSuit;
import HW12.Game.Deck;
import HW12.Game.Player;

import java.util.Random;

public class MainApp {

    static Random rnd = new Random();
    static final int cardsForPlayer = 6;
    static final int playersCount = rnd.nextInt(5) + 2;

    public static void main(String[] args) {
        // от 2 до 6. Почему-то в 17 джаве нельзя передавать 2 параметра
        Player[] players = startGame();
        for(Player player: players){
            System.out.println(player);
        }
    }

    static Deck deck = new Deck();

    private static Player[] startGame(){
        Player[] players = new Player[playersCount];
        int i = 0;
        for (Player player : players){
            Card[] cards = new Card[cardsForPlayer];
            players[i++] = new Player( cards, i);
        }
        for (int cardNumber = 0; cardNumber < cardsForPlayer; cardNumber++){
            for(Player player : players){
                Card card = deck.getNextCard();
                player.cards[cardNumber] = card;
            }
        }

        return players;
    }
}
