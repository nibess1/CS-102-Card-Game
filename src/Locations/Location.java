package Locations;

import Cards.*;
import Exception.*;
import java.util.*;

public class Location {
    // possible location abilities
    // players draw 2 cards and can play 2 cards this game
    // player with the lowest power here wins
    // cards with power 4 and below, +2 power here
    // picture cards get + 2 power here
    // both players can only place 1 card here.
    // no abilities here
    // no pictures cards can be placed here
    //
    private String name;
    private String description;
    private int p1Power = 0;
    private int p2Power = 0;
    private ArrayList<Card> p1LiveCards = new ArrayList<>();
    private ArrayList<Card> p1DestroyedCards = new ArrayList<>();
    private ArrayList<Card> p2LiveCards = new ArrayList<>();
    private ArrayList<Card> p2DestroyedCards = new ArrayList<>();

    public Location(String location) {
        this.name = location;
    }

    public Location() {

    }

    public void calculatePower(boolean p1) {
        int totalPower = 0;

        for (Card card : (p1 ? p1LiveCards : p2LiveCards)) {
            totalPower += card.getPower();
        }

        if (p1) {
            p1Power = totalPower;
            return;
        }
        p2Power = totalPower;
    }

    // to place the card at the location
    public void placeCard(Card cardToBePlaced, boolean p1) {

        if (cardToBePlaced instanceof Jack jackCard) {
            for (Card card : (p1 ? p1LiveCards : p2LiveCards)) {
                if (card.getSuite() == jackCard.getSuite()) {
                    card.increasePower(2);
                }
            }
        } else if (cardToBePlaced instanceof King kingCard) {
            for (Card card : (p1 ? p2LiveCards : p1LiveCards)) {
                if (card.getPower() < kingCard.getPower()) {
                    destroyCard(card, p1);
                }
            }
        }

        if (p1) {
            this.p1LiveCards.add(cardToBePlaced);
        } else {
            this.p2LiveCards.add(cardToBePlaced);
        }

        calculatePower(p1);
    }

    public void destroyCard(Card card, boolean p1) {
        if (p1) {
            p1LiveCards.remove(card);
            p1DestroyedCards.add(card);
            return;
        }
        p2LiveCards.remove(card);
        p2DestroyedCards.add(card);
    }

    public Card removeCard(int index, boolean p1) {
        Card cardToRemove;
        if (p1) {
            cardToRemove = p1LiveCards.get(index);
            p1LiveCards.remove(index);
        }
        else{
            cardToRemove = p2LiveCards.get(index);
            p2LiveCards.remove(index);
        }

        calculatePower(p1);
        return cardToRemove;
    }

    public ArrayList<Card> getCards(boolean p1) {
        if (p1) {
            return this.p1LiveCards;
        }
        return this.p2LiveCards;
    }

    // meant to initiate jack's abilities.
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return this.name + ", Your Power:" + this.p1Power + ", Enemy Power:" + this.p2Power;
    }

    public int getP1NumLiveCards() {
        return this.p1LiveCards.size();
    }

    public int getP2NumLiveCards() {
        return this.p2LiveCards.size();
    }

    // meant to print the names, and power of each location
    public static void getAllLocation(Location location1, Location location2, Location location3) {
        System.out.println("#1:" + location1.toString());
        System.out.println("#2:" + location2.toString());
        System.out.println("#3:" + location3.toString());
        System.out.println("");
    }
}
