import java.util.ArrayList;
import java.util.Scanner;

import cards.*;
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
    public boolean isPlayableLocation(Location loc, boolean p1) {
        if (hand.size() == 0) {
            return false;
        }

        try {
            if (!loc.isAvailable(p1)) {
                return false;
            }
        } catch (LocationRejectionException e) {
            return false;
        }

        // if location is SOL, hand must have non-picture cards
        if (loc instanceof SOL) {
            return countPictureCard() < hand.size();
        }

        // if location is CIS, hand must have picture cards
        if (loc instanceof CIS) {
            return countPictureCard() > 0;
        }

        return true;
    }

    // player to Skip Turn boolean
    public boolean toSkipTurn(Location loc1, Location loc2, Location loc3, boolean p1) {
        return !(isPlayableLocation(loc1, p1) || isPlayableLocation(loc2, p1) || isPlayableLocation(loc3, p1));
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
        System.out.println("");
    }

    public static int getCurrentNumberOfCards(Player hand) {
        int counterNumberCards = 0;

        for (int i = 0; i < hand.getHand().size(); i++) {
            counterNumberCards++;
        }

        return counterNumberCards;
    }

    public void turnInitialiser(Location location1, Location location2, Location location3, Scanner sc) {
        if (toSkipTurn(location1, location2, location3, false)) {
            return;
        }
        for (int i = 0; i < getNumberOfCardsPerTurn(); i++) {
            move(location1, location2, location3, sc);
        }

        
    }

    public void move(Location location1, Location location2, Location location3, Scanner sc) {
        int userHandBefore = hand.size();
        boolean invalidMove = true;

        for (int i = 0; i < numberOfCardsPerTurn; i++) {
            while (invalidMove) {
                Player.getHandCards(this);
                System.out.printf("Player %d's move, ", p1 ? 1 : 2);
                int userChoices[] = Turn.promptUserInput(sc, this);
                Turn.locationDecider(userChoices, location1, location2, location3, this, p1);
                
                if (userHandBefore != hand.size()) {
                    invalidMove = false;
                } else {
                    System.out.println("You did an invalid move, please input the card in other place.");
                }
            }
        }
    }
}
