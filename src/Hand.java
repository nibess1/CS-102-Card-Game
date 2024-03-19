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

    public int getNumberOfCards(){
        return this.numberOfCards;
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
