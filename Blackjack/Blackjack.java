import java.util.Scanner;

public class Blackjack {
    private static Scanner scnr = new Scanner(System.in);
    private static P1Random rng = new P1Random();
    private static int gamesPlayed = 0, playerHand = 0, dealerHand = 0, pulledCard, menuSelection = 0, tieGames;
    private static int playerWins = 0, dealerWins = 0;

    private static String getCardName(int cardNumber) {  // gives the card names based of value of the generated card
        String cardName;
        switch (cardNumber) {
            case 1:
                cardName = "ACE";
                break;
            case 11:
                cardName = "JACK";
                break;
            case 12:
                cardName = "QUEEN";
                break;
            case 13:
                cardName = "KING";
                break;
            default:
                cardName = String.valueOf(cardNumber);
                break;
        }
        return cardName;
    }

    private static void menuSelect() {  // runs the game & is the menu selection of the game

        System.out.print("\n1. Get another card\n" +
                "2. Hold hand\n" +
                "3. Print statistics\n" +
                "4. Exit\n" +
                "\n" +
                "Choose an option: ");
        menuSelection = scnr.nextInt();

        if (menuSelection == 1) {  // has multiple cases for which if the player gets blackjack then they automatically win and if they bust they lose.
            pulledCard = rng.nextInt(13) + 1;
            if (pulledCard == 13 || pulledCard == 12 || pulledCard == 11) {
                playerHand = playerHand + 10;
            } else {
                playerHand = playerHand + pulledCard;
            }
            if (playerHand > 21) {
                System.out.println("\nYour card is a " + getCardName(pulledCard) + "!");
                System.out.println("Your hand is: " + playerHand + "");
                System.out.println("\nYou exceeded 21! You lose.\n");
                dealerWins += 1;
                return;
            }
            if (playerHand == 21) {
                System.out.println("\nYour card is a " + getCardName(pulledCard) + "!");
                System.out.println("Your hand is: " + playerHand + "");
                System.out.println("\nBLACKJACK! You win!\n");
                playerWins += 1;
            } else {
                System.out.println("\nYour card is a " + getCardName(pulledCard) + "!");
                System.out.println("Your hand is: " + playerHand + "");
                menuSelect();
            }
        } else if (menuSelection == 2) {  // the player holds and a dealercard is generated
            dealerHand = rng.nextInt(11) + 16;
            System.out.println("\nDealer's hand: " + dealerHand);
            System.out.println("Your hand is: " + playerHand);
            compareHand();
        } else if (menuSelection == 3) {  //  Pulls out the stats for the game.
            double winPerctange = (double) playerWins / (gamesPlayed - 1) * 100;
            System.out.println("\nNumber of Player wins: " + playerWins);
            System.out.println("Number of Dealer wins: " + dealerWins);
            System.out.println("Number of tie games: " + tieGames);
            System.out.println("Total # of games played is: " + (gamesPlayed - 1));
            System.out.println("Percentage of Player wins: " + String.format("%.1f", winPerctange) + "%");
            menuSelect();
        } else if (menuSelection == 4) {  // exits the game
            System.exit(1);
        } else {
            System.out.println("\nInvalid input!");
            System.out.println("Please enter an integer value between 1 and 4.");
            menuSelect();
        }
    }

    private static void compareHand() { // runs after the user press 2 for the menu selection.
        if (((playerHand < dealerHand) && (dealerHand < 21)) || dealerHand == 21) {
            System.out.println("\nDealer wins!\n");
            dealerWins++;
        }
        if (playerHand > dealerHand) {
            System.out.println("\nYou win!\n");
            playerWins++;
        }
        if (playerHand == dealerHand) {
            System.out.println("\nIt's a tie! No one wins!\n");
            tieGames++;
        }
        if ((playerHand < dealerHand) && (dealerHand > 21)) {
            System.out.println("\nYou win!\n");
            playerWins++;
        }
    }

    public static void main(String[] args) {

        boolean gameActive = true;

        while (gameActive) { //  this whole function will keep looping as long as the user want to play the game.
            playerHand = 0;
            dealerHand = 0;
            gamesPlayed++;

            System.out.println("START GAME #" + gamesPlayed + "\n");
            pulledCard = rng.nextInt(13) + 1;

            if (pulledCard == 1) {
                System.out.println("Your card is a ACE!");
                playerHand = pulledCard + playerHand;
                System.out.println("Your hand is: " + playerHand);
            } else if (pulledCard == 11) {
                System.out.println("Your card is a JACK!");
                playerHand = 10 + playerHand;
                System.out.println("Your hand is: " + playerHand);
            } else if (pulledCard == 12) {
                System.out.println("Your card is a QUEEN!");
                playerHand = 10 + playerHand;
                System.out.println("Your hand is: " + playerHand);
            } else if (pulledCard == 13) {
                System.out.println("Your card is a KING!");
                playerHand = 10 + playerHand;
                System.out.println("Your hand is: " + playerHand);
            } else {
                System.out.println("Your card is a " + pulledCard + "!");
                playerHand = pulledCard + playerHand;
                System.out.println("Your hand is: " + playerHand);
            }
            menuSelect(); //  Calls for the action the user wants to do.

        }

    }
}