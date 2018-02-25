package com.donghee.android.droppingwordgame;

import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DroppingWordGame";

    private String[] wordList = {
            "word",
            "apple",
            "banana",
            "dog",
            "cat",
            "elephant",
            "tiger",
            "lunch",
            "football",
            "tennis"
    };

    private Random random = new Random();

    private int score;

    ConstraintLayout layout;

    private Handler handler = new Handler(){
        float number = 0;
        @Override
        public void handleMessage(Message msg) {
//                super.handleMessage(msg);

            ConstraintSet set = new ConstraintSet();
            set.clone(layout);

            textViewPosition[0] += 0.1f;

            number += 0.1f;

            if(textViewPosition[0] <= 1 ) {
                set.setVerticalBias(R.id.textView, number);
            }

            set.applyTo(layout);

            this.sendEmptyMessageDelayed(0, 1000);
        }
    };

    private float [] textViewPosition = {0.1f, 0.1f, 0.1f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.mainLayout);
        // 위젯 가지고 오기
        final Button button = findViewById(R.id.button);
        EditText editText = findViewById(R.id.editText);
        final TextView[] textViews = {
                findViewById(R.id.textView),
                findViewById(R.id.textView2),
                findViewById(R.id.textView3),
        };
        final TextView textViewScore = findViewById(R.id.textViewScore);
        textViewScore.setText(String.valueOf(score));

        //위젯에 값 및 이벤트 설정하기
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 게임 시작 코드
                Log.d(TAG,"on click button");
                handler.sendEmptyMessage(0);
            }
        });

        for (int i = 0; i < textViews.length; ++i) {
            textViews[i].setText(wordList[random.nextInt(wordList.length)]);
        }

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String inputText = v.getText().toString();
                Log.d(TAG, String.format("inputText = %s. actionId = %d", inputText, actionId));

                for (int i = 0; i < textViews.length; ++i) {
                    if(inputText.equals(textViews[i].getText())){
                        textViewScore.setText(String.valueOf(++score));
                    }
                }

                return false;
            }
        });

    }
}
