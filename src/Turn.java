import java.util.InputMismatchException;
import java.util.Scanner;

import Cards.Deck;
import Locations.*;
import Exception.LocationRejectionException;

public class Turn {
    
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
        // user draws
        for (int i = 0; i < player1.getNumberOfCardsPerTurn(); i++) {
            player1.handDraw(deck);
        }

        Player.getHandCards(player1);
        // pc draws
        for (int i = 0; i < player2.getNumberOfCardsPerTurn(); i++) {
            player2.handDraw(deck);
        }

        if (player1.toSkipTurn(location1, location2, location3, true)) {
            // PC turn if your locations are unavailable
            Pc.pcTurnInitialiser(location1, location2, location3, player2, deck);

            // end moves
            System.out.println("\nHere are the locations after the first turn");
            Location.getAllLocation(location1, location2, location3);

            return;

        }

        // prompt user input and play his card based on his choice based on the number
        // of times he can
        int userHandBefore = Player.getCurrentNumberOfCards(player1);
        boolean invalidMove = true;

        for (int i = 0; i < player1.getNumberOfCardsPerTurn(); i++) {
            while (invalidMove) {
                int userChoices[] = promptUserInput(sc, player1);
                locationDecider(userChoices, location1, location2, location3, player1, true, deck);

                System.out.println("Here are your cards after your move");
                Player.getHandCards(player1);

                if (userHandBefore != Player.getCurrentNumberOfCards(player1)) {
                    invalidMove = false;
                } else {
                    System.out.println("You did an invalid move, please input the card in other place.");
                }
            }

            userHandBefore = Player.getCurrentNumberOfCards(player1);
            invalidMove = true;
        }

        Pc.pcTurnInitialiser(location1, location2, location3, player2, deck);

        // display changes.
        System.out.println("\nHere are the locations after the first turn");
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
