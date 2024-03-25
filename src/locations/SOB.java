package locations;
import cards.*;

public class SOB extends Location {

    public SOB(){
        super.setName("SOB");
        super.setDescription("All club cards here gain a power increase of 2.");
    }

    @Override
    public void placeCard(Card cardToBePlaced, boolean p1) {

        if(cardToBePlaced.getSuite() == 'C'){
            cardToBePlaced.increasePower(2);
        }  
        super.placeCard(cardToBePlaced, p1);


    }
    
}
