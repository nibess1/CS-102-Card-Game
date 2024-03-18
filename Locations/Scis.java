package Locations;

import Cards.Card;

public class Scis extends Location {
    public Scis(){
        super.setName("SCIS");
        super.setDescription("All spade cards here gain a power increase of 2.");
    }

    public void placeCard(Card cardToBePlaced, boolean p1) {

        if(cardToBePlaced.getSuite() == 'S'){
            cardToBePlaced.increasePower(2);
        }  
        super.placeCard(cardToBePlaced, p1);
    }




}
