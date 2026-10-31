package com.calcmaster.pro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class BasicCalculatorActivity extends Activity {

    private TextView display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean newInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);

        display = findViewById(R.id.display);

        int[] numberIds = {
            R.id.zero, R.id.one, R.id.two, R.id.three,
            R.id.four, R.id.five, R.id.six,
            R.id.seven, R.id.eight, R.id.nine
        };

        for (int id : numberIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v ->
                enterNumber(button.getText().toString())
            );
        }

        findViewById(R.id.add).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.subtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.multiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.divide).setOnClickListener(v -> setOperator("/"));

        findViewById(R.id.decimal).setOnClickListener(v -> decimal());
        findViewById(R.id.equals).setOnClickListener(v -> calculate());
        findViewById(R.id.clear).setOnClickListener(v -> clear());
        findViewById(R.id.delete).setOnClickListener(v -> delete());
        findViewById(R.id.percent).setOnClickListener(v -> percentage());
    }

    private void enterNumber(String number) {
        String current = display.getText().toString();

        if (newInput || current.equals("Error")) {
            display.setText(number);
            newInput = false;
        } else if (current.length() < 15) {
            display.append(number);
        }
    }

    private void setOperator(String op) {
        try {
            firstNumber = Double.parseDouble(display.getText().toString());
            operator = op;
            newInput = true;
        } catch (Exception e) {
            display.setText("Error");
        }
    }

    private void calculate() {
        if (operator.isEmpty() || newInput) return;

        try {
            double secondNumber =
                Double.parseDouble(display.getText().toString());

            double result;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        display.setText("Cannot divide by zero");
                        operator = "";
                        newInput = true;
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
                default:
                    return;
            }

            display.setText(format(result));
            operator = "";
            newInput = true;

        } catch (Exception e) {
            display.setText("Error");
            operator = "";
        }
    }

    private void decimal() {
        String current = display.getText().toString();

        if (newInput || current.equals("Error")) {
            display.setText("0.");
            newInput = false;
        } else if (!current.contains(".")) {
            display.append(".");
        }
    }

    private void percentage() {
        try {
            double value = Double.parseDouble(
                display.getText().toString()
            );

            display.setText(format(value / 100));
            newInput = true;

        } catch (Exception e) {
            display.setText("Error");
        }
    }

    private void delete() {
        String current = display.getText().toString();

        if (current.equals("Error") || current.length() <= 1) {
            display.setText("0");
        } else {
            display.setText(current.substring(0, current.length() - 1));
        }

        newInput = false;
    }

    private void clear() {
        display.setText("0");
        firstNumber = 0;
        operator = "";
        newInput = true;
    }

    private String format(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "Error";
        }

        return new DecimalFormat("0.##########").format(value);
    }
}