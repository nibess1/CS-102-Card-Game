package locations;
import cards.*;


public class SCIS extends Location {
    public SCIS(){
        super.setName("SCIS");
        super.setDescription("All spade cards here gain a power increase of 2.");
    }

    @Override
    public void placeCard(Card cardToBePlaced, boolean p1) {

        if(cardToBePlaced.getSuite() == 'S'){
            cardToBePlaced.increasePower(2);
        }  
        super.placeCard(cardToBePlaced, p1);
    }
}
