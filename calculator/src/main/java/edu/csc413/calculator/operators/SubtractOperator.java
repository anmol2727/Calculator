package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

//Subtract subclass of Operator
public class SubtractOperator extends Operator {
    @Override
    public int priority() {
        //return given priority
        return 1;
    }

    @Override
    public Operand execute (Operand op1, Operand op2) {
        //execute subtraction operation
        Operand solution = new Operand(op1.getValue() - op2.getValue());
        return solution;
    }
}
