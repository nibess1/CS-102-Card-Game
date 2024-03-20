package Cards;

import Exception.LocationRejectionException;
import Locations.Location;

public class Queen extends Card implements Picture {
    private boolean canMove = true;
    public static final String ability = "Destroys all enemy cards with power 4 and below at this location";

    public Queen(char suite) {
        super(7, suite);
    }

    // check if queen has moved
    public boolean canMove() {
        return this.canMove;
    }

    public void setCanMove() {
        this.canMove = true;
    }

    // move queen card.
    public void move(Location oldLocation, Location newLocation, boolean p1) throws Exception {
        // check if queen has moved
        if (!canMove) {
            throw new Exception("Queen has already moved this game");
        }

        // check if the locations are the same
        if (oldLocation.equals(newLocation)) {
            throw new LocationRejectionException("Unable to move to the same location!");
        }

        // remove queen from old location
        oldLocation.removeCard(this, p1);

        // add queen into new location but DONT use placeCard function as i do not want
        // the location abilities
        // to affect queen.
        newLocation.getCards(p1).add(this);
        newLocation.calculatePower(p1);

        // set queen's move to false. it cant move anymore.
        this.canMove = false;
    }

    // console representation of queen card
    @Override
    public String toString() {
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

    // check if cards are equal
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Queen)) {
            return false;
        }

        Queen card = (Queen) obj;

        if (card.getPower() == getPower() && card.getSuite() == getSuite()) {
            return true;
        }
        return false;
    }
}
