package locations;

import cards.*;
import exception.LocationRejectionException;

public class SOSS extends Location {

    public SOSS() {
        super.setName("SOSS");
        super.setDescription("All diamond cards here gain a power increase of 2.");
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
        if (cardToBePlaced.getSuite() == 'D') {
            cardToBePlaced.increasePower(2);
        }
        super.placeCard(cardToBePlaced, p1);
    }

}
