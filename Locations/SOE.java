package Locations;

import Cards.Card;

public class SOE extends Location {

    public SOE(){
        super("SOE", "The card with the lowest power wins.");
    }

    @Override
    public boolean playerWins() {
        if (p1Power < p2Power) {
            return true;
        }
        return false;
    }

    
}
