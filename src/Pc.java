import cards.*;
import locations.*;

import java.util.*;

public class Pc {
    public static final int numLocs = 3;
    public static void pcTurnInitialiser(Location location1, Location location2, Location location3, Player player2,
            Deck deck) {
        if (player2.toSkipTurn(location1, location2, location3, false)) {
            return;
        }
        System.out.println("\nPC is making it's move...");
        for (int i = 0; i < player2.getNumberOfCardsPerTurn(); i++) {
            pcTurn(location1, location2, location3, player2, deck);
        }
    }

    public static void pcTurn(Location location1, Location location2, Location location3, Player player2, Deck deck) {
        double[][] possiblePlays = calculatePlayValue(location1, location2, location3, player2);
        int[] bestPlay = findBestPlay(possiblePlays);

        int pcChoices[] = { bestPlay[1], bestPlay[0] + 1};

        try {
            Turn.locationDecider(pcChoices, location1, location2, location3, player2, false);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PC DOES NOT HAVE THIS CARD");
            Player.getHandCards(player2);
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

    public static double cardValueBonus(Location loc, Card c){
        ArrayList<Card> locationCards = loc.getCards(false);
    
        if(c instanceof Jack){
            int numMatchingSuite = 0;
            for (Card locC : locationCards){
                if(locC.getSuite() == c.getSuite()){
                    numMatchingSuite++;
                }
            }

            return numMatchingSuite * 0.2 + c.getPower() * 0.8;
        }

        if(c instanceof King){
            int numBelowKing = 0;
            for (Card locC : locationCards){
                if(locC.getPower() < c.getPower()){
                    numBelowKing++;
                }
            }

            return numBelowKing * 0.2 + c.getPower() * 0.9;
        }

        if(c instanceof Ace){
            int numPictureCards = 0;
            for (Card locC : locationCards){
                if(locC instanceof Picture && !(locC instanceof Queen)){
                    numPictureCards++;
                }
            }

            return numPictureCards * 0.3 + c.getPower()* 0.5;
        }


        return c.getPower() * 1.0;
    }

    public static double calculatePlayStrength(Location primary, Location other1, Location other2, Card c){

        double cardPower = cardValueBonus(primary, c);
        
        if(!primary.isAvailable(false)){
            return 0.0;
        }

        if(primary instanceof Admin){
            return cardPower * 1.0;
        }

        if(primary instanceof SOL){
            if(Card.isPictureCard(c)){
                return 0.0;
            }
            return cardPower * 1.0;
        }

        if(primary instanceof CIS){
            if(!Card.isPictureCard(c)){
                return 0.0;
            }
            return cardPower * 1.0;
        }
        
        if(primary instanceof SOE){
            //if other locations are available, return 0.0, else return inverse
            if(other1.isAvailable(false) || other2.isAvailable(false)){
                return 0.0;
            }
            return 1.0 / cardPower;
        }

        if(primary instanceof SOA){
            if(c.getSuite() == 'H'){
                return cardPower * 1.0;
            }
            return cardPower * 0.8;
        }

        if(primary instanceof SCIS){
            if(c.getSuite() == 'S'){
                return cardPower * 1.0;
            }
            return cardPower * 0.8;
        }

        if(primary instanceof SOB){
            if(c.getSuite() == 'C'){
                return cardPower * 1.0;
            }
            return cardPower * 0.8;
        }

        if(primary instanceof SOSS){
            if(c.getSuite() == 'D'){
                return cardPower * 1.0;
            }
            return cardPower * 0.8;
        }

        return cardPower;
    }

    public static double[][] calculatePlayValue(Location loc1, Location loc2 , Location loc3, Player p){
        
        ArrayList<Card> playerCards = p.getHand();        
        double[][] playValues = new double[numLocs][playerCards.size()];


        for(int i = 0; i < playerCards.size(); i++){
            playValues[0][i] = calculatePlayStrength(loc1, loc2, loc3, playerCards.get(i));
        }

        for(int i = 0; i < playerCards.size(); i++){
            playValues[1][i] = calculatePlayStrength(loc2, loc1, loc3, playerCards.get(i));
        }

        for(int i = 0; i < playerCards.size(); i++){
            playValues[2][i] = calculatePlayStrength(loc3, loc2, loc1, playerCards.get(i));
        }

        return playValues;
    }

}
