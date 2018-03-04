package com.donghee.android.droppingwordgame;

import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

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
    // 최고 점수
    private int highScore;
    // 목숨
    private int life;

    // 메인 Layout
    private ConstraintLayout layout;

    // ConstraintLayout 의 view들의 속성을 바꿀 때 사용하는 객체 변수
    private ConstraintSet set = new ConstraintSet();

    // 목숨을 표시할 TextView
    private TextView textViewLife;
    // 최고 점수를 표시할 TextView
    private TextView textViewHighScore;

    // 문제용 TextView 관리용 셋
    private HashSet<TextView> hashSetQuiz = new HashSet<>();
    private Queue<TextView> queueSpare = new LinkedList<>();

    // 문제용 단어들의 위치값을 바꾸기 위해 사용된 핸들러
    private WordHandler handler;

    // 핸들러를 상속 받아 구현한 inner class
    private static class WordHandler extends Handler {
        private static final int DEFAULT_QUIZ_GENERATION_COUNT = 3;

        private HashMap<TextView, Float> hashMapTextViewPosition = new HashMap<>();
        private ConstraintLayout layout;
        private ConstraintSet set;
        private Random random;
        private HashSet<TextView> hashSetQuiz;
        private Queue<TextView> queueSpare;
        private String[] wordList;

        private boolean isPlay;

        private int quizGenerationCount = DEFAULT_QUIZ_GENERATION_COUNT;

        private final WeakReference<MainActivity> mainActivityWeakReference;

        public WordHandler(MainActivity mainActivity) {
            mainActivityWeakReference = new WeakReference(mainActivity);
            layout = mainActivity.layout;
            set = mainActivity.set;
            random = mainActivity.random;
            hashSetQuiz = mainActivity.hashSetQuiz;
            queueSpare = mainActivity.queueSpare;
            wordList = mainActivity.wordList;
        }

        // 게임 시작시 호출되는 메소드
        public void initialize() {
            if (!hashSetQuiz.isEmpty()) {
                for (TextView tv : hashSetQuiz) {
                    queueSpare.offer(tv);
                }
                hashSetQuiz.clear();
            }

            hashMapTextViewPosition.clear();

            for (TextView tv : queueSpare) {
                initializeTextView(tv);
                hashMapTextViewPosition.put(tv, new Float(0));
            }

            isPlay = true;
        }

        public void resetTextView(TextView tv) {

            initializeTextView(tv);
            hashMapTextViewPosition.put(tv, new Float(0));

            queueSpare.offer(tv);
        }

        // 문제용 TextView 셋팅
        public void initializeTextView(TextView tv) {

            tv.setText("");

            int id = tv.getId();
            set.setVerticalBias(id, 0);
            set.setHorizontalBias(id, random.nextFloat());
            set.applyTo(layout);
        }

        // 이 메소드를 이용하여 단어의 위치 값을 조정함
        @Override
        public void handleMessage(Message msg) {
//                super.handleMessage(msg);



            if (!queueSpare.isEmpty() && --quizGenerationCount <= 0) {
                quizGenerationCount = DEFAULT_QUIZ_GENERATION_COUNT;

                TextView tv = queueSpare.poll();
                tv.setText(wordList[random.nextInt(wordList.length)]);

                hashSetQuiz.add(tv);
            }

            if (!hashSetQuiz.isEmpty()) {
                TransitionManager.beginDelayedTransition(layout);
                for (TextView tv : hashSetQuiz) {
                    Float highPosition = hashMapTextViewPosition.get(tv);
                    highPosition += 0.1f;
                    hashMapTextViewPosition.put(tv, highPosition);

                    if (highPosition <= 1) {
                        set.setVerticalBias(tv.getId(), highPosition);
                    } else {
                        resetTextView(tv);
                        MainActivity mainActivity = mainActivityWeakReference.get();
                        mainActivity.decreaseLife();
                        if (mainActivity.isDead()) {
                            Toast toast = Toast.makeText(mainActivity, "GAME OVER", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            mainActivity.setHighScore();
                            isPlay = false;
                        }
                    }
                }
                set.applyTo(layout);
            }

            if (!queueSpare.isEmpty()) {
                for (TextView tv : queueSpare) {
                    hashSetQuiz.remove(tv);
                }
            }

            if (isPlay) {
                sendEmptyMessageDelayed(0, 1000);
            }
        }
    }

    private void decreaseLife() {
        String result = String.valueOf(--life);
        textViewLife.setText(result);
    }

    private boolean isDead() {
        return life <= 0;
    }

    private void setHighScore() {
        highScore = score;
        textViewHighScore.setText(String.valueOf(highScore));
    }

    // Activity가 실행 될때 최초로 호출 되는 메소드(main 메소드와 비슷함)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯 가지고 오기
        layout = findViewById(R.id.mainLayout);
        set.clone(layout);

        queueSpare.offer((TextView) findViewById(R.id.textView));
        queueSpare.offer((TextView) findViewById(R.id.textView2));
        queueSpare.offer((TextView) findViewById(R.id.textView3));
        queueSpare.offer((TextView) findViewById(R.id.textView4));

        EditText editText = findViewById(R.id.editText);
        final Button buttonStart = findViewById(R.id.button1);
        final TextView textViewScore = findViewById(R.id.textViewScore);
        textViewHighScore = findViewById(R.id.textViewHighScore);
        textViewLife = findViewById(R.id.textViewLife);

        //위젯에 값 및 이벤트 설정하기
        textViewScore.setText(String.valueOf(score));
        textViewHighScore.setText(String.valueOf(highScore));
        textViewLife.setText(String.valueOf(life));

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                score = 0;
                life = 3;

                textViewScore.setText(String.valueOf(score));
                textViewLife.setText(String.valueOf(life));

                handler.removeMessages(0);
                handler.initialize();
                handler.sendEmptyMessage(0);
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                boolean result = true;
                switch (actionId) {
                    default:
                        result = false;
                    case EditorInfo.IME_ACTION_DONE:
                        String inputText = v.getText().toString();
                        v.setText("");
                        //Log.d(TAG, String.format("inputText = %s", inputText));

                        for (TextView tv : hashSetQuiz) {
                            if (inputText.equals(tv.getText())) {
                                textViewScore.setText(String.valueOf(++score));

                                handler.resetTextView(tv);
                                break;
                            }
                        }
                        break;
                }
                return result;
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
