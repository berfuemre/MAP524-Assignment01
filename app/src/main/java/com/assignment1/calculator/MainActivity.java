/*
Berfu Emre - 170459226
MAP 524-Android Assignment 01
May 23, 2026
 */
package com.assignment1.calculator;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private TextView historyTextView;
    private Button modeButton;

    private Calculator calculator;
    private String expression = "";
    private boolean isAdvanced = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

        resultTextView = findViewById(R.id.resultTextView);
        historyTextView = findViewById(R.id.historyTextView);
        modeButton = findViewById(R.id.buttonMode);

        setNumberButtons();
        setOperatorButtons();
        setEqualButton();
        setClearButton();
        setModeButton();
    }

    private void setNumberButtons() {
        int[] numberButtons = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        for (int id : numberButtons) {
            Button button = findViewById(id);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = (Button) view;
                    String number = clickedButton.getText().toString();

                    calculator.push(number);
                    expression = expression + number;

                    resultTextView.setText(expression);
                }
            });
        }
    }

    private void setOperatorButtons() {
        int[] operatorButtons = {
                R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide
        };

        for (int id : operatorButtons) {
            Button button = findViewById(id);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = (Button) view;
                    String operator = clickedButton.getText().toString();

                    calculator.push(operator);
                    expression = expression + " " + operator + " ";

                    resultTextView.setText(expression);
                }
            });
        }
    }

    private void setEqualButton() {
        Button equalButton = findViewById(R.id.buttonEquals);

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAnswer();
            }
        });
    }

    private void calculateAnswer() {
        try {
            int answer = calculator.calculate();
            String fullExpression = expression + " = " + answer;

            resultTextView.setText(String.valueOf(answer));

            if (isAdvanced) {
                String oldHistory = historyTextView.getText().toString();
                historyTextView.setText(oldHistory + "\n" + fullExpression);
            }

            calculator.clear();
            expression = "";

        } catch (Exception e) {
            resultTextView.setText("Error");
            calculator.clear();
            expression = "";
        }
    }

    private void setClearButton() {
        Button clearButton = findViewById(R.id.buttonClear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.clear();
                expression = "";
                resultTextView.setText(R.string.result_default);
            }
        });
    }

    private void setModeButton() {
        modeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAdvanced = !isAdvanced;

                if (isAdvanced) {
                    modeButton.setText(R.string.standard);
                    historyTextView.setVisibility(View.VISIBLE);
                } else {
                    modeButton.setText(R.string.advance);
                    historyTextView.setVisibility(View.GONE);
                }
            }
        });
    }
}