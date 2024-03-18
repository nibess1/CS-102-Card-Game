<<<<<<< HEAD
package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class SOB extends Location {

    public SOB(){
        super.setName("SOB");
        super.setDescription("All club cards here gain a power increase of 2.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) {

        if(cardToBePlaced.getSuite() == 'C'){
            cardToBePlaced.increasePower(2);
        }  
        super.placeCard(cardToBePlaced, p1);
    
    }
    
}
=======
package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class SOB extends Location {

    public SOB(){
        super.setName("SOB");
        super.setDescription("All club cards here gain a power increase of 2.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) {

        if(cardToBePlaced.getSuite() == 'C'){
            cardToBePlaced.increasePower(2);
        }  
        super.placeCard(cardToBePlaced, p1);
    
    }
    
}
>>>>>>> b0473c83d9d50c428405d2c94d81eb5396175578
