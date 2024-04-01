package players;
import java.util.ArrayList;
import java.util.Scanner;

import cards.*;
import cards.picturecards.*;
import exception.LocationRejectionException;
import locations.*;

public class Player {
    private ArrayList<Card> hand;
    private int numberOfCardsPerTurn;
    private boolean p1;

    public Player(boolean p1) {
        this.p1 = p1;
        this.hand = new ArrayList<>();
        this.numberOfCardsPerTurn = 2;
    }

    public boolean getIsPlayer1(){
        return p1;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public int getNumberOfCardsPerTurn() {
        return this.numberOfCardsPerTurn;
    }

    // to take the card from the hand. This is for placing card on locations.
    public Card getCard(int cardNumber) {
        Card tempCard = this.hand.get(cardNumber);
        return tempCard;
    }

    public void removeCard(int cardNumber) {
        this.hand.remove(cardNumber);
    }

    // to add cards in hand
    public void handDraw(Deck deck) {
        this.hand.add(deck.draw());
    }

    private int countPictureCard() {
        int count = 0;
        for (Card c : hand) {
            if (c instanceof Picture) {
                count++;
            }
        }
        return count;
    }

    // check if player can place a card in a specific location
    public boolean isPlayableLocation(Location location, boolean p1) {
        if (hand.isEmpty()) {
            return false;
        }

        try {
            if (!location.isAvailable(p1)) {
                return false;
            }
        } catch (LocationRejectionException e) {
            return false;
        }

        // if location is SOL, hand must have non-picture cards
        if (location instanceof SOL) {
            return countPictureCard() < hand.size();
        }

        // if location is CIS, hand must have picture cards
        if (location instanceof CIS) {
            return countPictureCard() > 0;
        }

        return true;
    }

    // player to Skip Turn boolean
    public boolean toSkipTurn(boolean p1, Location... locations) {
        for (Location location : locations){
            if (isPlayableLocation(location, p1)){
                return false;
            }
        }
        return true;
    }

    // draw a specific card for testing edge cases
    public void handDraw(Deck deck, Card c) {
        this.hand.add(deck.draw(c));
    }

    // meant to display all cards of a hand
    public static void getHandCards(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.println("Card #" + (i + 1) + " " + player.getHand().get(i));
        }
        System.out.println();
    }

    public static int getCurrentNumberOfCards(Player hand) {
        return hand.getHand().size();
    }

    public void turnInitialiser(Scanner sc, Location... locations) {
        
        for (int i = 0; i < getNumberOfCardsPerTurn(); i++) {
            if (toSkipTurn(p1, locations)) {
                System.out.println("Turn is skipped because there are no playable cards!");
                return;
            }
            move(sc, locations);
        }
    }

    public void move(Scanner sc, Location... locations) {
        int userHandBefore = hand.size();
        boolean invalidMove = true;

        for (int i = 0; i < numberOfCardsPerTurn; i++) {
            while (invalidMove) {
                Player.getHandCards(this);
                System.out.printf("Player %d's move, ", p1 ? 1 : 2);
                int userChoices[] = Turn.promptUserInput(sc, this);
                Turn.locationDecider(userChoices, this, p1, locations);
                
                if (userHandBefore != hand.size()) {
                    invalidMove = false;
                } else {
                    System.out.println("You did an invalid move, please input the card in other place.");
                }
            }
        }
    }
}
