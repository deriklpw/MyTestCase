package com.derik.myapps.graphics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.derik.myapps.R;
import com.derik.myapps.component.CommonButton;

public class ComBtnActivity extends AppCompatActivity {

    private CommonButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_btn);
        btn = (CommonButton) findViewById(R.id.comm_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComBtnActivity.this, "clicked", Toast.LENGTH_LONG).show();
            }
        });
    }
}
