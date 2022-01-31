package HW12.Game;

import HW12.Game.Cards.Card;
import HW12.Game.Cards.CardRank;
import HW12.Game.Cards.CardSuit;

import java.util.Random;

public class Deck {
    public final Card[] deck = generateDeck();
    private int currentCard;

    public Deck() {
        mixDeck();
        currentCard = 0;
    }

    private Card[] generateDeck(){
        Card[] deck = new Card[52];
        int i = 0;
        for(CardSuit suit : CardSuit.values()) {
            for(CardRank rank : CardRank.values()){
                deck[i++] = new Card(rank, suit);
            }
        }
        return deck;
    }

    private void mixDeck(){
        int length = deck.length;
        Random rnd = new Random();
        for (int i = 0; i < length; i++){
            if(rnd.nextBoolean()) {
                int j = rnd.nextInt(length);
                Card temp = deck[j];
                deck[j] = deck[i];
                deck[i] = temp;
            }
        }
    }

    public Card getNextCard(){
        if(currentCard > 51){
            System.out.println("Колода пуста");
            return null;
        }
        return deck[currentCard++];
    }
}
