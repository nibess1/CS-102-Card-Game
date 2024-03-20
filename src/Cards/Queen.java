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

        // get index of queen
        int indexOfQueen = -1;

        for (int i = 0; i < oldLocation.getCards(p1).size(); i++) {
            if (this.equals(oldLocation.getCards(p1).get(i))) {
                indexOfQueen = i;
            }
        }

        if (indexOfQueen == -1) {
            throw new Exception("Error! queen can't be found");
        }

        newLocation.getCards(p1).add(oldLocation.removeCard(indexOfQueen, p1));

        this.canMove = false;
        newLocation.calculatePower(p1);
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
