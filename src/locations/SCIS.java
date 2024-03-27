package locations;

import cards.*;
import exception.LocationRejectionException;

public class SCIS extends Location {
    public SCIS() {
        super.setName("SCIS");
        super.setDescription("All spade cards here gain a power increase of 2.");
    }

    @Override
    public void placeCard(Card cardToBePlaced, boolean p1) {
        // check if location is available
        try {
            isAvailable(p1);
        } catch (LocationRejectionException e) {
            throw e;
        }

        //increase power
        if (cardToBePlaced.getSuite() == 'S') {
            cardToBePlaced.increasePower(2);
        }
        super.placeCard(cardToBePlaced, p1);
    }
}
