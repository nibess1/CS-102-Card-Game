package cards;

public class Card {
    private int power; // the number on the card
    private final char suite; // the symbol / group the card belongs to "H / C / S / D"\

    // constructor
    public Card(int power, char suite) {
        this.power = power;
        this.suite = suite;
    }

    // check if the card is a picture (non numbered card)
    public static boolean isPictureCard(Card card) {
        return (card instanceof Picture);
    }

    // get power of card
    public int getPower() {
        return this.power;
    }

    // check suit of card
    public char getSuite() {
        return this.suite;
    }

    // set power of card
    public void setPower(int powerToSet) {
        this.power = powerToSet;
    }

    // increase power of card.
    public void increasePower(int numberToIncrease) {
        this.power += numberToIncrease;
    }

    // console representation of the card
    @Override
    public String toString() {
        return "[power = " + power + ", suite = " + suite + " ]";
    }

    // check if the cards are the same card.
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