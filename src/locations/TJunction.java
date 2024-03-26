package locations;

public class TJunction extends Location{
    public TJunction() {
        super.setName("T Junction");
        super.setDescription("Player with the closest power to 21 wins.");
    }

    @Override
    public int playerWins() {
        
        if (Math.abs(getP1LocationPower() - 21) > Math.abs(getP2LocationPower()- 21) && !checkDestroyed()) {
            return 0;
        } else if (Math.abs(getP1LocationPower() - 21) < Math.abs(getP2LocationPower() - 21) && !checkDestroyed()){
            return 1;
        }
        return 2;
    }

}

