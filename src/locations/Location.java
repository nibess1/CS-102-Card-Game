package locations;

import java.util.*;

import cards.*;
import cards.picturecards.*;
import exception.*;

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
    protected String name;
    private String description;
    private int p1Power = 0;
    private int p2Power = 0;
    protected ArrayList<Card> p1LiveCards = new ArrayList<>();
    protected ArrayList<Card> p1DestroyedCards = new ArrayList<>();
    protected ArrayList<Card> p2LiveCards = new ArrayList<>();
    protected ArrayList<Card> p2DestroyedCards = new ArrayList<>();
    private boolean isDestroyed = false;
    private int cardLimit = 5;

    public Location(String location) {
        this.name = location;
    }

    public Location() {

    }

    public void setCardLimit(int cardLimit) {
        this.cardLimit = cardLimit;
    }

    public int getCardLimit() {
        return this.cardLimit;
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
        
        // check if location is available
        try {
            isAvailable(p1);
        } catch (LocationRejectionException e) {
            throw e;
        }

        // only check the card's abilities if its a picture, to reduce load on all these
        // only location CIS disables abilities, so check if it is CIS
        if (!"CIS".equals(name)) {
            if (cardToBePlaced instanceof Picture) {

                // jack ability
                if (cardToBePlaced instanceof Jack j) {
                    Jack.triggerAbility(p1, j, this);
                }
                // king ability
                else if (cardToBePlaced instanceof King k) {
                    // i pass !p1 because we want to destroy the opposition's cards.
                    King.triggerAbility(!p1, k, this);
                }
                // ace abilities.
                else if (cardToBePlaced instanceof Ace) {
                    Ace.triggerAbility(p1, this);
                }

                // if its a joker, set destroyed and quit function.
                else if (cardToBePlaced instanceof Joker) {
                    this.isDestroyed = true;
                    return;
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

    public boolean isAvailable(boolean p1) {
        if (checkDestroyed()) {
            throw new LocationRejectionException("ERROR: Location is destroyed!");
        }

        if (checkIfFull(p1)) {
            throw new LocationRejectionException(
                    "ERROR: Location is full! There are already " + (p1 ? p1LiveCards.size()
                            : p2LiveCards.size()) + " card(s) at " + name);

        }
        return true;
    }

    public boolean checkIfFull(boolean p1) {
        return p1 ? p1LiveCards.size() >= cardLimit : p2LiveCards.size() >= cardLimit;
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

    // for queen's move logic, and ace's ability logic.
    public void removeCard(Card card, boolean p1) {
        if (p1) {
            p1LiveCards.remove(card);
        } else {
            p2LiveCards.remove(card);
        }
        calculatePower(p1);
    }

    public ArrayList<Card> getCards(boolean p1) {
        if (p1) {
            return this.p1LiveCards;
        }
        return this.p2LiveCards;
    }

    // for AI to decide which location has the least power to place the card
    public boolean isLessThan(Location location) {
        return this.p2Power < location.p2Power;
    }

    // to check if player wins at this location, and location is NOT destroyed.
    public int getWinningPlayer() {
        if (isDestroyed || p1Power == p2Power) {
            return 0;
        }

        return p1Power > p2Power ? 1 : 2;
    }

    // get current location power for player
    public int getLocationPower(boolean p1) {
        return p1 ? p1Power : p2Power;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return this.name + "\t\t\t\t| Your Power:" + this.p1Power + "\t| Enemy Power:" + this.p2Power + "\t| Description: "
                + this.description;
    }

    public int getP1NumLiveCards() {
        return this.p1LiveCards.size();
    }

    public int getP2NumLiveCards() {
        return this.p2LiveCards.size();
    }

    public boolean checkDestroyed() {
        return this.isDestroyed;
    }

    // meant to print the names, and power of each location
    public static void getAllLocation(Location... locations) {
        for (int i = 0; i < locations.length; i++){
            if (!locations[i].checkDestroyed()){
                System.out.printf("#%d: %s\n", i + 1, locations[i].toString());
            }
        }     
        System.out.println();
    }

}
