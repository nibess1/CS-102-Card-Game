package Cards;

public class Card {
    private int power; // the number on the card
    private char picture; // the picture of the card "J / Q / K / A / Z" (A is normal, Z is joker)
    private char suite; // the symbol / group the card belongs to "H / C / S / D"
    private boolean destroyed; // check if the card has been destroyed

    public Card(int power, char picture, char suite) {
        this.power = power;
        this.picture = picture;
        this.suite = suite;
        this.destroyed = false;
    }

    // increase power of card.
    public void increasePower(int numberToIncrease) {
        this.power += numberToIncrease;
    }

    // get power of card
    public int getPower() {
        return this.power;
    }

    public char getSuite() {
        return this.suite;
    }

    public char getPicture() {
        return this.picture;
    }

    @Override
    public String toString() {
        return "[power=" + power + ", picture=" + picture + ", suite=" + suite + "]";
    }

    public void destroy() {
        this.destroyed = true;
    }

    public boolean canMove() {
        // nth
        return false;
    }

    public boolean equals(Object obj) {

        if (!(obj instanceof Card)) {
            return false;
        }

        Card card = (Card) obj;

        if (card.getPower() == this.power && card.getSuite() == this.suite && card.getPicture() == this.picture) {
            return true;
        }
        return false;
    }

}