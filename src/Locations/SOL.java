package Locations;

import Cards.*;
import Exception.*;

public class SOL extends Location {

    public SOL() {
        super.setName("SOL");
        super.setDescription("No power cards can be placed here.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) throws LocationRejectionException {
        if (Card.isPictureCard(cardToBePlaced)) {
            throw new LocationRejectionException("No power cards can be placed here.");
        }
        super.placeCard(cardToBePlaced, p1);
    }

}
