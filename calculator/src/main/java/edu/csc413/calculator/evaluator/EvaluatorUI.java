package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        // You need to fill in this fuction

        //do-while loop that acquires the buttons when they are used.
        boolean acquired;
        int a;
        a = -1;
        do {
            a = a + 1;
            if (!(arg0.getSource().equals(buttons[a]))) {
                acquired = false;
            }
            else {
                acquired = true;
            }
        }
        while (! acquired && a < 20);

        /*
         *conditional statement that makes sure = is not shown in the text field
         *this prevents the application from crashing due to an invalid token
         */
        if (a != 14 && a < 18) {
            txField.setText(txField.getText() + bText[a]);
        }

        //conditional statement that clears the entire text field when C is used
        if (arg0.getSource() == buttons[18]) {
            txField.setText("");
        }

        //conditional statement that clears an expression when CE is used
        if (arg0.getSource() == buttons[19]) {
            String presentText = txField.getText();
            //if the text field is already cleared, then CE will not do anything
            if (!presentText.isEmpty()) {
                txField.setText(presentText.substring(0, presentText.length() - 1));
            }
        }

        //conditional statement that trigger the evaluation of an expression when = is used
        if (arg0.getSource() == buttons[14]) {
            Evaluator analyzeExp = new Evaluator();
            String Exp = txField.getText();
            //if the entire text field is empty, then do not evaluate
            if (!Exp.isEmpty()) {
                txField.setText(String.valueOf(analyzeExp.eval(Exp)));
            }
        }
    }
}