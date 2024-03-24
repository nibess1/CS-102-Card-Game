package cards;

public class Joker extends Card implements Picture {
    public static final String ability = "Destroys the location this card is placed at.";

    public Joker() {
        super(6, '\0');
    }

    // console representation of jack card
    @Override
    public String toString() {
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

    // check if cards are equal
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Joker)) {
            return false;
        }

        Joker card = (Joker) obj;

        if (card.getPower() == getPower() && card.getSuite() == getSuite()) {
            return true;
        }
        return false;
    }
}
