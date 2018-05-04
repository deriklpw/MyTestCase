package com.derik.demo.c_third.regular;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.derik.demo.R;
import com.derik.demo.tools.MsgToast;

public class RegularTestActivity extends Activity {

    private EditText input;
    private TextView regularExpression;
    private TextView matchResult;
    private Button match;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_test);

        input = (EditText) findViewById(R.id.regular_test_input_string);
        regularExpression = (TextView) findViewById(R.id.regular_test_expression);
        matchResult = (TextView) findViewById(R.id.regular_test_result);
        match = (Button) findViewById(R.id.regular_click);
        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputStr = input.getText().toString();
                String regular = regularExpression.getText().toString();
                if (inputStr != null && regular != null) {

                    if (!regular.equals("")) {
                        matchResult.setText("" + inputStr.matches(regular));
                    } else {
                        MsgToast.show(RegularTestActivity.this, "Regular expression can not be none.");
                    }

                }
            }
        });
    }
}
