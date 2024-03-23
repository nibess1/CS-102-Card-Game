package Locations;

import Cards.*;
import Exception.*;
// import java.util.*;

public class CIS extends Location {

    public CIS() {
        super.setName("CIS");
        super.setDescription("Only picture cards can be placed here. Their powers will be neutralised.");
    }

    // tried changing it, will this work?
    @Override
    public void placeCard(Card cardToBePlaced, boolean p1) {

        // if location is destroyed, throw error.
        if (super.checkDestroyed()) {
            throw new LocationRejectionException("Location is destroyed!");
        }

        if (!Card.isPictureCard(cardToBePlaced)) {
            throw new LocationRejectionException("This card is not a picture card.");
        }

        // check if location is full
        if (!isAvailable(p1)) {
            int numCards = p1 ? this.p1LiveCards.size() : this.p2LiveCards.size();
            // plural
            if (numCards > 1) {
                throw new LocationRejectionException(
                        "Location is full! There are already " + numCards + " cards at " + this.name);
            } else {
                throw new LocationRejectionException("Location is full! There is already a card at " + this.name);
            }
        }

        if (cardToBePlaced instanceof Queen queenCard) {
            queenCard.setCanMove(false);
        }

        if (p1) {
            this.p1LiveCards.add(cardToBePlaced);
        } else {
            this.p2LiveCards.add(cardToBePlaced);
        }

        calculatePower(p1);

    }

    // @Override
    // public void placeCard(Card cardToBePlaced, boolean p1) {

    // if (!Card.isPictureCard(cardToBePlaced)) {
    // throw new LocationRejectionException("This card is not a picture card.");
    // }

    // Card cardNewPower = new Card ()

    // if (cardToBePlaced instanceof Queen queenCard) {
    // queenCard.setCanMove(false);
    // }

    // if (p1) {
    // this.p1LiveCards.add(cardToBePlaced);
    // } else {
    // this.p2LiveCards.add(cardToBePlaced);
    // }

    // calculatePower(p1);
    // }

}
