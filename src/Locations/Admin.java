package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class Admin extends Location {
    public Admin() {
        super.setName("Admin Building");
        super.setDescription("You can only place one card here");
    }

    @Override
    public void placeCard(Card cardToBePlaced, boolean p1) throws LocationRejectionException {
        int validPlace1 = getP1NumLiveCards();
        int validPlace2 = getP2NumLiveCards();

        if (validPlace1 == 0){
            super.placeCard(cardToBePlaced, p1);
        } else{
            throw new LocationRejectionException("You can only place one card here.");
        }

        if (validPlace2 == 0){
            super.placeCard(cardToBePlaced, !(p1));
        } else{
            throw new LocationRejectionException("You can only place one card here.");
        }
    }
}
