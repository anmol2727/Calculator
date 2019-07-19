package edu.csc413.calculator.operators;



import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    //HashMap to contain key, value as String, Operator declared as operators
    private static HashMap<String, Operator> operators;

    static {
        //operators is assigned to new HashMap in order to access operators as values
        operators = new HashMap<>();
        //storing operators so that we are able to check if a token is a valid operator
        operators.put( "+", new AddOperator() );
        operators.put( "-", new SubtractOperator() );
        operators.put( "*", new MultiplyOperator() );
        operators.put( "/", new DivideOperator() );
        operators.put( "^", new PowerOperator() );
        operators.put( "(", new OpenParenthesisOperator() );
        operators.put( ")", new CloseParenthesisOperator() );
    }


    public abstract int priority();
    public abstract Operand execute(Operand op1, Operand op2 );


    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     */
    public static boolean check( String token ) {
        //conditional statement that returns if a defined token is an operator
        if (operators.containsKey(token)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * An interface/public method should be created in Operator to allow the Evaluator
     *  (or other software components in our system) to look up Operators by token.
     */
    public static Operator getOperator(String token) {
        //return operators stored in operators by token
        return operators.get(token);
    }
}