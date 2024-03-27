package cards.picturecards;

import cards.*;

public class Joker extends Card implements Picture {
    public static final String ability = "Destroys the location this card is placed at.";

    public Joker() {
        super(1, '\0');
    }

    // console representation of joker card
    @Override
    public String toString() {
        return "Joker\t[power = " + super.getPower() + "\t\tability = " + ability + "]";
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
