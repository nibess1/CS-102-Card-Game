import cards.*;
import locations.*;

import java.util.*;

public class Pc extends Player{
    public static final int numLocs = 3;

    public Pc(boolean p1){
        super(p1);
    }

    @Override
    public void turnInitialiser(Location location1, Location location2, Location location3, Scanner sc) {
        if (toSkipTurn(location1, location2, location3, getIsPlayer1())) {
            return;
        }
        System.out.println("\nPC is making it's move...");
        for (int i = 0; i < getNumberOfCardsPerTurn(); i++) {
            move(location1, location2, location3);
        }
    }

    public void move(Location location1, Location location2, Location location3) {
        double[][] possiblePlays = calculatePlayValue(location1, location2, location3);
        int[] bestPlay = findBestPlay(possiblePlays);

        for(int i = 0; i < possiblePlays.length; i++){
            System.out.println(Arrays.toString(possiblePlays[i]));
        }

        int pcChoices[] = { bestPlay[1], bestPlay[0] + 1};

        try {
            Turn.locationDecider(pcChoices, location1, location2, location3, this, false);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PC DOES NOT HAVE THIS CARD");
            getHandCards(this);
        }

    }

    public static int[] findBestPlay(double[][] array) {
    int[] location = new int[2]; 
    double largestValue = Double.NEGATIVE_INFINITY; 

    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[i].length; j++) {
            if (array[i][j] > largestValue) {
                largestValue = array[i][j];
                location[0] = i; 
                location[1] = j; 
            }
        }
    }
    
    return location;
}

    public double cardValueBonus(Location loc, Card card){
        ArrayList<Card> locationCards = loc.getCards(getIsPlayer1());
        ArrayList<Card> enemyCards = loc.getCards(!getIsPlayer1());
    
        if(card instanceof Jack){
            int numMatchingSuite = 0;
            for (Card locationCard : locationCards){
                if(locationCard.getSuite() == card.getSuite()){
                    numMatchingSuite++;
                }
            }

            return numMatchingSuite * 0.2 + card.getPower() * 0.8;
        }

        if(card instanceof King){
            int numBelowKing = 0;
            for (Card locationCard : enemyCards){
                if(locationCard.getPower() > card.getPower()){
                    numBelowKing++;
                }
            }

            return numBelowKing * 0.3 + card.getPower() * 0.9;
        }

        if(card instanceof Ace){
            int numPictureCards = 0;
            for (Card locationCard : locationCards){
                if(locationCard instanceof Picture && !(locationCard instanceof Queen)){
                    numPictureCards++;
                }
            }

            return numPictureCards * 0.3 + card.getPower()* 0.5;
        }


        return card.getPower() * 1.0;
    }

    public double calculatePlayStrength(Location primaryLocation, Location otherLocation1, Location otherLocation2, Card c){

        double cardPower = cardValueBonus(primaryLocation, c);
        
        if(!primaryLocation.isAvailable(getIsPlayer1())){
            return 0.0;
        }

        if(primaryLocation instanceof Admin){
            return cardPower * 1.0;
        }

        if(primaryLocation instanceof SOL){
            if(Card.isPictureCard(c)){
                return 0.0;
            }
            return cardPower * 1.0;
        }

        if(primaryLocation instanceof CIS){
            if(!Card.isPictureCard(c)){
                return 0.0;
            }
            return cardPower * 1.0;
        }
        
        if(primaryLocation instanceof SOE){
            //if other locations are available, return 0.0, else return inverse
            if(otherLocation1.isAvailable(getIsPlayer1()) || otherLocation2.isAvailable(getIsPlayer1())){
                return 0.0;
            }
            return 1.0 / cardPower;
        }

        if(primaryLocation instanceof SOA){
            if(c.getSuite() == 'H'){
                return cardPower * 1.0;
            }
            return cardPower * 0.8;
        }

        if(primaryLocation instanceof SCIS){
            if(c.getSuite() == 'S'){
                return cardPower * 1.0;
            }
            return cardPower * 0.8;
        }

        if(primaryLocation instanceof SOB){
            if(c.getSuite() == 'C'){
                return cardPower * 1.0;
            }
            return cardPower * 0.8;
        }

        if(primaryLocation instanceof SOSS){
            if(c.getSuite() == 'D'){
                return cardPower * 1.0;
            }
            return cardPower * 0.8;
        }

        return cardPower;
    }

    public double[][] calculatePlayValue(Location location1, Location location2 , Location location3){
        
        ArrayList<Card> playerCards = getHand();
        double[][] playValues = new double[numLocs][playerCards.size()];


        for(int i = 0; i < playerCards.size(); i++){
            playValues[0][i] = calculatePlayStrength(location1, location2, location3, playerCards.get(i));
        }

        for(int i = 0; i < playerCards.size(); i++){
            playValues[1][i] = calculatePlayStrength(location1, location2, location3, playerCards.get(i));
        }

        for(int i = 0; i < playerCards.size(); i++){
            playValues[2][i] = calculatePlayStrength(location1, location2, location3, playerCards.get(i));
        }

        return playValues;
    }

}
