package HW12.Game.Cards;

public class Card {
    public CardSuit suit;
    public CardRank rank;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format(
                "%s%s",
                rank.rank.charAt(0),
                suit.unicode
        );
    }
}
