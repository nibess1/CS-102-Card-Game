package locations;

public class TJunction extends Location{
    public TJunction() {
        super.setName("T Junction");
        super.setDescription("Player with the closest power to 21 wins.");
    }

    @Override
    public int getWinningPlayer() {
        if(checkDestroyed()){
            return 0;
        }

        int diffPlayer1 = Math.abs(21 - getLocationPower(true));
        int diffPlayer2 = Math.abs(21 - getLocationPower(false));

        if (diffPlayer1 == diffPlayer2) {
            return 0; 
        } else if (diffPlayer1 < diffPlayer2) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public String toString() {
        return getName() + "\t| Your Power:" + getLocationPower(true) + "\t| Enemy Power:" + getLocationPower(false) + "\t| Description:"
                + getDescription();
    }

}

