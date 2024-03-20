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
        while (currentCard > 5 || currentCard < 1) {

            System.out.println("Which card would you like to place this turn? (Pick from 1 - 5)");
            currentCard = sc.nextInt();
            System.out.println("Where would you like to place this card at? (Pick from 1 - 3)");
            currentLocation = sc.nextInt();

            if (currentCard > 5 || currentCard < 1 || currentLocation > 3 || currentLocation < 1) {
                System.out.println("Please pick the correct range of numbers!");
                System.out.println("");
            }

        }
        int test[] = { currentCard - 1, currentLocation };
        return test;
    }

    // logic to calculate enemy (PC) on where to play it's cards
    // current logic: Put highest power card on hand at lowest power location
    public static void pcTurn(Location location1, Location location2, Location location3, Player player2, Deck deck) {
        int indexOfHighestPowerCard = 0;
        for (int i = 1; i < player2.getHand().size(); i++) {
            if (player2.getHand().get(i).getPower() > player2.getHand().get(indexOfHighestPowerCard).getPower()) {
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
            locationDecider(pcChoices, location1, location2, location3, player2, false, deck);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PC DOES NOT HAVE THIS CARD");
            Player.getHandCards(player2);
        }

    }

    // to decide where to place the card at.
    public static void locationDecider(int[] userChoices, Location location1, Location location2, Location location3,
            Player hand, boolean p1, Deck deck) {
                
            try {
                switch (userChoices[1]) {
                    case 1:
                        location1.placeCard(hand.getCard(userChoices[0]), p1);
                        hand.removeCard(userChoices[0]);
                        break;
                    case 2:
                        location2.placeCard(hand.getCard(userChoices[0]), p1);
                        hand.removeCard(userChoices[0]);
                        break;
                    case 3:
                        location3.placeCard(hand.getCard(userChoices[0]), p1);
                        hand.removeCard(userChoices[0]);
                        break;
                }
            } catch (LocationRejectionException e) {
                e.getErrorMessage();
                System.out.println();
            }
    }

    public static void nextTurn(Scanner sc, Player player1, Player player2, Deck deck, Location location1,
            Location location2, Location location3) {


        //display location status

        System.out.println("Drawing cards ...");
        // user draws
        for (int i = 0; i < player1.getNumberOfCardsPerTurn(); i++) {
            player1.handDraw(deck);
        }
        // pc draws
        for (int i = 0; i < player2.getNumberOfCardsPerTurn(); i++) {
            player2.handDraw(deck);
        }
        
        // prompt user input and play his card based on his choice based on the number
        // of times he can
        int userHandBefore = Hand.getCurrentNumberOfCards(userHand);
        boolean invalidMove = true;

        for (int i = 0; i < userHand.getNumberOfCards(); i++) {
            while (invalidMove) {
                int userChoices[] = promptUserInput(sc);
                locationDecider(userChoices, location1, location2, location3, userHand, true, deck);

                System.out.println("Here are your cards after your move");
                Hand.getHandCards(userHand);
                
                if (userHandBefore != Hand.getCurrentNumberOfCards(userHand)){
                    invalidMove = false;
                } else{
                    System.out.println("You did an invalid move, please input the card in other place.");
                }
            }

            userHandBefore = Hand.getCurrentNumberOfCards(userHand);
            invalidMove = true;
        }

        System.out.println("\nPC is making it's move...");
        for (int i = 0; i < player2.getNumberOfCardsPerTurn(); i++) {
            pcTurn(location1, location2, location3, player2, deck);
        }

        // display changes.
        System.out.println("\nHere are the locations after the first turn");
        Location.getAllLocation(location1, location2, location3);

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

        Player player1 = new Player();
        Player player2 = new Player();
        for(int i = 0; i < 3; i++){
            player1.handDraw(deck);
            player2.handDraw(deck);
        }

        System.out.println("Handing out cards...\n");

        System.out.println("Here are the locations!");
        Location.getAllLocation(location1, location2, location3);

        System.out.println("Here are your cards!");
        Player.getHandCards(player1);

        //Game start
        for (int i = 0; i < 5; i++){
            System.out.println("-------------------------- Turn " + (i + 1) + " --------------------------");
            nextTurn(sc, player1, player2, deck, location1, location2, location3);
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
