package cards.picturecards;

import cards.*;
import locations.Location;

public class Jack extends Card implements Picture {
    public static final String ability = "Gives all cards here with the same suite +2 power";

    public Jack(char suite) {
        super(7, suite);
    }

    public static void triggerAbility(boolean p1, Jack j, Location location) {
        for (Card card : location.getCards(p1)) {
            if (card.getSuite() == j.getSuite()) {
                card.increasePower(2);
            }
        }
    }

    // console representation of jack
    @Override
    public String toString() {
        return "Jack\t[ power = " + super.getPower() + "\t| suite = " + super.getSuite() + "\t| ability = " + ability + " ]";
    }

    // check if the cards are equal
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Jack)) {
            return false;
        }

        Jack card = (Jack) obj;

        return card.getPower() == getPower() && card.getSuite() == getSuite();
    }
}
