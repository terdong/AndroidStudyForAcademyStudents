package com.donghee.android.part5_15_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void onClick(View view){
        Intent intent = getIntent();
        intent.putExtra("result", "Hello SBS");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClickGoMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickGoOneActivity(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
