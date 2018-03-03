package com.donghee.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StringBuffer sb = new StringBuffer();

    private int[] numberIDs = {
            R.id.button_0,
            R.id.button_1,
            R.id.button_2,
            R.id.button_3,
            R.id.button_4,
            R.id.button_5,
            R.id.button_6,
            R.id.button_7,
            R.id.button_8,
            R.id.button_9
    };

    private int[] operatorIDs = {
            R.id.button_add,
            R.id.button_subtract,
            R.id.button_multi,
            R.id.button_division
    };

    private long firstNumber;
    private long secondNumber;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4_4);

        final TextView textViewResult = findViewById(R.id.text_view_result);

        for (int i = 0; i < numberIDs.length; ++i) {
            final int number = i;
            Button button = findViewById(numberIDs[number]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sb.append(number);
                    textViewResult.setText(sb.toString());
                }
            });
        }

        setOperatorButton((Button) findViewById(operatorIDs[0]), '+');
        setOperatorButton((Button) findViewById(operatorIDs[1]), '-');
        setOperatorButton((Button) findViewById(operatorIDs[2]), '*');
        setOperatorButton((Button) findViewById(operatorIDs[3]), '/');

        Button button = findViewById(R.id.button_equal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondNumber = Long.valueOf(sb.toString());
                sb.setLength(0);

                textViewResult.setText(String.valueOf(calculate()));
            }
        });
    }

    private void setOperatorButton(Button button, final char operator) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.operator = operator;

                setFirstStep();
            }
        });
    }

    private void setFirstStep() {
        firstNumber = Long.valueOf(sb.toString());
        sb.setLength(0);
    }

    private long calculate() {
        long result = 0;
        switch (operator) {
            case '+':
                result = firstNumber + secondNumber;
                break;
            case '-':
                result = firstNumber - secondNumber;
                break;
            case '/':
                result = firstNumber / secondNumber;
                break;
            case '*':
                result = firstNumber * secondNumber;
                break;
        }

        return result;
    }
}
