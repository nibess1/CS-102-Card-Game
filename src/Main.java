import Cards.*;
import Exception.LocationRejectionException;
import Locations.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {
    public int turn;

    // meant to prompt user for choices
    public static int[] promptUserInput(Scanner sc) {
        int currentCard = 6;
        int currentLocation = 0;
        while (currentCard > 5 || currentCard < 1){
            try {
                System.out.println("Which card would you like to place this turn? (Pick from 1 - 5)");
                currentCard = sc.nextInt();
                System.out.println("Where would you like to place this card at? (Pick from 1 - 3)");
                currentLocation = sc.nextInt();

                if (currentCard > 5 || currentCard < 1 || currentLocation > 3 || currentLocation < 1){
                    System.out.println("Please pick the correct range of numbers!");
                }

            } catch (LocationRejectionException e) {
                System.out.println(e.getMessage());
            }
        }
        int test[] = { currentCard - 1, currentLocation };
        return test;
    }

    // logic to calculate enemy (PC) on where to play it's cards
    // current logic: Put highest power card on hand at lowest power location
    public static void pcTurn(Location location1, Location location2, Location location3, Player pcHand, Deck deck) {
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
            Player.getHandCards(pcHand);
        }

    }

    // to decide where to place the card at.
    public static void locationDecider(int[] userChoices, Location location1, Location location2, Location location3,
            Player hand, boolean p1, Deck deck) {
        switch (userChoices[1]) {
            case 1:
                location1.placeCard(hand.getCard(userChoices[0]), p1);
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

    public static void nextTurn(Scanner sc, Player pcHand, Player userHand, Deck deck, Location location1,
            Location location2, Location location3) {


        //display location status

        System.out.println("Drawing cards ...");
        // user draws
        for (int i = 0; i < userHand.getNumberOfCardsPerTurn(); i++) {
            userHand.handDraw(deck);
        }
        // pc draws
        for (int i = 0; i < pcHand.getNumberOfCardsPerTurn(); i++) {
            pcHand.handDraw(deck);
        }
        
        // prompt user input and play his card based on his choice based on the number
        // of times he can
        for (int i = 0; i < userHand.getNumberOfCardsPerTurn(); i++) {
            Location.getAllLocation(location1, location2, location3);
            Player.getHandCards(userHand);
            System.out.println("You have (" + (userHand.getNumberOfCardsPerTurn() - i) + ") cards left to play this turn");
            int userChoices[] = promptUserInput(sc);
            locationDecider(userChoices, location1, location2, location3, userHand, true, deck);
        }

        System.out.println("\nPC is making it's move...");
        for (int i = 0; i < pcHand.getNumberOfCardsPerTurn(); i++) {
            pcTurn(location1, location2, location3, pcHand, deck);
        }

        // display changes.
        System.out.println("\nHere are the locations after the first turn");
        Location.getAllLocation(location1, location2, location3);
// System.out.println("Here are your cards after your move");
// Player.getHandCards(userHand);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();

        System.out.println("----------------------------Welcome to Project Gambit!!----------------------------");
        System.out.println("Deck starting with " + deck.getTotalCards() + " cards.");

        deck.shuffle();
        System.out.println("Shuffling deck...\n");

        // Picking 3 distinct locations by shuffling an arraylist of all possible
        // locations and take the first 3.
        List<String> possibleLocations = new ArrayList<String>(
                Arrays.asList("Locations.SCIS", "Locations.SOB", "Locations.SOA", "Locations.Admin", "Locations.CIS",
                        "Locations.SOE", "Locations.SOL", "Locations.SOSS"));

        Collections.shuffle(possibleLocations);

        Location[] location = new Location[3];

        // Instantiating locations
        try {
            for (int i = 0; i < 3; i++) {
                Class<?> className = Class.forName(possibleLocations.get(i));
                Object instance = className.getDeclaredConstructor().newInstance();
                location[i] = (Location) instance;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        Location location1 = location[0];
        Location location2 = location[1];
        Location location3 = location[2];

        System.out.println("Randomizing Locations...\n");

        Player userHand = new Player();
        Player pcHand = new Player();
        for(int i = 0; i < 3; i++){
            userHand.handDraw(deck);
            pcHand.handDraw(deck);
        }

        System.out.println("Handing out cards...\n");

        System.out.println("Here are the locations!");
        Location.getAllLocation(location1, location2, location3);

        System.out.println("Here are your cards!");
        Player.getHandCards(userHand);

        //Game start
        for (int i = 0; i < 5; i++){
            System.out.println("-------------------------- Turn " + (i + 1) + " --------------------------");
            nextTurn(sc, pcHand, userHand, deck, location1, location2, location3);
        }

        int playerWins = 0;
        String winningMessage = "";
        if (location1.playerWins()) {
            winningMessage += "location 1 with " + location1.getLocationPower() + " power and ";
            playerWins++;
        }
        if (location2.playerWins()) {
            winningMessage += "location 2 with " + location2.getLocationPower() + " power and ";
            playerWins++;
        }
        if (location3.playerWins()) {
            winningMessage += "location 3 with " + location3.getLocationPower() + " power and ";
            playerWins++;
        }
        if (playerWins >= 2) {
            System.out.println("You WON at " + winningMessage + ", Congrats!!");
        } else {
            System.out.println("You LOST");
        }

    }
}
