import locations.*;
import java.util.*;

public class ResultCalculator {

    private static int p1Counter;
    private static int p2Counter;
    private static List<Location> player1WinningLocations = new ArrayList<>();
    private static List<Location> player2WinningLocations = new ArrayList<>();

    public static void printWinningMessage(Player p1, Player p2, Location... locations) {

        for (Location location : locations) {
            if (location.getWinningPlayer() == 1) {
                p1Counter++;
                player1WinningLocations.add(location);
            } else if (location.getWinningPlayer() == 2) {
                p2Counter++;
                player2WinningLocations.add(location);
            }
        }

        if (p1Counter == p2Counter) {
            System.out.println("Both players are TIED!");
            return;

        }
        boolean p1Win = p1Counter > p2Counter;

        Player winner = p1Win ? p1 : p2;
        
        String winningMessage = "";
        winningMessage += p1Win ? "Player 1 " : "Player 2 ";

        if (winner instanceof Pc) {
            System.out.println("You Lost :( ");
            winningMessage += "(Bot) ";
        } else {
            System.out.println("Congratulations!");
            winningMessage += "(Challenger) "; 
        }
        winningMessage += "won at ";
        if (p1Win) {
            winningMessage += getWinnerMessage(player1WinningLocations, winner);
        } else {
            winningMessage += getWinnerMessage(player2WinningLocations, winner);

        }

        System.out.println(winningMessage);

    }

    public static String getWinnerMessage(List<Location> WinningLocations, Player player) {
        String result = "";
        for (int i = 0; i < WinningLocations.size() - 1; i++) {
            Location current = WinningLocations.get(i);
            result += "location " + current.getName() + " with " + current.getLocationPower(player.getIsPlayer1())
                    + " power, and at ";
        }

        Location last = WinningLocations.getLast();
        result += "location " + last.getName() + " with " + last.getLocationPower(player.getIsPlayer1()) + " power.";

        return result;
    }
}
