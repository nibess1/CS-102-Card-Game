import cards.*;
import locations.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();

        String menuInput;
        do {
            MenuPage.displayMenuPage();

            System.out.println("Type 'start' to begin game");
            System.out.println("Type 's' to configure game settings");
            menuInput = sc.nextLine();

            if ("s".equals(menuInput)) {
                MenuPage.displaySettingsPage(sc);
            }
        } while (!"start".equals(menuInput));

        System.out.println("Deck starting with " + deck.getTotalCards() + " cards.");

        deck.shuffle();
        System.out.println("Shuffling deck...\n");

        // Picking 3 distinct locations by shuffling an arraylist of all possible
        // locations and take the first 3.
        List<String> possibleLocations = new ArrayList<String>(
                Arrays.asList("locations.SCIS", "locations.SOB", "locations.SOA", "locations.Admin", "locations.CIS",
                        "locations.SOE", "locations.SOL", "locations.SOSS"));

        Collections.shuffle(possibleLocations);

        Location[] location = new Location[3];

        // Instantiating locations
        try {
            for (int i = 0; i < 3; i++) {
                Class<?> className = Class.forName(possibleLocations.get(i));
                Object instance = className.getDeclaredConstructor().newInstance();
                location[i] = (Location) instance;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        Location location1 = location[0];
        Location location2 = location[1];
        Location location3 = location[2];

        System.out.println("Randomizing Locations...\n");

        Player player1 = new Player();
        Player player2 = new Player();
        for (int i = 0; i < 3; i++) {
            player1.handDraw(deck);
            player2.handDraw(deck);
        }

        System.out.println("Handing out cards...\n");

        System.out.println("Here are the locations!");
        Location.getAllLocation(location1, location2, location3);

        System.out.println("Here are your cards!");
        Player.getHandCards(player1);

        // Game start
        for (int i = 0; i < 5; i++) {
            System.out.println("-------------------------- Turn " + (i + 1) + " --------------------------");
            Turn.nextTurn(sc, player1, player2, deck, location1, location2, location3);
        }

        //Game end
        int playerWins = 0;
        String winningMessage = "";
        if (location1.playerWins()) {
            winningMessage += "location 1 with " + location1.getLocationPower() + " power and ";
            playerWins++;
        }
        if (location2.playerWins()) {
            winningMessage += "location 2 with " + location2.getLocationPower() + " power and ";
            playerWins++;
        }
        if (location3.playerWins()) {
            winningMessage += "location 3 with " + location3.getLocationPower() + " power and ";
            playerWins++;
        }
        if (playerWins >= 2) {
            System.out.println("You WON at " + winningMessage + ", Congrats!!");
        } else {
            System.out.println("You LOST");
        }

        sc.close();

    }

}
