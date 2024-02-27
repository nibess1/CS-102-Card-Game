import java.util.ArrayList;
import Cards.*;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    //to take the card from the hand. This is for placing card on locations.
    public Card getCard(int cardNumber) {
        Card tempCard = this.hand.get(cardNumber);
        this.hand.remove(cardNumber);
        return tempCard;
    }

    //to add cards in hand
    public void handDraw(Deck deck) {
        this.hand.add(deck.draw());
    }
}
