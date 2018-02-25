package com.donghee.android.part2_3_1;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2018-02-25.
 */

public class ButtonOnClickListener implements View.OnClickListener {
    private TextView textView;
    private Button button;

    public ButtonOnClickListener(TextView textView, Button button){
        this.textView = textView;
        this.button = button;
    }

    @Override
    public void onClick(View v) {
        textView.setText(button.getText() + "이 클릭 되었습니다.");
    }
}
