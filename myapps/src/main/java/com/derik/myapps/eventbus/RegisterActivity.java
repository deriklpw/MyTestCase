package com.derik.myapps.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.derik.myapps.R;

import org.greenrobot.eventbus.EventBus;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void onClick(View v){
        EventBus.getDefault().post(new MessageEvent("derik"));
//        EventBus.getDefault().postSticky(new MessageEvent("Linoel"));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("RegisterActivity.onDestroy");
    }
}
