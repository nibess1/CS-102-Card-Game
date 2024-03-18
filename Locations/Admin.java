package Locations;

import Cards.*;
import java.util.*;
import Exception.*;

public class Admin extends Location {
    public Admin() {
        super("Admin Building", "You can only place one card here");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) throws LocationRejectionException {
        int validPlace1 = getP1NumLiveCards();
        int validPlace2 = getP2NumLiveCards();

        if (validPlace1 > 2) {
            throw new LocationRejectionException();
        } else if (validPlace2 > 2) {
            throw new LocationRejectionException();
        }else{
            super.placeCard(cardToBePlaced, p1);
        }
    }
}
