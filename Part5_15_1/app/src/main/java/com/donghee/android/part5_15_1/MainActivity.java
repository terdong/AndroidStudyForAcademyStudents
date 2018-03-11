package com.donghee.android.part5_15_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMainLayout(View view){

        //Log.d("MainActivity", "clicked MainActivity");

      Intent intent =new Intent(this, Main2Activity.class);
        startActivity(intent);

    }

    public void onClickSingleTopActivity(View view){
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    public void onClickFlagNewDocument(View view){
        Intent intent =new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        startActivity(intent);
    }

    public void onClickFlagForwardResult(View view){
        Intent intent =new Intent(this, Main2Activity.class);
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, String.format("result = %s", data.getStringExtra("result")));

    }
}
