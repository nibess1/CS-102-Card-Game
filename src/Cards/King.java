package Cards;

import Locations.Location;

public class King extends Card implements Picture {
    public static final String ability = "Destroys all enemy cards with power lower than this card at this location";

    public King(char suite) {
        super(6, suite);
    }

    public static void abilities(boolean p1, King k, Location location) {
        for (Card card : location.getCards(p1)) {
            if (card.getPower() < k.getPower()) {
                location.destroyCard(card, p1);
            }
        }
    }

    // console representation of King card
    @Override
    public String toString() {
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

    // check if the cards are equal
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof King)) {
            return false;
        }

        King card = (King) obj;

        if (card.getPower() == getPower() && card.getSuite() == getSuite()) {
            return true;
        }
        return false;
    }
}
