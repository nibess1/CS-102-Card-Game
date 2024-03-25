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

        String opponent;
        do{
            System.out.println("Type 'pc' to play against bot");
            System.out.println("Type 'p' to play against real human");
            opponent = sc.nextLine();
        } while (!("p".equals(opponent) || "pc".equals(opponent)));

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

        Player player1;
        Player player2;
        if ("pc".equals(opponent)){
            player1 = new Player(true);
            player2 = new Pc(false);
        }
        else{
            player1 = new Player(true);
            player2 = new Player(false);
        }
        
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
        int p1Counter = 0;
        int p2Counter = 0;
        String winningMessageP1 = "";
        String winningMessageP2 = "";

        if(player2 instanceof Pc){
            ResultCalculator.comparePowerPC(location1, location2, location3, winningMessageP1, p1Counter, p2Counter);
        } else{
            ResultCalculator.comparePowerPlayer(location1, location2, location3, winningMessageP1, winningMessageP2, p1Counter, p2Counter);
        }
        sc.close();

    }

}
