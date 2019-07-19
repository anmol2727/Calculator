package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

//Multiply subclass of Operator
public class MultiplyOperator extends Operator {
    @Override
    public int priority() {
        //return given priority
        return 2;
    }

    @Override
    public Operand execute (Operand op1, Operand op2) {
        //execute multiplication operation
        Operand solution = new Operand(op1.getValue() * op2.getValue());
        return solution;
    }
}
