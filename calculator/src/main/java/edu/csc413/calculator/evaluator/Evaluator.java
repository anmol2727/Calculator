package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.operators.Operator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    //add ( & ) as Delimiters
    private static final String DELIMITERS = "+-*^/()";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval( String expression ) {
        String token;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

        // initialize operator stack - necessary with operator priority schema
        // the priority of any operator in the operator stack other than
        // the usual mathematical operators - "+-*/" - should be less than the priority
        // of the usual operators



        while ( this.tokenizer.hasMoreTokens() ) {
            // filter out spaces
            if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
                // check if token is an operand
                if ( Operand.check( token )) {
                    operandStack.push( new Operand( token ));
                }
                else {
                    if ( ! Operator.check( token )) {
                        System.out.println( "*****invalid token******" );
                        throw new RuntimeException("*****invalid token******");
                    }
                    //conditional statement for when token is (
                    //Operator object is created and pushed to the operator stack (utdOP/up to date)
                    if (token.equals("(")) {
                        Operator utdOp = Operator.getOperator(token);
                        operatorStack.push(utdOp);
                        continue;
                    }
                    //conditional statement for when token is )
                    //operationHelperMethod() is called
                    if (token.equals(")")) {
                        //this method runs a do-while loop. Inside the loop,
                        //the declared attribute operandStack is popped twice for op1 and op2.
                        //this is accomplished using the execute method which assigns the value.
                        //the result is pushed onto the operandStack
                        //In the end the '(' operator is popped.
                        operationHelperMethod();
                        continue;
                    }


                    // TODO Operator is abstract - these two lines will need to be fixed:
                    // The Operator class should contain an instance of a HashMap,
                    // and values will be instances of the Operators.  See Operator class
                    // skeleton for an example.

                    //fix line so that newOperator is declared and calls for getOperator method
                    //this method is declared in the Operator file and allows Evaluator to
                    //look up Operators by token
                    Operator newOperator = Operator.getOperator(token);

                    //conditional statement for when the operatorStack is empty
                    if ((operatorStack.isEmpty())) {
                        //Operator object is created from the token using newOperator
                        //and pushed to the operator Stack
                        operatorStack.push(newOperator);
                    }
                    //otherwise
                    else {
                        while (operatorStack.peek().priority() >= newOperator.priority()) {
                            // note that when we eval the expression 1 - 2 we will
                            // push the 1 then the 2 and then do the subtraction operation
                            // This means that the first number to be popped is the
                            // second operand, not the first operand - see the following code
                            Operator oldOpr = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(oldOpr.execute(op1, op2));
                            //conditional statement for when he operatorStack is empty
                            if (operatorStack.isEmpty()) {
                                //break process
                                break;
                            }
                        }

                        operatorStack.push(newOperator);
                    }
                }
            }
        }

    
        // Control gets here when we've picked up all of the tokens; you must add
        // code to complete the evaluation - consider how the code given here
        // will evaluate the expression 1+2*3
        // When we have no more tokens to scan, the operand stack will contain 1 2
        // and the operator stack will have + * with 2 and * on the top;
        // In order to complete the evaluation we must empty the stacks (except
        // the init operator on the operator stack); that is, we should keep
        // evaluating the operator stack until it only contains the init operator;
        // Suggestion: create a method that takes an operator as argument and
        // then executes the while loop.

        //method called for when all the tokens are scanned. The do-while loop within
        //the method processes Operators until the OperatorStack is empty
        emptyOperatorStackHelperMethod();

        //once the above method is ran and completed, a new instance of Operand is
        //declared. It is assigned to the popping of the operandStack which gets
        //returned by getting the Value and ultimately completing the evaluation
        Operand complete = operandStack.pop();
        int solution = complete.getValue();

        return solution;
    }
    //helper method called for the ) token
    private void operationHelperMethod() {
        do {
            Operator prevOp = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(prevOp.execute(op1, op2));
        }
        while (!(operatorStack.peek().priority() <= 0));
        operatorStack.pop();
    }

    //helper method called to empty the Operator Stack
    private void emptyOperatorStackHelperMethod() {
        do {
            Operator presentOp = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(presentOp.execute(op1, op2));
        }
        while (!(operatorStack.isEmpty()));
    }
}