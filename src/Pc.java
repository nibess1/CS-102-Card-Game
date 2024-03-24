import Cards.Deck;
import Locations.*;

public class Pc {
    public static void pcTurnInitialiser(Location location1, Location location2, Location location3, Player player2,
            Deck deck) {
        if (player2.toSkipTurn(location1, location2, location3, false)) {
            return;
        }
        System.out.println("\nPC is making it's move...");
        for (int i = 0; i < player2.getNumberOfCardsPerTurn(); i++) {
            pcTurn(location1, location2, location3, player2, deck);
        }
    }

    public static void pcTurn(Location location1, Location location2, Location location3, Player player2, Deck deck) {
        int indexOfHighestPowerCard = 0;
        for (int i = 1; i < player2.getHand().size(); i++) {
            if (player2.getHand().get(i).getPower() > player2.getHand().get(indexOfHighestPowerCard).getPower()) {
                indexOfHighestPowerCard = i;
            }
        }
        // assume lowest power location is 1.
        int lowestPowerLocation = 1;
        // check if location 2 is lesser
        if (location2.isLessThan(location1)) {
            lowestPowerLocation = 2;
        }
        // if not, check if location 3 is lesser than both 1 and 2.
        else if (location3.isLessThan(location1) && location3.isLessThan(location2)) {
            lowestPowerLocation = 3;
        }

        // continue PC (AI)'s turn
        int pcChoices[] = { indexOfHighestPowerCard, lowestPowerLocation };

        try {
            Turn.locationDecider(pcChoices, location1, location2, location3, player2, false, deck);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PC DOES NOT HAVE THIS CARD");
            Player.getHandCards(player2);
        }

    }

}
