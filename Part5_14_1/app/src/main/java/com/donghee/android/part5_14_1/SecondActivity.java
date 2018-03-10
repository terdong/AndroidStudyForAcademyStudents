package com.donghee.android.part5_14_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textview = findViewById(R.id.textView3);

        Intent intent = getIntent();
        String message= intent.getStringExtra("message");
        textview.setText(message);

    }

    public void onClickClose(View view){
        EditText editText = findViewById(R.id.editText2);

        Intent intent = getIntent();
        intent.putExtra("message1", editText.getText().toString());
        setResult(RESULT_OK, intent);

        finish();
    }

    public void onClickThirdActivity(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}
