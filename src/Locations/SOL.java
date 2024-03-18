package Locations;

import Cards.Card;
import src.Exception.LocationRejectionException;

public class SOL extends Location {

    public SOL(){
        super.setName("SOL");
        super.setDescription("No power cards can be placed here.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) throws LocationRejectionException {
        
        if (cardToBePlaced.getPicture() != 'A'){
            throw new LocationRejectionException();
        }

    }
    
}
