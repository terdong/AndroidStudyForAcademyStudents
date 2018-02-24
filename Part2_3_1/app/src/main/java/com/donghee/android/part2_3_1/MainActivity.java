package com.donghee.android.part2_3_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
                String checkBoxText = checkBox1.getText().toString();
                if(isChecked){
                    textView.setText(String.format("%s가 체크 되었습니다.", checkBoxText));
                }else{
                    textView.setText(String.format("%s가 체크 해제 되었습니다.", checkBoxText));
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String checkBoxText = checkBox2.getText().toString();
                if(isChecked){
                    textView.setText(String.format("%s가 체크 되었습니다.", checkBoxText));
                }else{
                    textView.setText(String.format("%s가 체크 해제 되었습니다.", checkBoxText));
                }
            }
        });

        //TODO: 라디오 버튼 이벤트 생성
        //TODO: 이름없는 객체 생성
        //TODO: 중복된 코드를 위한 코드 리팩토링
        //TODO: 추가된 코드만 다운 받는 방법

    }
}
