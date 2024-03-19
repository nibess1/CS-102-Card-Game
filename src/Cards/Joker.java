package Cards;
public class Joker extends Card{
    public static final String ability = "Destroys the location this card is placed at";

    public Joker() {
        super(6, '\0');
    }

    @Override
    public String toString(){
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Joker)) {
            return false;
        }

        Joker card = (Joker) obj;

        if (card.getPower() == getPower() && card.getSuite() == getSuite()) {
            return true;
        }
        return false;
    }
}
