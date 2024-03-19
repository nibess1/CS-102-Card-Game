import java.util.ArrayList;
import Cards.*;

public class Hand {
    private ArrayList<Card> hand;
    private int numberOfCards;

    public Hand() {
        this.hand = new ArrayList<>();
        this.numberOfCards = 2;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public int getNumberOfCards() {
        return this.numberOfCards;
    }

    // to take the card from the hand. This is for placing card on locations.
    public Card getCard(int cardNumber) {
        Card tempCard = this.hand.get(cardNumber);
        this.hand.remove(cardNumber);
        return tempCard;
    }

    // to add cards in hand
    public void handDraw(Deck deck) {
        this.hand.add(deck.draw());
    }

    // meant to display all cards of a hand
    public static void getHandCards(Hand hand) {
        for (int i = 0; i < hand.getHand().size(); i++) {
            System.out.println("Card #" + (i + 1) + " " + hand.getHand().get(i));
        }
        System.out.println("");
    }
}
