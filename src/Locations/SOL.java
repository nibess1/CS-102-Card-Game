<<<<<<< HEAD
package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class SOL extends Location {

    public SOL(){
        super.setName("SOL");
        super.setDescription("No power cards can be placed here.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) throws LocationRejectionException {
        
        if (cardToBePlaced.getPicture() == 'A' && cardToBePlaced.getPower() != 1){
            throw new LocationRejectionException();
        }

    }
    
}
=======
package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class SOL extends Location {

    public SOL(){
        super.setName("SOL");
        super.setDescription("No power cards can be placed here.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) throws LocationRejectionException {
        
        if (cardToBePlaced.getPicture() == 'A' && cardToBePlaced.getPower() != 1){
            throw new LocationRejectionException();
        }

    }
    
}
>>>>>>> b0473c83d9d50c428405d2c94d81eb5396175578
