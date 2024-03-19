package Cards;

import Locations.Location;

public class Queen extends Card {
    private boolean canMove = true;
    public static final String ability = "Destroys all enemy cards with power 4 and below at this location";

    public Queen(char suite) {
        super(7, suite);
    }
    
    public boolean canMove() {
        return this.canMove;
    }

    public void move(Location oldLocation, Location newLocation, boolean p1) throws Exception {
        if (!canMove) {
            throw new Exception("Queen has already moved this game");
        }

        if (oldLocation.equals(newLocation)) {
            throw new Exception("Unable to move to the same location!");
        }

        int indexOfQueen = -1;

        for (int i = 0; i < oldLocation.getCards(p1).size(); i++) {
            if (this.equals(oldLocation.getCards(p1).get(i))) {
                indexOfQueen = i;
            }
        }

        if (indexOfQueen == -1) {
            throw new Exception("Error! queen can't be found");
        }

        newLocation.placeCard(oldLocation.removeCard(indexOfQueen, p1), p1);
        this.canMove = false;
    }

    @Override
    public String toString(){
        return "[power = " + super.getPower() + ", suite = " + super.getSuite() + ", ability = " + ability + "]";
    }

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
