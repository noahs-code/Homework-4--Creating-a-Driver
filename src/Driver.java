/* Driver.java - This class acts as the driver and user input section of the program. It allows the user to add together polynomials through typing into the console.
 *
 * FileInputStream - used for reading from a text file to read the contents and then print to the console
 * Scanner - Used to read the contents of a text file when importing its data.
 * StringTokenizer - Used to separate strings into individual substrings based on certain char breakpoints. Extremely helpful with formatting polynomials from strings
 *
 * @author Noah Shew
 * @version v1.0
 */

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();
        Polynomial result = null;
        boolean running = true;

        while (running) {
            System.out.println("\n--- Polynomial Adder Menu ---");
            System.out.println("1. Enter first polynomial");
            System.out.println("2. Enter second polynomial");
            System.out.println("3. Add polynomials");
            System.out.println("4. Display result");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    poly1 = readPolynomial(scanner, "first");

                    break;
                case 2:
                    poly2 = readPolynomial(scanner, "second");

                    break;
                case 3:
                    result = addPolynomials(poly1, poly2);

                    break;
                case 4:
                    if (result != null){
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("No polynomials defined. Please make sure you enter both polynomials.");
                    }

                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-5.");
            }
        }
        scanner.close();
    }

    /* readPolynomial - reads the console input and takes it as a scanner input to then convert the string into a polynomial object.
     *
     * @param scanner - Used to bring the scanner input inside the method, carries over from main. Allows for this method to read console input.
     * @param label - Used by the switch cases to define which polynomial is being edited.
     * @return newPoly - returns the new polynomial value to assign the value to the "poly1" and "poly2" variables
     */
    public static Polynomial readPolynomial(Scanner scanner, String label) {
        scanner.nextLine(); //clearing buffer to read console input properly
        System.out.println("Please enter the  " + label + " polynomial.");

        String tempPoly = scanner.nextLine();
        tempPoly = tempPoly.replace(" ", ""); //Found on the String documentation page. replaces the specified char with the new second char that you define. here it is used to cut away the empty spaces, no matter the size
        tempPoly = tempPoly.replace("-", "+-"); //Also using to reformat the equation to make the splitting of the polynomial much easier with StringTokenizer, and to fit the Term string constructor's sign checks. negative terms need to be provided with a leading sign

        StringTokenizer stringTokenizer = new StringTokenizer(tempPoly, "+"); //using StringTokenizer to separate the polynomial more easily into individual strings. Mentioned that it might be helpful in one of the lecture videos. Supposedly outdated though and better to use String.split()

        Polynomial newPoly = new Polynomial(); //creating polynomial to return
        int stLength = stringTokenizer.countTokens(); //variable to allow it to remain constant size

        for (int i = 0; i < stLength; i++){
            String tempString = stringTokenizer.nextToken();
            Term tempTerm = new Term(tempString);
            newPoly.addTerm(tempTerm);
        }

        String print = newPoly.toString();
        System.out.println("Adding polynomial: " + print);

        return newPoly;
    }

    /* addPolynomials - This method accesses and streamlines the polynomial add method in the Polynomial class. It also error checks to make sure that the polynomials are not empty before being added together to prevent null or empty returns.
     *
     * @param p1 - The first polynomial which the second is being added to.
     * @param p2 - The second polynomial, which is being added to the first.
     * @return p1.add(p2) - Creates a fully new polynomial to return and be used within the switch statement.
     */
    public static Polynomial addPolynomials(Polynomial p1, Polynomial p2) {
        if (p1 == null || p2 == null){
            System.out.println("One of the polynomials has not been defined yet. Please make sure you enter values for both polynomials first.");
            return null;
        } else {
            return p1.add(p2);
        }
    }
}