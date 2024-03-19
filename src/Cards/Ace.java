package Cards;

public class Ace extends Card {
    public static final String ability = "Activate your cards with abilities once more";

    public Ace(char suite) {
        super(1, suite);
    }

    @Override
    public String toString(){
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Ace)) {
            return false;
        }

        Ace card = (Ace) obj;

        if (card.getPower() == getPower() && card.getSuite() == getSuite()) {
            return true;
        }
        return false;
    }
}
