import java.util.ArrayList;
import Cards.*;

public class Player {
    private ArrayList<Card> hand;
    private int numberOfCardsPerTurn;

    public Player() {
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
        this.hand.remove(cardNumber);
        return tempCard;
    }

    // to add cards in hand
    public void handDraw(Deck deck) {
        this.hand.add(deck.draw());
    }

    // meant to display all cards of a hand
    public static void getHandCards(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.println("Card #" + (i + 1) + " " + player.getHand().get(i));
        }
        System.out.println("");
    }
}
