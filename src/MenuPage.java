import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MenuPage {
    public static void displayMenuPage() {

        final int verticalHeight = 10;
        final int horizontalHeight = 100;

        for (int i = 0; i < verticalHeight; i++) {
            if (i == verticalHeight / 2) {
                System.out
                        .println("----------------------------Welcome to Project Gambit!!----------------------------");

            } else {
                for (int j = 0; j < horizontalHeight; j++) {
                    System.out.print("");
                }
            }
            System.out.print("\n");
        }
    }

    public static void saveSettings(int volumeValue) {
        try (PrintStream out = new PrintStream(new FileOutputStream("settings.txt", false));) {
            out.println("Settings");
            out.println(volumeValue);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void displaySettingsPage(Scanner sc) {
        System.out.println("Settings");
        try (Scanner toRead = new Scanner(new File("settings.txt"))) {
            toRead.useDelimiter(",|\r\n|\n");
            toRead.nextLine();
            System.out.println("Game volume: " + toRead.nextLine());

        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            try (PrintStream out = new PrintStream(new FileOutputStream("settings.txt", false));) {
                out.println("Settings");
                out.println("5");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        String settingsInput;
        int volumeValue = -1;

        do {
            System.out.println("Type 'exit' to return to main menu");
            System.out.println("Type a value to configure game volume");
            settingsInput = sc.nextLine();
            try {
                volumeValue = Integer.parseInt(settingsInput);
            } catch (Exception e) {
                System.out.println("Please enter 'exit' or a value between 0 to 10!");
            }
            if (volumeValue >= 0 && volumeValue <= 10) {
                MenuPage.saveSettings(volumeValue);
            }
        } while (!(settingsInput.equals("exit")));
    }
}
