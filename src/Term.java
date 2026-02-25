/*
 * Term.java - This class acts as the format for the Term objects that get placed into the Polynomial array. It automatically formats the exponent and coefficient for each term before storing it in the object as a string to make it easier to format in the polynomial.
 *
 * @author Noah Shew
 * @version v1.0
 */

public class Term {
    //CONSTANT VARIABLES
    public static final int DEFAULT_COEFFICIENT = 1;
    public static final int DEFAULT_EXPONENT = 1;

    //INSTANCE VARIABLES
    private int coefficient;
    private int exponent;

    //CONSTRUCTORS
    //Default constructor
    /* Term - Acts as the defualt constructor for the term class, so if the main constructor fails in any way it provides stand-in values.
    *
     */
    public Term(){
        this.coefficient = DEFAULT_COEFFICIENT;
        this.exponent = DEFAULT_EXPONENT;
    }

    //Full constructor
    /* Term - This is the full constructor, which acts as the main template for all Term objects when they are put into the Polynomial arraylist. It helps with storing the coefficient and exponent data in one object for each individual term in the arraylist.
    *
    * @param coefficient - This variable represents the int that has been provided as the leading coefficient variable attached to the x term
    * @param exponent - This variable represents the int that has been provided as the exponent variable attached to the x term
     */
    public Term(int coefficient, int exponent){
        this.setAll(coefficient, exponent);
    }

    //Copy constructor
    /* Term - This is the copy constructor, which copies the original term object which is provided and copies it to the new term object
    *
    * @param original - The original Term object being copied from.
     */
    public Term(Term original){
        if (original == null) {
            throw new IllegalArgumentException("Term string provided is empty. Cannot be read."); //throws an exception to prevent a crash when invalid data is provided
        } else {
            this.coefficient = original.getCoefficient(); //or original.coefficient?
            this.exponent = original.getExponent(); //or original.exponent?
        }
    }

    //String constructor
    /* Term - this constructor is a constructor that takes in a string and then disassembles that string using substrings to then assign values to the coefficient and exponent variables
    *
    * @param term - is the term value provided as a string that the constructor will then take and convert to a Term object
     */
    public Term(String term){
        if (term == null || term.isEmpty()){
            throw new IllegalArgumentException("Term string provided is empty. Cannot be read."); //throws an exception to prevent a crash and identify the source when invalid data is provided
        }

        int sign = 0;
        if (term.charAt(0) == '-'){
            sign = -1; //negative
        } else {
            sign = 1; //positive
        }

        String absoluteTerm = term.substring(1); //removes sign

        if (term.contains("x") == false){
            this.coefficient = (sign) * (Integer.parseInt(absoluteTerm));
            this.exponent = 0;
        } else {
            int xLocation;
            xLocation = absoluteTerm.indexOf("x"); //uses absoluteTerm because it removes the sign making the index easier to identify

            if (xLocation == 0){ //coefficients
                this.coefficient = sign;
            } else {
                String tempCoefficient = absoluteTerm.substring(0, xLocation);
                this.coefficient = (sign * Integer.parseInt(tempCoefficient));
            }

            if(absoluteTerm.contains("^") == false){ //exponents
                this.exponent = 1;
            } else {
                String tempExponent = absoluteTerm.substring(xLocation + 2); // moves over 2 index locations from the x to land on the exponent's first number and then continues to the end to get the exponent, x^/[exponent]
                this.exponent = Integer.parseInt(tempExponent);
            }
        }
    }

    //SETTERS
    /* setAll - This method calls on the setCoefficient and setExponent methods to act as a cleaner and more concise way of setting the values for the Term class
    *
    * @param coefficient - This variable represents the int that has been provided as the leading coefficient variable attached to the x term
    * @param exponent - This variable represents the int that has been provided as the exponent variable attached to the x term
    * @return true - this method will return true if the data provided is valid for BOTH set methods and it was able to copy the data onto the object
    * @return false - this method will return false if the data provided is not valid, such as being zero
     */
    public boolean setAll(int coefficient, int exponent){
        return this.setCoefficient(coefficient) && this.setExponent(exponent);
    }

