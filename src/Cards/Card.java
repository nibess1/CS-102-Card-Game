package Cards;

public class Card {
    private int power; // the number on the card
    private final char suite; // the symbol / group the card belongs to "H / C / S / D"\
    private boolean destroyed = false; // check if the card has been destroyed

    public Card(int power, char suite) {
        this.power = power;
        this.suite = suite;
        this.destroyed = false;
    }

    public static boolean isPictureCard(Card card) {
        return (card instanceof Picture);
    }

    // get power of card
    public int getPower() {
        return this.power;
    }

    public char getSuite() {
        return this.suite;
    }

    // increase power of card.
    public void increasePower(int numberToIncrease) {
        this.power += numberToIncrease;
    }

    public void destroy() {
        this.destroyed = true;
    }

    @Override
    public String toString() {
        return "[power = " + power + ", suite = " + suite + " ]";
    }

    public boolean equals(Object obj) {

        if (!(obj instanceof Card)) {
            return false;
        }

        Card card = (Card) obj;

        if (card.getPower() == this.power && card.getSuite() == this.suite && !isPictureCard(card)) {
            return true;
        }
        return false;
    }

}