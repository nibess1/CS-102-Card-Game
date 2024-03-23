package Locations;

import Cards.*;
import Exception.LocationRejectionException;
// import java.util.*;

public class CIS extends Location {

    public CIS() {
        super.setName("CIS");
        super.setDescription("Only picture cards can be placed here. Their powers will be neutralised.");
    }

    // tried changing it, will this work?
    @Override
    public void placeCard(Card cardToBePlaced, boolean p1) {

        if (!Card.isPictureCard(cardToBePlaced)) {
            throw new LocationRejectionException("This card is not a picture card.");
        }
        super.placeCard(cardToBePlaced, p1);

        if (cardToBePlaced instanceof Queen queenCard) {
            queenCard.setCanMove(false);
        }
    }
}
