import Cards.*;
import Locations.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.*;

public class Main {
    // meant to print the names, and power of each location
    public static void getAllLocation(Location location1, Location location2, Location location3) {
        System.out.println("#1:" + location1.toString());
        System.out.println("#2:" + location2.toString());
        System.out.println("#3:" + location3.toString());
        System.out.println("");
    }

    // meant to display all cards of a hand
    public static void getHandCards(Hand hand) {
        for (int i = 0; i < hand.getHand().size(); i++) {
            System.out.println("Card #" + (i + 1) + " " + hand.getHand().get(i));
        }
        System.out.println("");
    }

    // meant to prompt user for choices
    public static int[] promptUserInput(Scanner sc) {
        System.out.println("Which card would you like to place this turn?");
        int currentCard = sc.nextInt();
        System.out.println("Where would you like to place this card at?");
        int currentLocation = sc.nextInt();
        int test[] = { currentCard - 1, currentLocation };
        return test;
    }

    // logic to calculate enemy (PC) on where to play it's cards
    // current logic: Put highest power card on hand at lowest power location
    public static void pcTurn(Location location1, Location location2, Location location3, Hand pcHand, Deck deck) {
        int indexOfHighestPowerCard = 0;
        for (int i = 1; i < pcHand.getHand().size(); i++) {
            if (pcHand.getHand().get(i).getPower() > pcHand.getHand().get(indexOfHighestPowerCard).getPower()) {
                indexOfHighestPowerCard = i;
            }
        }
        // assume lowest power location is 1.
        int lowestPowerLocation = 1;
        // check if location 2 is lesser
        if (location2.isLessThan(location1)) {
            lowestPowerLocation = 2;
        }
        // if not, check if location 3 is lesser than both 1 and 2.
        else if (location3.isLessThan(location1) && location3.isLessThan(location2)) {
            lowestPowerLocation = 3;
        }

        // continue PC (AI)'s turn
        int pcChoices[] = { indexOfHighestPowerCard, lowestPowerLocation };

        try {
            locationDecider(pcChoices, location1, location2, location3, pcHand, false, deck);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PC DOES NOT HAVE THIS CARD");
            getHandCards(pcHand);
        }

    }

    // to decide where to place the card at.
    public static void locationDecider(int[] userChoices, Location location1, Location location2, Location location3,
            Hand hand, boolean p1, Deck deck) {
        switch (userChoices[1]) {
            case 1:
                Card cardToPlay = hand.getCard(userChoices[0]);
                if (cardToPlay instanceof Jack) {
                    location1.placeJack((Jack) cardToPlay, p1, deck);
                }
                 else {
                    location1.placeCard(cardToPlay, p1);
                }
                break;
            case 2:
                location2.placeCard(hand.getCard(userChoices[0]), p1);
                break;
            case 3:
                location3.placeCard(hand.getCard(userChoices[0]), p1);
                break;
            default:
                System.out.println("Error has occured attempting to place a card");
                break;
        }

    }

    public static void nextTurn(Scanner sc, Hand pcHand, Hand userHand, Deck deck, Location location1,
            Location location2, Location location3) {

        // user draws
        for (int i = 0; i < userHand.getNumberOfCards(); i++) {
            userHand.handDraw(deck);
        }
        // pc draws
        for (int i = 0; i < pcHand.getNumberOfCards(); i++) {
            pcHand.handDraw(deck);
        }

        System.out.println("Here are your cards after drawing a card for the next turn");
        // display user cards
        getHandCards(userHand);

        // prompt user input and play his card based on his choice based on the number
        // of times he can
        for (int i = 0; i < userHand.getNumberOfCards(); i++) {
            int userChoices[] = promptUserInput(sc);
            locationDecider(userChoices, location1, location2, location3, userHand, true, deck);
            System.out.println("Here are your cards after your move");
            getHandCards(userHand);


        }

        System.out.println("\nPC is making it's move...");
        for (int i = 0; i < pcHand.getNumberOfCards(); i++) {
            pcTurn(location1, location2, location3, pcHand, deck);
        }

        // display changes.
        System.out.println("\nHere are the locations after the first turn");
        getAllLocation(location1, location2, location3);
        System.out.println("Here are your cards after your move");
        getHandCards(userHand);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();

        System.out.println("----------------------------Welcome to Project Gambit!!----------------------------");
        System.out.println("Deck starting with " + deck.getTotalCards() + " cards.");

        deck.shuffle();
        System.out.println("Shuffling deck...\n");

        List<String> possibleLocations = new ArrayList<String>(Arrays.asList("SCIS", "SOB", "SOA", "Admin", "CIS", "SOE", "SOL", "SOSS"));
        Collections.shuffle(possibleLocations); 

        Location[] location = new Location[3];

        try{
            for(int i = 0; i < 3; i++){
                Class<?> className = Class.forName(possibleLocations.get(i));
                Object instance = className.getDeclaredConstructor().newInstance();
                location[i] = (Location) instance;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }

        Location location1 = location[0];
        Location location2 = location[1];
        Location location3 = location[2];
        
        

        System.out.println("Randomizing Locations...\n");

        Hand userHand = new Hand();
        Hand pcHand = new Hand();
        pcHand.handDraw(deck);
        userHand.handDraw(deck);
        pcHand.handDraw(deck);
        userHand.handDraw(deck);
        pcHand.handDraw(deck);
        userHand.handDraw(deck);
        System.out.println("Handing out cards...\n");

        System.out.println("Here are the locations!");
        getAllLocation(location1, location2, location3);

        System.out.println("Here are your cards!");
        getHandCards(userHand);

        nextTurn(sc, pcHand, userHand, deck, location1, location2, location3);
        nextTurn(sc, pcHand, userHand, deck, location1, location2, location3);
        nextTurn(sc, pcHand, userHand, deck, location1, location2, location3);
        nextTurn(sc, pcHand, userHand, deck, location1, location2, location3);
        nextTurn(sc, pcHand, userHand, deck, location1, location2, location3);

        int playerWins = 0;
        String winningMessage = "";
        if (location1.playerWins()) {
            winningMessage += "location 1 with " + location1.getLocationPower() + " power and";
            playerWins++;
        }
        if (location2.playerWins()) {
            winningMessage += "location 2 with " + location2.getLocationPower() + " power and";
            playerWins++;
        }
        if (location3.playerWins()) {
            winningMessage += "location 3 with " + location3.getLocationPower() + " power and";
            playerWins++;
        }
        if (playerWins >= 2) {
            System.out.println("You WON at " + winningMessage + ", Congrats!!");
        } else {
            System.out.println("You LOST");
        }

    }
}
