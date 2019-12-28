import java.util.Scanner;

public class PakuriProgram {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String menuChoice = "0";
        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");

        // asks the user for a capacity size.
        int capacity = 0;
        boolean correctChoice = false;
        while (!correctChoice) {
            System.out.print("Enter max capacity of the Pakudex: ");
            // to catch an exception if the user inputs a value that is not numerical.
            try {
                capacity = Integer.parseInt(scnr.next());
                if (capacity <= 0) {
                    System.out.println("Please enter a valid size.");
                } else {
                    correctChoice = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid size.");
            }
        }
        // creates a Pakudex object
        Pakudex dex = new Pakudex(capacity);

        System.out.println("The Pakudex can hold " + capacity + " species of Pakuri.");

        //This loops runs the Pakudex Menu.
        while (!menuChoice.equals("6")) {
            printMenu();
            menuChoice = scnr.next();
            switch (menuChoice) {
                case "1":
                    //List Pakuri. if there is not any pakuri then an error message is given.
                    if (dex.getSize() == 0) {
                        System.out.println("No Pakuri in Pakudex yet!");
                    } else {
                        System.out.println("Pakuri In Pakudex:");
                        String[] listOfPakuri = dex.getSpeciesArray();
                        for (int i = 0; i < dex.getSize(); i++) {
                            System.out.println((i + 1) + ". " + listOfPakuri[i]);
                        }
                    }
                    break;
                case "2":
                    //Show Pakuri
                    System.out.print("Enter the name of the species to display: ");
                    String name = scnr.next();

                    // if the name does not exist then it will give error.
                    if (dex.getStats(name) == null)
                        System.out.println("Error: No such Pakuri!");
                    else {
                        int[] stats = dex.getStats(name);
                        System.out.println("\nSpecies: " + name +
                                "\nAttack: " + stats[0] +
                                "\nDefense: " + stats[1] +
                                "\nSpeed: " + stats[2]);
                    }
                    break;
                case "3":
                    // Add Pakuri
                    if (dex.getCapacity() == 0) {
                        System.out.println("Error: Pakudex is full!");
                    } else {
                        System.out.print("Enter the name of the species to add: ");
                        String name1 = scnr.next();
                        boolean added = dex.addPakuri(name1);
                        if (added) {
                            System.out.println("Pakuri species " + name1 + " successfully added!");
                        } else {
                            System.out.println("Error: Pakudex already contains this species!");
                        }
                    }
                    break;
                case "4":
                    // Evolve Pakuri
                    System.out.print("Enter the name of the species to evolve: ");
                    String nameEvolve = scnr.next();
                    boolean evolved = dex.evolveSpecies(nameEvolve);

                    // if the species evolved then it will give a message saying it evolved
                    if (evolved) {
                        System.out.println(nameEvolve + " has evolved!");
                    } else {
                        System.out.println("Error: No such Pakuri!");
                    }
                    break;
                case "5":
                    // Sort Pakuri"
                    dex.sortPakuri();
                    System.out.println("Pakuri have been sorted!");
                    break;
                case "6":
                    //exit the Pakudex
                    System.out.println("Thanks for using Pakudex! Bye!");
                    break;
                default:
                    System.out.println("Unrecognized menu selection!");
                    break;
            }
        }
    }

    //Prints the menu for the Pakudex.
    private static void printMenu() {
        System.out.print("\nPakudex Main Menu" + "\n" +
                "-----------------" + "\n" +
                "1. List Pakuri" + "\n" +
                "2. Show Pakuri" + "\n" +
                "3. Add Pakuri" + "\n" +
                "4. Evolve Pakuri" + "\n" +
                "5. Sort Pakuri" + "\n" +
                "6. Exit" + "\n\nWhat would you like to do? ");
    }
}
