import java.util.Scanner;

// By Keyshawn Storey & Miguel Caputo
public class SciCalculator {
    private static double RESULT = 0.0;
    private static int menuInput = 1;
    private static Scanner scnr = new Scanner(System.in);

    private static void menuDisplay() {  // created a class for displaying menu
        System.out.print("Current Result: " + RESULT + "\n" +
                "\nCalculator Menu\n" +
                "---------------\n" +
                "0. Exit Program\n" +
                "1. Addition\n" +
                "2. Subtraction\n" +
                "3. Multiplication\n" +
                "4. Division\n" +
                "5. Exponentiation\n" +
                "6. Logarithm\n" +
                "7. Display Average\n" +
                "\nEnter Menu Selection: ");
        menuInput = scnr.nextInt();
    }

    public static void main(String[] args) {
        int  numCalc = 0;
        double firstInput, secondInput, averageCalc = 0, sumOfCalc = 0;
        menuDisplay();
        while (menuInput != 0) { //  When 0 is pressed, it exits the loop
            switch (menuInput) {
                case 0:
                    break;
                case 1:
                    System.out.print("Enter first operand: "); // Runs addition
                    firstInput = scnr.nextDouble();
                    System.out.print("Enter second operand: ");
                    secondInput = scnr.nextDouble();
                    RESULT = firstInput + secondInput;
                    sumOfCalc = sumOfCalc + RESULT;
                    numCalc++;
                    menuDisplay();
                    break;
                case 2:
                    System.out.print("Enter first operand: ");  // Runs subtraction
                    firstInput = scnr.nextDouble();
                    System.out.print("Enter second operand: ");
                    secondInput = scnr.nextDouble();
                    RESULT = firstInput - secondInput;
                    sumOfCalc = sumOfCalc + RESULT;
                    numCalc++;
                    menuDisplay();
                    break;
                case 3:
                    System.out.print("Enter first operand: ");  //  Runs multiplication
                    firstInput = scnr.nextDouble();
                    System.out.print("Enter second operand: ");
                    secondInput = scnr.nextDouble();
                    RESULT = firstInput * secondInput;
                    sumOfCalc = sumOfCalc + RESULT;
                    numCalc++;
                    menuDisplay();
                    break;
                case 4:
                    System.out.print("Enter first operand: ");  // Runs division
                    firstInput = scnr.nextDouble();
                    System.out.print("Enter second operand: ");
                    secondInput = scnr.nextDouble();
                    RESULT = firstInput / secondInput;
                    sumOfCalc = sumOfCalc + RESULT;
                    numCalc++;
                    menuDisplay();
                    break;
                case 5:
                    System.out.print("Enter first operand: ");  // Runs exponential
                    firstInput = scnr.nextDouble();
                    System.out.print("Enter second operand: ");
                    secondInput = scnr.nextDouble();
                    RESULT = Math.pow(firstInput, secondInput);
                    sumOfCalc = sumOfCalc + RESULT;
                    numCalc++;
                    menuDisplay();
                    break;
                case 6:
                    System.out.print("Enter first operand: "); //  Runs logarithmic
                    firstInput = scnr.nextDouble();
                    System.out.print("Enter second operand: ");
                    secondInput = scnr.nextDouble();
                    RESULT = (Math.log(secondInput)) / (Math.log(firstInput));
                    sumOfCalc = sumOfCalc + RESULT;
                    numCalc++;
                    menuDisplay();
                    break;
                case 7:
                    if (numCalc == 0) {
                        System.out.println("\nError: No calculations yet to average!\n");  //  Pulls out calculation stats
                        System.out.print("Enter Menu Selection: ");
                        menuInput = scnr.nextInt();
                    } else {
                        averageCalc = sumOfCalc / numCalc;
                        System.out.println("\nSum of calculations: " + sumOfCalc);
                        System.out.println("Number of calculations: " + numCalc);
                        System.out.println("Average of calculations: " + String.format("%.2f", averageCalc));
                        System.out.print("\nEnter Menu Selection: ");
                        menuInput = scnr.nextInt();
                    }
                    break;
                default:
                    System.out.print("\nError: Invalid selection!\n");
                    System.out.print("Enter Menu Selection: ");
                    menuInput = scnr.nextInt();
                    break;

            }
        }
        System.out.print("\nThanks for using this calculator. Goodbye!");
    }
}
