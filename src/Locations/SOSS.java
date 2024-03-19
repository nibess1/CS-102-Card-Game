package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class SOSS extends Location {

    public SOSS() {
        super.setName("SOSS");
        super.setDescription("All diamond cards here gain a power increase of 2.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) {

        if (cardToBePlaced.getSuite() == 'D') {
            cardToBePlaced.increasePower(2);
        }
        super.placeCard(cardToBePlaced, p1);
    }

}
