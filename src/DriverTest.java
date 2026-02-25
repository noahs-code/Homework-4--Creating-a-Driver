/* DriverTest.java - This class acts as a JUnit test class for the Driver.java class. It contains some basic methods that test to ensure the functionality of the methods present in the Driver class.
 *
 *
 * @author Noah Shew
 * @version v1.0
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {

    private static final Term[] TEST_TERMS_ONE = {
            new Term(3,4),
            new Term(8,2),
            new Term(4,0),
            new Term(4,1)
    };

    private static final Term[] TEST_TERMS_TWO = {
            new Term(3, 3),
            new Term(6,1),
            new Term(4,3)
    };

    private static final Term[] TEST_TERMS_THREE = {
            new Term(-3, 2),
            new Term(-5,1),
            new Term(-1,3)
    };

    private static final Term[] TEST_TERMS_FOUR = {
            new Term(-2, 2),
            new Term(-1,0),
            new Term(-7,1)
    };

    private static final Term[] TEST_TERMS_FIVE = {
            new Term(1,3),
            new Term(2, 2),
            new Term(2,1),
            new Term(4,0)
    };

    private static final Term[] TEST_TERMS_SIX = {
            new Term(1,3),
            new Term(-3, 2),
            new Term(6,1),
            new Term(-4,0)
    };


    /* testAddPolynomials_SimpleCase - This method tests a basic addition of simple polynomials. Expects "8x^2" as a result
     */
    @Test
    public void testAddPolynomials_SimpleCase() {
        Polynomial p1 = new Polynomial();
        p1.addTerm(new Term(3, 2));

        Polynomial p2 = new Polynomial();
        p2.addTerm(new Term (5,2));


        Polynomial result = Driver.addPolynomials(p1, p2);
        String resultString = result.toString();

        assertEquals("8x^2", resultString);
    }

    /* testAddPolynomials_DifferentDegrees - This method tests a basic addition of polynomials with different degrees. Expects "3x^4 + 7x^3 + 8x^2 + 10x + 4" as a result
     */
    @Test
    public void testAddPolynomials_DifferentDegrees() {
        Polynomial p1 = new Polynomial();
        for(int i =0; i < TEST_TERMS_ONE.length; i++) {
            p1.addTerm(TEST_TERMS_ONE[i]);
        }
        Polynomial p2 = new Polynomial();
        for(int i =0; i < TEST_TERMS_TWO.length; i++) {
            p2.addTerm(TEST_TERMS_TWO[i]);
        }

        Polynomial result = Driver.addPolynomials(p1, p2);
        String resultString = result.toString();

        assertEquals("3x^4 + 7x^3 + 8x^2 + 10x + 4", resultString);
    }

    /* testAddPolynomials_EmptyPolynomials - This method tests the addition of polynomials with null or empty values. Expects "null" or "0" as a result
     */
    @Test
    public void testAddPolynomials_EmptyPolynomials() {
        Polynomial p1 = null; //null
        Polynomial p2 = new Polynomial(); //0

        Polynomial result = Driver.addPolynomials(p1, p2);

        assertNull(result);

        Polynomial p3 = new Polynomial(); // 0/empty
        Polynomial p4 = new Polynomial(); // 0/empty

        Polynomial result2 = Driver.addPolynomials(p3, p4);
        String resultString = result2.toString();

        assertEquals("0", resultString);
    }

    /* testAddPolynomials_NegativeCoefficients - This method tests the addition of polynomials with negative leading coefficients. Expects "-x^3 - 5x^2 - 12x - 1" as the result
     */
    @Test
    public void testAddPolynomials_NegativeCoefficients() {
        Polynomial p1 = new Polynomial();
        for(int i =0; i < TEST_TERMS_THREE.length; i++) {
            p1.addTerm(TEST_TERMS_THREE[i]);
        }
        Polynomial p2 = new Polynomial();
        for(int i =0; i < TEST_TERMS_FOUR.length; i++) {
            p2.addTerm(TEST_TERMS_FOUR[i]);
        }

        Polynomial result = Driver.addPolynomials(p1, p2);
        String resultString = result.toString();

        assertEquals("-x^3 - 5x^2 - 12x - 1", resultString);
    }

    /* testAddPolynomials_CombineLikeTerms - This method tests the addition of polynomials with like terms. Expects "2x^3 - x^2 + 8x" as a result
     */
    @Test
    public void testAddPolynomials_CombineLikeTerms() {
        Polynomial p1 = new Polynomial();
        for(int i =0; i < TEST_TERMS_FIVE.length; i++) {
            p1.addTerm(TEST_TERMS_FIVE[i]);
        }
        Polynomial p2 = new Polynomial();
        for(int i =0; i < TEST_TERMS_SIX.length; i++) {
            p2.addTerm(TEST_TERMS_SIX[i]);
        }

        Polynomial result = Driver.addPolynomials(p1, p2);
        String resultString = result.toString();

        assertEquals("2x^3 - x^2 + 8x", resultString);
    }
}