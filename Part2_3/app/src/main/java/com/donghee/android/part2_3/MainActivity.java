package com.donghee.android.part2_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main_activity);

        final Button button = (Button)findViewById(R.id.button6);

        TextView tv = (TextView)findViewById(R.id.textView5);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = null;

                int buttonState = button.getVisibility();
                if(buttonState == View.VISIBLE){
                    //button.setVisibility(View.GONE);
                    button.setVisibility(View.INVISIBLE);
                    message = "버튼이 사라졌습니다.";
                }else{
                    button.setVisibility(View.VISIBLE);
                    message = "버튼이 복구되었습니다.";
                }

                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });

 /*       TextView tv = (TextView)findViewById(R.id.tvTitle);
        tv.setText("타이틀");*/

/*        LinearLayout linear = new LinearLayout(this);

        Button bt = new Button(this);
        bt.setText("Button 1");
        linear.addView(bt);

        Button bt2 = new Button(this);
        bt2.setText("Button 2");
        linear.addView(bt2);

        setContentView(linear);*/
    }
}
