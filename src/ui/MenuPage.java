package ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MenuPage {
    public static void displayMenuPage() {

        final int verticalHeight = 10;

        for (int i = 0; i < verticalHeight; i++) {
            if (i == verticalHeight / 2) {
                System.out
                        .println("----------------------------Welcome to Project Gambit!!----------------------------");

            }

            System.out.print("\n");
        }
    }

    //Create or overwrite settings
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
            System.out
                    .println("Type 'exit' to return to main menu or a value between 1 to 10 to configure game volume");
            settingsInput = sc.nextLine();
            
            if ("exit".equals(settingsInput)){
                break;
            }

            try {
                volumeValue = Integer.parseInt(settingsInput);

                if (volumeValue < 0 || volumeValue > 10) {
                    //This is exception is thrown just to jump to the catch block
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter 'exit' or a value between 0 to 10!");
                continue;
            }

            MenuPage.saveSettings(volumeValue);
        } while (true);
    }
}
