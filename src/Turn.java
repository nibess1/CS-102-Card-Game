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

    public static void locationDecider(int[] userChoices, Location location1, Location location2, Location location3,
            Player hand, boolean p1) {

        try {
            switch (userChoices[1]) {
                case 1:
                    place(userChoices[0], location1, hand, p1);
                    break;
                case 2:
                    place(userChoices[0], location2, hand, p1);
                    break;
                case 3:
                    place(userChoices[0], location3, hand, p1);
                    break;
                default:
                    break;
            }
        } catch (LocationRejectionException e) {
            e.getErrorMessage();
            System.out.println();
        }
    }

    public static void nextTurn(Scanner sc, Player player1, Player player2, Deck deck, Location location1,
            Location location2, Location location3) {

        // display location status

        System.out.println("Drawing cards ...");
        // player 1 draws
        for (int i = 0; i < player1.getNumberOfCardsPerTurn(); i++) {
            player1.handDraw(deck);
        }
        // player 2 draws
        for (int i = 0; i < player2.getNumberOfCardsPerTurn(); i++) {
            player2.handDraw(deck);
        }
        
        player1.turnInitialiser(location1, location2, location3, sc);
        player2.turnInitialiser(location1, location2, location3, sc);

        // display changes.
        System.out.println("\nHere are the locations after the turn");
        Location.getAllLocation(location1, location2, location3);

    }

    public static int[] promptUserInput(Scanner sc, Player player1) {
        int currentCard = 0;
        int currentLocation = 0;
        int totalCardsOnHand = player1.getHand().size();

        System.out.println(
                "Which card would you like to place this turn? (Pick from 1 - " + totalCardsOnHand + ")");
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
