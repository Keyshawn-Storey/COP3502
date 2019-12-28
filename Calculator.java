import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        double userFirstInput, userSecondInput, finishedCalculation;
        int userMenuInput;
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter first operand: ");
        userFirstInput = scnr.nextDouble(); //Asks user for first number.


        System.out.print("Enter second operand: ");
        userSecondInput = scnr.nextDouble(); //Asks user for second number.


        System.out.println("Calculator Menu \n" + "--------------- \n" + "1. Addition \n" + "2. Subtraction \n" + "3. Multiplication \n" + "4. Division \n");
        System.out.print("Which operation do you want to perform? ");
        userMenuInput = scnr.nextInt(); //Asks user for which operation.

        if (userMenuInput == 1) {                                           // Statement for menu selection
            finishedCalculation = userFirstInput + userSecondInput;
            System.out.print("The result of the operation is " + finishedCalculation + ". Goodbye!");
        } else if (userMenuInput == 2) {
            finishedCalculation = userFirstInput - userSecondInput;
            System.out.print("The result of the operation is " + finishedCalculation + ". Goodbye!");
        } else if (userMenuInput == 3) {
            finishedCalculation = userFirstInput * userSecondInput;
            System.out.print("The result of the operation is " + finishedCalculation + ". Goodbye!");
        } else if (userMenuInput == 4) {
            finishedCalculation = userFirstInput / userSecondInput;
            System.out.print("The result of the operation is " + finishedCalculation + ". Goodbye!");
        } else if (userMenuInput > 1 || userMenuInput < 4) {                // If/or statement if menu selection not in 1-4.
            System.out.print("Error: Invalid selection! Terminating program.");
        }
    }
}
