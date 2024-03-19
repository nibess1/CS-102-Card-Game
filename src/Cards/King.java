package Cards;

public class King extends Card {
    public static final String ability = "Destroys all enemy cards with power lower than this card at this location";

    public King(char suite) {
        super(6, suite);
    }

    @Override
    public String toString(){
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof King)) {
            return false;
        }

        King card = (King) obj;

        if (card.getPower() == getPower() && card.getSuite() == getSuite()) {
            return true;
        }
        return false;
    }
}
