package players;
import java.util.InputMismatchException;
import java.util.Scanner;

import cards.Deck;
import exception.LocationRejectionException;
import locations.*;

public class Turn {

    public static void place(int cardNum, Location loc, Player hand, boolean p1) {
        loc.placeCard(hand.getCard(cardNum), p1);
        hand.removeCard(cardNum);
    }


    public static void locationDecider(int[] userChoices, Player hand, boolean p1, Location... locations) {
        try {
            place(userChoices[0], locations[userChoices[1] - 1], hand, p1);
        } catch (LocationRejectionException e) {
            e.getErrorMessage();
            System.out.println();
        }
    }

    
    public static void nextTurn(Scanner sc, Player player1, Player player2, Deck deck, Location... locations) {

        // display location status
        System.out.println("Drawing cards ...");
        for (int i = 0; i < player1.getNumberOfCardsPerTurn(); i++) {
            player1.handDraw(deck);
        }
        for (int i = 0; i < player2.getNumberOfCardsPerTurn(); i++) {
            player2.handDraw(deck);
        }
        

        player1.turnInitialiser(sc, locations);
        player2.turnInitialiser(sc, locations);


        System.out.println("\nHere are the locations after the turn");
        Location.getAllLocation(locations);

    }


    public static int[] promptUserInput(Scanner sc, Player player1) {
        int currentCard = 0;
        int currentLocation = 0;
        int totalCardsOnHand = player1.getHand().size();

        System.out
        .println("Which card would you like to place this turn? (Pick from 1 - " + totalCardsOnHand + ")");
        currentCard = getInput(sc);
        while (currentCard < 1 || currentCard > totalCardsOnHand) {
            System.out.println("Please enter a number between 1 - " + totalCardsOnHand);
            currentCard = getInput(sc);
        }


        System.out.println("Where would you like to place this card at? (Pick from 1 - 3)");
        currentLocation = getInput(sc);
        while (currentLocation < 1 || currentLocation > 3) {
            System.out.println("Please enter a number between 1 - 3");
            currentLocation = getInput(sc);
        }


        return new int[] { currentCard - 1, currentLocation };
    }


    public static int getInput(Scanner sc) {
        int toReturn = -1;
        try {
            toReturn = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!");
        }
        sc.nextLine();
        return toReturn;
    }

}
