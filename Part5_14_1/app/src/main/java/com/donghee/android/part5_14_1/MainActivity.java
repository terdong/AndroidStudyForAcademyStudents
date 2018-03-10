package com.donghee.android.part5_14_1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SECOND_ACTIVITY_CODE = 99;
    private static final int THIRD_ACTIVITY_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSecondActivity(View view){

        Intent intent = new Intent(this, SecondActivity.class);

        EditText editText = findViewById(R.id.editText);

        String message = editText.getText().toString();

        //Log.T("Part5_14_1", )

        intent.putExtra("message", message);
        startActivityForResult(intent, 99);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SECOND_ACTIVITY_CODE){
            if(resultCode == RESULT_OK){

                TextView textView = findViewById(R.id.textView);

                String message =  data.getStringExtra("message1");
                textView.setText(message);

            }
        }else if(requestCode == THIRD_ACTIVITY_CODE){
        }
    }

    public void onClickImplicitThirdActivity(View view){
        Intent intent = new Intent();
        intent.setAction("com.sbsacademy.ThirdView");
        startActivity(intent);
    }

    public void onClickOpenBrowser(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
        startActivity(intent);
    }
}
