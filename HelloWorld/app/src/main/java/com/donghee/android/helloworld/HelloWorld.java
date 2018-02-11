package com.donghee.android.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class HelloWorld extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        Button buttonNameGenerator = (Button)findViewById(R.id.button_name_generator);

        buttonNameGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String [] names = {
                        "도연","동희","내강","민섭","승민","혜원","세영","지훈"
                };

                Random random = new Random();
                String result = names[random.nextInt(names.length)];

                //String str = "생성된 이름 = " + result;
                String str = String.format("생성된 이름 = %s", result);
                Log.d("HelloWorld", str);

                Toast.makeText(HelloWorld.this, str, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
