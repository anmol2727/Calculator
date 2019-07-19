package edu.csc413.calculator.evaluator;
/**
 * Operand class used to represent an operand
 * in a valid mathmetical expression.
 */
public class Operand {
    /**
    * construct operand from string token.
    */
    private int value;

    public Operand( String token ) {
        //sets the value of the parameter to the parseInt function so that it can analyze a string and return an int
        this.value = Integer.parseInt(token);
    }

    /**
     * construct operand from integer
     */
    public Operand( int value ) {
        //sets the value of the parameter to the same variable in order to construct the operand
        this.value = value;
    }

    /**
     * return value of opernad
     */
    public int getValue() {
        return value;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check( String token ) {
        //try-catch block to throw an exception when the program tries to convert string to int
        try {
            Integer.parseInt(token);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}