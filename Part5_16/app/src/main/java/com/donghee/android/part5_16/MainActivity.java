package com.donghee.android.part5_16;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tv.setText("안녕하세요?");

/*        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/

    }

    public void test(){
        Log.d("Part5_16","Hello Hello");
    }

    public void startThread(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; ++i) {
                    final int number = i;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = findViewById(R.id.textView1);
                            tv.setText(String.format("안녕하세요? count = %d", number));
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
