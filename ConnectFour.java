import java.util.Arrays;
import java.util.Scanner;

public class ConnectFour {
    public static char[][] gameArray;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // print the menu for the game
        System.out.print("What would you like the height of the board to be? ");
        int height = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length = scnr.nextInt();
        gameArray = new char[height][length];
        initializeBoard(gameArray);
        printBoard(gameArray);
        System.out.println("\nPlayer 1: x");
        System.out.println("Player 2: o");

        // initializing variables
        int playerturn = 1;
        int col = 0;
        int row = 0;
        boolean playerOneWon = false, playerTwoWon = false;

        for (int i = 0; i < height * length; i++) {
            System.out.print("\nPlayer " + playerturn + ": Which column would you like to choose? ");
            col = scnr.nextInt();
            if (playerturn == 1) {

                //  runs the game for player 1
                row = insertChip(gameArray, col, 'x');
                printBoard(gameArray);
                playerturn = 2;
                playerOneWon = checkIfWinner(gameArray, row, col, 'x');
            } else {

                // runs the game for player 2
                row = insertChip(gameArray, col, 'o');
                printBoard(gameArray);
                playerturn = 1;
                playerTwoWon = checkIfWinner(gameArray, row, col, 'o');

            }
            if (playerOneWon || playerTwoWon)
                break;
        }
        //  win statements
        if (playerOneWon) {
            System.out.println("Player 1 won the game!");
        } else if (playerTwoWon) {
            System.out.println("Player 2 won the game!");
        } else {
            System.out.println("Draw. Nobody wins.");
        }
    }

    public static void initializeBoard(char[][] array) {

        //sets board to '-'
        for (char[] row : array) {
            Arrays.fill(row, '-');
        }
        gameArray = array;
    }

    public static void printBoard(char[][] array) {

        // prints the board
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static int insertChip(char[][] array, int col, char chipType) {
        int row = 0;

        // inserts chip in the board.
        for (int i = 0; i <= array[0].length; i++) {
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                break;
            } else {
                row++;
            }
        }
        return row;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int countRow = 0;
        int countCol = 0;
        int rows = array.length - 1;
        int cols = array[0].length - 1;

        // checks for horizontal win
        for (int i = 0; i <= rows; i++) {
            for (; cols >= 0; cols--) {
                if (array[i][cols] == chipType) {
                    countRow++;
                }
                if (countRow == 4) {
                    return true;
                }
            }
            countRow = 0;
            cols = array[0].length - 1;
        }
        //checks for a vertical win
        for (int j = 0; j <= cols; j++) {
            for (; rows >= 0; rows--) {
                if (array[rows][j] == chipType) {
                    countCol++;
                }
                if (countCol == 4) {
                    return true;
                }
            }
            countCol = 0;
            rows = array.length - 1;

        }

        return false;
    }
}
