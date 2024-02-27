package Cards;
public class Card{
    private int power; // the number on the card
    private char picture; // the picture of the card "J / Q / K / A / Z" (A is normal, Z is joker)
    private char symbol; // the symbol / group the card belongs to "H / C / S / D"
    private boolean destroyed; // check if the card has been destroyed


    public Card(int power, char picture, char symbol) {
        this.power = power;
        this.picture = picture;
        this.symbol = symbol;
        this.destroyed = false;
    }

    //increase power of card.
    public void increasePower(int numberToIncrease){
        this.power += numberToIncrease;
    }

    //get power of card
    public int getPower(){
        return this.power;
    }

    @Override
    public String toString() {
        return "[power=" + power + ", picture=" + picture + ", symbol=" + symbol +  "]";
    }

    public void destroy(){
        this.destroyed = true;
    }

    

    
}