package com.donghee.android.part5_15_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SingleTopActivity extends AppCompatActivity {

    private static final String TAG = "SingleTopActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_top);
        Log.d(TAG, "onCreate called");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent called");
    }

    public void OnClickSingleTopActivity(View view){
        Log.d(TAG, "OnClickSingleTopActivity called");
        startActivity(new Intent(this, SingleTopActivity.class));
    }
}
