package com.donghee.android.droppingwordgame;

import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DroppingWordGame";

    // 샘플 단어 리스트
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

    // 점수 기록용 변수
    private int score;

    // 문제용 단어 TextView 배열(나중에 List로 바꿀 예정)
    private TextView[] textViews;

    // 메인 Layout
    private ConstraintLayout layout;

    // ConstraintLayout 의 view들의 속성을 바꿀 때 사용하는 객체 변수
    private ConstraintSet set = new ConstraintSet();

//    private ArrayList<TextView> quizList;
//    private ArrayList<TextView> List;

    // 문제용 단어들의 위치값을 바꾸기 위해 사용된 핸들러
    private WordHandler handler;

    // 핸들러를 상속 받아 구현한 inner class
    private static class WordHandler extends Handler {
        private float[] textViewPosition = new float[3];
        private TextView[] textViews;
        private ConstraintLayout layout;
        private ConstraintSet set;

        private final WeakReference<MainActivity> mainActivityWeakReference;
        public WordHandler(MainActivity mainActivity){
            mainActivityWeakReference = new WeakReference(mainActivity);
            textViews = mainActivity.textViews;
            layout = mainActivity.layout;
            set = mainActivity.set;
            initialize();
        }

        // 문제용 단어들을 초기화 시키는 메소드
        public void initialize(){
            setWords();
            for (int i = 0; i < textViews.length; ++i) {
                textViewPosition[i] = 0;
                set.setVerticalBias(textViews[i].getId(), textViewPosition[i]);
            }
            set.applyTo(layout);
        }

        // 플레이어가 맞추거나 끝까지 떨어진 단어들을 다시 원래 위치로 이동 시키는 메소드
        public void resetTextView(int i){
            textViewPosition[i] = 0;
            set.setVerticalBias(textViews[i].getId(), textViewPosition[i]);
            set.applyTo(layout);
        }

        // 문제용 단어 TextView에 임의의 단어를 설정하는 메소드
        private void setWords(){
            MainActivity mainActivity = mainActivityWeakReference.get();
            String [] wordList = mainActivity.wordList;
            Random random = mainActivity.random;
            for (int i = 0; i < textViews.length; ++i) {
                textViews[i].setText(wordList[random.nextInt(wordList.length)]);
            }
        }

        // 이 메소드를 이용하여 단어의 위치 값을 조정함
        @Override
        public void handleMessage(Message msg) {
//                super.handleMessage(msg);

            TransitionManager.beginDelayedTransition(layout);

            for (int i = 0; i < textViews.length; ++i) {
                textViewPosition[i] += 0.1f;
                if (textViewPosition[i] <= 1) {
                    set.setVerticalBias(textViews[i].getId(), textViewPosition[i]);
                }else{

                }
                Log.d(TAG,String.format("textViewPosition[i] = %s", textViewPosition[i]));
            }

            set.applyTo(layout);

            sendEmptyMessageDelayed(0, 1000);
        }
    }

    // Activity가 실행 될때 최초로 호출 되는 메소드(main 메소드와 비슷함)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯 가지고 오기
        textViews = new TextView[3];
        textViews[0] = findViewById(R.id.textView);
        textViews[1] = findViewById(R.id.textView2);
        textViews[2] = findViewById(R.id.textView3);

        layout = findViewById(R.id.mainLayout);

        set.clone(layout);

        final Button buttonStart = findViewById(R.id.button);
        final Button buttonStop = findViewById(R.id.button2);
        EditText editText = findViewById(R.id.editText);
        final TextView textViewScore = findViewById(R.id.textViewScore);
        textViewScore.setText(String.valueOf(score));

        //위젯에 값 및 이벤트 설정하기
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 게임 시작 코드
                //Log.d(TAG, "on click buttona");
                handler.sendEmptyMessage(0);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 게임 시작 코드
                //Log.d(TAG, "on click buttona");
                handler.removeMessages(0);
                handler.initialize();
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String inputText = v.getText().toString();
                v.setText("");
                Log.d(TAG, String.format("inputText = %s. actionId = %d", inputText, actionId));

                for (int i = 0; i < textViews.length; ++i) {
                    if (inputText.equals(textViews[i].getText())) {
                        textViewScore.setText(String.valueOf(++score));

                        handler.resetTextView(i);
                    }
                }

                return false;
            }
        });

        handler = new WordHandler(this);
    }

    // Activity가 정지 될 때 호출되는 메소드
    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(0);
    }
}
