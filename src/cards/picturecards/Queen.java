package cards.picturecards;

import cards.*;
import exception.LocationRejectionException;
import locations.Location;

public class Queen extends Card implements Picture {
    private boolean canMove = true;
    public static final String ability = "This card is able to move once (Simply click on this card and the location you desire to move it to.)";

    public Queen(char suite) {
        super(7, suite);
    }

    // check if queen has moved
    public boolean canMove() {
        return this.canMove;
    }

    public void setCanMove(boolean value) {
        this.canMove = value;
    }

    // move queen card.
    public void triggerAbility(Location oldLocation, Location newLocation, boolean p1) throws Exception {
        // check if queen has moved
        if (!canMove) {
            throw new Exception("ERROR: Queen has already moved this game");
        }

        // check if the locations are the same
        if (oldLocation.equals(newLocation)) {
            throw new LocationRejectionException("ERROR: Unable to move to the same location!");
        }

        // remove queen from old location
        oldLocation.removeCard(this, p1);

        // add queen into new location but DONT use placeCard function as i do not want
        // the location abilities
        // to affect queen.
        newLocation.getCards(p1).add(this);
        newLocation.calculatePower(p1);

        // set queen's move to false. it cant move anymore.
        setCanMove(false);
    }

    // console representation of queen card
    @Override
    public String toString() {
        return "Queen\t[ power = " + super.getPower() + "\t| suite = " + super.getSuite() + "\t| ability = " + ability + " ]";
    }

    // check if cards are equal
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Queen)) {
            return false;
        }

        Queen card = (Queen) obj;

        return card.getPower() == getPower() && card.getSuite() == getSuite();
    }
}
