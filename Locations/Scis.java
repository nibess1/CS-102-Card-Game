package Locations;

import Cards.Card;

public class Scis extends Location {
    public Scis(){
        super();
    }

    public void placeCard(Card cardToBePlaced, boolean p1) {

        if(cardToBePlaced.getSuite() == 'S'){
            cardToBePlaced.increasePower(2);
        }  
        super.placeCard(cardToBePlaced, p1);
    }




}
