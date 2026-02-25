
/*
 * Polynomial.java - This class acts as the storage for all of the Term objects in a specific polynomial order. It automatically adds like terms and formats them in order of exponent values when storing to its ArrayLists.
 * Has been updated to meet the test requirements for Homework 3.
 *
 * FileInputStream - used for reading from a text file to read the contents and then print to the console
 * FileOutputSteam - used to write text from the code into a text file
 * IOException - Helps with detecting and preventing big errors by catching if there is an IOException such as file not being able to be located or the code being unable to read the file
 * PrintWriter - Used to write to the text file alongside the FileOutputStream
 * ArrayList - Used to create and store the Term objects in a modular array that dynamically changes size.
 * Scanner - Used to read the contents of a text file when importing its data.
 *
 * @author Noah Shew
 * @version v2.0
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Polynomial {
    //INSTANCE VARIABLES
    private ArrayList<Term> terms;

    //CONSTRUCTORS
    //Full constructor?
    /* Polynomial - Acts as the template for each polynomial object/ArrayList. Creates an arraylist to store all of the Term values that get added to it.
     */
    public Polynomial(){
         terms = new ArrayList<>();
    }

    //Copy constructor
    /* Polynomial - This is the copy constructor, and creates a deep copy required by (PolynomialTest) by making a new arraylist in this polynomial to then copy all of the 'original' polynomial's values to. It loops through the whole original arraylist to manually transfer over each term object.
    *
    * @param original - this is the original Polynomial that is being copied from for the new polynomial. It acts as the template that the new polynomial is copying from.
     */
    public Polynomial(Polynomial original){
        this.terms = new ArrayList<>();
        for (int i = 0; i < original.terms.size(); i++){
            Term temp = original.terms.get(i); //gets the original polynomial's terms and assigns them to a temp value
            this.terms.add(new Term(temp.getCoefficient(), temp.getExponent())); //uses the temp variable to assign the original terms values to the new polynomial's terms
        }
    }

    //GETTERS
    /* getNumTerms - Returns the number of terms that is in the polynomial arraylist. Basically a modification of .size() for the polynomial class
    *
    * @return - returns the size of the arraylist for the polynomial class
     */
    public int getNumTerms(){
        return terms.size(); //returns the length of the terms arraylist
    }

    /* getTerm - Returns the specific term in the arraylist that is at the specified index provided
    *
    * @param index - this is an int value that tells the getter where to look for the desired term value to be returned
    * @return - returns the term value at the requested index
     */
    public Term getTerm(int index){
        return terms.get(index);
    }

    //METHODS
    /* addTerm - Acts as the main way to add the Term objects to an arraylist. Takes in the coefficient and exponent values and then automatically formats and adds them to the arraylist of your choosing. Will also automatically add or sort term objects based on exponent value.
    *
    * @param newTerm - Adjusted to take term objects, this represents the provided term object to the method. It uses the getters in the Term class to then create internal variables for the coefficient and exponent of this term object
    * @return - acts as a 'break' of sorts to end the method before continuing on to the 'terms.add' thing below so it doesn't record the same thing twice.
     */
    public void addTerm(Term newTerm){ //adds new term and combines like terms
        int coefficient, exponent;
        coefficient = newTerm.getCoefficient();
        exponent = newTerm.getExponent(); //added these to allow addTerm to take an actual term value and not predetermined coefficient and exponent values

        for(int i = 0; i < terms.size(); i++){
            Term term = terms.get(i);

            if (term.getExponent() == exponent){ //compares the preexisting exponent in the arraylist object at the specific index, and compares it with the exponent of the term you are adding
                int newCoefficient = term.getCoefficient() + coefficient; //adds the current coefficient in the index to the new coefficient that is being added to the list

                if (newCoefficient == 0){
                    terms.remove(i); //removes term from the arraylist that is going to now be 0, as it is an invalid amount. acts as a sort of error check that I did not have before
                } else {
                    term.setCoefficient(newCoefficient); //goes to the current term object at index and sets the new coefficient to be the sum of the two coefficients
                }
                return; //prevents the method from continuing to the terms.add beneath and adding a copy of a term when it was actually added instead
            }
        }
        //if the check for the same exponent doesn't add it to the list, then it will add the new term to the end of the list
        terms.add(new Term(coefficient, exponent));

        //SORTING
        for (int i = 0; i < terms.size(); i++){

            for (int j = 0; j < terms.size() - 1; j++){
                Term term = terms.get(j);
                Term term2 = terms.get(j + 1);

                if (term.compareTo(term2) == -1){
                    terms.set(j, term2);
                    terms.set(j+1,term);
                }
            }
        }
    }

    /* toString - As the name implies, this method takes all of the data from the object it is being called with and then formats it into a string so that it is ready to easily be printed. It also checks to make sure that it is formatted properly based on the coefficient's values, as well as formating the first term in the polynomial properly
     *
     * @return "0" - If the arraylist provided is empty, the toString will just provide a 0 to indicate the numerical value of the polynomial. empty polynomial = 0
     * @return String whole - this method will return a fully formated string with the entire polynomial already formatted.
     */
    public String toString(){//returns the whole polynomial list as one complete string
        String whole = "";

        if (terms.isEmpty()){ //isEmpty() replaces/is another way of saying 'terms.size() == 0'
            return "0";
        }

        for (int i = 0; i < terms.size(); i++){
            Term term = terms.get(i);

            String temp;
            temp = term.toString(); //converts each term to a string for easier editing using the Term's own toString
            String temp2 = temp.substring(1); //removes the negative AND positive signs for the term to print into the string with a proper format. prevents "... - -#" situations. changed to cover both because of changes made to Term because of TermTest

            if (i==0 && term.getCoefficient() < 0){
                whole = whole + "-" + temp2;
            } else if (i == 0){ //HELPS TO FORMAT THE POLYNOMIAL
                whole = whole + temp2;
            } else if (term.getCoefficient() > 0){
                whole = whole + " + " + temp2;
            } else if  (term.getCoefficient() < 0){
                whole = whole + " - " + temp2;
            }
        }

        return whole;
    }

    /* store - this method opens up an outputstream to write to a text file, and automatically formats the coefficient and exponent in the text file when writing to it.
    *
    * @param filename - this is the filename that the file will be named and created after.
     */
    public void store(String filename) throws IOException{//used for saving to file
        PrintWriter outputStream = new PrintWriter(new FileOutputStream(filename));

        for(int i = 0; i < terms.size(); i++){
            Term term = terms.get(i);
            int coefficient = term.getCoefficient();
            int exponent = term.getExponent();
            outputStream.printf("%d %d%n", coefficient, exponent);
        }
        outputStream.close();
    }

    /* load - this method opens up an inputsteam to input the data from a text file, and automatically reads and formats all of the integers within the text file to be in a proper polynomial format.
     *
     * @param filename - this is the filename that the file the code will search for and take from is named. it tells the method where to look
     */
    public void load(String filename) throws IOException{//used for loading from a file
        Scanner inputStream = new Scanner(new FileInputStream(filename));
        Polynomial polynomial = new Polynomial();
        int coefficient;
        int exponent;

        while (inputStream.hasNextInt() == true){
            coefficient = inputStream.nextInt();
            exponent = inputStream.nextInt();

            Term newTerm = new Term(coefficient, exponent);

            polynomial.addTerm(newTerm);
        }

        System.out.println("Loaded Q(x) = " + polynomial.toString());
        inputStream.close();
    }

    /* clear - this method loops through the arraylist when called to remove all the term objects inside of it. It clears the array.
     */
    public void clear(){
        int tempLoop = terms.size();
        for(int i = 0; i < tempLoop; i++){
            terms.remove(0);
        }
    }

    /* add - This method just provides a basic way of looping through an entire ArrayList to add one entire ArrayList to another. Uses a loop to call the addTerm() for the whole list.
    *
    * @param polynomial2 - Indicated by the PolynomialTest class in the add testers, this is the second polynomial that is being added to 'polynomial1' or the polynomial that is being referenced by the 'this.addTerm'
     */
    public Polynomial add(Polynomial polynomial2){
        for(int i = 0; i < polynomial2.getNumTerms(); i++){
            Term newTerm = polynomial2.getTerm(i);
            this.addTerm(new Term(newTerm.getCoefficient(), newTerm.getExponent()));
        }

        Polynomial newPoly = new Polynomial();
        newPoly = this;
        return newPoly;

    }
}
