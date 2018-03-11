package com.donghee.android.part5_15_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SingleInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
    }

    public void onClick(View view){
        startActivity(new Intent(this,Main3Activity.class));
    }
}
