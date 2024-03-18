package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class SOA extends Location {

    public SOA(){
        super.setName("SOA");
        super.setDescription("All heart cards here gain a power increase of 2.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) {

        if(cardToBePlaced.getSuite() == 'H'){
            cardToBePlaced.increasePower(2);
        }  
        super.placeCard(cardToBePlaced, p1);
    }
    
}
