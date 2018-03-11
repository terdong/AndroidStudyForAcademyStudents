package com.donghee.android.part5_15_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = findViewById(R.id.textView3);
        textView.setText(String.format("One Activity count = %d", count++));
    }

    public void onClickMain2Activity(View view){
        startActivity(new Intent(this, Main2Activity.class));
    }

    public void onClickTwoActivity(View view){
        startActivity(new Intent("com.sbsacademy.VIEW"));
    }

    public void onClickSingleInstanceActivity(View view){
        startActivity(new Intent("com.sbsacademy.SingleInstanceVIEW"));
    }

    public void onClickClearTop(View view){
        Intent intent =new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickForwardResult(View view){
        Intent intent =new Intent(this, Main3Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
        finish();
    }
}
