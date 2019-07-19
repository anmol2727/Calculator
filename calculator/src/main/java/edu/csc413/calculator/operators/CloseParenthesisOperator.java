package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

//CloseParenthesis subclass of Operator
public class CloseParenthesisOperator extends Operator {
    @Override
    public int priority() {
        //return lowest priority
        return 0;
    }

    @Override
    public Operand execute (Operand op1, Operand op2) {
        //return null
        return null;
    }
}
