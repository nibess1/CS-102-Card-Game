package Cards;

import Locations.Location;

public class Ace extends Card implements Picture {
    public static final String ability = "Activate your picture abilities (Except Ace) once more.";

    public Ace(char suite) {
        super(1, suite);
    }

    public static void triggerAbility(boolean p1, Location location) {
        for (Card card : location.getCards(p1)) {
            if (card instanceof Picture) {
                if (card instanceof Queen q) {
                    q.setCanMove(true);
                }
                // if there's already an ace there, don't trigger ace.
                else if (card instanceof Ace) {
                    continue;
                } else {
                    // remove and place the card again to retrigger effects
                    location.removeCard(card, p1);
                    location.placeCard(card, p1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Ace)) {
            return false;
        }

        Ace card = (Ace) obj;

        if (card.getPower() == getPower() && card.getSuite() == getSuite()) {
            return true;
        }
        return false;
    }
}
