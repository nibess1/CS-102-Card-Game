<<<<<<< HEAD
package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class Admin extends Location {
    public Admin() {
        super.setName("Admin Building");
        super.setDescription("You can only place one card here");
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
=======
package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class Admin extends Location {
    public Admin() {
        super.setName("Admin Building");
        super.setDescription("You can only place one card here");
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
>>>>>>> b0473c83d9d50c428405d2c94d81eb5396175578
