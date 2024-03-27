package cards.picturecards;

import java.util.ArrayList;

import cards.*;
import locations.Location;

public class King extends Card implements Picture {
    public static final String ability = "Destroys all enemy cards with power higher than this card at this location";

    public King(char suite) {
        super(8, suite);
    }

    public static void triggerAbility(boolean p1, King k, Location location) {
    
        ArrayList<Card> cards = location.getCards(p1);
        for (int i = cards.size() - 1; i >= 0; i--) {
            Card currentCard = cards.get(i);
            if (currentCard.getPower() > k.getPower()) {
                location.destroyCard(currentCard, p1);
            }
        }
    }

    // console representation of King card
    @Override
    public String toString() {
        return "King\t[power = " + super.getPower() + "\tsuite = " + super.getSuite() + "\tability = " + ability + "]";
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
