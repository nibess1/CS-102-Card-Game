package Cards;

import Locations.Location;

public class Queen extends Card {
    private boolean canMove;

    public Queen(int power, char picture, char suite, String description) {
        super(power, picture, suite, description);
        this.canMove = true;
    }

    @Override
    public boolean canMove() {
        return this.canMove;
    }

    public void move(Location oldLocation, Location newLocation, boolean p1) throws Exception {
        if (!canMove) {
            throw new Exception("Queen has already moved this game");
        }

        if (oldLocation.equals(newLocation)) {
            throw new Exception("You're attempting to move him to the same place!");
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

    public void hasMoved() {
        this.canMove = false;
    }
}
