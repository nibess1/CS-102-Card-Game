package Locations;

import Cards.*;
import Exception.*;
import java.util.*;

public class CIS extends Location {

    private ArrayList<Card> p1LiveCards = new ArrayList<>();
    private ArrayList<Card> p2LiveCards = new ArrayList<>();

    public CIS() {
        super.setName("CIS");
        super.setDescription("Only picture cards can be placed here. Their powers will be neutralised.");
    }

    @Override
    public void placeCard(Card cardToBePlaced, boolean p1) {

        if (!Card.isPictureCard(cardToBePlaced)) {
            throw new LocationRejectionException("This card is not a picture card.");
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

}
