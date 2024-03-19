package Cards;
public class Jack extends Card{
    public static final String ability = "Gives all cards here with the same suite +2 power";

    public Jack(char suite) {
        super(7, suite);
    }

    @Override
    public String toString(){
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

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
