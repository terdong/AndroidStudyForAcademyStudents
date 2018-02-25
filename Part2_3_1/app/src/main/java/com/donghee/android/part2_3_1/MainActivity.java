package com.donghee.android.part2_3_1;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Part2_3_1";

    private void setTextViewByCheckBox(CheckBox checkBox, Boolean isChecked, TextView textView){
        String checkBoxText = checkBox.getText().toString();
        if(isChecked){
            textView.setText(String.format("%s가 체크 되었습니다.", checkBoxText));
        }else{
            textView.setText(String.format("%s가 체크 해제 되었습니다.", checkBoxText));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //TextView textView = findViewById(R.id.textView_00);
        //textView.setAutoLinkMask(Linkify.WEB_URLS);
        //textView.setText("https://www.google.com");

        final TextView textView = findViewById(R.id.textView2);

        final Button button = findViewById(R.id.button2);

        final CheckBox checkBox1 = findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = findViewById(R.id.checkBox2);

        RadioGroup radioGroup = findViewById(R.id.radioGroup0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(button.getText() + "이 클릭 되었습니다.");
            }
        });

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setTextViewByCheckBox(checkBox1, isChecked, textView);
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setTextViewByCheckBox(checkBox2, isChecked, textView);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG, String.format("checkedId = %d", checkedId));
                RadioButton radioButton= findViewById(checkedId);
                textView.setText(String.format("%s 이 클릭되었습니다.", radioButton.getText()));
            }
        });

        //TODO: 라디오 버튼 이벤트 생성
        //TODO: 이름없는 클래스 생성(익명 클래스)
        //TODO: 중복된 코드를 위한 코드 리팩토링
        //TODO: 추가된 코드만 다운 받는 방법

        //TODO: ImageView 에서 이미지 이름 가지고 오기

    }
}
