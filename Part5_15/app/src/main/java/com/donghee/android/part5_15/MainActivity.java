package com.donghee.android.part5_15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "Part5_15";

    private void printLog(String message){
        Log.d(TAG, message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printLog("1. onCreate is Called");

        TextView tv;

        char lastOperation = '-';
        int lastNumber = 3;

        int currentNumber = 333;

        int f1 = currentNumber / 100;
        int f2 = currentNumber / 10;
        int f3 = currentNumber / 1;

        currentNumber = (currentNumber - lastNumber) / 10;

        int result = 3 + 2;


        result -= 99;
        tv.setText(String.valueOf(result));


    }

    @Override
    protected void onStart() {
        super.onStart();

        printLog("2. onStart is Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        printLog("3. onResume is Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printLog("4. onStop is Called");
    }
}
