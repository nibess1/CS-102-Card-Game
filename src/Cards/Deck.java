package Cards;

import java.util.*;

public class Deck {
    public ArrayList<Card> deck = new ArrayList<>();

    // create a deck of 53 cards
    public Deck() {

        char[] suites = {'S', 'C', 'H', 'D'};

        for (char suite : suites){
            for(int i = 2; i <= 10; i++){
                deck.add(new Card(i, suite));
            }

            deck.add(new Jack(suite));
            deck.add(new Queen(suite));
            deck.add(new King(suite));
            deck.add(new Ace(suite));
        }

        deck.add(new Joker());

    }
    
    // shuffle deck
    public void shuffle() {
        Collections.shuffle(deck);
    }

    // get total cards left
    public int getTotalCards() {
        return this.deck.size();
    }

    // draw a card from the deck. the handDraw() method in hand calls this method.
    public Card draw() {
        Card cardToBeDrawn = null;
        try {
            cardToBeDrawn = deck.get(deck.size() - 1);
            deck.remove(deck.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Deck is empty");
        }

        return cardToBeDrawn;
    }

    // meant to test if the draw feature is working as intended
    public void deckTest() {
        for (int i = 1; i <= 53; i++) {
            System.out.println(this.draw() instanceof Jack);
        }

    }
}
