import locations.*;

public class ResultCalculator{

    public static void comparePowerPC(Location location1, Location location2, Location location3, String winningMessageP1, int p1Counter, int p2Counter){
        //if versus pc
        if (location1.playerWins() == 1){
            winningMessageP1 += location1.getName() + " with " + location1.getLocationPower() + " power and ";
            p1Counter++;
        } else if (location1.playerWins() == 0){
            p2Counter++;
        }

        if (location2.playerWins() == 1){
            winningMessageP1 += location2.getName() + " with " + location2.getLocationPower() + " power and ";
            p1Counter++;
        } else if (location2.playerWins() == 0){
            p2Counter++;
        }

        if (location3.playerWins() == 1){
            winningMessageP1 += location3.getName() + " with " + location3.getLocationPower() + " power";
            p1Counter++;
        } else if (location3.playerWins() == 0){
            p2Counter++;
        }

        resultPrintPC(p1Counter, p2Counter, winningMessageP1);
    }

    public static void comparePowerPlayer(Location location1, Location location2, Location location3, String winningMessageP1, String winningMessageP2, int p1Counter, int p2Counter){
        //if versus player
        if (location1.playerWins() == 1){
            winningMessageP1 += location1.getName() + " with " + location1.getLocationPower() + " power and ";
            p1Counter++;
        } else if (location1.playerWins() == 0){
            winningMessageP2 += location1.getName() + " with " + location1.getLocationPower() + " power and ";
            p2Counter++;
        }

        if (location2.playerWins() == 1){
            winningMessageP1 += location2.getName() + " with " + location2.getLocationPower() + " power and ";
            p1Counter++;
        } else if (location2.playerWins() == 0){
            winningMessageP2 += location2.getName() + " with " + location2.getLocationPower() + " power and ";
            p2Counter++;
        }

        if (location3.playerWins() == 1){
            winningMessageP1 += location3.getName() + " with " + location3.getLocationPower() + " power";
            p1Counter++;
        } else if (location3.playerWins() == 0){
            winningMessageP2 += location3.getName() + " with " + location3.getLocationPower() + " power";
            p2Counter++;
        }

        resultPrint(p1Counter, p2Counter, winningMessageP1, winningMessageP2);
    }

    public static void resultPrint(int p1Counter, int p2Counter, String winningMessageP1, String winningMessageP2){
        if (p1Counter > p2Counter) {
            System.out.println("Player one WON at " + winningMessageP1 + ", Congrats!!");
        } else if (p2Counter > p1Counter){
            System.out.println("Player two WON at " + winningMessageP2 + ", Congrats!!");
        } else{
            System.out.println("It's a DRAW");
        }
    }

    public static void resultPrintPC(int p1Counter, int p2Counter, String winningMessageP1){
        if (p1Counter > p2Counter) {
            System.out.println("Player one WON at " + winningMessageP1 + ", Congrats!!");
        } else if (p2Counter > p1Counter){
            System.out.println("You LOST!");
        } else{
            System.out.println("It's a DRAW");
        }
    }
}
