package HW12.Game.Cards;

public enum CardSuit {
    DIAMONDS("БУБ", "БУБИ", '\u2666'),
    HEARTS("ЧЕР", "ЧЕРВИ", '\u2665'),
    CLUBS("КРЕ", "КРЕСТИ", '\u2663'),
    SPADES("ПИК", "ПИКИ", '\u2660');


    public String shortName;
    public String fullName;
    public char unicode;

    CardSuit(String shortName, String fullName, char unicode) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.unicode = unicode;
    }
}
