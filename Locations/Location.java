package Locations;

import java.util.*;
import Cards.*;

public class Location {
    // possible location abilities
    // players draw 2 cards and can play 2 cards this game
    // player with the lowest power hear wins
    // cards with power 4 and below, +2 power here
    // picture cards get + 2 power here
    // both players can only place 1 card here.
    private String[] possibleLocations = { "SCIS", "LKCSB", "School of Accounting", "SIC", "SOE" };
    private String name;
    private int p1Power;
    private int p2Power;
    private ArrayList<Card> liveCards;
    private ArrayList<Card> destroyedCards;

    Random random = new Random();

    public Location() {
        this.name = possibleLocations[random.nextInt(possibleLocations.length)];
        this.p1Power = 0;
        this.p2Power = 0;
        this.liveCards = new ArrayList<>();
        this.destroyedCards = new ArrayList<>();
    }

    // to place the card at the location
    public void placeCard(Card cardToBePlaced, boolean p1) {
        if (p1) {
            this.p1Power += cardToBePlaced.getPower();
        } else {
            this.p2Power += cardToBePlaced.getPower();
        }

        this.liveCards.add(cardToBePlaced);
    }

    //meant to initiate jack's abilities.
    public void placeJack(Jack cardToBePlaced, boolean p1, Deck deck) {
        Card newCard = deck.draw();
        System.out.println("New card drawn is " + newCard);
        cardToBePlaced.increasePower(newCard.getPower());
        System.out.println("Jack's power is now " + cardToBePlaced.getPower());

        this.placeCard(cardToBePlaced, p1);
    }

    // for AI to decide which location has the least power to place the card
    public boolean isLessThan(Location location) {
        if (this.p2Power < location.p2Power) {
            return true;
        }
        return false;
    }

    // to check if player wins at this location
    public boolean playerWins() {
        if (p1Power > p2Power) {
            return true;
        }
        return false;
    }

    // get current location power for player
    public int getLocationPower() {
        return this.p1Power;
    }

    public String toString() {
        return this.name + ", Your Power:" + this.p1Power + ", Enemy Power:" + this.p2Power;
    }
}
