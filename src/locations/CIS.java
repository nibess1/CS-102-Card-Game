package locations;

// import java.util.*;
import cards.*;
import exception.LocationRejectionException;

public class CIS extends Location {

    public CIS() {
        super.setName("CIS");
        super.setDescription("Only picture cards can be placed here. Their powers will be neutralised.");
    }

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
