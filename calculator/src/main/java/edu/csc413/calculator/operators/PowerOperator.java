package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

//Power subclass of Operator
public class PowerOperator extends Operator {
    @Override
    public int priority() {
        //return given priority
        return 3;
    }

    //pwrOperator function used in execute function to calculate the power of op1, op2
    private int pwrOperator (int a, int b) {
        int pwrOutcome = a;
        int calc;
        calc = 2;
        do {
            pwrOutcome = pwrOutcome * a;
            calc = calc + 1;
        }
        while (calc <= b);
        return pwrOutcome;
    }

    @Override
    public Operand execute (Operand op1, Operand op2) {
        //execute power operation using above function
        Operand solution = new Operand(pwrOperator(op1.getValue(), op2.getValue()));
        return solution;
    }
}