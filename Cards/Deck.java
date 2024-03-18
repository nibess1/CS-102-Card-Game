package Cards;

import java.util.*;

public class Deck {
    public ArrayList<Card> deck = new ArrayList<>();

    // create a deck of 53 cards
    public Deck() {
        deck.add(new Card(2, 'A', 'S', ""));
        deck.add(new Card(3, 'A', 'S', ""));
        deck.add(new Card(4, 'A', 'S', ""));
        deck.add(new Card(5, 'A', 'S', ""));
        deck.add(new Card(6, 'A', 'S', ""));
        deck.add(new Card(7, 'A', 'S', ""));
        deck.add(new Card(8, 'A', 'S', ""));
        deck.add(new Card(9, 'A', 'S', ""));
        deck.add(new Card(10, 'A', 'S', ""));
        deck.add(new Jack(7, 'J', 'S', "Gives all cards here with the same suite +2 power"));
        deck.add(new Queen(7, 'Q', 'S', "This card can move once"));
        deck.add(new Card(6, 'K', 'S', "Destroys all enemy cards powerd 4 and below at this location"));
        deck.add(new Card(1, 'A', 'S', "Activate your cards with abilities once more"));

        deck.add(new Card(2, 'A', 'C', ""));
        deck.add(new Card(3, 'A', 'C', ""));
        deck.add(new Card(4, 'A', 'C', ""));
        deck.add(new Card(5, 'A', 'C', ""));
        deck.add(new Card(6, 'A', 'C', ""));
        deck.add(new Card(7, 'A', 'C', ""));
        deck.add(new Card(8, 'A', 'C', ""));
        deck.add(new Card(9, 'A', 'C', ""));
        deck.add(new Card(10, 'A', 'C', ""));
        deck.add(new Jack(7, 'J', 'C', "Gives all cards here with the same suite +2 power"));
        deck.add(new Queen(7, 'Q', 'C', "This card can move once"));
        deck.add(new King(6, 'K', 'C', "Destroys all enemy cards powerd 4 and below at this location"));
        deck.add(new Card(1, 'A', 'C', "Activate your cards with abilities once more"));

        deck.add(new Card(2, 'A', 'H', ""));
        deck.add(new Card(3, 'A', 'H', ""));
        deck.add(new Card(4, 'A', 'H', ""));
        deck.add(new Card(5, 'A', 'H', ""));
        deck.add(new Card(6, 'A', 'H', ""));
        deck.add(new Card(7, 'A', 'H', ""));
        deck.add(new Card(8, 'A', 'H', ""));
        deck.add(new Card(9, 'A', 'H', ""));
        deck.add(new Card(10, 'A', 'H', ""));
        deck.add(new Jack(7, 'J', 'H', "Gives all cards here with the same suite +2 power"));
        deck.add(new Queen(7, 'Q', 'H', "This card can move once"));
        deck.add(new Card(6, 'K', 'H', "Destroys all enemy cards powerd 4 and below at this location"));
        deck.add(new Card(1, 'A', 'H', "Activate your cards with abilities once more"));

        deck.add(new Card(2, 'A', 'D', ""));
        deck.add(new Card(3, 'A', 'D', ""));
        deck.add(new Card(4, 'A', 'D', ""));
        deck.add(new Card(5, 'A', 'D', ""));
        deck.add(new Card(6, 'A', 'D', ""));
        deck.add(new Card(7, 'A', 'D', ""));
        deck.add(new Card(8, 'A', 'D', ""));
        deck.add(new Card(9, 'A', 'D', ""));
        deck.add(new Card(10, 'A', 'D', ""));
        deck.add(new Jack(7, 'J', 'D', "Gives all cards here with the same suite +2 power"));
        deck.add(new Queen(7, 'Q', 'D', "This card can move once"));
        deck.add(new Card(6, 'K', 'D', "Destroys all enemy cards powerd 4 and below at this location"));
        deck.add(new Card(1, 'A', 'D', "Activate your cards with abilities once more"));

        deck.add(new Card(0, 'Z', 'D', "Destroys the location this card is placed at"));
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
