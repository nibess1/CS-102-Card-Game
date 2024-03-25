import cards.*;
import locations.*;

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
        do {
            System.out.println("\nType 'pc' to play against bot");
            System.out.println("Type 'p' to play against real human");
            opponent = sc.nextLine();
        } while (!("p".equals(opponent) || "pc".equals(opponent)));

        System.out.println("Deck starting with " + deck.getTotalCards() + " cards.");

        deck.shuffle();
        System.out.println("Shuffling deck...\n");

        // Picking 3 distinct locations by shuffling an arraylist of all possible
        // locations and take the first 3.
        List<Location> possibleLocations = new ArrayList<>();
        possibleLocations.add(new CIS());
        possibleLocations.add(new Admin());
        possibleLocations.add(new SCIS());
        possibleLocations.add(new SOA());
        possibleLocations.add(new SOB());
        possibleLocations.add(new SOE());
        possibleLocations.add(new SOL());
        possibleLocations.add(new SOSS());
        possibleLocations.add(new TJunction());
        possibleLocations.add(new YeowLeongClassroom());
        possibleLocations.add(new SCIS());
        

        Collections.shuffle(possibleLocations);

        Location location1 = possibleLocations.get(0);
        Location location2 = possibleLocations.get(1);
        Location location3 = possibleLocations.get(2);

        System.out.println("Randomizing Locations...\n");

        Player player1;
        Player player2;

        if ("pc".equals(opponent)) {
            player1 = new Player(true);
            player2 = new Pc(false);
        } else {
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

        // Game start
        for (int i = 0; i < 5; i++) {
            System.out.println("-------------------------- Turn " + (i + 1) + " --------------------------");
            Turn.nextTurn(sc, player1, player2, deck, location1, location2, location3);
        }

        // Game end
        int p1Counter = 0;
        int p2Counter = 0;
        String winningMessageP1 = "";
        String winningMessageP2 = "";

        if (player2 instanceof Pc) {
            ResultCalculator.comparePowerPC(location1, location2, location3, winningMessageP1, p1Counter, p2Counter);
        } else {
            ResultCalculator.comparePowerPlayer(location1, location2, location3, winningMessageP1, winningMessageP2,
                    p1Counter, p2Counter);
        }
        sc.close();
    }
}