    /* setCoefficient - This method sets the coefficient variable for the Term object, and also makes sure to check that the integer being provided isn't 0, as that is the only case where the whole term would not exist because it would be 0
    *
    * @param coefficient - This variable represents the int that has been provided as the leading coefficient variable attached to the x term
    * @return true - this method will return true if the data provided is valid for BOTH set methods and it was able to copy the data onto the object
    * @return false - this method will return false if the data provided is not valid, such as being zero
     */
    public boolean setCoefficient(int coefficient){
        this.coefficient = coefficient;
        return true; //indicates that the variable was valid and has been set
    }

    /* setExponent - This method sets the exponent variable, and returns true if successful.
    *
    * @param exponent - This variable represents the int that has been provided as the exponent variable attached to the x term
    * @return false - this method will return false if the data provided is not valid, such as being zero
     */
    public boolean setExponent(int exponent){
        this.exponent = exponent;
        return true;
    }

    //GETTERS
    /* getExponent - Retrieves the exponent value from a specific term object.
    *
    * @return - returns the exponent value of the object.
     */
    public int getExponent(){
        return this.exponent;
    }

    /* getCoefficient - Retrieves the coefficient value from a specific term object.
     *
     * @return - returns the coefficient value of the object.
     */
    public int getCoefficient(){
        return this.coefficient;
    }

    //EXTRA METHODS
    /* toString - As the name implies, this method takes all of the data from the object it is being called with and then formats it into a string so that it is ready to easily be printed.
     *
     * @return String - this method will return a fully formated string, which makes it very helpful for using to print to the console from the main method.
     */

    public String toString(){ //returns the term in the proper intended format (ax^n)
        if (coefficient == 0){
            return String.format("");
        }

        String sign = "";
        if (coefficient > 0){
            sign = "+";
        } else {
            sign = "-";
        }

        int absoluteCoeff;
        absoluteCoeff = Math.abs(coefficient); //uses the absolute value function of Math to get just the number value, so there is no double negative signs. A different way would be trying to parse it into a string and then removing the negative sign before making it an int again and it would just be too messy

        if (exponent == 0){
            return String.format("%s%d", sign, absoluteCoeff);
        } else if (exponent == 1){
            if (absoluteCoeff == 1){
                return String.format("%sx", sign);
            } else {
            return String.format("%s%dx", sign, absoluteCoeff);
            }
        } else if (absoluteCoeff == 1){
            return String.format("%sx^%d", sign, exponent);
        }
            return String.format("%s%dx^%d", sign, absoluteCoeff, exponent); //returns "(+/-)ax^n", a=coeff, n=exp
    }

    /* equals - This method compares the exponent values of two Term objects, and returns false if they are not the same value, and true if they are.
    *
    * @param object - represents the Term object that is being compared with the other term. term1 = 'this', term2 = 'object'/'otherTerm'
    * @return false - checks to see if the object provided is a Term object, and also creates the otherTerm Term object to then be compared to the this. returns as false if not
    * @return false - the exponent values of the two Term objects did not match
    * @return true - the exponent values of the two Term objects did match
     */
    public boolean equals(Object object){
        if (!(object instanceof Term otherTerm)){
            return false;
        }
        return this.coefficient == otherTerm.coefficient && this.exponent == otherTerm.exponent; //true if they are the same coefficient AND exponent, false if not
    }

    /* compareTo - this method helps to sort terms in order of their exponent values.
    *
    * @return 1 - this means that the exponent of the term that is selected is bigger than the OTHER exponent value.
    * @return 0 - this means that the exponent of both terms are equal to eachother.
    * @return -1 - this means that the exponent of the term that is selected is smaller than the OTHER exponent value.
     */
    public int compareTo(Term other){ //helps to sort terms in descending order of exponents
        if(this.exponent > other.exponent){
            return 1; //1 is positive and indicates that the exponent is bigger than the other exponent
        } else if (this.exponent < other.exponent){
            return -1; //indicates the exponent is smaller than the other exponent
        } else {
            return 0; //indicates that the two exponents are equal
        }
    }

    /* clone - This method creates a clone/copy of the original term it is being called from. Creates a new 'cloneTerm' to avoid a shallow copy error in the TermTest
    *
    * @return cloneTerm - returns a new Term object that is a copy of the original
     */
    public Object clone(){
        Term cloneTerm = new Term(this.coefficient, this.exponent);
        return cloneTerm;
    }
}
