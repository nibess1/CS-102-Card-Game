package cards.picturecards;

import java.util.*;

import cards.*;
import locations.Location;

public class Ace extends Card implements Picture {
    public static final String ability = "Activate your picture abilities (Except Ace) once more.";

    public Ace(char suite) {
        super(1, suite);
    }

    public static void triggerAbility(boolean p1, Location location) {
        // Card card : location.getCards(p1)
        ArrayList<Card> cards = location.getCards(p1);
        //loop the livecards, starting from the last item.
        for (int i = cards.size() - 1; i >= 0; i--) {
            Card currentCard = cards.get(i);
            if (currentCard instanceof Picture) {
                if (currentCard instanceof Queen q) {
                    q.setCanMove(true);
                }
                // if there's already an ace there, don't trigger ace.
                else if (currentCard instanceof Ace) {
                    continue;
                } else {
                    // remove and place the card again to retrigger effects
                    location.removeCard(currentCard, p1);
                    location.placeCard(currentCard, p1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Ace\t[power = " + super.getPower() + "\tsuite = " + super.getSuite() + "\tability = " + ability + "]";
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
