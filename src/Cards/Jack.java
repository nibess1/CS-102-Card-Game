package Cards;

public class Jack extends Card implements Picture {
    public static final String ability = "Gives all cards here with the same suite +2 power";

    public Jack(char suite) {
        super(7, suite);
    }

    // console representation of jack
    @Override
    public String toString() {
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

    // check if the cards are equal
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Jack)) {
            return false;
        }

        Jack card = (Jack) obj;

        if (card.getPower() == getPower() && card.getSuite() == getSuite()) {
            return true;
        }
        return false;
    }
}
